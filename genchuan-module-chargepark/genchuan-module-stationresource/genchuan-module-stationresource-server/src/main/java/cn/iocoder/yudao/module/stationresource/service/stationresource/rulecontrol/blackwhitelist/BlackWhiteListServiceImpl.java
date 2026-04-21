package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.blackwhitelist;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.BlackWhiteListPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.blackwhitelist.vo.ops.BlackWhiteListUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.blackwhitelist.BlackWhiteListDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.blackwhitelist.BlackWhiteListMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 黑白名单 Service 实现类
 */
@Service
@Validated
public class BlackWhiteListServiceImpl implements BlackWhiteListService {

    @Resource
    private BlackWhiteListMapper blackWhiteListMapper;

    /**
     * 获取图表统计数据
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public BlackWhiteListChartRespVO getChartData() {
        BlackWhiteListChartRespVO resp = new BlackWhiteListChartRespVO();
        // 卡片数据
        BlackWhiteListChartRespVO.CardDataVO cardData = blackWhiteListMapper.selectCardData();
        if (cardData == null) {
            cardData = new BlackWhiteListChartRespVO.CardDataVO();
            cardData.setEnableListCount(0);
            cardData.setTotalInterceptCount(0);
        }
        resp.setCardData(cardData);

        // 饼图数据
        List<BlackWhiteListChartRespVO.TypePieVO> pieList = blackWhiteListMapper.selectTypePieList();
        resp.setTypePieList(pieList);
        return resp;
    }

    /**
     * 批量生效名单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableList(List<Long> ids) {
        blackWhiteListMapper.update(new LambdaUpdateWrapper<BlackWhiteListDO>()
                .in(BlackWhiteListDO::getId, ids)
                .set(BlackWhiteListDO::getStatus, "已生效")
                .set(BlackWhiteListDO::getAuditTime, LocalDateTime.now())
                .set(BlackWhiteListDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    /**
     * 批量禁用名单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableList(List<Long> ids) {
        blackWhiteListMapper.update(new LambdaUpdateWrapper<BlackWhiteListDO>()
                .in(BlackWhiteListDO::getId, ids)
                .set(BlackWhiteListDO::getStatus, "已禁用")
                .set(BlackWhiteListDO::getAuditTime, LocalDateTime.now())
                .set(BlackWhiteListDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    /**
     * 创建黑白名单（校验车牌唯一）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createList(BlackWhiteListCreateReqVO createReqVO) {
        // 校验车牌唯一性
        BlackWhiteListDO exist = blackWhiteListMapper.selectOne(new LambdaQueryWrapper<BlackWhiteListDO>()
                .eq(BlackWhiteListDO::getPlateNo, createReqVO.getPlateNo())
                .eq(BlackWhiteListDO::getDeleted, false));
        if (exist != null) {
            throw exception("车牌号码已存在，不可重复添加");
        }

        BlackWhiteListDO entity = BeanUtils.toBean(createReqVO, BlackWhiteListDO.class);
        entity.setStatus("待生效");
        entity.setInterceptCount(0);
        blackWhiteListMapper.insert(entity);
    }

    /**
     * 更新黑白名单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateList(BlackWhiteListUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 校验车牌唯一性
        BlackWhiteListDO exist = blackWhiteListMapper.selectOne(new LambdaQueryWrapper<BlackWhiteListDO>()
                .eq(BlackWhiteListDO::getPlateNo, updateReqVO.getPlateNo())
                .ne(BlackWhiteListDO::getId, updateReqVO.getId())
                .eq(BlackWhiteListDO::getDeleted, false));
        if (exist != null) {
            throw exception("车牌号码已被其他记录使用");
        }

        BlackWhiteListDO updateBean = BeanUtils.toBean(updateReqVO, BlackWhiteListDO.class);
        blackWhiteListMapper.updateById(updateBean);
    }

    /**
     * 导入黑白名单
     */
    @Override
    public BlackWhiteListImportResp importList(MultipartFile file, boolean updateSupport) throws Exception {
        BlackWhiteListImportResp resp = new BlackWhiteListImportResp();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            Map<String, Object> map = VrvExcelUtils.importExcelAndReturnEntity(file, BlackWhiteListCreateReqVO.class.getName());
            List<BlackWhiteListCreateReqVO> reqList = (List<BlackWhiteListCreateReqVO>) map.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int success = 0;
            List<BlackWhiteListImportResp.ImportFailure> failures = new ArrayList<>();

            for (int i = 0; i < reqList.size(); i++) {
                BlackWhiteListCreateReqVO req = reqList.get(i);
                int row = i + 2;
                try {
                    if (updateSupport) {
                        importUpdateOrCreate(req);
                    } else {
                        createList(req);
                    }
                    success++;
                } catch (Exception e) {
                    BlackWhiteListImportResp.ImportFailure failure = new BlackWhiteListImportResp.ImportFailure();
                    failure.setRowIndex(row);
                    failure.setMessage(e.getMessage());
                    failures.add(failure);
                }
            }

            resp.setSuccessCount(success);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;
        } catch (Exception e) {
            BlackWhiteListImportResp.ImportFailure failure = new BlackWhiteListImportResp.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    /**
     * 导入：存在则更新，不存在则新增
     */
    private void importUpdateOrCreate(BlackWhiteListCreateReqVO req) {
        BlackWhiteListDO exist = blackWhiteListMapper.selectOne(new LambdaQueryWrapper<BlackWhiteListDO>()
                .eq(BlackWhiteListDO::getPlateNo, req.getPlateNo())
                .eq(BlackWhiteListDO::getDeleted, false));

        if (exist == null) {
            createList(req);
        } else {
            BlackWhiteListUpdateReqVO update = BeanUtils.toBean(req, BlackWhiteListUpdateReqVO.class);
            update.setId(exist.getId());
            updateList(update);
        }
    }

    /**
     * 校验记录是否存在
     */
    private BlackWhiteListDO validateExists(Long id) {
        BlackWhiteListDO info = blackWhiteListMapper.selectById(id);
        if (info == null) {
            throw exception("黑白名单记录不存在");
        }
        return info;
    }

    @Override
    public BlackWhiteListDO getListInfo(Long id) {
        return validateExists(id);
    }

    @Override
    public PageResult<BlackWhiteListDO> getListPage(BlackWhiteListPageReqVO pageReqVO) {
        return blackWhiteListMapper.selectPage(pageReqVO);
    }
}
