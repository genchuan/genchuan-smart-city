package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayCallbackDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayCallbackMapper;
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
public class PayCallbackServiceImpl implements PayCallbackService {

    @Resource
    private PayCallbackMapper payCallbackMapper;

    @Override
    public PayCallbackDO getPayCallback(Long id) {
        return payCallbackMapper.selectById(id);
    }

    @Override
    public PageResult<PayCallbackDO> getPayCallbackPage(PayCallbackPageReqVO pageReqVO) {
        return payCallbackMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void processPayCallback(IdReqVO reqVO) {
        PayCallbackDO callback = payCallbackMapper.selectById(reqVO.getId());
        if (callback == null) throw exception(PAY_CALLBACK_NOT_EXISTS);
        PayCallbackDO update = new PayCallbackDO();
        update.setId(reqVO.getId());
        update.setStatus(10);
        update.setLastExecuteTime(LocalDateTime.now());
        payCallbackMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void repushPayCallback(IdReqVO reqVO) {
        PayCallbackDO callback = payCallbackMapper.selectById(reqVO.getId());
        if (callback == null) throw exception(PAY_CALLBACK_NOT_EXISTS);
        PayCallbackDO update = new PayCallbackDO();
        update.setId(reqVO.getId());
        update.setStatus(0);
        update.setNextNotifyTime(LocalDateTime.now().plusMinutes(5));
        payCallbackMapper.updateById(update);
    }

    @Override
    public PayCallbackChartRespVO getPayCallbackChart(PayCallbackChartReqVO chartReqVO) {
        PayCallbackChartRespVO resp = new PayCallbackChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(payCallbackMapper.selectTrend(start, end));

        PayCallbackChartRespVO.CardData card = new PayCallbackChartRespVO.CardData();
        card.setTodayNotifyCount(payCallbackMapper.selectTodayCount(todayStart, now));

        Long totalCount = payCallbackMapper.selectTodayCount(todayStart, now);
        Long successCount = payCallbackMapper.selectTodaySuccessCount(todayStart, now);
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
