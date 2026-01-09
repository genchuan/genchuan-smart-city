package cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.CoreMetricVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.TreatTrendVO;
import cn.iocoder.yudao.module.industry.dal.mysql.lawenf.dashboard.global.coremetrics.LawCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 执法核心指标 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class LawCoreMetricsServiceImpl implements LawCoreMetricsService {

    @Resource
    private LawCoreMetricsMapper lawCoreMetricsMapper;

    @Override
    public LawCoreMetricsRespVO getLawCoreMetrics2(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO) {
            return lawCoreMetricsMapper.getLawCoreMetrics2(lawCoreMetricsQueryReqVO);
    }

    @Override
    public LawCoreMetricsRespVO getLawCoreMetrics(LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO) {
//        //指标预警阈值
//        BigDecimal avgCaseHandleTimeWarningThreshold=new BigDecimal(100);
//
//        LawCoreMetricsRespVO lawCoreMetricsRespVO =new LawCoreMetricsRespVO();
//        //一、先获取指标 的 当前值，预警状态，更新时间
//        //1.获取 平均时长
//        //(1)当前值，更新时间
//        CoreMetricVO avgCaseHandleTimeCoreMetricVO=lawCoreMetricsMapper.getAvgCaseHandleTime(lawCoreMetricsQueryReqVO);
//        //(2)预警状态
//        if (avgCaseHandleTimeCoreMetricVO.getValue().compareTo(avgCaseHandleTimeWarningThreshold) > 0) {
//            // 超过阈值逻辑
//            avgCaseHandleTimeCoreMetricVO.setWarnStatus(1);//修改为预警报错
//        }
//        // (3)近7天趋势 - Java 层处理补全缺失日期
//        List<TreatTrendVO> avgCaseHandleTimeTreatTrendVOList = lawCoreMetricsMapper.getAvgCaseHandleTimeTrendVOList(lawCoreMetricsQueryReqVO);
//
//        // 将查询结果按日期字符串映射到值
//        Map<String, BigDecimal> valueMap = avgCaseHandleTimeTreatTrendVOList.stream()
//                .collect(Collectors.toMap(
//                        TreatTrendVO::getDate,
//                        TreatTrendVO::getValue
//                ));
//
//        List<TreatTrendVO> completeTrendList = new ArrayList<>();
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        for (int i = 6; i >= 0; i--) {
//            LocalDate date = today.minusDays(i);
//            String dateStr = date.format(formatter);
//            BigDecimal value = valueMap.getOrDefault(dateStr, BigDecimal.ZERO);
//
//            TreatTrendVO vo = new TreatTrendVO();
//            vo.setDate(dateStr);
//            vo.setValue(value);
//            completeTrendList.add(vo);
//        }
//
//        //  把完整列表赋值回 CoreMetricVO
//        avgCaseHandleTimeCoreMetricVO.setTreatTrendVOList(completeTrendList);
//
//        // 最后再放入响应
//        lawCoreMetricsRespVO.setAvgCaseHandleTimeCoreMetricVO(avgCaseHandleTimeCoreMetricVO);
//
//
//
//
//
//        //三、返回 数据
//        return lawCoreMetricsRespVO;

//        return lawCoreMetricsMapper.getLawCoreMetrics2(lawCoreMetricsQueryReqVO);
        // =========================
        // 指标预警阈值定义
        // =========================
        BigDecimal avgCaseHandleTimeWarningThreshold = new BigDecimal(100);
        BigDecimal lawComplianceRateWarningThreshold = new BigDecimal(90); // 示例阈值
        BigDecimal repeatRptRateWarningThreshold = new BigDecimal(5);      // 示例阈值
        BigDecimal crossDeptCoopRateWarningThreshold = new BigDecimal(80); // 示例阈值

        LawCoreMetricsRespVO lawCoreMetricsRespVO = new LawCoreMetricsRespVO();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // =========================
        // 1. 获取案件平均办结时长指标
        // =========================
        CoreMetricVO avgCaseHandleTimeCoreMetricVO = lawCoreMetricsMapper.getAvgCaseHandleTime(lawCoreMetricsQueryReqVO);
        if (avgCaseHandleTimeCoreMetricVO.getValue().compareTo(avgCaseHandleTimeWarningThreshold) > 0) {
            // 超过阈值逻辑
            avgCaseHandleTimeCoreMetricVO.setWarnStatus(1);
        }
        List<TreatTrendVO> avgCaseHandleTimeTreatTrendVOList = lawCoreMetricsMapper.getAvgCaseHandleTimeTrendVOList(lawCoreMetricsQueryReqVO);
        Map<String, BigDecimal> avgCaseHandleTimeMap = avgCaseHandleTimeTreatTrendVOList.stream()
                .collect(Collectors.toMap(TreatTrendVO::getDate, TreatTrendVO::getValue));
        List<TreatTrendVO> avgCaseHandleTimeCompleteTrend = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            String dateStr = today.minusDays(i).format(formatter);
            BigDecimal value = avgCaseHandleTimeMap.getOrDefault(dateStr, BigDecimal.ZERO);
            TreatTrendVO vo = new TreatTrendVO();
            vo.setDate(dateStr);
            vo.setValue(value);
            avgCaseHandleTimeCompleteTrend.add(vo);
        }
        avgCaseHandleTimeCoreMetricVO.setTreatTrendVOList(avgCaseHandleTimeCompleteTrend);
        lawCoreMetricsRespVO.setAvgCaseHandleTimeCoreMetricVO(avgCaseHandleTimeCoreMetricVO);
        lawCoreMetricsRespVO.setAvgCaseHandleTime(avgCaseHandleTimeCoreMetricVO.getValue().intValue());

        // =========================
        // 2. 获取执法合规率指标
        // =========================
        CoreMetricVO lawComplianceRateCoreMetricVO = lawCoreMetricsMapper.getLawComplianceRate(lawCoreMetricsQueryReqVO);
        if (lawComplianceRateCoreMetricVO.getValue().compareTo(lawComplianceRateWarningThreshold) < 0) {
            // 低于阈值逻辑
            lawComplianceRateCoreMetricVO.setWarnStatus(1);
        }
        List<TreatTrendVO> lawComplianceRateTrendVOList = lawCoreMetricsMapper.getLawComplianceRateTrendVOList(lawCoreMetricsQueryReqVO);
        Map<String, BigDecimal> lawComplianceRateMap = lawComplianceRateTrendVOList.stream()
                .collect(Collectors.toMap(TreatTrendVO::getDate, TreatTrendVO::getValue));
        List<TreatTrendVO> lawComplianceRateCompleteTrend = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            String dateStr = today.minusDays(i).format(formatter);
            BigDecimal value = lawComplianceRateMap.getOrDefault(dateStr, BigDecimal.ZERO);
            TreatTrendVO vo = new TreatTrendVO();
            vo.setDate(dateStr);
            vo.setValue(value);
            lawComplianceRateCompleteTrend.add(vo);
        }
        lawComplianceRateCoreMetricVO.setTreatTrendVOList(lawComplianceRateCompleteTrend);
        lawCoreMetricsRespVO.setLawComplianceRateCoreMetricVO(lawComplianceRateCoreMetricVO);
        lawCoreMetricsRespVO.setLawComplianceRate(lawComplianceRateCoreMetricVO.getValue().doubleValue());

        // =========================
        // 3. 获取重复举报率指标
        // =========================
        CoreMetricVO repeatRptRateCoreMetricVO = lawCoreMetricsMapper.getRepeatRptRate(lawCoreMetricsQueryReqVO);
        if (repeatRptRateCoreMetricVO.getValue().compareTo(repeatRptRateWarningThreshold) > 0) {
            // 超过阈值逻辑
            repeatRptRateCoreMetricVO.setWarnStatus(1);
        }
        List<TreatTrendVO> repeatRptRateTrendVOList = lawCoreMetricsMapper.getRepeatRptRateTrendVOList(lawCoreMetricsQueryReqVO);
        Map<String, BigDecimal> repeatRptRateMap = repeatRptRateTrendVOList.stream()
                .collect(Collectors.toMap(TreatTrendVO::getDate, TreatTrendVO::getValue));
        List<TreatTrendVO> repeatRptRateCompleteTrend = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            String dateStr = today.minusDays(i).format(formatter);
            BigDecimal value = repeatRptRateMap.getOrDefault(dateStr, BigDecimal.ZERO);
            TreatTrendVO vo = new TreatTrendVO();
            vo.setDate(dateStr);
            vo.setValue(value);
            repeatRptRateCompleteTrend.add(vo);
        }
        repeatRptRateCoreMetricVO.setTreatTrendVOList(repeatRptRateCompleteTrend);
        lawCoreMetricsRespVO.setRepeatRptRateCoreMetricVO(repeatRptRateCoreMetricVO);
        lawCoreMetricsRespVO.setRepeatRptRate(repeatRptRateCoreMetricVO.getValue().doubleValue());

        // =========================
        // 4. 获取跨部门协同率指标
        // =========================
        CoreMetricVO crossDeptCoopRateCoreMetricVO = lawCoreMetricsMapper.getCrossDeptCoopRate(lawCoreMetricsQueryReqVO);
        if (crossDeptCoopRateCoreMetricVO.getValue().compareTo(crossDeptCoopRateWarningThreshold) < 0) {
            // 低于阈值逻辑
            crossDeptCoopRateCoreMetricVO.setWarnStatus(1);
        }
        List<TreatTrendVO> crossDeptCoopRateTrendVOList = lawCoreMetricsMapper.getCrossDeptCoopRateTrendVOList(lawCoreMetricsQueryReqVO);
        Map<String, BigDecimal> crossDeptCoopRateMap = crossDeptCoopRateTrendVOList.stream()
                .collect(Collectors.toMap(TreatTrendVO::getDate, TreatTrendVO::getValue));
        List<TreatTrendVO> crossDeptCoopRateCompleteTrend = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            String dateStr = today.minusDays(i).format(formatter);
            BigDecimal value = crossDeptCoopRateMap.getOrDefault(dateStr, BigDecimal.ZERO);
            TreatTrendVO vo = new TreatTrendVO();
            vo.setDate(dateStr);
            vo.setValue(value);
            crossDeptCoopRateCompleteTrend.add(vo);
        }
        crossDeptCoopRateCoreMetricVO.setTreatTrendVOList(crossDeptCoopRateCompleteTrend);
        lawCoreMetricsRespVO.setCrossDeptCoopRateCoreMetricVO(crossDeptCoopRateCoreMetricVO);
        lawCoreMetricsRespVO.setCrossDeptCoopRate(crossDeptCoopRateCoreMetricVO.getValue().doubleValue());

        // =========================
        // 返回响应数据
        // =========================
        return lawCoreMetricsRespVO;
    }
}
