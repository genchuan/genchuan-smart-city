package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.offtimerule;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.OfftimeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.OfftimeRuleRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.offtimerule.OfftimeRuleDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.rulecontrol.offtimerule.OfftimeRuleMapper;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * 错时规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OfftimeRuleServiceImpl implements OfftimeRuleService {

    @Resource
    private OfftimeRuleMapper offtimeRuleMapper;

    @Override
    @Transactional(readOnly = true)
    public OfftimeRuleChartRespVO getOfftimeRuleChart() {
        OfftimeRuleChartRespVO resp = new OfftimeRuleChartRespVO();

        // 卡片统计数据
        OfftimeRuleChartRespVO.CardDataVO cardData = offtimeRuleMapper.selectCardData();
        if (cardData == null) {
            cardData = new OfftimeRuleChartRespVO.CardDataVO();
            cardData.setEnableRuleCount(0);
            cardData.setTotalOffOrderCount(0);
        }
        resp.setCardData(cardData);

        // 订单趋势折线图
        List<OfftimeRuleChartRespVO.OrderLineVO> lineList = offtimeRuleMapper.selectOrderLineList();
        resp.setOrderLineList(lineList);

        return resp;
    }

    @Override
    public OfftimeRuleDO getOfftimeRule(Long id) {
        return offtimeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<OfftimeRuleRespVO> getOfftimeRulePage(OfftimeRulePageReqVO pageReqVO) {
        Page<OfftimeRuleRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        Page<OfftimeRuleRespVO> resultPage = offtimeRuleMapper.getPage(page, pageReqVO);
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableOfftimeRule(List<Long> ids) {
        offtimeRuleMapper.update(new LambdaUpdateWrapper<OfftimeRuleDO>()
                .in(OfftimeRuleDO::getId, ids)
                .set(OfftimeRuleDO::getStatus, "已生效")
                .set(OfftimeRuleDO::getAuditTime, LocalDateTime.now())
                .set(OfftimeRuleDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableOfftimeRule(List<Long> ids) {
        offtimeRuleMapper.update(new LambdaUpdateWrapper<OfftimeRuleDO>()
                .in(OfftimeRuleDO::getId, ids)
                .set(OfftimeRuleDO::getStatus, "已禁用")
                .set(OfftimeRuleDO::getAuditTime, LocalDateTime.now())
                .set(OfftimeRuleDO::getAuditUserId, SecurityFrameworkUtils.getLoginUserId())
        );
    }

    /**
     * 校验规则是否存在
     */
    private OfftimeRuleDO validateOfftimeRuleExists(Long id) {
        OfftimeRuleDO rule = offtimeRuleMapper.selectById(id);
        if (rule == null) {
            throw exception("错时规则不存在");
        }
        return rule;
    }

    /**
     * 校验唯一性：同场站+同时段只能存在一条规则
     */
    private void validateUnique(Long stationId, String offTime, Long excludeId) {
        List<OfftimeRuleDO> list = offtimeRuleMapper.selectList(new LambdaQueryWrapper<OfftimeRuleDO>()
                .eq(OfftimeRuleDO::getStationId, stationId)
                .eq(OfftimeRuleDO::getOffTime, offTime)
                .eq(OfftimeRuleDO::getDeleted, false)
                .last("LIMIT 2")
        );

        if (list.size() > 1) {
            throw exception("数据异常：同场站同时段存在多条错时规则，请清理数据");
        }

        boolean duplicate = list.stream().anyMatch(rule -> {
            if (excludeId == null) {
                return true;
            }
            return !rule.getId().equals(excludeId);
        });

        if (duplicate) {
            throw exception("该场站下已存在相同时段的错时规则");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOfftimeRule(OfftimeRuleCreateReqVO createReqVO) {
        // 唯一性校验
        validateUnique(createReqVO.getStationId(), createReqVO.getOffTime(), null);

        OfftimeRuleDO rule = BeanUtils.toBean(createReqVO, OfftimeRuleDO.class);
        rule.setStatus("待生效");
        rule.setOffOrderCount(0);
        offtimeRuleMapper.insert(rule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOfftimeRule(OfftimeRuleUpdateReqVO updateReqVO) {
        // 校验存在
        validateOfftimeRuleExists(updateReqVO.getId());
        // 校验唯一性
        validateUnique(updateReqVO.getStationId(), updateReqVO.getOffTime(), updateReqVO.getId());

        OfftimeRuleDO updateObj = BeanUtils.toBean(updateReqVO, OfftimeRuleDO.class);
        offtimeRuleMapper.updateById(updateObj);
    }

    @Override
    public OfftimeRuleImportResp importOfftimeRule(MultipartFile file, boolean updateSupport) {
        OfftimeRuleImportResp resp = new OfftimeRuleImportResp();
        resp.setSuccessCount(0);
        resp.setFailureCount(0);
        resp.setFailureList(new ArrayList<>());

        try {
            Map<String, Object> map = VrvExcelUtils.importExcelAndReturnEntity(file, OfftimeRuleCreateReqVO.class.getName());
            List<OfftimeRuleCreateReqVO> reqList = (List<OfftimeRuleCreateReqVO>) map.get("entityList");

            if (CollUtil.isEmpty(reqList)) {
                throw exception("导入数据不能为空");
            }

            int success = 0;
            List<OfftimeRuleImportResp.ImportFailure> failures = new ArrayList<>();

            for (int i = 0; i < reqList.size(); i++) {
                OfftimeRuleCreateReqVO req = reqList.get(i);
                int row = i + 2;
                try {
                    if (updateSupport) {
                        updateIfExists(req);
                    } else {
                        createOfftimeRule(req);
                    }
                    success++;
                } catch (Exception e) {
                    OfftimeRuleImportResp.ImportFailure failure = new OfftimeRuleImportResp.ImportFailure();
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
            OfftimeRuleImportResp.ImportFailure failure = new OfftimeRuleImportResp.ImportFailure();
            failure.setRowIndex(1);
            failure.setMessage("导入失败：" + e.getMessage());
            resp.getFailureList().add(failure);
            resp.setFailureCount(1);
            return resp;
        }
    }

    /**
     * 存在则更新，不存在则新增
     */
    private void updateIfExists(OfftimeRuleCreateReqVO req) {
        List<OfftimeRuleDO> list = offtimeRuleMapper.selectList(new LambdaQueryWrapper<OfftimeRuleDO>()
                .eq(OfftimeRuleDO::getStationId, req.getStationId())
                .eq(OfftimeRuleDO::getOffTime, req.getOffTime())
                .eq(OfftimeRuleDO::getDeleted, false)
                .last("LIMIT 1")
        );

        if (CollUtil.isEmpty(list)) {
            createOfftimeRule(req);
        } else {
            OfftimeRuleDO exist = list.get(0);
            OfftimeRuleUpdateReqVO update = BeanUtils.toBean(req, OfftimeRuleUpdateReqVO.class);
            update.setId(exist.getId());
            updateOfftimeRule(update);
        }
    }
}
