package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundRecordDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.AllOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

/**
 * 退款申请 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class RefundApplyServiceImpl implements RefundApplyService {

    @Resource private RefundApplyMapper refundApplyMapper;
    @Resource private RefundRecordMapper refundRecordMapper;
    @Resource private AllOrderMapper allOrderMapper;

    @Override public Long createRefundApply(RefundApplySaveReqVO v) {
        RefundApplyDO o = BeanUtils.toBean(v, RefundApplyDO.class);
        if (o.getApplyTime() == null) o.setApplyTime(LocalDateTime.now());
        refundApplyMapper.insert(o); return o.getId();
    }
    @Override public void updateRefundApply(RefundApplySaveReqVO v) {
        validateExists(v.getId()); refundApplyMapper.updateById(BeanUtils.toBean(v, RefundApplyDO.class));
    }
    @Override public void deleteRefundApply(Long id) { validateExists(id); refundApplyMapper.deleteById(id); }
    @Override public void deleteRefundApplyListByIds(List<Long> ids) { refundApplyMapper.deleteByIds(ids); }
    @Override public RefundApplyDO getRefundApply(Long id) { return refundApplyMapper.selectById(id); }
    @Override public PageResult<RefundApplyDO> getRefundApplyPage(RefundApplyPageReqVO v) { return refundApplyMapper.selectPage(v); }
        @Override
    public RefundApplyChartRespVO getRefundApplyChart(RefundApplyChartReqVO v) {
        RefundApplyChartRespVO resp = new RefundApplyChartRespVO();
        LocalDateTime start = v.getApplyTimeStart() != null ? v.getApplyTimeStart() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getApplyTimeEnd()   != null ? v.getApplyTimeEnd()   : LocalDateTime.now();
        resp.setTrendData(refundApplyMapper.selectTrend(start, end));
        resp.setTypeData(refundApplyMapper.selectGroupByStatus(start, end));
        RefundApplyChartRespVO.CardData card = new RefundApplyChartRespVO.CardData();
        card.setWaitAuditCount(refundApplyMapper.selectCountByStatus("pending_audit", start, end).intValue());
        Long all      = refundApplyMapper.selectCountByStatus(null, start, end);
        Long approved = refundApplyMapper.selectCountByStatus("completed", start, end);
        if (all != null && all > 0) {
            card.setAuditPassRate(new BigDecimal(approved).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(all), 1, java.math.RoundingMode.HALF_UP));
        } else { card.setAuditPassRate(BigDecimal.ZERO); }
        resp.setCardData(card);
        return resp;
    }


    /** 审核通过（PUT /approve）：待审核 → 待执行 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveRefundApply(IdReqVO reqVO) {
        RefundApplyDO apply = refundApplyMapper.selectById(reqVO.getId());
        if (apply == null) throw exception(REFUND_APPLY_NOT_EXISTS);
        if (!"pending_audit".equals(apply.getStatus())) throw exception(REFUND_APPLY_STATUS_CANNOT_APPROVE);
        RefundApplyDO update = new RefundApplyDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_exec");
        update.setAuditUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        refundApplyMapper.updateById(update);
    }

    /** 审核驳回（PUT /reject）：待审核 → 已驳回 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectRefundApply(IdReqVO reqVO) {
        RefundApplyDO apply = refundApplyMapper.selectById(reqVO.getId());
        if (apply == null) throw exception(REFUND_APPLY_NOT_EXISTS);
        if (!"pending_audit".equals(apply.getStatus())) throw exception(REFUND_APPLY_STATUS_CANNOT_APPROVE);
        RefundApplyDO update = new RefundApplyDO();
        update.setId(reqVO.getId());
        update.setStatus("rejected");
        update.setAuditUserId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setRefundReason("驳回原因：" + (reqVO.getRemark() != null ? reqVO.getRemark() : "不符合退款条件"));
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        refundApplyMapper.updateById(update);
    }

    /** 执行退款（PUT /execute）：待执行 → 已完成，创建退款记录 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executeRefundApply(IdReqVO reqVO) {
        RefundApplyDO apply = refundApplyMapper.selectById(reqVO.getId());
        if (apply == null) throw exception(REFUND_APPLY_NOT_EXISTS);
        if (!"pending_exec".equals(apply.getStatus())) throw exception(REFUND_APPLY_STATUS_CANNOT_EXEC);
        // 1. 更新申请状态为已完成
        RefundApplyDO updateApply = new RefundApplyDO();
        updateApply.setId(reqVO.getId());
        updateApply.setStatus("completed");
        updateApply.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        refundApplyMapper.updateById(updateApply);
        // 2. 创建退款记录
        RefundRecordDO record = new RefundRecordDO();
        record.setRecordNo("RR-" + System.currentTimeMillis());
        record.setApplyId(reqVO.getId());
        record.setOrderId(apply.getOrderId());
        record.setRefundAmount(apply.getRefundAmount());
        record.setRefundTime(LocalDateTime.now());
        record.setStatus("normal");
        refundRecordMapper.insert(record);
        // 3. 更新关联订单状态为已退款
        if (apply.getOrderId() != null) {
            AllOrderDO updateOrder = new AllOrderDO();
            updateOrder.setId(apply.getOrderId());
            updateOrder.setStatus("refunded");
            allOrderMapper.updateById(updateOrder);
        }
    }

    /** 重新申请（PUT /reapply）：已驳回 → 待审核 */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reapplyRefundApply(IdReqVO reqVO) {
        RefundApplyDO apply = refundApplyMapper.selectById(reqVO.getId());
        if (apply == null) throw exception(REFUND_APPLY_NOT_EXISTS);
        if (!"rejected".equals(apply.getStatus())) throw exception(REFUND_APPLY_STATUS_CANNOT_APPROVE);
        RefundApplyDO update = new RefundApplyDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_audit");
        update.setApplyTime(LocalDateTime.now());
        update.setAuditTime(null);
        update.setAuditUserId(null);
        if (reqVO.getRemark() != null) update.setRefundReason(reqVO.getRemark());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        refundApplyMapper.updateById(update);
    }

    /** 批量审核（POST /batch-audit） */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAuditRefundApply(IdsReqVO reqVO) {
        if (reqVO.getIds() == null || reqVO.getIds().isEmpty()) return;
        Long operatorId = SecurityFrameworkUtils.getLoginUserId();
        for (Long id : reqVO.getIds()) {
            RefundApplyDO apply = refundApplyMapper.selectById(id);
            if (apply == null || !"pending_audit".equals(apply.getStatus())) continue;
            RefundApplyDO update = new RefundApplyDO();
            update.setId(id);
            update.setStatus("pending_exec");
            update.setAuditUserId(operatorId);
            update.setAuditTime(LocalDateTime.now());
            update.setOperatorId(operatorId);
            if (reqVO.getRemark() != null) update.setRefundReason(reqVO.getRemark());
            refundApplyMapper.updateById(update);
        }
    }

    private void validateExists(Long id) {
        if (refundApplyMapper.selectById(id) == null) throw exception(REFUND_APPLY_NOT_EXISTS);
    }
}
