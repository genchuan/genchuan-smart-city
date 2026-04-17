package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.TempParkOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.TempParkOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import cn.iocoder.yudao.module.ordertrade.framework.tool.OrderUtils;
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
 * 临时停车订单 Service 实现类
 * 新架构：独立订单，无主子关联
 * @author genchuan
 */
@Service
@Validated
public class TempParkOrderServiceImpl implements TempParkOrderService {

    @Resource private TempParkOrderMapper tempParkOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;

    @Override public Long createTempParkOrder(TempParkOrderSaveReqVO v) {
        TempParkOrderDO o = BeanUtils.toBean(v, TempParkOrderDO.class);
        tempParkOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateTempParkOrder(TempParkOrderSaveReqVO v) {
        validateExists(v.getId()); tempParkOrderMapper.updateById(BeanUtils.toBean(v, TempParkOrderDO.class));
    }
    @Override public void deleteTempParkOrder(Long id) { validateExists(id); tempParkOrderMapper.deleteById(id); }
    @Override public void deleteTempParkOrderListByIds(List<Long> ids) { tempParkOrderMapper.deleteByIds(ids); }
    @Override public TempParkOrderDO getTempParkOrder(Long id) { return tempParkOrderMapper.selectById(id); }
    @Override public PageResult<TempParkOrderDO> getTempParkOrderPage(TempParkOrderPageReqVO v) { return tempParkOrderMapper.selectPage(v); }
        @Override
    public TempParkOrderChartRespVO getTempParkOrderChart(TempParkOrderChartReqVO v) {
        TempParkOrderChartRespVO resp = new TempParkOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(tempParkOrderMapper.selectTrend(start, end));
        resp.setStationData(tempParkOrderMapper.selectGroupByStatus());
        resp.setTodayOrderCount(tempParkOrderMapper.selectTodayCount(todayStart, now).intValue());
        resp.setTodayRevenue(tempParkOrderMapper.selectTodayRevenue(todayStart, now));
        Long total = tempParkOrderMapper.selectTodayCount(todayStart, now);
        Long paid  = tempParkOrderMapper.selectTodayPaidCount(todayStart, now);
        if (total != null && total > 0) {
            resp.setPayRate(new BigDecimal(paid).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(total), 1, java.math.RoundingMode.HALF_UP));
        } else { resp.setPayRate(BigDecimal.ZERO); }
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payTempParkOrder(IdReqVO reqVO) {
        TempParkOrderDO order = tempParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(TEMP_PARK_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        TempParkOrderDO update = new TempParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        tempParkOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelTempParkOrder(IdReqVO reqVO) {
        TempParkOrderDO order = tempParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(TEMP_PARK_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        TempParkOrderDO update = new TempParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        tempParkOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundTempParkOrder(IdReqVO reqVO) {
        TempParkOrderDO order = tempParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(TEMP_PARK_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 更新订单状态
        TempParkOrderDO update = new TempParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("refunding");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        tempParkOrderMapper.updateById(update);
        // 创建退款申请（触发退款流程）
        RefundApplyDO apply = new RefundApplyDO();
        apply.setApplyNo(OrderUtils.generateRefundNo());
        apply.setApplicantId(SecurityFrameworkUtils.getLoginUserId());
        apply.setApplyNo(OrderUtils.generateRefundNo());
        apply.setOrderId(reqVO.getId());
        apply.setRefundAmount(order.getAmount());
        apply.setRefundReason(reqVO.getRemark() != null ? reqVO.getRemark() : "申请退款");
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus("pending_audit");
        refundApplyMapper.insert(apply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceTempParkOrder(IdReqVO reqVO) {
        TempParkOrderDO order = tempParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(TEMP_PARK_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus()) && !"completed".equals(order.getStatus())) {
            throw exception(ALL_ORDER_STATUS_CANNOT_INVOICE);
        }
        // 开票由发票模块处理
    }

    private void validateExists(Long id) {
        if (tempParkOrderMapper.selectById(id) == null) throw exception(TEMP_PARK_ORDER_NOT_EXISTS);
    }
}
