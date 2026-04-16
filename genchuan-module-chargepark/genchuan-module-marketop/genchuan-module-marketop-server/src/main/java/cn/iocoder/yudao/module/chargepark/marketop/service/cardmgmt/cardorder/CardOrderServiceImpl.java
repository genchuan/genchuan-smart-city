package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardOrderMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class CardOrderServiceImpl implements CardOrderService {

    @Resource
    private CardOrderMapper cardOrderMapper;

    @Override
    public PageResult<CardOrderDO> getPage(CardOrderPageReqVO reqVO) {
        return cardOrderMapper.selectPage(reqVO);
    }

    @Override
    public CardOrderDO get(Long id) {
        return cardOrderMapper.selectById(id);
    }

    @Override
    public void pay(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        if (!"待支付".equals(cardOrder.getPayStatus())) {
            throw exception(CARD_ORDER_STATUS_ERROR);
        }
        cardOrder.setPayStatus("已支付");
        cardOrder.setPayTime(java.time.LocalDateTime.now());
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public void activate(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        if (!"已支付".equals(cardOrder.getPayStatus())) {
            throw exception(CARD_ORDER_STATUS_ERROR);
        }
        cardOrder.setPayStatus("已完成");
        cardOrder.setActiveTime(java.time.LocalDateTime.now());
        cardOrderMapper.updateById(cardOrder);
        // TODO: 推送用户通知
    }

    @Override
    public void invoice(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        if (!"未开票".equals(cardOrder.getInvoiceStatus())) {
            throw exception(CARD_ORDER_STATUS_ERROR);
        }
        cardOrder.setInvoiceStatus("已开票");
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public void cancel(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        if (!"待支付".equals(cardOrder.getPayStatus())) {
            throw exception(CARD_ORDER_STATUS_ERROR);
        }
        cardOrder.setPayStatus("已取消");
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public CardOrderChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        CardOrderChartRespVO respVO = new CardOrderChartRespVO();
        respVO.setTodayOrderCount(0);
        respVO.setTodayRevenue(BigDecimal.ZERO);
        respVO.setTrendList(new ArrayList<>());
        respVO.setTypeCountList(new ArrayList<>());
        return respVO;
    }

    private CardOrderDO validateExists(Long id) {
        CardOrderDO cardOrder = cardOrderMapper.selectById(id);
        if (cardOrder == null) {
            throw exception(CARD_ORDER_NOT_EXISTS);
        }
        return cardOrder;
    }

}
