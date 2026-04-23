package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlAllocateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlRestockReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

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
    public void restock(StockControlRestockReqVO reqVO) {
        StockControlDO stockControl = validateExists(reqVO.getId());
        // 补货: 增加当前库存
        stockControl.setCurrentStock(stockControl.getCurrentStock() + reqVO.getQuantity());
        // 更新库存状态
        updateStockStatus(stockControl);
        stockControl.setSyncTime(LocalDateTime.now());
        stockControlMapper.updateById(stockControl);
    }

    @Override
    public void warn(Long id) {
        StockControlDO stockControl = validateExists(id);
        stockControl.setWarnStatus(StockControlStatusEnum.WARNING.getValue());
        stockControlMapper.updateById(stockControl);
        // TODO: 推送库存预警通知
    }

    @Override
    public void allocate(StockControlAllocateReqVO reqVO) {
        // 跨区域调配库存: 根据 cardId 查找库存记录并扣减
        StockControlDO stockControl = stockControlMapper.selectOne(StockControlDO::getCardId, reqVO.getCardId());
        if (stockControl == null) {
            throw exception(STOCK_CONTROL_NOT_EXISTS);
        }
        if (stockControl.getCurrentStock() < reqVO.getQuantity()) {
            throw exception(STOCK_INSUFFICIENT);
        }
        stockControl.setCurrentStock(stockControl.getCurrentStock() - reqVO.getQuantity());
        updateStockStatus(stockControl);
        stockControl.setSyncTime(LocalDateTime.now());
        stockControlMapper.updateById(stockControl);
        // TODO: 创建目标场站库存记录或增加目标场站库存
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
                .filter(r -> "-1".equals(r.getWarnStatus()))
                .count();
        respVO.setWarnStockCount(warnStockCount);

        // stockTrend 先空着，后续实现
        respVO.setStockTrend(new ArrayList<>());

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
