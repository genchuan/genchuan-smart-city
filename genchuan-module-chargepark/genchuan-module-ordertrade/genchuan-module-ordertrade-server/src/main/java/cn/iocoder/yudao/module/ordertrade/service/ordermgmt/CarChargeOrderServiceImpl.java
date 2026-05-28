package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.CarChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceListMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.CarChargeOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import cn.iocoder.yudao.module.ordertrade.framework.tool.OrderUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

/**
 * 汽车充电订单 Service 实现类
 * 新架构：独立订单，无主子关联
 * @author genchuan
 */
@Service
@Validated
public class CarChargeOrderServiceImpl implements CarChargeOrderService {

    @Resource private CarChargeOrderMapper carChargeOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;
    @Resource private InvoiceListMapper invoiceListMapper;

    @Override public Long createCarChargeOrder(CarChargeOrderSaveReqVO v) {
        CarChargeOrderDO o = BeanUtils.toBean(v, CarChargeOrderDO.class);
        carChargeOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateCarChargeOrder(CarChargeOrderSaveReqVO v) {
        validateExists(v.getId()); carChargeOrderMapper.updateById(BeanUtils.toBean(v, CarChargeOrderDO.class));
    }
    @Override public void deleteCarChargeOrder(Long id) { validateExists(id); carChargeOrderMapper.deleteById(id); }
    @Override public void deleteCarChargeOrderListByIds(List<Long> ids) { carChargeOrderMapper.deleteByIds(ids); }
    @Override public CarChargeOrderDO getCarChargeOrder(Long id) { return carChargeOrderMapper.selectByIdJoinStation(id); }
    @Override public PageResult<CarChargeOrderDO> getCarChargeOrderPage(CarChargeOrderPageReqVO v) {
        Page<CarChargeOrderDO> page = new Page<>(v.getPageNo(), v.getPageSize());
        var result = carChargeOrderMapper.selectPageJoinStation(page, v);
        List<CarChargeOrderDO> records = result.getRecords();
        if (!records.isEmpty()) {
            List<Long> orderIds = records.stream().map(CarChargeOrderDO::getId).collect(Collectors.toList());
            Map<Long, String> invoiceStatusMap = invoiceListMapper.selectStatusMapByOrderIds(orderIds);
            records.forEach(o -> o.setInvoiceStatus(invoiceStatusMap.get(o.getId())));
        }
        return new PageResult<>(records, result.getTotal());
    }
        @Override
    public CarChargeOrderChartRespVO getCarChargeOrderChart(CarChargeOrderChartReqVO v) {
        CarChargeOrderChartRespVO resp = new CarChargeOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(carChargeOrderMapper.selectTrend(start, end));
        resp.setStationData(carChargeOrderMapper.selectGroupByStation(start, end));
        CarChargeOrderChartRespVO.CardData card = new CarChargeOrderChartRespVO.CardData();
        card.setTodayOrderCount(carChargeOrderMapper.selectTodayCount(todayStart, now).intValue());
        card.setTodayChargeQuantity(carChargeOrderMapper.selectTodayChargeQuantity(todayStart, now));
        card.setTodayRevenue(carChargeOrderMapper.selectTodayRevenue(todayStart, now));
        resp.setCardData(card);
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopCarChargeOrder(IdReqVO reqVO) {
        CarChargeOrderDO order = carChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
        if (!"charging".equals(order.getStatus())) throw exception(CAR_CHARGE_ORDER_NOT_CHARGING);
        // 停止充电：更新状态为待支付，记录结束时间
        CarChargeOrderDO update = new CarChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_pay");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        carChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payCarChargeOrder(IdReqVO reqVO) {
        CarChargeOrderDO order = carChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        CarChargeOrderDO update = new CarChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        carChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelCarChargeOrder(IdReqVO reqVO) {
        CarChargeOrderDO order = carChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        CarChargeOrderDO update = new CarChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        carChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundCarChargeOrder(IdReqVO reqVO) {
        CarChargeOrderDO order = carChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 更新订单状态
        //CarChargeOrderDO update = new CarChargeOrderDO();
        //update.setId(reqVO.getId());
        order.setStatus("refunding");
        order.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        order.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        carChargeOrderMapper.updateById(order);
        // 创建退款申请（触发退款流程）
        RefundApplyDO apply = new RefundApplyDO();
        apply.setApplicantId(SecurityFrameworkUtils.getLoginUserId());
        apply.setApplyNo(OrderUtils.generateRefundNo());
        apply.setOrderId(reqVO.getId());
        apply.setRefundAmount(order.getAmount());
        apply.setRefundReason(reqVO.getRemark() != null ? reqVO.getRemark() : "申请退款");
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus("pending_audit");
        apply.setCreator(SecurityFrameworkUtils.getLoginUserId()+"");
        apply.setUpdater(SecurityFrameworkUtils.getLoginUserId()+"");
        apply.setApplicantId(SecurityFrameworkUtils.getLoginUserId());
        refundApplyMapper.insert(apply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceCarChargeOrder(InvoiceOrderReqVO reqVO) {
        CarChargeOrderDO order = carChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus()) && !"completed".equals(order.getStatus())) {
            throw exception(ALL_ORDER_STATUS_CANNOT_INVOICE);
        }
        if (invoiceListMapper.selectByOrderId(reqVO.getId()) != null) {
            throw exception(INVOICE_LIST_ALREADY_APPLIED);
        }
        InvoiceListDO invoice = new InvoiceListDO();
        invoice.setInvoiceNo("INV" + java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 14).toUpperCase());
        invoice.setOrderId(reqVO.getId());
        invoice.setTitle(reqVO.getInvoiceTitle());
        invoice.setTaxNo(reqVO.getInvoiceTaxNo());
        invoice.setEmail(reqVO.getInvoiceEmail());
        invoice.setAmount(order.getAmount());
        invoice.setStatus("pending_audit");
        invoice.setRemark(reqVO.getRemark());
        invoiceListMapper.insert(invoice);

        order.setInvoiceStatus("invoiced");
        carChargeOrderMapper.updateById(order);
    }

    private void validateExists(Long id) {
        if (carChargeOrderMapper.selectById(id) == null) throw exception(CAR_CHARGE_ORDER_NOT_EXISTS);
    }
}
