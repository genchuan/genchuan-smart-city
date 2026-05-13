package cn.iocoder.yudao.module.inspectop.service.sparestock;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock.SpareStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.sparestock.SpareStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 备件仓储 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SpareStockServiceImpl implements SpareStockService {

    @Resource
    private SpareStockMapper spareStockMapper;

    @Override
    public Long createSpareStock(SpareStockSaveReqVO createReqVO) {
        // 插入
        SpareStockDO spareStock = BeanUtils.toBean(createReqVO, SpareStockDO.class);
        spareStockMapper.insert(spareStock);

        // 返回
        return spareStock.getId();
    }

    @Override
    public void updateSpareStock(SpareStockSaveReqVO updateReqVO) {
        // 校验存在
        validateSpareStockExists(updateReqVO.getId());
        // 更新
        SpareStockDO updateObj = BeanUtils.toBean(updateReqVO, SpareStockDO.class);
        spareStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpareStock(Long id) {
        // 校验存在
        validateSpareStockExists(id);
        // 删除
        spareStockMapper.deleteById(id);
    }

    @Override
        public void deleteSpareStockListByIds(List<Long> ids) {
        // 删除
        spareStockMapper.deleteByIds(ids);
        }


    private void validateSpareStockExists(Long id) {
        if (spareStockMapper.selectById(id) == null) {
            throw exception(SPARE_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public SpareStockDO getSpareStock(Long id) {
        return spareStockMapper.selectById(id);
    }

    @Override
    public PageResult<SpareStockDO> getSpareStockPage(SpareStockPageReqVO pageReqVO) {
        return spareStockMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void inSpareStock(SpareStockInReqVO reqVO) {
        // 1. 查询当前库存记录
        LambdaQueryWrapperX<SpareStockDO> queryWrapper = new LambdaQueryWrapperX<SpareStockDO>()
                .eq(SpareStockDO::getSpareId, reqVO.getSpareId());
        SpareStockDO stock = spareStockMapper.selectOne(queryWrapper);

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 2. 如果库存记录不存在，则创建新记录
        if (stock == null) {
            stock = new SpareStockDO();
            stock.setSpareId(reqVO.getSpareId());
            stock.setSpareName(reqVO.getSpareName());
            stock.setCurrentStock(reqVO.getInCount()); // 初始库存为入库数量

            // 设置库存状态：如果未指定则使用默认值"1"（正常）
            String status = reqVO.getStatus() != null ? reqVO.getStatus() : "1";
            stock.setStatus(status);

            // 设置入库时间
            stock.setInTime(now);

            // 记录供应商信息（首次入库）
            stock.setReserve1(reqVO.getSupplier());

            // 设置创建信息
            stock.setCreator("system"); // 实际项目中应从用户上下文获取
            stock.setCreateTime(now);

            // 插入新记录
            spareStockMapper.insert(stock);
        } else {
            // 3. 如果记录存在，则更新库存记录
            // 3.1 增加库存数量
            stock.setCurrentStock(stock.getCurrentStock() + reqVO.getInCount());

            // 3.2 更新入库时间
            stock.setInTime(now);

            // 3.3 追加供应商信息（不覆盖历史）
            String currentSuppliers = stock.getReserve1();
            if (currentSuppliers == null || currentSuppliers.isEmpty()) {
                // 如果当前没有供应商记录，直接设置
                stock.setReserve1(reqVO.getSupplier());
            } else {
                // 如果已有供应商记录，追加新的供应商（用逗号分隔）
                // 检查是否已存在该供应商，避免重复
                if (!currentSuppliers.contains(reqVO.getSupplier())) {
                    stock.setReserve1(currentSuppliers + "," + reqVO.getSupplier());
                }
            }

            // 3.4 自动更新库存状态（根据库存量）
            updateStockStatus(stock);

            // 3.5 设置更新信息
            stock.setUpdater("system"); // 实际项目中应从用户上下文获取
            stock.setUpdateTime(now);

            // 更新记录
            spareStockMapper.updateById(stock);
        }
    }

    /**
     * 根据库存量自动更新库存状态
     * 状态规则：
     * 1. 库存为0 -> "3"（预警库存）
     * 2. 库存小于等于10 -> "2"（低库存）
     * 3. 库存大于10 -> "1"（正常）
     */
    private void updateStockStatus(SpareStockDO stock) {
        Integer currentStock = stock.getCurrentStock();
        if (currentStock == 0) {
            stock.setStatus("3"); // 预警库存
        } else if (currentStock <= 10) {
            stock.setStatus("2"); // 低库存
        } else {
            stock.setStatus("1"); // 正常
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void outSpareStock(SpareStockOutReqVO reqVO) {
        // 1. 查询当前库存记录
        LambdaQueryWrapperX<SpareStockDO> queryWrapper = new LambdaQueryWrapperX<SpareStockDO>()
                .eq(SpareStockDO::getSpareId, reqVO.getSpareId());
        SpareStockDO stock = spareStockMapper.selectOne(queryWrapper);

        // 2. 检查库存记录是否存在
        if (stock == null) {
            throw exception(SPARE_STOCK_NOT_EXISTS);
        }

        // 3. 检查库存是否充足
        if (stock.getCurrentStock() < reqVO.getOutCount()) {
            throw exception("库存数量不足");
        }

        // 4. 减少库存数量
        stock.setCurrentStock(stock.getCurrentStock() - reqVO.getOutCount());

        // 5. 更新出库时间
        stock.setOutTime(LocalDateTime.now());

        // 6. 可以记录领用人员信息到备用字段
        stock.setReserve2(reqVO.getReceiver());

        // 7. 更新库存状态（如果库存为0，可以更新状态）
        if (stock.getCurrentStock() <= 0) {
            stock.setStatus("3");
        }

        // 8. 更新库存记录
        spareStockMapper.updateById(stock);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void replenishSpareStock(SpareStockReplenishReqVO reqVO) {
        // 1. 根据ID查询库存记录
        SpareStockDO stock = spareStockMapper.selectById(reqVO.getId());

        // 2. 校验库存记录是否存在
        if (stock == null) {
            throw exception(SPARE_STOCK_NOT_EXISTS);
        }

        // 3. 增加库存数量
        Integer newStock = stock.getCurrentStock() + reqVO.getReplenishCount();
        stock.setCurrentStock(newStock);

        // 4. 如果之前是低库存状态，补货后自动更新为正常状态
        if ("2".equals(stock.getStatus()) && newStock > 0) {
            stock.setStatus("1");
        }

        // 5. 更新库存记录
        spareStockMapper.updateById(stock);
    }

    @Override
    public SpareStockChartRespVO getSpareStockChart(SpareStockChartReqVO reqVO) {
        SpareStockChartRespVO respVO = new SpareStockChartRespVO();

        // 获取库存趋势数据
        List<SpareStockChartRespVO.TrendData> trendData = spareStockMapper.selectTrendData(reqVO.getTimeRange());
        respVO.setTrendData(trendData);

        // 获取备件库存分布数据
        List<SpareStockChartRespVO.StockData> stockData = spareStockMapper.selectStockData(reqVO.getTimeRange());
        respVO.setStockData(stockData);

        // 获取卡片统计数据
        SpareStockChartRespVO.CardData cardData = spareStockMapper.selectCardData(reqVO.getTimeRange());
        respVO.setCardData(cardData);

        return respVO;
    }
    @Override
    public List<SpareStockSimpleRespVO> getSimpleSpareList() {
        // 直接调用Mapper查询不重复的备件列表
        return spareStockMapper.selectDistinctSpareList();
    }
}