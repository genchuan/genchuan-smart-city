package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.HourlyCompletionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning.RoadCleaningMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.roadcleaning.RoadCleaningCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.CompletionRatePointVO;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.ROAD_CLEANING_BATCH_ADJUST_DIMENSION_INVALID;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.ROAD_CLEANING_NOT_EXISTS;

/**
 * 道路清扫计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class RoadCleaningServiceImpl implements RoadCleaningService {

    @Resource
    private RoadCleaningMapper roadCleaningMapper;

    @Resource
    private RoadCleaningCodeGenerator codeGenerator;

    @Override
    public Long createRoadCleaning(RoadCleaningSaveReqVO createReqVO) {
        if (createReqVO.getStaffIds() == null || createReqVO.getStaffIds().trim().isEmpty()) {
            createReqVO.setStaffIds("[]");
        }
        if (createReqVO.getToolIds() == null || createReqVO.getToolIds().trim().isEmpty()) {
            createReqVO.setToolIds("[]");
        }
        if (createReqVO.getCheckPhotoUrl() == null || createReqVO.getCheckPhotoUrl().trim().isEmpty()) {
            createReqVO.setCheckPhotoUrl("[]");
        }
        // 插入
        RoadCleaningDO roadCleaning = BeanUtils.toBean(createReqVO, RoadCleaningDO.class);

        //自动生成编号
        roadCleaning.setPlanNo(codeGenerator.generatePlanNo());
        roadCleaning.setCleaningId(codeGenerator.generateCleaningId());

        roadCleaningMapper.insert(roadCleaning);
        // 返回
        return roadCleaning.getId();
    }

    @Override
    public void updateRoadCleaning(RoadCleaningSaveReqVO updateReqVO) {
        // 校验存在
        validateRoadCleaningExists(updateReqVO.getId());
        if (updateReqVO.getStaffIds() == null || updateReqVO.getStaffIds().trim().isEmpty()) {
            updateReqVO.setStaffIds("[]");
        }
        if (updateReqVO.getToolIds() == null || updateReqVO.getToolIds().trim().isEmpty()) {
            updateReqVO.setToolIds("[]");
        }
        if (updateReqVO.getCheckPhotoUrl() == null || updateReqVO.getCheckPhotoUrl().trim().isEmpty()) {
            updateReqVO.setCheckPhotoUrl("[]");
        }
        // 更新
        RoadCleaningDO updateObj = BeanUtils.toBean(updateReqVO, RoadCleaningDO.class);
        roadCleaningMapper.updateById(updateObj);
    }

    @Override
    public void deleteRoadCleaning(Long id) {
        // 校验存在
        validateRoadCleaningExists(id);
        // 删除
        roadCleaningMapper.deleteById(id);
    }

    @Override
    public void deleteRoadCleaningBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        List<RoadCleaningDO> roadCleanings = roadCleaningMapper.selectBatchIds(ids);
        if (roadCleanings.size() != ids.size()) {
            throw exception(ROAD_CLEANING_NOT_EXISTS);
        }

        roadCleaningMapper.deleteBatchIds(ids);
    }

    private void validateRoadCleaningExists(Long id) {
        if (roadCleaningMapper.selectById(id) == null) {
            throw exception(ROAD_CLEANING_NOT_EXISTS);
        }
    }

    @Override
    public RoadCleaningDO getRoadCleaning(Long id) {
        return roadCleaningMapper.selectById(id);
    }

    @Override
    public PageResult<RoadCleaningDO> getRoadCleaningPage(RoadCleaningPageReqVO pageReqVO) {
        return roadCleaningMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RoadCleaningDetailDO> getRoadCleaningDetailPage(RoadCleaningPageReqVO pageReqVO) {
        Long total = roadCleaningMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<RoadCleaningDetailDO> list = roadCleaningMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public List<OptionVO> getRoadCleaningNameOptions() {

        List<RoadCleaningDO> list;
        list = roadCleaningMapper.selectList(
                new LambdaQueryWrapperX<RoadCleaningDO>()
                        .eq(RoadCleaningDO::getDeleted, 0)
                        .orderByDesc(RoadCleaningDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList(list, roadCleaningDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(roadCleaningDO.getPlanNo());
            vo.setValue(roadCleaningDO.getCleaningId());
            return vo;
        });
    }

    @Override
    public void batchAdjustRoadCleaning(RoadCleaningBatchAdjustReqVO adjustReqVO) {

        List<Long> cleaningIds = adjustReqVO.getIds();
        if (CollectionUtils.isEmpty(cleaningIds)) {
            return;
        }

        List<RoadCleaningDO> roadCleanings = roadCleaningMapper.selectList(
                new LambdaQueryWrapperX<RoadCleaningDO>()
                        .in(RoadCleaningDO::getId, cleaningIds)
                        .eq(RoadCleaningDO::getDeleted, 0)
        );

        if (roadCleanings.size() != cleaningIds.size()) {
            throw exception(ROAD_CLEANING_NOT_EXISTS);
        }

        String dimension = adjustReqVO.getAdjustDimension();
        String adjustValue = adjustReqVO.getAdjustValue();

        String reviewerId = String.valueOf(cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId());
        LocalDateTime now = LocalDateTime.now();

        for (RoadCleaningDO roadCleaning : roadCleanings) {
            boolean needUpdate = false;

            switch (dimension) {
                case "time_period":
                    if (!adjustValue.equals(roadCleaning.getTimePeriod())) {
                        roadCleaning.setTimePeriod(adjustValue);
                        needUpdate = true;
                    }
                    break;
                case "frequency":
                    if (!adjustValue.equals(roadCleaning.getFrequency())) {
                        roadCleaning.setFrequency(adjustValue);
                        needUpdate = true;
                    }
                    break;
                case "staff":
                    if (!adjustValue.equals(roadCleaning.getStaffIds())) {
                        roadCleaning.setStaffIds(adjustValue);
                        needUpdate = true;
                    }
                    break;

                case "review_status":
                    // adjustValue: 达标/不达标
                    if (!adjustValue.equals(roadCleaning.getReviewStatus())
                            || roadCleaning.getReviewTime() == null
                            || !reviewerId.equals(roadCleaning.getReviewBy())) {

                        roadCleaning.setReviewStatus(adjustValue);
                        roadCleaning.setReviewBy(reviewerId);
                        roadCleaning.setReviewTime(now);

                        // 复核意见 -> reformRequire
                        if (adjustReqVO.getAdjustRemark() != null) {
                            roadCleaning.setReformRequire(adjustReqVO.getAdjustRemark());
                        }
                        needUpdate = true;
                    }
                    break;

                default:
                    throw exception(ROAD_CLEANING_BATCH_ADJUST_DIMENSION_INVALID);
            }

            if (needUpdate) {
                roadCleaningMapper.updateById(roadCleaning);
            }
        }
    }

    @Override
    public RoadCleaningAllRespVO getAll() {
        RoadCleaningAllRespVO resp = new RoadCleaningAllRespVO();
        resp.setTotalPlanCount(roadCleaningMapper.countTotalPlan());
        resp.setExecutingPlanCount(roadCleaningMapper.countExecutingPlan());
        resp.setQualityQualifiedCount(roadCleaningMapper.countQualityQualified());
        resp.setFullAttendanceStaffCount(countFullAttendanceStaff());
        resp.setPlanStatusDistribution(roadCleaningMapper.selectPlanStatusPie());
        resp.setRoadSectionTypeDistribution(roadCleaningMapper.selectRoadSectionTypePie());
        resp.setQualityQualifiedRateByArea(roadCleaningMapper.selectQualityQualifiedRateByArea());
        return resp;
    }

    private Long countFullAttendanceStaff() {
        List<String> rows = roadCleaningMapper.selectExecutingStaffIdsJson();
        java.util.Set<String> set = new java.util.HashSet<>();
        for (String json : rows) {
            if (json == null || json.trim().isEmpty() || "null".equalsIgnoreCase(json.trim())) continue;
            try {
                List<String> ids = com.alibaba.fastjson.JSON.parseArray(json, String.class);
                if (ids == null) continue;
                for (String id : ids) {
                    if (id != null && !id.trim().isEmpty()) set.add(id.trim());
                }
            } catch (Exception ignore) {}
        }
        return (long) set.size();
    }

    @Override
    public RoadCleaningPendingRespVO getPendingData() {
        RoadCleaningPendingRespVO resp = new RoadCleaningPendingRespVO();

        // 1. 待执行计划总数
        resp.setPendingPlanCount(roadCleaningMapper.countPendingPlan());

        // 2. 待执行涉及区域数
        resp.setPendingAreaCount(roadCleaningMapper.countPendingArea());

        // 3. 待执行涉及人员数（去重）
        resp.setPendingStaffCount(countPendingStaff());

        // 4. 清扫频次分布
        resp.setFrequencyDistribution(roadCleaningMapper.selectPendingFrequencyDistribution());

        // 5. 路段类型占比
        resp.setRoadSectionTypeDistribution(roadCleaningMapper.selectPendingRoadSectionTypeDistribution());

        // 6. 不同时段清扫计划数量对比
        resp.setPlanCountByTimePeriod(roadCleaningMapper.selectPendingPlanCountByTimePeriod());

        return resp;
    }

    /**
     * 计算待执行计划涉及的去重人员数
     */
    private Long countPendingStaff() {
        List<String> rows = roadCleaningMapper.selectPendingStaffIds();
        Set<String> staffSet = new HashSet<>();

        for (String json : rows) {
            if (json == null || json.trim().isEmpty() || "null".equalsIgnoreCase(json.trim()) || "[]".equals(json.trim())) {
                continue;
            }
            try {
                // 处理JSON数组字符串，如：["staff1","staff2"]
                List<String> ids = com.alibaba.fastjson.JSON.parseArray(json, String.class);
                if (ids != null) {
                    for (String id : ids) {
                        if (id != null && !id.trim().isEmpty()) {
                            staffSet.add(id.trim());
                        }
                    }
                }
            } catch (Exception e) {
                // 如果不是JSON格式，尝试按逗号分割
                String[] ids = json.replace("[", "").replace("]", "").replace("\"", "").split(",");
                for (String id : ids) {
                    String trimmed = id.trim();
                    if (!trimmed.isEmpty()) {
                        staffSet.add(trimmed);
                    }
                }
            }
        }

        return (long) staffSet.size();
    }

    @Override
    public RoadCleaningExecutingRespVO getExecutingData() {
        RoadCleaningExecutingRespVO resp = new RoadCleaningExecutingRespVO();

        // 1. 当前作业任务数
        resp.setCurrentTaskCount(roadCleaningMapper.countCurrentTask());

        // 2. 正常运行数
        resp.setNormalRunningCount(roadCleaningMapper.countNormalRunning());

        // 3. 异常标记数
        resp.setAbnormalCount(roadCleaningMapper.countAbnormal());

        // 4. 当日清扫路段完成率实时增长趋势
        resp.setCompletionRateTrend(buildCompletionRateTrend());

        return resp;
    }

    /**
     * 构建完成率增长趋势（每小时）
     */
    private List<CompletionRatePointVO> buildCompletionRateTrend() {
        List<CompletionRatePointVO> trend = new ArrayList<>();

        // 获取当天总计划数
        Long totalPlans = roadCleaningMapper.countTodayTotalPlans();
        if (totalPlans == null || totalPlans == 0) {
            // 如果没有计划，返回空列表或默认值
            return getDefaultHourlyData();
        }

        // 获取各小时的完成情况
        List<HourlyCompletionDO> hourlyCompletions = roadCleaningMapper.selectHourlyCompletion();

        // 转换为Map方便查询
        Map<Integer, Integer> completionMap = new HashMap<>();
        if (hourlyCompletions != null) {
            for (HourlyCompletionDO item : hourlyCompletions) {
                completionMap.put(item.getHourPoint(), item.getCompletedCount());
            }
        }

        // 构建0-23小时的完成率数据
        int cumulativeCompleted = 0;
        for (int hour = 0; hour < 24; hour++) {
            // 累加截止到当前小时的完成数量
            if (completionMap.containsKey(hour)) {
                cumulativeCompleted += completionMap.get(hour);
            }

            // 计算完成率
            double completionRate = 0;
            if (totalPlans > 0) {
                completionRate = Math.round((cumulativeCompleted * 100.0 / totalPlans) * 10) / 10.0;
            }

            // 只返回当前时间之前的数据
            if (hour <= LocalDateTime.now().getHour()) {
                CompletionRatePointVO point = new CompletionRatePointVO();
                point.setTimePoint(String.format("%02d:00", hour));
                point.setCompletionRate(completionRate);
                trend.add(point);
            }
        }

        return trend;
    }

    /**
     * 获取默认的每小时数据（当无计划时）
     */
    private List<CompletionRatePointVO> getDefaultHourlyData() {
        List<CompletionRatePointVO> trend = new ArrayList<>();
        int currentHour = LocalDateTime.now().getHour();

        for (int hour = 0; hour <= currentHour; hour++) {
            CompletionRatePointVO point = new CompletionRatePointVO();
            point.setTimePoint(String.format("%02d:00", hour));
            point.setCompletionRate(0.0);
            trend.add(point);
        }

        return trend;
    }

    @Override
    public RoadCleaningCheckRespVO getCheckData() {
        RoadCleaningCheckRespVO resp = new RoadCleaningCheckRespVO();

        // 1. 卡片数据
        resp.setPendingCheckCount(roadCleaningMapper.countPendingCheck());
        resp.setQualifiedCount(roadCleaningMapper.countQualified());
        resp.setNeedReformCount(roadCleaningMapper.countNeedReform());

        // 2. 圆环图 - 核查结果占比
        resp.setCheckResultDistribution(roadCleaningMapper.selectCheckResultDistribution());

        // 3. 圆环图 - 区域分布占比
        resp.setAreaDistribution(roadCleaningMapper.selectAreaDistribution());

        // 4. 柱状图 - 区域质量达标率
        resp.setAreaQualityRateList(roadCleaningMapper.selectAreaQualityRates());

        return resp;
    }

    @Override
    public RoadCleaningCompletedRespVO getCompletedData(String timeRange) {
        RoadCleaningCompletedRespVO resp = new RoadCleaningCompletedRespVO();

        // 计算时间范围
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime;

        switch (timeRange) {
            case "day":
                startTime = endTime.minusDays(30); // 近30天
                break;
            case "week":
                startTime = endTime.minusWeeks(4); // 近4周
                break;
            case "month":
                startTime = endTime.minusMonths(6); // 近6个月
                break;
            default:
                startTime = endTime.minusDays(30);
        }

        // 1. 卡片数据
        resp.setCompletedTaskCount(roadCleaningMapper.countCompletedTasks());
        resp.setTotalCleaningMileage(roadCleaningMapper.sumCleaningMileage());
        resp.setAvgQualityRate(roadCleaningMapper.avgQualityRate());
        resp.setProblemHandleRate(roadCleaningMapper.calculateProblemHandleRate());

        // 2. 柱状图 - 任务完成量对比
        List<BarItemVO> completionComparison;
        switch (timeRange) {
            case "day":
                completionComparison = roadCleaningMapper.selectDailyTaskCompletion(startTime, endTime);
                break;
            case "week":
                completionComparison = roadCleaningMapper.selectWeeklyTaskCompletion(startTime, endTime);
                break;
            case "month":
                completionComparison = roadCleaningMapper.selectMonthlyTaskCompletion(startTime, endTime);
                break;
            default:
                completionComparison = roadCleaningMapper.selectDailyTaskCompletion(startTime, endTime);
        }
        resp.setTaskCompletionComparison(completionComparison);

        // 3. 折线图 - 质量达标率趋势
        resp.setQualityRateTrend(roadCleaningMapper.selectQualityRateTrend());

        // 4. 圆环图 - 各区域完成量占比
        resp.setAreaCompletionDistribution(roadCleaningMapper.selectAreaCompletionDistribution());

        // 5. 圆环图 - 各人员作业量占比
        resp.setStaffWorkloadDistribution(roadCleaningMapper.selectStaffWorkloadDistribution());

        return resp;
    }
}