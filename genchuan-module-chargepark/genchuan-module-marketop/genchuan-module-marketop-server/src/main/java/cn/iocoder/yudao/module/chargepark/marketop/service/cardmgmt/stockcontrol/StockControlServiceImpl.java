package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlAllocateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlRestockReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlWarnStatusEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

@Service
@Validated
public class StockControlServiceImpl implements StockControlService {

    @Resource
    private StockControlMapper stockControlMapper;

    @Override
    public PageResult<StockControlDO> getPage(StockControlPageReqVO reqVO) {
        return stockControlMapper.selectPage(reqVO);
    }

    @Override
    public StockControlDO get(Long id) {
        return stockControlMapper.selectById(id);
    }

    @Override
    @LogRecord(type = STOCK_CONTROL_TYPE, subType = STOCK_CONTROL_RESTOCK_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = STOCK_CONTROL_RESTOCK_SUCCESS)
    public void restock(StockControlRestockReqVO reqVO) {
        StockControlDO stockControl = validateExists(reqVO.getId());
        // 补货: 增加当前库存
        stockControl.setCurrentStock(stockControl.getCurrentStock() + reqVO.getNum());
        // 更新库存状态
        updateStockStatus(stockControl);
        stockControl.setSyncTime(LocalDateTime.now());
        stockControlMapper.updateById(stockControl);
        // 记录操作日志上下文
        LogRecordContext.putVariable("stockControl", stockControl);
        LogRecordContext.putVariable("num", reqVO.getNum());
    }

    @Override
    @LogRecord(type = STOCK_CONTROL_TYPE, subType = STOCK_CONTROL_WARN_SUB_TYPE, bizNo = "{{#id}}",
            success = STOCK_CONTROL_WARN_SUCCESS)
    public void warn(Long id) {
        StockControlDO stockControl = validateExists(id);
        stockControl.setWarnStatus(StockControlWarnStatusEnum.WARNED.getValue());
        stockControlMapper.updateById(stockControl);
        // 记录操作日志上下文
        LogRecordContext.putVariable("stockControl", stockControl);
        // TODO: 推送库存预警通知
    }

    @Override
    @LogRecord(type = STOCK_CONTROL_TYPE, subType = STOCK_CONTROL_ALLOCATE_SUB_TYPE, bizNo = "{{#reqVO.cardId}}",
            success = STOCK_CONTROL_ALLOCATE_SUCCESS)
    public void allocate(StockControlAllocateReqVO reqVO) {
        // 查询源场站库存记录
        StockControlDO source = stockControlMapper.selectByCardIdAndStationId(reqVO.getCardId(), reqVO.getSourceStationId());
        if (source == null) {
            throw exception(STOCK_CONTROL_NOT_EXISTS);
        }
        if (source.getCurrentStock() < reqVO.getNum()) {
            throw exception(STOCK_INSUFFICIENT);
        }
        // 记录操作日志上下文
        LogRecordContext.putVariable("stockControl", source);
//        // 查询目标场站库存记录
//        StockControlDO target = stockControlMapper.selectByCardIdAndStationId(reqVO.getCardId(), reqVO.getTargetStationId());
//        if (target == null) {
//            throw exception(STOCK_CONTROL_NOT_EXISTS);
//        }
//        // 源场站扣减
//        source.setCurrentStock(source.getCurrentStock() - reqVO.getNumber());
//        updateStockStatus(source);
//        source.setSyncTime(LocalDateTime.now());
//        stockControlMapper.updateById(source);
//        // 目标场站增加
//        target.setCurrentStock(target.getCurrentStock() + reqVO.getNumber());
//        updateStockStatus(target);
//        target.setSyncTime(LocalDateTime.now());
//        stockControlMapper.updateById(target);
    }

    @Override
    public StockControlChartRespVO getChart(Long startTime, Long endTime, Long stationId) {
        LocalDateTime startDateTime = startTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(startTime), ZoneId.systemDefault())
                : null;
        LocalDateTime endDateTime = endTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(endTime), ZoneId.systemDefault())
                : null;

        List<StockControlDO> records = stockControlMapper.selectListByTimeRange(startDateTime, endDateTime, stationId);

        StockControlChartRespVO respVO = new StockControlChartRespVO();

        // 总库存量 = 所有记录的 current_stock 总和
        int totalStock = records.stream()
                .mapToInt(r -> r.getCurrentStock() != null ? r.getCurrentStock() : 0)
                .sum();
        respVO.setTotalStock(totalStock);

        // 预警数量 = warnStatus 为已告警的记录数
        int warnStockCount = (int) records.stream()
                .filter(r -> "1".equals(r.getWarnStatus()))
                .count();
        respVO.setWarnStockCount(warnStockCount);

        // stockTrend: 按create_time转日期分组统计current_stock总和，默认近30天，补全缺失日期
        LocalDateTime trendStart = startDateTime != null ? startDateTime : LocalDateTime.now().minusDays(30);
        List<Map<String, Object>> trendRows = stockControlMapper.selectStockTrend(trendStart, endDateTime, stationId);
        Map<String, Integer> dayCountMap = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();
        for (int i = 29; i >= 0; i--) {
            dayCountMap.put(today.minusDays(i).toString(), 0);
        }
        for (Map<String, Object> row : trendRows) {
            String date = row.get("date").toString();
            int count = ((Number) row.get("count")).intValue();
            dayCountMap.put(date, count);
        }
        List<StockControlChartRespVO.TrendItem> trendList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dayCountMap.entrySet()) {
            StockControlChartRespVO.TrendItem item = new StockControlChartRespVO.TrendItem();
            item.setDate(entry.getKey());
            item.setCount(entry.getValue());
            trendList.add(item);
        }
        respVO.setStockTrend(trendList);

        // stockDistribution = 根据 cardId 关联 card_config 表，通过 type 分类统计数量
        List<StockControlChartRespVO.DistributionItem> distributionList =
                stockControlMapper.selectDistributionByType(startDateTime, endDateTime, stationId);
        respVO.setStockDistribution(distributionList);

        return respVO;
    }

    private StockControlDO validateExists(Long id) {
        StockControlDO stockControl = stockControlMapper.selectById(id);
        if (stockControl == null) {
            throw exception(STOCK_CONTROL_NOT_EXISTS);
        }
        return stockControl;
    }

    private void updateStockStatus(StockControlDO stockControl) {
        int current = stockControl.getCurrentStock();
        int threshold = stockControl.getWarnThreshold() != null ? stockControl.getWarnThreshold() : 0;
        if (current <= 0) {
            stockControl.setStatus(StockControlStatusEnum.WARNING.getValue());
        } else if (current <= threshold) {
            stockControl.setStatus(StockControlStatusEnum.LOW.getValue());
        } else {
            stockControl.setStatus(StockControlStatusEnum.NORMAL.getValue());
        }
    }

}
