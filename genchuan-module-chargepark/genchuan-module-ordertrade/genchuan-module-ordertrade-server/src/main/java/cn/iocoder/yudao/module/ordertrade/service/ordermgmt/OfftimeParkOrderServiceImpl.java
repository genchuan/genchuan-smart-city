package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.OfftimeParkOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceListMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.OfftimeParkOrderMapper;
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
 * 错时停车订单 Service 实现类
 * 新架构：独立订单，无主子关联
 * @author genchuan
 */
@Service
@Validated
public class OfftimeParkOrderServiceImpl implements OfftimeParkOrderService {

    @Resource private OfftimeParkOrderMapper offtimeParkOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;
    @Resource private InvoiceListMapper invoiceListMapper;

    @Override public Long createOfftimeParkOrder(OfftimeParkOrderSaveReqVO v) {
        OfftimeParkOrderDO o = BeanUtils.toBean(v, OfftimeParkOrderDO.class);
        offtimeParkOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateOfftimeParkOrder(OfftimeParkOrderSaveReqVO v) {
        validateExists(v.getId()); offtimeParkOrderMapper.updateById(BeanUtils.toBean(v, OfftimeParkOrderDO.class));
    }
    @Override public void deleteOfftimeParkOrder(Long id) { validateExists(id); offtimeParkOrderMapper.deleteById(id); }
    @Override public void deleteOfftimeParkOrderListByIds(List<Long> ids) { offtimeParkOrderMapper.deleteByIds(ids); }
    @Override public OfftimeParkOrderDO getOfftimeParkOrder(Long id) { return offtimeParkOrderMapper.selectById(id); }
    @Override public PageResult<OfftimeParkOrderDO> getOfftimeParkOrderPage(OfftimeParkOrderPageReqVO v) { return offtimeParkOrderMapper.selectPage(v); }
        @Override
    public OfftimeParkOrderChartRespVO getOfftimeParkOrderChart(OfftimeParkOrderChartReqVO v) {
        OfftimeParkOrderChartRespVO resp = new OfftimeParkOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(offtimeParkOrderMapper.selectTrend(start, end));
        resp.setStationData(offtimeParkOrderMapper.selectGroupByStatus());
        Long total = offtimeParkOrderMapper.selectTodayCount(todayStart, now);
        Long paid  = offtimeParkOrderMapper.selectTodayPaidCount(todayStart, now);
        OfftimeParkOrderChartRespVO.CardData card = new OfftimeParkOrderChartRespVO.CardData();
        card.setTodayOrderCount(total != null ? total.intValue() : 0);
        card.setTodayRevenue(offtimeParkOrderMapper.selectTodayRevenue(todayStart, now));
        card.setPayRate(total != null && total > 0
                ? new BigDecimal(paid).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(total), 1, java.math.RoundingMode.HALF_UP)
                : BigDecimal.ZERO);
        resp.setCardData(card);
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payOfftimeParkOrder(IdReqVO reqVO) {
        OfftimeParkOrderDO order = offtimeParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(OFFTIME_PARK_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        OfftimeParkOrderDO update = new OfftimeParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        offtimeParkOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOfftimeParkOrder(IdReqVO reqVO) {
        OfftimeParkOrderDO order = offtimeParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(OFFTIME_PARK_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        OfftimeParkOrderDO update = new OfftimeParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        offtimeParkOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundOfftimeParkOrder(IdReqVO reqVO) {
        OfftimeParkOrderDO order = offtimeParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(OFFTIME_PARK_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 更新订单状态
        OfftimeParkOrderDO update = new OfftimeParkOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("refunding");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        offtimeParkOrderMapper.updateById(update);
        // 创建退款申请（触发退款流程）
        RefundApplyDO apply = new RefundApplyDO();
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
    public void invoiceOfftimeParkOrder(InvoiceOrderReqVO reqVO) {
        OfftimeParkOrderDO order = offtimeParkOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(OFFTIME_PARK_ORDER_NOT_EXISTS);
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
        invoice.setReserve1(reqVO.getInvoiceEmail());
        invoice.setAmount(order.getAmount());
        invoice.setStatus("pending_audit");
        invoice.setRemark(reqVO.getRemark());
        invoiceListMapper.insert(invoice);
    }

    private void validateExists(Long id) {
        if (offtimeParkOrderMapper.selectById(id) == null) throw exception(OFFTIME_PARK_ORDER_NOT_EXISTS);
    }
}
