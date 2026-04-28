package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.CardOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.CardOrderInvoiceStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.CardOrderPayStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        cardOrder.setPayStatus(CardOrderPayStatusEnum.PAID.getValue());
        cardOrder.setPayTime(LocalDateTime.now());
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public void activate(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        cardOrder.setPayStatus(CardOrderPayStatusEnum.COMPLETED.getValue());
        cardOrder.setActiveTime(LocalDateTime.now());
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public void invoice(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        cardOrder.setInvoiceStatus(CardOrderInvoiceStatusEnum.INVOICED.getValue());
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public void cancel(Long id) {
        CardOrderDO cardOrder = validateExists(id);
        cardOrder.setPayStatus(CardOrderPayStatusEnum.CANCELLED.getValue());
        cardOrderMapper.updateById(cardOrder);
    }

    @Override
    public CardOrderChartRespVO getChart() {
        CardOrderChartRespVO respVO = new CardOrderChartRespVO();

        // 今日订单数
        Long todayOrderCount = cardOrderMapper.selectTodayCount();
        respVO.setTodayOrderCount(todayOrderCount.intValue());

        // 今日营收
        BigDecimal todayRevenue = cardOrderMapper.selectTodayRevenue();
        respVO.setTodayRevenue(todayRevenue);

        // 近30天按天统计，补全缺失日期
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> countByDay = cardOrderMapper.selectCountByDay(startTime);
        Map<String, Map<String, Object>> dayDataMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            String key = today.minusDays(i).toString();
            Map<String, Object> val = new LinkedHashMap<>();
            val.put("count", 0);
            val.put("amount", BigDecimal.ZERO);
            dayDataMap.put(key, val);
        }
        for (Map<String, Object> row : countByDay) {
            String date = row.get("date").toString();
            Map<String, Object> val = new LinkedHashMap<>();
            val.put("count", ((Number) row.get("count")).intValue());
            val.put("amount", row.get("amount") instanceof BigDecimal ? row.get("amount") : new BigDecimal(row.get("amount").toString()));
            dayDataMap.put(date, val);
        }
        List<CardOrderChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Map<String, Object>> entry : dayDataMap.entrySet()) {
            CardOrderChartRespVO.TrendItem item = new CardOrderChartRespVO.TrendItem();
            item.setDate(entry.getKey());
            item.setCount((Integer) entry.getValue().get("count"));
            item.setAmount((BigDecimal) entry.getValue().get("amount"));
            trendList.add(item);
        }
        respVO.setTrendList(trendList);

        // 按卡种类型统计订单数
        List<Map<String, Object>> typeCountList = cardOrderMapper.selectTypeCountList();
        List<CardOrderChartRespVO.TypeCountItem> typeItems = typeCountList.stream().map(m -> {
            CardOrderChartRespVO.TypeCountItem item = new CardOrderChartRespVO.TypeCountItem();
            item.setType((String) m.get("type"));
            item.setCount(((Number) m.get("count")).intValue());
            return item;
        }).toList();
        respVO.setTypeCountList(typeItems);

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
