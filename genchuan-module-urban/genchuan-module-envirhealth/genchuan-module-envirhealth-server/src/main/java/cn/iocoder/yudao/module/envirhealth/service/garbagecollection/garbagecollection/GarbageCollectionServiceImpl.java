package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.importer.vo.ImportRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageCollectionMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagecollection.GarbageCollectionCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.NameValueVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.StatisticsRespVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 收运计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class GarbageCollectionServiceImpl implements GarbageCollectionService {

    @Resource
    private GarbageCollectionMapper garbageCollectionMapper;
    @Resource
    private GarbageCollectionCodeGenerator codeGenerator;
    @Resource
    private GarbageAbnormalMapper garbageAbnormalMapper;
    @Resource
    private Validator validator;

    @Override
    public Long createGarbageCollection(GarbageCollectionSaveReqVO createReqVO) {
        // 插入
        GarbageCollectionDO garbageCollection = BeanUtils.toBean(createReqVO, GarbageCollectionDO.class);

        garbageCollection.setId(null);
        garbageCollection.setPlanNo(codeGenerator.generatePlanNo());
        garbageCollection.setCollectionId(codeGenerator.generateCollectionId());

        garbageCollectionMapper.insert(garbageCollection);
        // 返回
        return garbageCollection.getId();
    }

    @Override
    public void updateGarbageCollection(GarbageCollectionSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageCollectionExists(updateReqVO.getId());
        // 更新
        GarbageCollectionDO updateObj = BeanUtils.toBean(updateReqVO, GarbageCollectionDO.class);
        garbageCollectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageCollection(Long id) {
        // 校验存在
        validateGarbageCollectionExists(id);
        // 删除
        garbageCollectionMapper.deleteById(id);
    }

    private void validateGarbageCollectionExists(Long id) {
        if (garbageCollectionMapper.selectById(id) == null) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }
    }

    @Override
    public void deleteGarbageCollectionBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        // 校验所有计划是否存在
        List<GarbageCollectionDO> garbageCollections = garbageCollectionMapper.selectBatchIds(ids);
        if (garbageCollections.size() != ids.size()) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }

        // 批量删除
        garbageCollectionMapper.deleteBatchIds(ids);
    }

    @Override
    public GarbageCollectionDO getGarbageCollection(Long id) {
        return garbageCollectionMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageCollectionDO> getGarbageCollectionPage(GarbageCollectionPageReqVO pageReqVO) {
        return garbageCollectionMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageCollectionDetailDO> getGarbageCollectionDetailPage(GarbageCollectionPageReqVO pageReqVO) {
        Long total = garbageCollectionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageCollectionDetailDO> list = garbageCollectionMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public GarbageCollectionCardAllVO getGarbageCollectionCardAll() {
        return garbageCollectionMapper.selectCardAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getGarbageTypeCircleAll() {
        return garbageCollectionMapper.selectGarbageTypeCircleAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPlanStatusCircleAll() {
        return garbageCollectionMapper.selectPlanStatusCircleAll();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getAreaDistributionCircleAll() {
        return garbageCollectionMapper.selectAreaDistributionCircleAll();
    }

    @Override
    public List<AreaCompletionRateColumnAllVO> getAreaCompletionRateColumnAll() {
        return garbageCollectionMapper.selectAreaCompletionRateColumnAll();
    }

    @Override
    public GarbageCollectionCardPendingVO getGarbageCollectionCardPending() {
        GarbageCollectionCardPendingVO vo = new GarbageCollectionCardPendingVO();

        // 1. 查询待执行计划总数
        Long totalPendingCount = garbageCollectionMapper.selectPendingTotalCount();
        vo.setTotalPendingCount(totalPendingCount == null ? 0L : totalPendingCount);

        // 2. 按区域统计待执行数量（转换为List<NameValueVO>：区域名称 -> 数量）
        List<Map<String, Object>> areaList = garbageCollectionMapper.selectPendingCountByArea();
        List<NameValueVO> areaPendingList = areaList.stream()
                .map(item -> new NameValueVO(
                        item.get("areaName").toString(),
                        Long.valueOf(item.get("count").toString())
                ))
                .collect(Collectors.toList());
        vo.setAreaPendingCountMap(areaPendingList); // 赋值给List类型字段

        // 3. 按品类统计待执行数量（转换为List<NameValueVO>：品类名称 -> 数量）
        List<Map<String, Object>> garbageTypeList = garbageCollectionMapper.selectPendingCountByGarbageType();
        List<NameValueVO> garbageTypePendingList = garbageTypeList.stream()
                .map(item -> new NameValueVO(
                        item.get("garbageTypeName").toString(),
                        Long.valueOf(item.get("count").toString())
                ))
                .collect(Collectors.toList());
        vo.setGarbageTypePendingCountMap(garbageTypePendingList); // 赋值给List类型字段

        return vo;
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByArea() {
        return garbageCollectionMapper.selectPendingByArea();
    }

    @Override
    public List<GarbageCollectionCircleAllVO> getPendingGarbageCollectionByGarbageType() {
        return garbageCollectionMapper.selectPendingByGarbageType();
    }

    @Override
    public List<TimePeriodPendingColumnVO> getTimePeriodPendingColumn() {
        return garbageCollectionMapper.selectTimePeriodPendingColumn();
    }

    @Override
    public StatisticsRespVO getGarbageCollectionStatistics() {
        StatisticsRespVO respVO = new StatisticsRespVO();

        // 1. 查询总计划数
        Long totalCount = garbageCollectionMapper.selectTotalCount();
        respVO.setTotal(totalCount == null ? 0 : totalCount.intValue());

        // 2. 查询垃圾收集计划各状态统计（待执行/执行中/已完成）
        List<Map<String, Object>> statusStats = garbageCollectionMapper.selectStatisticsByPlanStatus();

        // 3. 转换为Map格式并初始化所有状态为0
        Map<String, Integer> planStatusCounts = new LinkedHashMap<>();
        planStatusCounts.put("未开始", 0);
        planStatusCounts.put("进行中", 0);
        planStatusCounts.put("已完成", 0);
        planStatusCounts.put("异常", 0);
        planStatusCounts.put("待复核", 0);

        // 4. 填充垃圾收集计划的状态数据（待执行/执行中/已完成）
        for (Map<String, Object> stat : statusStats) {
            String statusName = (String) stat.get("status_name");
            Long count = (Long) stat.get("count");
            // 只填充待执行/执行中/已完成，跳过异常相关状态
            if (planStatusCounts.containsKey(statusName)
                    && !"异常".equals(statusName)
                    && !"待复核".equals(statusName)) {
                planStatusCounts.put(statusName, count.intValue());
            }
        }

        // 5. 查询异常表数据，填充异常待处置、处置待复核
        // 异常待处置：handle_status = 待处置
        Long handlePendingCount = garbageAbnormalMapper.countHandlePending();
        planStatusCounts.put("异常", handlePendingCount == null ? 0 : handlePendingCount.intValue());

        // 处置待复核：handle_status = 待复核
        Long reviewPendingCount = garbageAbnormalMapper.countReviewPending();
        planStatusCounts.put("待复核", reviewPendingCount == null ? 0 : reviewPendingCount.intValue());

        respVO.setPlanStatusCounts(planStatusCounts);
        return respVO;
    }

    @Override
    public GarbageCollectionCardExecutingVO getGarbageCollectionCardExecuting() {
        // 1. 查询统计数据
        Map<String, Object> stats = garbageCollectionMapper.selectCardExecuting();

        // 2. 提取数据
        Long normalRunningCount = stats.get("normal_running_count") == null ? 0L :
                ((Number) stats.get("normal_running_count")).longValue();
        Long abnormalCount = stats.get("abnormal_count") == null ? 0L :
                ((Number) stats.get("abnormal_count")).longValue();

        // 3. 计算当前作业任务数
        Long currentTaskCount = normalRunningCount + abnormalCount;

        // 4. 构建返回对象
        return GarbageCollectionCardExecutingVO.builder()
                .currentTaskCount(currentTaskCount.intValue())
                .normalRunningCount(normalRunningCount.intValue())
                .abnormalCount(abnormalCount.intValue())
                .build();
    }

    @Override
    public List<GarbageCollectionDailyTrendVO> getDailyCollectionVolumeTrend() {
        return garbageCollectionMapper.selectDailyCollectionVolumeTrend();
    }

    @Override
    public GarbageCollectionCardCompletedVO getGarbageCollectionCardCompleted() {
        // 1. 查询统计数据
        Map<String, Object> stats = garbageCollectionMapper.selectCardCompleted();

        // 2. 提取数据并处理空值
        Long completedTaskCount = stats.get("completed_task_count") == null ? 0L :
                ((Number) stats.get("completed_task_count")).longValue();

        BigDecimal totalCollectedVolume = stats.get("total_collected_volume") == null ?
                BigDecimal.ZERO : new BigDecimal(stats.get("total_collected_volume").toString());

        BigDecimal averageCompletionRate = stats.get("average_completion_rate") == null ?
                BigDecimal.ZERO : new BigDecimal(stats.get("average_completion_rate").toString());

        BigDecimal averageAbnormalCompleteRate = stats.get("average_abnormal_complete_rate") == null ?
                BigDecimal.ZERO : new BigDecimal(stats.get("average_abnormal_complete_rate").toString());

        // 3. 构建返回对象
        return GarbageCollectionCardCompletedVO.builder()
                .completedTaskCount(completedTaskCount.intValue())
                .totalCollectedVolume(totalCollectedVolume.setScale(2, BigDecimal.ROUND_HALF_UP))
                .averageCompletionRate(averageCompletionRate.setScale(2, BigDecimal.ROUND_HALF_UP))
                .abnormalCompleteRate(averageAbnormalCompleteRate.setScale(2, BigDecimal.ROUND_HALF_UP))
                .build();
    }

    @Override
    public List<CompletionRateTrendVO> getCompletionRateTrend(LocalDateTime startTime, LocalDateTime endTime) {
        // 参数校验
        if (startTime == null || endTime == null) {
            throw exception(COLLECTION_STATISTICS_TIME_REQUIRED);
        }

        List<CompletionRateTrendVO> result = garbageCollectionMapper.selectCompletionRateTrend(startTime, endTime);

        // 处理空结果集
        if (CollectionUtils.isEmpty(result)) {
            return new ArrayList<>();
        }

        // 格式化完成率（保留两位小数）
        result.forEach(item -> {
            if (item.getCompletionRate() != null) {
                item.setCompletionRate(item.getCompletionRate()
                        .setScale(2, BigDecimal.ROUND_HALF_UP));
            } else {
                item.setCompletionRate(BigDecimal.ZERO);
            }
        });

        return result;
    }

    @Override
    public List<GarbageCollectionCircleCompletedVO> getCompletedVolumeByArea() {
        return garbageCollectionMapper.selectCompletedVolumeByArea();
    }

    @Override
    public List<GarbageCollectionCircleCompletedVO> getCompletedVolumeByGarbageType() {
        return garbageCollectionMapper.selectCompletedVolumeByGarbageType();
    }

    @Override
    public Map<String, List<CollectionVolumeBarVO>> getCollectionVolumeComparison() {
        Map<String, List<CollectionVolumeBarVO>> result = new LinkedHashMap<>();

        // 1. 查询当日数据（按小时）
        List<CollectionVolumeBarVO> todayData = garbageCollectionMapper.selectTodayCollectionVolume();
        result.put("today", formatVolumeData(todayData));

        // 2. 查询当周数据（按天）
        List<CollectionVolumeBarVO> weekData = garbageCollectionMapper.selectWeeklyCollectionVolume();
        result.put("week", formatVolumeData(weekData));

        // 3. 查询当月数据（按天）
        List<CollectionVolumeBarVO> monthData = garbageCollectionMapper.selectMonthlyCollectionVolume();
        result.put("month", formatVolumeData(monthData));

        return result;
    }

    /**
     * 格式化收运量数据
     */
    private List<CollectionVolumeBarVO> formatVolumeData(List<CollectionVolumeBarVO> data) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }

        return data.stream()
                .map(item -> {
                    if (item.getCollectedVolume() != null) {
                        item.setCollectedVolume(item.getCollectedVolume()
                                .setScale(2, BigDecimal.ROUND_HALF_UP));
                    } else {
                        item.setCollectedVolume(BigDecimal.ZERO);
                    }
                    return item;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OptionVO> getExecutingOptions() {

        List<GarbageCollectionDO> list;
        list = garbageCollectionMapper.selectList(
                new LambdaQueryWrapperX<GarbageCollectionDO>()
                        .eq(GarbageCollectionDO::getDeleted, 0)
                        .eq(GarbageCollectionDO::getPlanStatusId, "uuid-plan-status-002")
                        .orderByDesc(GarbageCollectionDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList(list, garbageCollectionDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(garbageCollectionDO.getPlanStatusId());
            vo.setValue(garbageCollectionDO.getPlanNo());
            return vo;
        });
    }

    @Override
    public ImportRespVO<GarbageCollectionSaveReqVO> batchImport(List<GarbageCollectionSaveReqVO> importList) {
        if (CollectionUtils.isEmpty(importList)) {
            return ImportRespVO.empty();
        }

        List<GarbageCollectionSaveReqVO> successList = new ArrayList<>();
        List<ImportRespVO.ImportErrorItem> errorList = new ArrayList<>();

        int rowIndex = 1; // Excel行号从2开始

        for (GarbageCollectionSaveReqVO importVO : importList) {
            rowIndex++;
            Map<String, Object> rawData = new LinkedHashMap<>();
            rawData.put("collectionId", importVO.getCollectionId());
            rawData.put("planNo", importVO.getPlanNo());
            rawData.put("areaCode", importVO.getAreaCode());

            try {
                // 校验逻辑...
                Set<ConstraintViolation<GarbageCollectionSaveReqVO>> violations = validator.validate(importVO);
                if (!violations.isEmpty()) {
                    String errorMsg = violations.stream()
                            .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                            .collect(Collectors.joining("; "));
                    errorList.add(new ImportRespVO.ImportErrorItem(rowIndex, errorMsg, rawData));
                    continue;
                }

                // 保存逻辑
                GarbageCollectionDO garbageCollection = BeanUtils.toBean(importVO, GarbageCollectionDO.class);

                // ===== 强制覆盖：始终使用系统生成的业务主键 =====
                garbageCollection.setCollectionId(codeGenerator.generateCollectionId());
                garbageCollection.setPlanNo(codeGenerator.generatePlanNo());

                // id是自增的，不需要设置
                garbageCollection.setId(null);

                garbageCollectionMapper.insert(garbageCollection);

                // 设置返回的id
                importVO.setId(garbageCollection.getId());
                // 同时更新返回VO中的业务主键为系统生成的
                importVO.setCollectionId(garbageCollection.getCollectionId());
                importVO.setPlanNo(garbageCollection.getPlanNo());

                successList.add(importVO);

            } catch (Exception e) {
                log.error("批量导入失败，行号: {}, 数据: {}", rowIndex, importVO, e);
                errorList.add(new ImportRespVO.ImportErrorItem(
                        rowIndex,
                        e.getMessage() != null ? e.getMessage() : "导入失败",
                        rawData
                ));
            }
        }

        log.info("批量导入完成，总记录数：{}，成功：{}，失败：{}",
                importList.size(), successList.size(), errorList.size());

        return new ImportRespVO<>(
                successList,
                errorList,
                null,
                null,
                successList.size(),
                errorList.size(),
                importList.size()
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateGarbageCollection(GarbageCollectionBatchUpdateReqVO reqVO) {
        // 1. 参数校验
        List<Long> ids = reqVO.getIds();
        Map<String, Object> updateFields = reqVO.getUpdateFields();

        if (ids == null || ids.isEmpty()) {
            throw exception(GARBAGE_COLLECTION_IDS_NOT_EMPTY);
        }
        if (updateFields == null || updateFields.isEmpty()) {
            throw exception(UPDATE_FIELDS_NOT_EMPTY);
        }

        // 2. 校验计划是否存在
        List<GarbageCollectionDO> existingPlans = garbageCollectionMapper.selectBatchIds(ids);
        if (existingPlans.size() != ids.size()) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }

        // 3. 构建更新条件
        LambdaUpdateWrapper<GarbageCollectionDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(GarbageCollectionDO::getId, ids);

        // 4. 动态设置要更新的字段
        updateFields.forEach((field, value) -> {
            if (value == null) {
                return; // 跳过null值
            }

            switch (field) {
                case "timePeriod":
                    validateStringField(field, value);
                    updateWrapper.set(GarbageCollectionDO::getTimePeriod, value.toString());
                    break;

                case "frequency":
                    validateStringField(field, value);
                    updateWrapper.set(GarbageCollectionDO::getFrequency, value.toString());
                    break;

                case "vehicleId":
                    validateStringField(field, value);
                    updateWrapper.set(GarbageCollectionDO::getVehicleId, value.toString());
                    break;

                case "staffIds":
                    // 处理负责人员IDs（JSON格式）
                    String staffIdsJson = convertToJsonArray(value);
                    updateWrapper.set(GarbageCollectionDO::getStaffIds, staffIdsJson);
                    break;

                case "pointIds":
                    // 处理收运点位IDs（JSON格式）
                    String pointIdsJson = convertToJsonArray(value);
                    updateWrapper.set(GarbageCollectionDO::getPointIds, pointIdsJson);
                    break;

                case "planStatusId":
                    validateStringField(field, value);
                    updateWrapper.set(GarbageCollectionDO::getPlanStatusId, value.toString());
                    break;

                case "completionRate":
                    if (value instanceof Number) {
                        updateWrapper.set(GarbageCollectionDO::getCompletionRate,
                                BigDecimal.valueOf(((Number) value).doubleValue()));
                    } else {
                        updateWrapper.set(GarbageCollectionDO::getCompletionRate,
                                new BigDecimal(value.toString()));
                    }
                    break;

                case "progress":
                    if (value instanceof Number) {
                        updateWrapper.set(GarbageCollectionDO::getProgress,
                                ((Number) value).intValue());
                    } else {
                        updateWrapper.set(GarbageCollectionDO::getProgress,
                                Integer.parseInt(value.toString()));
                    }
                    break;

                case "checkinStatus":
                    validateStringField(field, value);
                    updateWrapper.set(GarbageCollectionDO::getCheckinStatus, value.toString());
                    break;

                case "isAbnormal":
                    if (value instanceof Boolean) {
                        updateWrapper.set(GarbageCollectionDO::getIsAbnormal, (Boolean) value);
                    } else {
                        updateWrapper.set(GarbageCollectionDO::getIsAbnormal,
                                Boolean.parseBoolean(value.toString()));
                    }
                    break;

                default:
                    log.warn("不支持的更新字段: {}", field);
                    throw exception(UNSUPPORTED_FIELD, field);
            }
        });

        // 5. 添加更新时间
        updateWrapper.set(GarbageCollectionDO::getUpdateTime, LocalDateTime.now());

        // 6. 执行批量更新
        int updateCount = garbageCollectionMapper.update(updateWrapper);

        // 7. 记录操作日志
        log.info("批量更新收运计划完成，IDs: {}, 更新字段: {}, 影响行数: {}",
                ids, updateFields.keySet(), updateCount);
    }

    /**
     * 校验字符串字段
     */
    private void validateStringField(String field, Object value) {
        if (value == null) {
            throw exception(FIELD_CANNOT_NULL, field);
        }
    }

    /**
     * 转换为JSON数组格式
     */
    private String convertToJsonArray(Object value) {
        if (value == null) {
            return "[]";
        }

        try {
            if (value instanceof List) {
                // 如果是List，直接转JSON
                return JSON.toJSONString(value);
            } else if (value instanceof String) {
                String strValue = value.toString().trim();
                // 如果已经是JSON数组格式，直接返回
                if (strValue.startsWith("[") && strValue.endsWith("]")) {
                    return strValue;
                }
                // 如果是单个ID，转换为数组
                return String.format("[\"%s\"]", strValue);
            } else {
                // 其他类型，转换为字符串数组
                return String.format("[\"%s\"]", value.toString());
            }
        } catch (Exception e) {
            log.error("转换为JSON数组失败", e);
            return "[]";
        }
    }

    @Override
    public void updatePlanStatus(String collectionId, String planStatusId) {
        // 1. 校验收运计划是否存在
        GarbageCollectionDO collection = getCollectionByCollectionId(collectionId);
        if (collection == null) {
            throw exception(COLLECTION_NOT_EXISTS);
        }

        // 2. 更新计划状态
        GarbageCollectionDO updateObj = new GarbageCollectionDO();
        updateObj.setId(collection.getId());
        updateObj.setPlanStatusId(planStatusId);
        garbageCollectionMapper.updateById(updateObj);
    }

    @Override
    public GarbageCollectionDO getCollectionByCollectionId(String collectionId) {
        return garbageCollectionMapper.selectByCollectionId(collectionId);
    }
}