package cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlAllocateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.StockControlRestockReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;

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
        stockControl.setWarnStatus("已告警");
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
    public StockControlChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        StockControlChartRespVO respVO = new StockControlChartRespVO();
        respVO.setTotalStock(0);
        respVO.setWarnCount(0);
        respVO.setTrendList(new ArrayList<>());
        respVO.setStockList(new ArrayList<>());
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
            stockControl.setStatus("预警库存");
        } else if (current <= threshold) {
            stockControl.setStatus("低库存");
        } else {
            stockControl.setStatus("正常库存");
        }
    }

}
