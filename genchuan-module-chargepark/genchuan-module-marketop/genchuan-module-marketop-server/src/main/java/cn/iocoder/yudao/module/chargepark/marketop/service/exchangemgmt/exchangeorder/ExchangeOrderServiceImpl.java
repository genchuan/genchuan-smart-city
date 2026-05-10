package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderDeliverReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.ExchangeOrderPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory.ExchangeCategoryService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

@Service
@Validated
public class ExchangeOrderServiceImpl implements ExchangeOrderService {

    @Resource
    private ExchangeOrderMapper exchangeOrderMapper;

    @Resource
    private ExchangeCategoryService exchangeCategoryService;

    @Override
    public PageResult<ExchangeOrderDO> getPage(ExchangeOrderPageReqVO reqVO) {
        return exchangeOrderMapper.selectPage(reqVO);
    }

    @Override
    public ExchangeOrderDO get(Long id) {
        return exchangeOrderMapper.selectById(id);
    }

    @Override
    @LogRecord(type = EXCHANGE_ORDER_TYPE, subType = EXCHANGE_ORDER_PAY_SUB_TYPE, bizNo = "{{#id}}",
            success = EXCHANGE_ORDER_PAY_SUCCESS)
    public void pay(Long id) {
        ExchangeOrderDO exchangeOrder = validateExists(id);
//        if (!WAITING.getValue().equals(exchangeOrder.getPayStatus())) {
//            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
//        }
        exchangeOrder.setPayStatus(PAID.getValue());
        exchangeOrder.setPayTime(LocalDateTime.now());
        exchangeOrderMapper.updateById(exchangeOrder);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeOrder", exchangeOrder);
    }

    @Override
    @LogRecord(type = EXCHANGE_ORDER_TYPE, subType = EXCHANGE_ORDER_DELIVER_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = EXCHANGE_ORDER_DELIVER_SUCCESS)
    public void deliver(ExchangeOrderDeliverReqVO reqVO) {
        ExchangeOrderDO exchangeOrder = validateExists(reqVO.getId());
//        if (!PAID.getValue().equals(exchangeOrder.getPayStatus())) {
//            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
//        }
        exchangeOrder.setPayStatus(COMPLETED.getValue());
        exchangeOrder.setShipTime(LocalDateTime.now());
        exchangeOrder.setLogisticsInfo(reqVO.getLogisticsInfo());
        exchangeOrderMapper.updateById(exchangeOrder);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeOrder", exchangeOrder);
    }

    @Override
    @LogRecord(type = EXCHANGE_ORDER_TYPE, subType = EXCHANGE_ORDER_CANCEL_SUB_TYPE, bizNo = "{{#id}}",
            success = EXCHANGE_ORDER_CANCEL_SUCCESS)
    public void cancel(Long id) {
        ExchangeOrderDO exchangeOrder = validateExists(id);
//        if (!WAITING.getValue().equals(exchangeOrder.getPayStatus())) {
//            throw exception(EXCHANGE_ORDER_STATUS_ERROR);
//        }
        exchangeOrder.setPayStatus(CANCELLED.getValue());
        exchangeOrderMapper.updateById(exchangeOrder);
        // 记录操作日志上下文
        LogRecordContext.putVariable("exchangeOrder", exchangeOrder);
    }

    @Override
    public ExchangeOrderChartRespVO getChart() {
        ExchangeOrderChartRespVO respVO = new ExchangeOrderChartRespVO();

        // 今日订单总数
        Long todayOrderCount = exchangeOrderMapper.selectTodayCount();
        respVO.setTodayOrderCount(todayOrderCount.intValue());

        // 今日兑换量 (pay_status = '2' 已完成)
        Long todayExchangeCount = exchangeOrderMapper.selectTodayExchangeCount();
        respVO.setTodayExchangeCount(todayExchangeCount.intValue());

        // 近30天按天统计订单数，补全缺失日期
        LocalDateTime startTime = LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> countByDay = exchangeOrderMapper.selectCountByDay(startTime);
        Map<String, Integer> dayCountMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            dayCountMap.put(today.minusDays(i).toString(), 0);
        }
        for (Map<String, Object> row : countByDay) {
            String date = row.get("date").toString();
            int count = ((Number) row.get("count")).intValue();
            dayCountMap.put(date, count);
        }
        List<ExchangeOrderChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            ExchangeOrderChartRespVO.TrendItem item = new ExchangeOrderChartRespVO.TrendItem();
            item.setDate(entry.getKey());
            item.setCount(entry.getValue());
            trendList.add(item);
        }
        respVO.setTrendList(trendList);

        // 按 categoryId 分类统计订单数，翻译类目名称
        List<Map<String, Object>> categoryCountList = exchangeOrderMapper.selectCategoryCountList();
        List<ExchangeOrderChartRespVO.TypeItem> typeItems = new ArrayList<>();
        for (Map<String, Object> m : categoryCountList) {
            ExchangeOrderChartRespVO.TypeItem item = new ExchangeOrderChartRespVO.TypeItem();
            Long categoryId = ((Number) m.get("category_id")).longValue();
            item.setCategoryId(categoryId);
            item.setCount(((Number) m.get("count")).intValue());
            ExchangeCategoryDO category = exchangeCategoryService.get(categoryId);
            if (category != null) {
                item.setName(category.getName());
            }
            typeItems.add(item);
        }
        respVO.setTypeList(typeItems);

        return respVO;
    }

    @Override
    public List<ExchangeOrderDO> getListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        return exchangeOrderMapper.selectBatchIds(ids);
    }

    private ExchangeOrderDO validateExists(Long id) {
        ExchangeOrderDO exchangeOrder = exchangeOrderMapper.selectById(id);
        if (exchangeOrder == null) {
            throw exception(EXCHANGE_ORDER_NOT_EXISTS);
        }
        return exchangeOrder;
    }

}
