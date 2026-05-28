package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.json.JsonUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlAllocateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlRestockReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlWarnStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig.CardConfigService;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Resource
    private CardConfigService cardConfigService;

    @Resource
    private StationInfoApi stationInfoApi;

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
        stockControl.setCurrentStock(stockControl.getCurrentStock() + reqVO.getNum());
        updateStockStatus(stockControl);
        stockControl.setSyncTime(LocalDateTime.now());
        stockControlMapper.updateById(stockControl);
        CardConfigDO cardConfig = cardConfigService.get(stockControl.getCardId());
        String cardName = cardConfig != null ? cardConfig.getName() : String.valueOf(stockControl.getCardId());
        appendReplenishLog(stockControl, "[" + cardName + "]补货" + reqVO.getNum());
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
    @Transactional(rollbackFor = Exception.class)
    public void allocate(StockControlAllocateReqVO reqVO) {
        CardConfigDO cardConfig = cardConfigService.get(reqVO.getCardId());
        if (cardConfig == null) {
            throw exception(CARD_CONFIG_NOT_EXISTS);
        }
        Set<String> cardStationIds = new HashSet<>();
        if (StrUtil.isNotBlank(cardConfig.getStationId())) {
            cardStationIds = Arrays.stream(cardConfig.getStationId().split(","))
                    .map(String::trim)
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.toSet());
        }
//        if (cardStationIds.contains(reqVO.getSourceStationId().trim())) {
//            throw exception(STOCK_ALLOCATE_SOURCE_IN_RANGE);
//        }
//        if (!cardStationIds.contains(reqVO.getTargetStationId().trim())) {
//            throw exception(STOCK_ALLOCATE_TARGET_NOT_IN_RANGE);
//        }
        StockControlDO donor = stockControlMapper.selectMaxStockBySourceStationId(reqVO.getSourceStationId());
//        if (donor == null || donor.getCurrentStock() < reqVO.getNum()) {
//            throw exception(STOCK_INSUFFICIENT);
//        }
        StockControlDO target = stockControlMapper.selectByCardId(reqVO.getCardId());
//        if (target == null) {
//            throw exception(STOCK_CONTROL_NOT_EXISTS);
//        }
        if (donor != null) {
            donor.setCurrentStock(donor.getCurrentStock() - reqVO.getNum());
            updateStockStatus(donor);
            donor.setSyncTime(LocalDateTime.now());
            stockControlMapper.updateById(donor);
        }
        if (target != null) {
            target.setCurrentStock(target.getCurrentStock() + reqVO.getNum());
            updateStockStatus(target);
            target.setSyncTime(LocalDateTime.now());
            stockControlMapper.updateById(target);
        }

        String logMsg = buildAllocateLogMsg(reqVO);
//        appendAllocateLog(donor, logMsg);
        appendAllocateLog(target, logMsg);
        LogRecordContext.putVariable("stockControl", target);
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
                .filter(r -> "2".equals(r.getStatus()))
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

    private String buildAllocateLogMsg(StockControlAllocateReqVO reqVO) {
        String sourceName = getStationName(reqVO.getSourceStationId());
        String targetName = getStationName(reqVO.getTargetStationId());
        return sourceName + "调配" + reqVO.getNum() + "到场站" + targetName;
    }

    private String getStationName(String stationId) {
        try {
            Long id = Long.valueOf(stationId.trim());
            StationInfoRespDTO station = stationInfoApi.getStation(id).getCheckedData();
            return station != null ? station.getName() : stationId;
        } catch (Exception e) {
            return stationId;
        }
    }

    private void appendAllocateLog(StockControlDO record, String logMsg) {
        List<String> logs = new ArrayList<>();
        if (record != null && StrUtil.isNotBlank(record.getReserve1())) {
            try {
                List<String> existing = JsonUtils.parseArray(record.getReserve1(), String.class);
                if (existing != null) {
                    logs = existing;
                }
            } catch (Exception ignored) {
            }
            logs.add(logMsg);
            record.setReserve1(JsonUtils.toJsonString(logs));
            stockControlMapper.updateById(record);
        }
    }

    private void appendReplenishLog(StockControlDO record, String logMsg) {
        List<String> logs = new ArrayList<>();
        if (StrUtil.isNotBlank(record.getReserve2())) {
            try {
                List<String> existing = JsonUtils.parseArray(record.getReserve2(), String.class);
                if (existing != null) {
                    logs = existing;
                }
            } catch (Exception ignored) {
            }
        }
        logs.add(logMsg);
        record.setReserve2(JsonUtils.toJsonString(logs));
        stockControlMapper.updateById(record);
    }

}
