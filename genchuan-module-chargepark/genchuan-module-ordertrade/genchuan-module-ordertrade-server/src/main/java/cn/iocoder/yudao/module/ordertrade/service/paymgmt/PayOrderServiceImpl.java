package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayOrderDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayOrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PayOrderServiceImpl implements PayOrderService {

    @Resource
    private PayOrderMapper payOrderMapper;

    @Override
    public PayOrderDO getPayOrder(Long id) {
        return payOrderMapper.selectById(id);
    }

    @Override
    public PageResult<PayOrderDO> getPayOrderPage(PayOrderPageReqVO pageReqVO) {
        return payOrderMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void payPayOrder(IdReqVO reqVO) {
        PayOrderDO order = payOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(PAY_ORDER_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(order.getStatus())) throw exception(PAY_ORDER_STATUS_CANNOT_PAY);
        PayOrderDO update = new PayOrderDO();
        update.setId(reqVO.getId());
        update.setStatus(10);
        update.setSuccessTime(LocalDateTime.now());
        payOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refundPayOrder(IdReqVO reqVO) {
        PayOrderDO order = payOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(PAY_ORDER_NOT_EXISTS);
        if (!Integer.valueOf(10).equals(order.getStatus())) throw exception(PAY_ORDER_STATUS_CANNOT_REFUND);
        PayOrderDO update = new PayOrderDO();
        update.setId(reqVO.getId());
        update.setStatus(20);
        payOrderMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPayOrder(IdReqVO reqVO) {
        PayOrderDO order = payOrderMapper.selectById(reqVO.getId());
        if (order == null) throw exception(PAY_ORDER_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(order.getStatus())) throw exception(PAY_ORDER_STATUS_CANNOT_CANCEL);
        PayOrderDO update = new PayOrderDO();
        update.setId(reqVO.getId());
        update.setStatus(40);
        payOrderMapper.updateById(update);
    }

    @Override
    public PayOrderChartRespVO getPayOrderChart(PayOrderChartReqVO chartReqVO) {
        PayOrderChartRespVO resp = new PayOrderChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(payOrderMapper.selectTrend(start, end));
        resp.setChannelData(payOrderMapper.selectGroupByChannel());
        Long totalCount = payOrderMapper.selectTodayCount(todayStart, now);
        Long successCount = payOrderMapper.selectTodaySuccessCount(todayStart, now);
        PayOrderChartRespVO.CardData card = new PayOrderChartRespVO.CardData();
        card.setTodayOrderCount(totalCount);
        card.setSuccessRate(totalCount != null && totalCount > 0
                ? new BigDecimal(successCount).multiply(BigDecimal.valueOf(100)).divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP)
                : BigDecimal.ZERO);
        resp.setCardData(card);
        return resp;
    }
}
