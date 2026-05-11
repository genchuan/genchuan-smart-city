package cn.iocoder.yudao.module.inspectop.service.assetstock;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.assetstock.AssetStockMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 库存管理 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetStockServiceImpl implements AssetStockService {

    @Resource
    private AssetStockMapper assetStockMapper;

    @Override
    public Long createAssetStock(AssetStockSaveReqVO createReqVO) {
        // 插入
        AssetStockDO assetStock = BeanUtils.toBean(createReqVO, AssetStockDO.class);
        assetStockMapper.insert(assetStock);

        // 返回
        return assetStock.getId();
    }

    @Override
    public void updateAssetStock(AssetStockSaveReqVO updateReqVO) {
        // 校验存在
        validateAssetStockExists(updateReqVO.getId());
        // 更新
        AssetStockDO updateObj = BeanUtils.toBean(updateReqVO, AssetStockDO.class);
        assetStockMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssetStock(Long id) {
        // 校验存在
        validateAssetStockExists(id);
        // 删除
        assetStockMapper.deleteById(id);
    }

    @Override
        public void deleteAssetStockListByIds(List<Long> ids) {
        // 删除
        assetStockMapper.deleteByIds(ids);
        }


    private void validateAssetStockExists(Long id) {
        if (assetStockMapper.selectById(id) == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }
    }

    @Override
    public AssetStockRespVO getAssetStock(Long id) {
        // 调用 Mapper 的关联查询方法获取包含资产名称的数据
        AssetStockRespVO assetStock = assetStockMapper.selectOneWithJoin(id);
        if (assetStock == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }
        return assetStock;
    }

    @Override
    public PageResult<AssetStockRespVO> getAssetStockPage(AssetStockPageReqVO pageReqVO) {
        // 创建 MyBatis-Plus 分页对象
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AssetStockRespVO> mpPage =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(
                        pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<AssetStockRespVO> resultPage =
                assetStockMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造并返回 PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocateAssetStock(AssetStockAllocateReqVO allocateReqVO) {
        // 1. 获取源库存记录
        AssetStockDO sourceStock = assetStockMapper.selectById(allocateReqVO.getId());
        if (sourceStock == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }

        // 2. 检查库存数量是否足够
        if (sourceStock.getCurrentStock() < allocateReqVO.getAllocateCount()) {
            throw exception("库存数量不足");
        }

        // 3. 查询目标场站是否已有该资产的库存记录
        LambdaQueryWrapperX<AssetStockDO> queryWrapper = new LambdaQueryWrapperX<AssetStockDO>()
                .eq(AssetStockDO::getAssetId, sourceStock.getAssetId())
                .eq(AssetStockDO::getStationId, allocateReqVO.getTargetStationId());
        AssetStockDO targetStock = assetStockMapper.selectOne(queryWrapper);

        // 4. 扣减源库存，并记录调出信息
        sourceStock.setCurrentStock(sourceStock.getCurrentStock() - allocateReqVO.getAllocateCount());
        // 【修改点】构建并追加调出记录到 reserve2
        String sourceRecord = String.format("[调出至场站%d,数量:%d]",
                allocateReqVO.getTargetStationId(), allocateReqVO.getAllocateCount());
        sourceStock.setReserve2(appendRecord(sourceStock.getReserve2(), sourceRecord));
        assetStockMapper.updateById(sourceStock);

        // 5. 处理目标库存
        if (targetStock != null) {
            // 目标场站已存在该资产库存，增加库存数量
            targetStock.setCurrentStock(targetStock.getCurrentStock() + allocateReqVO.getAllocateCount());
            // 【修改点】构建并追加调入记录到 reserve2
            String targetRecord = String.format("[从库存%d调入,数量:%d]",
                    sourceStock.getId(), allocateReqVO.getAllocateCount());
            targetStock.setReserve2(appendRecord(targetStock.getReserve2(), targetRecord));
            assetStockMapper.updateById(targetStock);
        } else {
            // 目标场站不存在该资产库存，创建新记录
            // 【修改点】为新库存设置调入记录
            String newStockRecord = String.format("[从库存%d调入,数量:%d]",
                    sourceStock.getId(), allocateReqVO.getAllocateCount());
            AssetStockDO newStock = AssetStockDO.builder()
                    .assetId(sourceStock.getAssetId())
                    .currentStock(allocateReqVO.getAllocateCount())
                    .warnThreshold(sourceStock.getWarnThreshold())
                    .status("1") // 假设状态1为正常
                    .stationId(allocateReqVO.getTargetStationId())
                    .reserve2(newStockRecord) // 直接设置调入记录
                    .build();
            assetStockMapper.insert(newStock);
        }
    }

    /**
     * 向原有记录字符串中追加新记录。
     * 如果原记录为空，则直接返回新记录；否则在原记录后添加分号和换行符，再追加新记录。
     *
     * @param originalRecord 原始记录字符串
     * @param newRecord 要追加的新记录
     * @return 追加后的完整记录字符串
     */
    private String appendRecord(String originalRecord, String newRecord) {
        if (originalRecord == null || originalRecord.isEmpty()) {
            return newRecord;
        } else {
            // 使用“; ”作为分隔符，使记录更清晰。您可以根据喜好调整。
            return originalRecord + "; " + newRecord;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void alarmAssetStock(AssetStockAlarmReqVO alarmReqVO) {
        // 1. 获取库存记录
        AssetStockDO assetStock = assetStockMapper.selectById(alarmReqVO.getId());
        if (assetStock == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }

        // 2. 创建更新对象
        AssetStockDO updateObj = new AssetStockDO();
        updateObj.setId(assetStock.getId());
        updateObj.setStatus(alarmReqVO.getStatus());

        // 3. 根据状态值设置相应的处理逻辑
        String status = alarmReqVO.getStatus();
        switch (status) {
            case "1": // 正常
                // 不需要额外处理
                break;
            case "2": // 低库存
                // 如果是低库存，可以将低库存信息记录到备用字段
                updateObj.setReserve1("低库存告警");
                break;
            case "3": // 预警库存
                // 如果是预警库存，可以将预警信息记录到备用字段
                updateObj.setReserve1("库存预警");
                break;
            default:
                // 理论上不会到这里，因为前面的校验已经确保了状态值
                throw new IllegalArgumentException("无效的库存状态: " + status);
        }

        // 4. 更新数据库
        assetStockMapper.updateById(updateObj);
    }

    @Override
    public AssetStockChartRespVO getAssetStockChart(AssetStockChartReqVO reqVO) {
        AssetStockChartRespVO respVO = new AssetStockChartRespVO();

        // 获取库存趋势数据
        List<AssetStockChartRespVO.TrendData> trendData = assetStockMapper.selectTrendData(reqVO.getTimeRange());
        respVO.setTrendData(trendData);

        // 获取资产库存分布数据
        List<AssetStockChartRespVO.StockData> stockData = assetStockMapper.selectStockData(reqVO.getTimeRange());
        respVO.setStockData(stockData);

        // 获取卡片统计数据
        AssetStockChartRespVO.CardData cardData = assetStockMapper.selectCardData(reqVO.getTimeRange());
        respVO.setCardData(cardData);

        return respVO;
    }

}