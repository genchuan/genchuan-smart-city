package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.chargeparklink;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ChargeParkLinkSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.chargeparklink.vo.ops.ChargeParkLinkUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.chargeparklink.ChargeParkLinkDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.chargeparklink.ChargeParkLinkMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.CHARGE_PARK_LINK_NOT_EXISTS;

/**
 * 充停联动 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ChargeParkLinkServiceImpl implements ChargeParkLinkService {

    @Resource
    private ChargeParkLinkMapper chargeParkLinkMapper;


    // ==================== 图表统计 ====================
    @Override
    @Transactional(readOnly = true)
    public ChargeParkLinkChartRespVO getChargeParkLinkChart() {
        ChargeParkLinkChartRespVO resp = new ChargeParkLinkChartRespVO();

        // 卡片数据
        ChargeParkLinkChartRespVO.CardDataVO cardData = chargeParkLinkMapper.selectCardData();
        if (cardData == null) {
            cardData = new ChargeParkLinkChartRespVO.CardDataVO();
            cardData.setTodayOrderCount(0);
            cardData.setTodayIncome(BigDecimal.ZERO);
            cardData.setPayRate(BigDecimal.ZERO);
        }
        resp.setCardData(cardData);

        // 折线图
        List<ChargeParkLinkChartRespVO.DiscountLineVO> lineList = chargeParkLinkMapper.selectDiscountLineList();
        resp.setDiscountLineList(lineList);

        // 柱状图
        List<ChargeParkLinkChartRespVO.OrderBarVO> barList = chargeParkLinkMapper.selectOrderBarList();
        resp.setOrderBarList(barList);

        return resp;
    }

    // ==================== 生效 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableChargeParkLink(List<Long> ids) {
        chargeParkLinkMapper.update(new LambdaUpdateWrapper<ChargeParkLinkDO>()
                .in(ChargeParkLinkDO::getId, ids)
                .set(ChargeParkLinkDO::getStatus, "已生效")
                .set(ChargeParkLinkDO::getAuditTime, LocalDateTime.now())
                .set(ChargeParkLinkDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    // ==================== 禁用 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableChargeParkLink(List<Long> ids) {
        chargeParkLinkMapper.update(new LambdaUpdateWrapper<ChargeParkLinkDO>()
                .in(ChargeParkLinkDO::getId, ids)
                .set(ChargeParkLinkDO::getStatus, "已禁用")
                .set(ChargeParkLinkDO::getAuditTime, LocalDateTime.now())
                .set(ChargeParkLinkDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    // ==================== 新增 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createChargeParkLink(ChargeParkLinkCreateReqVO reqVO) {
        // 唯一性校验：同场站 + 同优惠类型 + 同车型 唯一
        validateUnique(reqVO.getStationId(), reqVO.getDiscountType(), reqVO.getCarType(), null);

        ChargeParkLinkDO link = BeanUtils.toBean(reqVO, ChargeParkLinkDO.class);
        link.setStatus("待生效"); // 默认待生效
        link.setTodayOrderCount(0);
        link.setTodayIncome(BigDecimal.ZERO);
        link.setPayRate(BigDecimal.ZERO);
        chargeParkLinkMapper.insert(link);
    }

    // ==================== 更新 ====================
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateChargeParkLink(ChargeParkLinkUpdateReqVO reqVO) {
        ChargeParkLinkDO exist = validateExists(reqVO.getId());
        validateUnique(reqVO.getStationId(), reqVO.getDiscountType(), reqVO.getCarType(), exist.getId());

        ChargeParkLinkDO updateBean = BeanUtils.toBean(reqVO, ChargeParkLinkDO.class);
        chargeParkLinkMapper.updateById(updateBean);
    }

    // ==================== 导入 ====================
    @Override
    public ChargeParkLinkImportResp importChargeParkLink(MultipartFile file, boolean updateSupport) {
        ChargeParkLinkImportResp resp = new ChargeParkLinkImportResp();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            Map<String, Object> map = VrvExcelUtils.importExcelAndReturnEntity(file, ChargeParkLinkCreateReqVO.class.getName());
            List<ChargeParkLinkCreateReqVO> list = (List<ChargeParkLinkCreateReqVO>) map.get("entityList");

            if (CollUtil.isEmpty(list)) {
                throw exception("导入数据不能为空");
            }

            int success = 0;
            List<ChargeParkLinkImportResp.ImportFailure> failures = new ArrayList<>();

            for (int i = 0; i < list.size(); i++) {
                ChargeParkLinkCreateReqVO req = list.get(i);
                int row = i + 2;
                try {
                    if (updateSupport) {
                        updateIfExists(req);
                    } else {
                        createChargeParkLink(req);
                    }
                    success++;
                } catch (Exception e) {
                    ChargeParkLinkImportResp.ImportFailure f = new ChargeParkLinkImportResp.ImportFailure();
                    f.setRowIndex(row);
                    f.setMessage(e.getMessage());
                    failures.add(f);
                }
            }

            resp.setSuccessCount(success);
            resp.setFailureCount(failures.size());
            resp.setFailureList(failures);
            return resp;
        } catch (Exception e) {
            ChargeParkLinkImportResp.ImportFailure f = new ChargeParkLinkImportResp.ImportFailure();
            f.setRowIndex(1);
            f.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(f);
            resp.setFailureCount(1);
            return resp;
        }
    }

    // ==================== 工具方法 ====================
    private ChargeParkLinkDO validateExists(Long id) {
        ChargeParkLinkDO link = chargeParkLinkMapper.selectById(id);
        if (link == null) {
            throw exception("充停联动规则不存在");
        }
        return link;
    }

    /**
     * 唯一性校验：同场站 + 优惠类型 + 车型 唯一
     */
    private void validateUnique(Long stationId, String discountType, String carType, Long excludeId) {
        List<ChargeParkLinkDO> list = chargeParkLinkMapper.selectList(new LambdaQueryWrapper<ChargeParkLinkDO>()
                .eq(ChargeParkLinkDO::getStationId, stationId)
                .eq(ChargeParkLinkDO::getDiscountType, discountType)
                .eq(ChargeParkLinkDO::getCarType, carType)
                .eq(ChargeParkLinkDO::getDeleted, false)
                .last("LIMIT 2")
        );

        if (list.size() > 1) {
            throw exception("数据异常：该场站+优惠类型+车型存在重复规则");
        }

        boolean duplicate = list.stream().anyMatch(item ->
                excludeId == null || !item.getId().equals(excludeId)
        );

        if (duplicate) {
            throw exception("该场站+优惠类型+车型已存在充停联动规则，不可重复");
        }
    }

    /**
     * 导入：存在则更新，不存在则新增
     */
    private void updateIfExists(ChargeParkLinkCreateReqVO req) {
        List<ChargeParkLinkDO> list = chargeParkLinkMapper.selectList(new LambdaQueryWrapper<ChargeParkLinkDO>()
                .eq(ChargeParkLinkDO::getStationId, req.getStationId())
                .eq(ChargeParkLinkDO::getDiscountType, req.getDiscountType())
                .eq(ChargeParkLinkDO::getCarType, req.getCarType())
                .eq(ChargeParkLinkDO::getDeleted, false)
        );

        if (list.size() > 1) {
            throw exception("数据重复，请清理后再导入");
        }

        if (list.isEmpty()) {
            createChargeParkLink(req);
        } else {
            ChargeParkLinkDO exist = list.get(0);
            ChargeParkLinkUpdateReqVO update = BeanUtils.toBean(req, ChargeParkLinkUpdateReqVO.class);
            update.setId(exist.getId());
            updateChargeParkLink(update);
        }
    }
    @Override
    public Long createChargeParkLink(ChargeParkLinkSaveReqVO createReqVO) {
        // 插入
        ChargeParkLinkDO chargeParkLink = BeanUtils.toBean(createReqVO, ChargeParkLinkDO.class);
        chargeParkLinkMapper.insert(chargeParkLink);

        // 返回
        return chargeParkLink.getId();
    }

    @Override
    public void updateChargeParkLink(ChargeParkLinkSaveReqVO updateReqVO) {
        // 校验存在
        validateChargeParkLinkExists(updateReqVO.getId());
        // 更新
        ChargeParkLinkDO updateObj = BeanUtils.toBean(updateReqVO, ChargeParkLinkDO.class);
        chargeParkLinkMapper.updateById(updateObj);
    }

    @Override
    public void deleteChargeParkLink(Long id) {
        // 校验存在
        validateChargeParkLinkExists(id);
        // 删除
        chargeParkLinkMapper.deleteById(id);
    }

    @Override
        public void deleteChargeParkLinkListByIds(List<Long> ids) {
        // 删除
        chargeParkLinkMapper.deleteByIds(ids);
        }


    private void validateChargeParkLinkExists(Long id) {
        if (chargeParkLinkMapper.selectById(id) == null) {
            throw exception(CHARGE_PARK_LINK_NOT_EXISTS);
        }
    }

    @Override
    public ChargeParkLinkDO getChargeParkLink(Long id) {
        return chargeParkLinkMapper.selectById(id);
    }

    @Override
    public PageResult<ChargeParkLinkDO> getChargeParkLinkPage(ChargeParkLinkPageReqVO pageReqVO) {
        return chargeParkLinkMapper.selectPage(pageReqVO);
    }

}
