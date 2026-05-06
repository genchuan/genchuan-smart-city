package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceListMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.AllOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import cn.iocoder.yudao.module.ordertrade.framework.tool.OrderUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

/**
 * 全部订单 Service 实现类
 * @author genchuan
 */
@Service
@Validated
public class AllOrderServiceImpl implements AllOrderService {

    @Resource private AllOrderMapper allOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;
    @Resource private InvoiceListMapper invoiceListMapper;

    @Override
    public Long createAllOrder(AllOrderSaveReqVO createReqVO) {
        AllOrderDO obj = BeanUtils.toBean(createReqVO, AllOrderDO.class);
        allOrderMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateAllOrder(AllOrderSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        allOrderMapper.updateById(BeanUtils.toBean(updateReqVO, AllOrderDO.class));
    }

    @Override
    public void deleteAllOrder(Long id) { validateExists(id); allOrderMapper.deleteById(id); }

    @Override
    public void deleteAllOrderListByIds(List<Long> ids) { allOrderMapper.deleteByIds(ids); }

    @Override
    public AllOrderDO getAllOrder(Long id) { return allOrderMapper.selectByIdJoinStation(id); }

    @Override
    public PageResult<AllOrderDO> getAllOrderPage(AllOrderPageReqVO pageReqVO) {
        Page<AllOrderDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = allOrderMapper.selectPageJoinStation(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

        @Override
    public AllOrderChartRespVO getAllOrderChart(AllOrderChartReqVO v) {
        AllOrderChartRespVO resp = new AllOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(allOrderMapper.selectTrend(start, end));
        resp.setTypeData(allOrderMapper.selectGroupByStatus());
        Long total = allOrderMapper.selectTodayCount(todayStart, now);
        Long paid  = allOrderMapper.selectTodayPaidCount(todayStart, now);
        AllOrderChartRespVO.CardData card = new AllOrderChartRespVO.CardData();
        card.setTodayOrderCount(total != null ? total.intValue() : 0);
        card.setTodayRevenue(allOrderMapper.selectTodayRevenue(todayStart, now));
        card.setPayRate(total != null && total > 0
                ? new BigDecimal(paid).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(total), 1, RoundingMode.HALF_UP)
                : BigDecimal.ZERO);
        resp.setCardData(card);
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payAllOrder(IdReqVO reqVO) {
        AllOrderDO order = allOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ALL_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        AllOrderDO update = new AllOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setPayTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        allOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelAllOrder(IdReqVO reqVO) {
        AllOrderDO order = allOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ALL_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        AllOrderDO update = new AllOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        allOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundAllOrder(IdReqVO reqVO) {
        AllOrderDO order = allOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ALL_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 1. 更新订单状态为退款中
        AllOrderDO update = new AllOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("refunding");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        allOrderMapper.updateById(update);
        // 2. 创建退款申请（触发退款流程）
        RefundApplyDO apply = new RefundApplyDO();
        apply.setOrderId(reqVO.getId());
        apply.setApplyNo(OrderUtils.generateRefundNo());
        apply.setApplicantId(SecurityFrameworkUtils.getLoginUserId());
        apply.setRefundAmount(order.getAmount());      // 退款金额 = 订单金额
        apply.setRefundReason(reqVO.getRemark() != null ? reqVO.getRemark() : "申请退款");
        apply.setApplyTime(LocalDateTime.now());
        apply.setStatus("pending_audit");
        refundApplyMapper.insert(apply);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceAllOrder(InvoiceOrderReqVO reqVO) {
        AllOrderDO order = allOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(ALL_ORDER_NOT_EXISTS);
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
        if (allOrderMapper.selectById(id) == null) throw exception(ALL_ORDER_NOT_EXISTS);
    }
}
