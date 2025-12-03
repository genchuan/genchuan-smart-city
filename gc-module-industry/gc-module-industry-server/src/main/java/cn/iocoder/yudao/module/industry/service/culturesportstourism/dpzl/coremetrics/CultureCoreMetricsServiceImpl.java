package cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics;

import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.dal.mysql.culturesportstourism.dpzl.coremetrics.CultureCoreMetricsMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CultureCoreMetricsServiceImpl implements CultureCoreMetricsService {

    @Resource
    private CultureCoreMetricsMapper cultureCoreMetricsMapper;

    @Override
    public CultureCoreMetricsRespVO getCoreIndicators(CultureCoreMetricsQueryReqVO queryVO) {
        // 空值判断，默认 recent7
        if (queryVO.getTimeCycle() == null || queryVO.getTimeCycle().isBlank()) {
            queryVO.setTimeCycle("recent7");
        }

        // 解析时间周期
        LocalDateTime now = LocalDateTime.now();
        switch (queryVO.getTimeCycle()) {
            case "today":
                queryVO.setStartTime(now.toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            case "yesterday":
                LocalDateTime yesterday = now.minusDays(1);
                queryVO.setStartTime(yesterday.toLocalDate().atStartOfDay());
                queryVO.setEndTime(yesterday.toLocalDate().atTime(23, 59, 59));
                break;
            case "recent7":
                queryVO.setStartTime(now.minusDays(6).toLocalDate().atStartOfDay()); // 包含今天
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            case "recent30":
                queryVO.setStartTime(now.minusDays(29).toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
            default:
                // 如果输入非法，默认 recent7
                queryVO.setStartTime(now.minusDays(6).toLocalDate().atStartOfDay());
                queryVO.setEndTime(now.toLocalDate().atTime(23, 59, 59));
                break;
        }

        // 文旅资源总数阈值
        BigDecimal totalSceneCountReminder = BigDecimal.valueOf(1200); // 提醒阈值
        BigDecimal totalSceneCountWarning = BigDecimal.valueOf(1000);  // 预警阈值

        // 当日客流峰值阈值
        BigDecimal maxCountReminder = BigDecimal.valueOf(200); // 提醒阈值
        BigDecimal maxCountWarning = BigDecimal.valueOf(300);  // 预警阈值

        // 投诉办结率阈值（%）
        BigDecimal completeRateReminder = BigDecimal.valueOf(85); // 提醒阈值
        BigDecimal completeRateWarning = BigDecimal.valueOf(80);  // 预警阈值

        // 设施完好率阈值（%）
        BigDecimal facilityGoodRateReminder = BigDecimal.valueOf(95); // 提醒阈值
        BigDecimal facilityGoodRateWarning = BigDecimal.valueOf(90);  // 预警阈值

        // 活动开展数阈值
        BigDecimal newSceneCountReminder = BigDecimal.valueOf(60); // 提醒阈值
        BigDecimal newSceneCountWarning = BigDecimal.valueOf(50);  // 预警阈值

        // 直接查询数据库
        CultureCoreMetricsRespVO result = new CultureCoreMetricsRespVO();

        // 1. 文旅资源总数
        CultureCoreMetricsRespVO.CoreMetricVO totalSceneCountVO = cultureCoreMetricsMapper.selectTotalSceneCount(queryVO);
        if (totalSceneCountVO.getValue().compareTo(totalSceneCountWarning) <= 0) {
            totalSceneCountVO.setWarnStatus(2); // 预警
        } else if (totalSceneCountVO.getValue().compareTo(totalSceneCountReminder) <= 0) {
            totalSceneCountVO.setWarnStatus(1); // 提醒
        } else {
            totalSceneCountVO.setWarnStatus(0); // 正常
        }
        result.setTotalSceneCount(totalSceneCountVO);

        // 2. 当日客流峰值
        CultureCoreMetricsRespVO.CoreMetricVO maxCountVO = cultureCoreMetricsMapper.selectMaxCount(queryVO);
        if (maxCountVO.getValue().compareTo(maxCountWarning) <= 0) {
            maxCountVO.setWarnStatus(2);
        } else if (maxCountVO.getValue().compareTo(maxCountReminder) <= 0) {
            maxCountVO.setWarnStatus(1);
        } else {
            maxCountVO.setWarnStatus(0);
        }
        result.setMaxCount(maxCountVO);

        // 3. 投诉办结率
        CultureCoreMetricsRespVO.CoreMetricVO completeRateVO = cultureCoreMetricsMapper.selectCompleteRate(queryVO);
        if (completeRateVO.getValue().compareTo(completeRateWarning) <= 0) {
            completeRateVO.setWarnStatus(2);
        } else if (completeRateVO.getValue().compareTo(completeRateReminder) <= 0) {
            completeRateVO.setWarnStatus(1);
        } else {
            completeRateVO.setWarnStatus(0);
        }
        result.setCompleteRate(completeRateVO);

        // 4. 设施完好率
        CultureCoreMetricsRespVO.CoreMetricVO facilityGoodRateVO = cultureCoreMetricsMapper.selectFacilityGoodRate(queryVO);
        if (facilityGoodRateVO.getValue().compareTo(facilityGoodRateWarning) <= 0) {
            facilityGoodRateVO.setWarnStatus(2);
        } else if (facilityGoodRateVO.getValue().compareTo(facilityGoodRateReminder) <= 0) {
            facilityGoodRateVO.setWarnStatus(1);
        } else {
            facilityGoodRateVO.setWarnStatus(0);
        }
        result.setFacilityGoodRate(facilityGoodRateVO);

        // 5. 活动开展数
        CultureCoreMetricsRespVO.CoreMetricVO newSceneCountVO = cultureCoreMetricsMapper.selectNewSceneCount(queryVO);
        if (newSceneCountVO.getValue().compareTo(newSceneCountWarning) <= 0) {
            newSceneCountVO.setWarnStatus(2);
        } else if (newSceneCountVO.getValue().compareTo(newSceneCountReminder) <= 0) {
            newSceneCountVO.setWarnStatus(1);
        } else {
            newSceneCountVO.setWarnStatus(0);
        }
        result.setNewSceneCount(newSceneCountVO);

        // 测试每个查询
        System.out.println("totalSceneCount: " + cultureCoreMetricsMapper.selectTotalSceneCount(queryVO));
        return result;
    }
}
