package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.all.GarbageCollectionCardAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.completed.GarbageCollectionCardCompletedVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.executing.GarbageCollectionCardExecutingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.card.pending.GarbageCollectionCardPendingVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.all.GarbageCollectionCircleAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.circle.completed.GarbageCollectionCircleCompletedVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.all.AreaCompletionRateColumnAllVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.completed.CollectionVolumeBarVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.column.pending.TimePeriodPendingColumnVO;
import cn.iocoder.yudao.module.envirhealth.util.statistics.StatisticsRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.completed.CompletionRateTrendVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.trend.executing.GarbageCollectionDailyTrendVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;
import cn.iocoder.yudao.module.envirhealth.util.garbagecollection.codegenerator.garbagecollection.GarbageCollectionCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.util.vo.NameValueVO;
import cn.iocoder.yudao.module.envirhealth.util.options.vo.OptionVO;
import jakarta.validation.ConstraintViolation;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import jakarta.validation.Validator;
import org.springframework.util.CollectionUtils;


import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageCollectionMapper;
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
    public Map<String, Integer> importGarbageCollection(List<GarbageCollectionSaveReqVO> importList) {
        Map<String, Integer> result = new HashMap<>();
        result.put("success", 0);
        result.put("fail", 0);

        if (CollectionUtils.isEmpty(importList)) {
            return result;
        }

        for (GarbageCollectionSaveReqVO saveReqVO : importList) {
            try {
                // 1. JSR380校验
                Set<ConstraintViolation<GarbageCollectionSaveReqVO>> violations = validator.validate(saveReqVO);
                if (!violations.isEmpty()) {
                    result.put("fail", result.get("fail") + 1);
                    continue;
                }

                // 2. 转换为DO并填充默认值
                GarbageCollectionDO garbageCollection = BeanUtils.toBean(saveReqVO, GarbageCollectionDO.class);

                // 自动生成业务主键和计划编号
                if (garbageCollection.getCollectionId() == null) {
                    garbageCollection.setCollectionId(codeGenerator.generateCollectionId());
                }
                if (garbageCollection.getPlanNo() == null) {
                    garbageCollection.setPlanNo(codeGenerator.generatePlanNo());
                }

                // 填充默认值
                if (garbageCollection.getPlanStatusId() == null) {
                    garbageCollection.setPlanStatusId("uuid-plan-status-001");
                }
                if (garbageCollection.getCompletionRate() == null) {
                    garbageCollection.setCompletionRate(BigDecimal.ZERO);
                }
                if (garbageCollection.getAbnormalCount() == null) {
                    garbageCollection.setAbnormalCount(0);
                }
                if (garbageCollection.getCreateTime() == null) {
                    garbageCollection.setCreateTime(LocalDateTime.now());
                }

                // JSON字段空值处理
                if (garbageCollection.getStaffIds() == null || garbageCollection.getStaffIds().trim().isEmpty()) {
                    garbageCollection.setStaffIds("[]");
                }
                if (garbageCollection.getPointIds() == null || garbageCollection.getPointIds().trim().isEmpty()) {
                    garbageCollection.setPointIds("[]");
                }

                // 3. 插入数据库
                garbageCollectionMapper.insert(garbageCollection);
                result.put("success", result.get("success") + 1);

            } catch (Exception e) {
                result.put("fail", result.get("fail") + 1);
            }
        }

        return result;
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
        planStatusCounts.put("待执行", 0);
        planStatusCounts.put("执行中", 0);
        planStatusCounts.put("已完成", 0);
        planStatusCounts.put("异常", 0); // 后续替换为异常表数据
        planStatusCounts.put("待复核", 0); // 后续替换为异常表数据

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

        // 3. 计算当前作业任务数（执行中 + 异常）
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
}