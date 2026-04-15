package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.BikeChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.BikeChargeOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

/**
 * 两轮充电订单 Service 实现类
 * 新架构：独立订单，无主子关联
 * @author genchuan
 */
@Service
@Validated
public class BikeChargeOrderServiceImpl implements BikeChargeOrderService {

    @Resource private BikeChargeOrderMapper bikeChargeOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;

    @Override public Long createBikeChargeOrder(BikeChargeOrderSaveReqVO v) {
        BikeChargeOrderDO o = BeanUtils.toBean(v, BikeChargeOrderDO.class);
        bikeChargeOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateBikeChargeOrder(BikeChargeOrderSaveReqVO v) {
        validateExists(v.getId()); bikeChargeOrderMapper.updateById(BeanUtils.toBean(v, BikeChargeOrderDO.class));
    }
    @Override public void deleteBikeChargeOrder(Long id) { validateExists(id); bikeChargeOrderMapper.deleteById(id); }
    @Override public void deleteBikeChargeOrderListByIds(List<Long> ids) { bikeChargeOrderMapper.deleteByIds(ids); }
    @Override public BikeChargeOrderDO getBikeChargeOrder(Long id) { return bikeChargeOrderMapper.selectById(id); }
    @Override public PageResult<BikeChargeOrderDO> getBikeChargeOrderPage(BikeChargeOrderPageReqVO v) { return bikeChargeOrderMapper.selectPage(v); }
        @Override
    public BikeChargeOrderChartRespVO getBikeChargeOrderChart(BikeChargeOrderChartReqVO v) {
        BikeChargeOrderChartRespVO resp = new BikeChargeOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(bikeChargeOrderMapper.selectTrend(start, end));
        resp.setStationData(bikeChargeOrderMapper.selectGroupByStatus());
        resp.setTodayOrderCount(bikeChargeOrderMapper.selectTodayCount(todayStart, now).intValue());
        resp.setTodayChargeQuantity(bikeChargeOrderMapper.selectTodayChargeQuantity(todayStart, now));
        resp.setTodayRevenue(bikeChargeOrderMapper.selectTodayRevenue(todayStart, now));
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopBikeChargeOrder(IdReqVO reqVO) {
        BikeChargeOrderDO order = bikeChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
        if (!"charging".equals(order.getStatus())) throw exception(BIKE_CHARGE_ORDER_NOT_CHARGING);
        // 停止充电：更新状态为待支付，记录结束时间
        BikeChargeOrderDO update = new BikeChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_pay");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        bikeChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payBikeChargeOrder(IdReqVO reqVO) {
        BikeChargeOrderDO order = bikeChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        BikeChargeOrderDO update = new BikeChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        bikeChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelBikeChargeOrder(IdReqVO reqVO) {
        BikeChargeOrderDO order = bikeChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        BikeChargeOrderDO update = new BikeChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        bikeChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundBikeChargeOrder(IdReqVO reqVO) {
        BikeChargeOrderDO order = bikeChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 更新订单状态
        BikeChargeOrderDO update = new BikeChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("refunding");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        bikeChargeOrderMapper.updateById(update);
        // 创建退款申请（触发退款流程）
        RefundApplyDO apply = new RefundApplyDO();
        apply.setApplicantId(SecurityFrameworkUtils.getLoginUserId());
        apply.setRefundAmount(order.getAmount());
        apply.setRefundReason(reqVO.getRemark() != null ? reqVO.getRemark() : "申请退款");
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus("pending_audit");
        refundApplyMapper.insert(apply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceBikeChargeOrder(IdReqVO reqVO) {
        BikeChargeOrderDO order = bikeChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus()) && !"completed".equals(order.getStatus())) {
            throw exception(ALL_ORDER_STATUS_CANNOT_INVOICE);
        }
        // 开票由发票模块处理
    }

    private void validateExists(Long id) {
        if (bikeChargeOrderMapper.selectById(id) == null) throw exception(BIKE_CHARGE_ORDER_NOT_EXISTS);
    }
}
