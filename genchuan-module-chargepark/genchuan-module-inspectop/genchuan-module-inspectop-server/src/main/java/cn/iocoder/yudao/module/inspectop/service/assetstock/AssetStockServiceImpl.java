package cn.iocoder.yudao.module.inspectop.service.assetstock;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
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
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

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
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = ASSET_STOCK_CREATE_SUCCESS)
    public Long createAssetStock(AssetStockSaveReqVO createReqVO) {
        // 插入
        AssetStockDO assetStock = BeanUtils.toBean(createReqVO, AssetStockDO.class);
        assetStockMapper.insert(assetStock);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return assetStock.getId();
    }


    @Override
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = ASSET_STOCK_UPDATE_SUCCESS)
    public void updateAssetStock(AssetStockSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        AssetStockDO oldAssetStock = validateAssetStockExists(updateReqVO.getId());

        // 【新增】补货数量判断逻辑
        // 获取新旧库存数量
        Integer oldStock = oldAssetStock.getCurrentStock();
        Integer newStock = updateReqVO.getCurrentStock();

        // 判断是否为补货操作（新库存 > 旧库存）且补货数量超过10
        boolean isReplenishment = newStock > oldStock;
        boolean exceedsReplenishmentThreshold = isReplenishment && (newStock - oldStock) > 10;

        // 2. 创建更新对象
        AssetStockDO updateObj = BeanUtils.toBean(updateReqVO, AssetStockDO.class);

        // 【新增】如果补货数量超过10，自动将状态改为1（正常）
        if (exceedsReplenishmentThreshold) {
            updateObj.setStatus("1"); // 将状态设置为正常
        }

        // 3. 更新数据库
        assetStockMapper.updateById(updateObj);

        // 4. 记录操作日志上下文（用于DIFF比较）
        AssetStockSaveReqVO oldVO = BeanUtils.toBean(oldAssetStock, AssetStockSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);

        // 【新增】设置日志上下文变量，记录补货操作信息
        LogRecordContext.putVariable("isReplenishment", isReplenishment);
        LogRecordContext.putVariable("replenishmentAmount", isReplenishment ? (newStock - oldStock) : 0);
        LogRecordContext.putVariable("exceedsThreshold", exceedsReplenishmentThreshold);
        LogRecordContext.putVariable("autoStatusChanged", exceedsReplenishmentThreshold);
    }

    @Override
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = ASSET_STOCK_DELETE_SUCCESS)
    public void deleteAssetStock(Long id) {
        // 校验存在
        validateAssetStockExists(id);
        // 删除
        assetStockMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_DELETE_LIST_SUB_TYPE,
            success = ASSET_STOCK_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteAssetStockListByIds(List<Long> ids) {
        // 删除
        assetStockMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }


    private AssetStockDO validateAssetStockExists(Long id) {
        AssetStockDO assetStock = assetStockMapper.selectById(id);
        if (assetStock == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }
        return assetStock; // 返回查询到的对象
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
        Page<AssetStockRespVO> mpPage =
                new Page<>(
                        pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用 Mapper 的关联查询方法
        Page<AssetStockRespVO> resultPage =
                assetStockMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 构造并返回 PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_ALLOCATE_SUB_TYPE,
            bizNo = "{{#allocateReqVO.id}}", success = ASSET_STOCK_ALLOCATE_SUCCESS)
    public void allocateAssetStock(AssetStockAllocateReqVO allocateReqVO) {
        // 1. 获取源库存记录（包含关联信息）
        AssetStockRespVO sourceStock = assetStockMapper.selectOneWithJoin(allocateReqVO.getId());
        if (sourceStock == null) {
            throw exception(ASSET_STOCK_NOT_EXISTS);
        }

        // 2. 检查库存数量是否足够
        if (sourceStock.getCurrentStock() < allocateReqVO.getAllocateCount()) {
            throw exception("库存数量不足");
        }

        // 3. 通过 SQL 查询目标场站名称
        String targetStationName = getStationNameById(allocateReqVO.getTargetStationId());
        String sourceStationName = sourceStock.getStationName(); // 从关联查询中获取
        String assetName = sourceStock.getAssetName(); // 从关联查询中获取

        // 4. 查询目标场站是否已有该资产的库存记录
        AssetStockDO targetStock = findStockByAssetAndStation(sourceStock.getAssetId(), allocateReqVO.getTargetStationId());

        // 5. 创建更新对象
        AssetStockDO sourceStockDO = new AssetStockDO();
        sourceStockDO.setId(sourceStock.getId());
        sourceStockDO.setCurrentStock(sourceStock.getCurrentStock() - allocateReqVO.getAllocateCount());

        // 【修改点】构建并追加调出记录到 reserve2
        String sourceRecord = String.format("[从%s调出至%s,数量:%d]",
                sourceStationName != null ? sourceStationName : "场站" + sourceStock.getStationId(),
                targetStationName != null ? targetStationName : "场站" + allocateReqVO.getTargetStationId(),
                allocateReqVO.getAllocateCount());
        sourceStockDO.setReserve2(appendRecord(sourceStock.getReserve2(), sourceRecord));
        assetStockMapper.updateById(sourceStockDO);

        // 6. 处理目标库存
        if (targetStock != null) {
            // 目标场站已存在该资产库存，增加库存数量
            targetStock.setCurrentStock(targetStock.getCurrentStock() + allocateReqVO.getAllocateCount());
            // 【修改点】构建并追加调入记录到 reserve2
            String targetRecord = String.format("[从%s(%s)调入,数量:%d]",
                    sourceStationName != null ? sourceStationName : "场站" + sourceStock.getStationId(),
                    assetName != null ? assetName : "资产" + sourceStock.getAssetId(),
                    allocateReqVO.getAllocateCount());
            targetStock.setReserve2(appendRecord(targetStock.getReserve2(), targetRecord));
            assetStockMapper.updateById(targetStock);
        } else {
            // 目标场站不存在该资产库存，创建新记录
            String newStockRecord = String.format("[从%s(%s)调入,数量:%d]",
                    sourceStationName != null ? sourceStationName : "场站" + sourceStock.getStationId(),
                    assetName != null ? assetName : "资产" + sourceStock.getAssetId(),
                    allocateReqVO.getAllocateCount());

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

        // 7. 设置日志上下文变量
        LogRecordContext.putVariable("allocateReqVO", allocateReqVO);
    }

    /**
     * 通过 SQL 查询场站名称
     */
    private String getStationNameById(Long stationId) {
        if (stationId == null) {
            return null;
        }

        // 通过原生SQL查询场站名称
        // 注意：这里假设 station_info 表在当前微服务的数据库中
        return assetStockMapper.selectStationNameById(stationId);
    }

    /**
     * 查询目标场站是否已有该资产的库存记录
     */
    private AssetStockDO findStockByAssetAndStation(Long assetId, Long stationId) {
        LambdaQueryWrapperX<AssetStockDO> queryWrapper = new LambdaQueryWrapperX<AssetStockDO>()
                .eq(AssetStockDO::getAssetId, assetId)
                .eq(AssetStockDO::getStationId, stationId);
        return assetStockMapper.selectOne(queryWrapper);
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
    @LogRecord(type = ASSET_STOCK_TYPE, subType = ASSET_STOCK_ALARM_SUB_TYPE,
            bizNo = "{{#alarmReqVO.id}}", success = ASSET_STOCK_ALARM_SUCCESS)
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

        // 5. 设置日志上下文变量
        LogRecordContext.putVariable("alarmReqVO", alarmReqVO);
        LogRecordContext.putVariable("status", status);

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