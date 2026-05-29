package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayRefundDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayRefundMapper;
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
public class PayRefundServiceImpl implements PayRefundService {

    @Resource
    private PayRefundMapper payRefundMapper;

    @Override
    public PayRefundDO getPayRefund(Long id) {
        return payRefundMapper.selectById(id);
    }

    @Override
    public PageResult<PayRefundDO> getPayRefundPage(PayRefundPageReqVO pageReqVO) {
        return payRefundMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executePayRefund(IdReqVO reqVO) {
        PayRefundDO refund = payRefundMapper.selectById(reqVO.getId());
        if (refund == null) throw exception(PAY_REFUND_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(refund.getStatus())) throw exception(PAY_REFUND_STATUS_CANNOT_EXECUTE);
        PayRefundDO update = new PayRefundDO();
        update.setId(reqVO.getId());
        update.setStatus(10);
        payRefundMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPayRefund(IdReqVO reqVO) {
        PayRefundDO refund = payRefundMapper.selectById(reqVO.getId());
        if (refund == null) throw exception(PAY_REFUND_NOT_EXISTS);
        if (!Integer.valueOf(0).equals(refund.getStatus())) throw exception(PAY_REFUND_STATUS_CANNOT_CANCEL);
        PayRefundDO update = new PayRefundDO();
        update.setId(reqVO.getId());
        update.setStatus(30);
        payRefundMapper.updateById(update);
    }

    @Override
    public PayRefundChartRespVO getPayRefundChart(PayRefundChartReqVO chartReqVO) {
        PayRefundChartRespVO resp = new PayRefundChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(payRefundMapper.selectTrend(start, end));

        PayRefundChartRespVO.CardData card = new PayRefundChartRespVO.CardData();
        card.setTodayRefundCount(payRefundMapper.selectTodayCount(todayStart, now));

        Long totalCount = payRefundMapper.selectTodayCount(todayStart, now);
        Long successCount = payRefundMapper.selectTodaySuccessCount(todayStart, now);
        if (totalCount != null && totalCount > 0) {
            card.setSuccessRate(new BigDecimal(successCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setSuccessRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }
}
