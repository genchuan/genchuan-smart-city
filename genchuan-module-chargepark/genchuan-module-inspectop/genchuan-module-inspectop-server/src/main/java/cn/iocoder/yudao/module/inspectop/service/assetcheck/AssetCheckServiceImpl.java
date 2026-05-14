package cn.iocoder.yudao.module.inspectop.service.assetcheck;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetcheck.AssetCheckDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.assetcheck.AssetCheckMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 资产盘点 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class AssetCheckServiceImpl implements AssetCheckService {

    @Resource
    private AssetCheckMapper assetCheckMapper;

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_CREATE_SUB_TYPE, bizNo = "{{#createReqVO.id}}",
            success = ASSET_CHECK_CREATE_SUCCESS)
    public Long createAssetCheck(AssetCheckSaveReqVO createReqVO) {
        // 插入
        AssetCheckDO assetCheck = BeanUtils.toBean(createReqVO, AssetCheckDO.class);
        assetCheckMapper.insert(assetCheck);

        // 返回
        return assetCheck.getId();
    }

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_UPDATE_SUB_TYPE, bizNo = "{{#updateReqVO.id}}",
            success = ASSET_CHECK_UPDATE_SUCCESS)
    public void updateAssetCheck(AssetCheckSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        AssetCheckDO oldAssetCheck = validateAssetCheckExists(updateReqVO.getId());
        // 2. 更新
        AssetCheckDO updateObj = BeanUtils.toBean(updateReqVO, AssetCheckDO.class);
        assetCheckMapper.updateById(updateObj);
        // 3. 记录操作日志上下文（用于DIFF比较）
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(oldAssetCheck, AssetCheckSaveReqVO.class));
    }

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_DELETE_SUB_TYPE, bizNo = "{{#id}}",
            success = ASSET_CHECK_DELETE_SUCCESS)
    public void deleteAssetCheck(Long id) {
        // 校验存在
        AssetCheckDO assetCheckDO = validateAssetCheckExists(id);
        // 删除
        assetCheckMapper.deleteById(id);

        // 记录操作日志上下文
        LogRecordContext.putVariable("id", assetCheckDO.getId());
    }

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_DELETE_LIST_SUB_TYPE,
            success = ASSET_CHECK_DELETE_LIST_SUCCESS, bizNo = "{{#id}}")
    public void deleteAssetCheckListByIds(List<Long> ids) {
        // 删除
        int i = assetCheckMapper.deleteByIds(ids);
        // 设置日志上下文变量，供成功消息模板使用
        LogRecordContext.putVariable("ids", ids);
    }


    private AssetCheckDO validateAssetCheckExists(Long id) {
        AssetCheckDO assetCheck = assetCheckMapper.selectById(id);
        if (assetCheck == null) {
            throw exception(ASSET_CHECK_NOT_EXISTS);
        }
        return assetCheck; // 返回查询到的对象
    }

    @Override
    public AssetCheckDO getAssetCheck(Long id) {
        return assetCheckMapper.selectById(id);
    }

    @Override
    public PageResult<AssetCheckDO> getAssetCheckPage(AssetCheckPageReqVO pageReqVO) {
        return assetCheckMapper.selectPage(pageReqVO);
    }

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_CREATE_SIMPLE_SUB_TYPE, bizNo = "{{#createReqVO.id}}",
            success = ASSET_CHECK_CREATE_SIMPLE_SUCCESS)
    public Long createAssetCheck(AssetCheckCreateReqVO createReqVO) {
        // 1. 创建AssetCheckDO对象
        AssetCheckDO assetCheck = new AssetCheckDO();

        // 2. 设置从VO中获取的字段
        assetCheck.setType(createReqVO.getType());           // 盘点类型
        assetCheck.setCheckTime(createReqVO.getCheckTime()); // 盘点时间

        // 3. 设置默认值
        assetCheck.setStatus("1");  // 默认状态：待盘点
        assetCheck.setProgress(createReqVO.getProgress());  // 初始进度：0%

        // 5. 插入数据库
        assetCheckMapper.insert(assetCheck);

        // 6. 返回新生成的主键ID
        return assetCheck.getId();
    }

    @Override
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_EXECUTE_SUB_TYPE, bizNo = "{{#executeReqVO.id}}",
            success = ASSET_CHECK_EXECUTE_SUCCESS)
    public void executeAssetCheck(AssetCheckExecuteReqVO executeReqVO) {
        // 1. 校验资产盘点是否存在
        Long id = executeReqVO.getId();
        AssetCheckDO assetCheck = assetCheckMapper.selectById(id);
        if (assetCheck == null) {
            throw exception(ASSET_CHECK_NOT_EXISTS);
        }

        // 2. 创建更新对象
        AssetCheckDO updateObj = new AssetCheckDO();
        updateObj.setId(id);
        updateObj.setStatus("2"); // 更新状态为2

        // 3. 执行更新
        assetCheckMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_UPDATE_PROGRESS_SUB_TYPE, bizNo = "{{#updateProgressReqVO.id}}",
            success = ASSET_CHECK_UPDATE_PROGRESS_SUCCESS)
    public void updateAssetCheckProgress(AssetCheckUpdateProgressReqVO updateProgressReqVO) {
        // 1. 校验资产盘点是否存在
        Long id = updateProgressReqVO.getId();
        AssetCheckDO assetCheck = assetCheckMapper.selectById(id);
        if (assetCheck == null) {
            throw exception(ASSET_CHECK_NOT_EXISTS);
        }

        // 2. 创建更新对象
        AssetCheckDO updateObj = new AssetCheckDO();
        updateObj.setId(id);
        updateObj.setProgress(updateProgressReqVO.getProgress());

        // 3. 可选的业务逻辑：根据进度自动更新状态
        // 例如：当进度达到100%时，自动将状态更新为已完成
        Integer progress = updateProgressReqVO.getProgress();
        if (progress != null && progress == 100) {
            updateObj.setStatus("3");
        }

        // 4. 执行更新
        assetCheckMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogRecord(type = ASSET_CHECK_TYPE, subType = ASSET_CHECK_CONFIRM_SUB_TYPE, bizNo = "{{#confirmReqVO.id}}",
            success = ASSET_CHECK_CONFIRM_SUCCESS)
    public void confirmAssetCheck(AssetCheckConfirmReqVO confirmReqVO) {
        // 1. 校验资产盘点是否存在
        Long id = confirmReqVO.getId();
        AssetCheckDO assetCheck = assetCheckMapper.selectById(id);
        if (assetCheck == null) {
            throw exception(ASSET_CHECK_NOT_EXISTS);
        }

        // 2. 可选的业务逻辑：确认前检查进度是否为100%
        // 如果业务要求确认前必须完成盘点（进度为100%），可以添加以下检查
         if (assetCheck.getProgress() == null || assetCheck.getProgress() < 100) {
             throw exception("进度是否为100%");
         }

        // 3. 可选的业务逻辑：检查当前状态是否可以确认
        // 如果只有特定状态才能确认，可以添加以下检查
         if (!"3".equals(assetCheck.getStatus())) { // 只有状态为3（已执行）的可以确认
             throw exception("只有特定状态才能确认");
         }

         Long userId = SecurityFrameworkUtils.getLoginUserId();

        // 4. 创建更新对象
        AssetCheckDO updateObj = new AssetCheckDO();
        updateObj.setId(id);
        updateObj.setConfirmUserId(userId);
        updateObj.setStatus("3"); // 更新状态为3（已确认）

        // 5. 可选：设置确认时间（如果需要的话）
         updateObj.setConfirmTime(LocalDateTime.now());
        // 注意：如果confirmTime字段是数据库自动生成的，这里可以不设置

        // 6. 执行更新
        assetCheckMapper.updateById(updateObj);
    }

    @Override
    public AssetCheckChartRespVO getAssetCheckChart(AssetCheckChartReqVO chartReqVO) {
        // 1. 获取折线图趋势数据
        List<AssetCheckMapper.TrendData> mapperTrendData =
                assetCheckMapper.selectTrendData(chartReqVO);

        // 2. 转换为响应VO的TrendData列表
        List<AssetCheckChartRespVO.TrendData> trendDataList = convertToTrendDataList(mapperTrendData);

        // 3. 获取卡片统计数据
        AssetCheckMapper.CardData mapperCardData =
                assetCheckMapper.selectCardData(chartReqVO);

        // 4. 转换为响应VO的CardData
        AssetCheckChartRespVO.CardData cardData = convertToCardData(mapperCardData);

        // 5. 构建并返回响应VO
        return AssetCheckChartRespVO.builder()
                .trendData(trendDataList)
                .cardData(cardData)
                .build();
    }

    /**
     * 转换Mapper的TrendData为响应VO的TrendData
     */
    private List<AssetCheckChartRespVO.TrendData> convertToTrendDataList(
            List<AssetCheckMapper.TrendData> mapperData) {
        if (mapperData == null || mapperData.isEmpty()) {
            return Collections.emptyList();
        }

        return mapperData.stream()
                .map(item -> AssetCheckChartRespVO.TrendData.builder()
                        .time(item.getTime())
                        .progress(item.getProgress())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * 转换Mapper的CardData为响应VO的CardData
     */
    private AssetCheckChartRespVO.CardData convertToCardData(
            AssetCheckMapper.CardData mapperData) {
        if (mapperData == null) {
            return AssetCheckChartRespVO.CardData.builder()
                    .checkCount(0)
                    .checkFinishRate(0.0)
                    .build();
        }

        return AssetCheckChartRespVO.CardData.builder()
                .checkCount(mapperData.getCheckCount() != null ? mapperData.getCheckCount() : 0)
                .checkFinishRate(mapperData.getCheckFinishRate() != null ? mapperData.getCheckFinishRate() : 0.0)
                .build();
    }



}