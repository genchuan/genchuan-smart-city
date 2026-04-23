package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderDeliverReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ExchangeOrderServiceImpl implements ExchangeOrderService {

    @Resource
    private ExchangeOrderMapper exchangeOrderMapper;

    @Override
    public PageResult<ExchangeOrderDO> getPage(ExchangeOrderPageReqVO reqVO) {
        return exchangeOrderMapper.selectPage(reqVO);
    }

    @Override
    public ExchangeOrderDO get(Long id) {
        return exchangeOrderMapper.selectById(id);
    }

    @Override
    public void pay(Long id) {
        ExchangeOrderDO exchangeOrder = validateExists(id);
        if (!WAITING.getValue().equals(exchangeOrder.getPayStatus())) {
            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
        }
        exchangeOrder.setPayStatus(PAID.getValue());
        exchangeOrder.setPayTime(LocalDateTime.now());
        exchangeOrderMapper.updateById(exchangeOrder);
    }

    @Override
    public void deliver(ExchangeOrderDeliverReqVO reqVO) {
        ExchangeOrderDO exchangeOrder = validateExists(reqVO.getId());
        if (!PAID.getValue().equals(exchangeOrder.getPayStatus())) {
            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
        }
        exchangeOrder.setPayStatus(COMPLETED.getValue());
        exchangeOrder.setShipTime(LocalDateTime.now());
        exchangeOrder.setLogisticsInfo(reqVO.getExpressNo());
        exchangeOrderMapper.updateById(exchangeOrder);
        // TODO: 推送物流通知
    }

    @Override
    public void cancel(Long id) {
        ExchangeOrderDO exchangeOrder = validateExists(id);
        if (!WAITING.getValue().equals(exchangeOrder.getPayStatus())) {
            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
        }
        exchangeOrder.setPayStatus(CANCELLED.getValue());
        exchangeOrderMapper.updateById(exchangeOrder);
        // TODO: 返还用户积分
    }

    @Override
    public ExchangeOrderChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        ExchangeOrderChartRespVO respVO = new ExchangeOrderChartRespVO();
        respVO.setTodayOrderCount(0);
        respVO.setTodayExchangeCount(0);
        respVO.setTrendList(new ArrayList<>());
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private ExchangeOrderDO validateExists(Long id) {
        ExchangeOrderDO exchangeOrder = exchangeOrderMapper.selectById(id);
        if (exchangeOrder == null) {
            throw exception(EXCHANGE_ORDER_NOT_EXISTS);
        }
        return exchangeOrder;
    }

}
