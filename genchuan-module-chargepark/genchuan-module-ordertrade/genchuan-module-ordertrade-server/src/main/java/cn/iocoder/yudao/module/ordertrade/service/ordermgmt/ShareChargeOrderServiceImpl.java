package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.ShareChargeOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceListMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt.ShareChargeOrderMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt.RefundApplyMapper;
import cn.iocoder.yudao.module.ordertrade.framework.tool.OrderUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

/**
 * 共享充电订单 Service 实现类
 * 新架构：独立订单，无主子关联
 * @author genchuan
 */
@Service
@Validated
public class ShareChargeOrderServiceImpl implements ShareChargeOrderService {

    @Resource private ShareChargeOrderMapper shareChargeOrderMapper;
    @Resource private RefundApplyMapper refundApplyMapper;
    @Resource private InvoiceListMapper invoiceListMapper;

    @Override public Long createShareChargeOrder(ShareChargeOrderSaveReqVO v) {
        ShareChargeOrderDO o = BeanUtils.toBean(v, ShareChargeOrderDO.class);
        shareChargeOrderMapper.insert(o); return o.getId();
    }
    @Override public void updateShareChargeOrder(ShareChargeOrderSaveReqVO v) {
        validateExists(v.getId()); shareChargeOrderMapper.updateById(BeanUtils.toBean(v, ShareChargeOrderDO.class));
    }
    @Override public void deleteShareChargeOrder(Long id) { validateExists(id); shareChargeOrderMapper.deleteById(id); }
    @Override public void deleteShareChargeOrderListByIds(List<Long> ids) { shareChargeOrderMapper.deleteByIds(ids); }
    @Override public ShareChargeOrderDO getShareChargeOrder(Long id) { return shareChargeOrderMapper.selectByIdJoinStation(id); }
    @Override public PageResult<ShareChargeOrderDO> getShareChargeOrderPage(ShareChargeOrderPageReqVO v) {
        Page<ShareChargeOrderDO> page = new Page<>(v.getPageNo(), v.getPageSize());
        var result = shareChargeOrderMapper.selectPageJoinStation(page, v);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }
        @Override
    public ShareChargeOrderChartRespVO getShareChargeOrderChart(ShareChargeOrderChartReqVO v) {
        ShareChargeOrderChartRespVO resp = new ShareChargeOrderChartRespVO();
        LocalDateTime start = v.getStartTime() != null ? v.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end   = v.getEndTime()   != null ? v.getEndTime()   : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();
        resp.setTrendData(shareChargeOrderMapper.selectTrend(start, end));
        resp.setStationData(shareChargeOrderMapper.selectGroupByStation(start, end));
        ShareChargeOrderChartRespVO.CardData card = new ShareChargeOrderChartRespVO.CardData();
        card.setTodayOrderCount(shareChargeOrderMapper.selectTodayCount(todayStart, now).intValue());
        card.setTodayLendCount(shareChargeOrderMapper.selectTodayLendCount(todayStart, now).intValue());
        card.setTodayRevenue(shareChargeOrderMapper.selectTodayRevenue(todayStart, now));
        resp.setCardData(card);
        return resp;
    }


    // ==================== 业务操作 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnOrderShareChargeOrder(IdReqVO reqVO) {
        ShareChargeOrderDO order = shareChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
        if (!"lending".equals(order.getStatus())) throw exception(SHARE_CHARGE_ORDER_NOT_BORROWED);
        // 归还：记录归还时间，计算使用时长，状态改为待支付
        LocalDateTime now = LocalDateTime.now();
        long minutes = java.time.temporal.ChronoUnit.MINUTES.between(order.getLendTime(), now);
        ShareChargeOrderDO update = new ShareChargeOrderDO();
        update.setId(reqVO.getId());
        update.setReturnTime(now);
        update.setActualDuration((int) minutes);
        update.setStatus("pending_pay");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        shareChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payShareChargeOrder(IdReqVO reqVO) {
        ShareChargeOrderDO order = shareChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_PAY);
        ShareChargeOrderDO update = new ShareChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("paid");
        update.setUpdateTime(LocalDateTime.now());
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        shareChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelShareChargeOrder(IdReqVO reqVO) {
        ShareChargeOrderDO order = shareChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
        if (!"pending_pay".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_CANCEL);
        ShareChargeOrderDO update = new ShareChargeOrderDO();
        update.setId(reqVO.getId());
        update.setStatus("cancelled");
        update.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        shareChargeOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundShareChargeOrder(IdReqVO reqVO) {
        ShareChargeOrderDO order = shareChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
        if (!"paid".equals(order.getStatus())) throw exception(ALL_ORDER_STATUS_CANNOT_REFUND);
        // 更新订单状态
        //ShareChargeOrderDO update = new ShareChargeOrderDO();
        //update.setId(reqVO.getId());
        order.setStatus("refunding");
        order.setOperatorId(SecurityFrameworkUtils.getLoginUserId());
        order.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        shareChargeOrderMapper.updateById(order);
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
    public void invoiceShareChargeOrder(InvoiceOrderReqVO reqVO) {
        ShareChargeOrderDO order = shareChargeOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
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
        if (shareChargeOrderMapper.selectById(id) == null) throw exception(SHARE_CHARGE_ORDER_NOT_EXISTS);
    }
}
