package cn.iocoder.yudao.module.inspectop.service.cyclereport;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.inspectop.dal.mysql.cyclereport.CycleReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Validated
@Slf4j
public class CycleReportServiceImpl implements CycleReportService {

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Override
    public PageResult<CycleReportDO> getCycleReportPage(CycleReportPageReqVO pageReqVO) {
        return cycleReportMapper.selectPage(pageReqVO);
    }

    @Override
    public CycleReportDO getCycleReport(Long id) {
        return cycleReportMapper.selectById(id);
    }

    @Override
    public CycleReportRespVO generateCycleReport(CycleReportGenerateReqVO generateReqVO) {

        // ========== 核心：自动根据报表类型计算时间(自定义报表：保留前端传入的 startTime、endTime 不变) ==========
        String reportType = generateReqVO.getReportCycle();
        if (reportType!=null){
            //如果不是自定义报表，自动计算时间
            if (StrUtil.isNotBlank(reportType) && !"自定义报表".equals(reportType)) {
                // 非自定义：自动计算 开始/结束 时间
                Date[] dates = autoCalcReportTime(reportType);
                // 覆盖前端传入的时间（自动生成）
                // 方式1：使用 Date -> LocalDateTime 直接转换（推荐，无格式问题）
                generateReqVO.setStatTimeStart(LocalDateTime.ofInstant(dates[0].toInstant(), ZoneId.systemDefault()));
                generateReqVO.setStatTimeEnd(LocalDateTime.ofInstant(dates[1].toInstant(), ZoneId.systemDefault()));
            }
        }

        // 1. 获取统计参数
        Long stationId = generateReqVO.getStationId();
        LocalDateTime statTimeStart = generateReqVO.getStatTimeStart();
        LocalDateTime statTimeEnd = generateReqVO.getStatTimeEnd();

        // 2. 构建返回对象
        CycleReportRespVO respVO = new CycleReportRespVO();

        // 3. 设置基础信息
        respVO.setReportCycle(generateReqVO.getReportCycle());
        respVO.setStatTimeStart(statTimeStart);
        respVO.setStatTimeEnd(statTimeEnd);
        respVO.setStationId(stationId);
        respVO.setStationName(cycleReportMapper.selectStationNameById(stationId));
        respVO.setGenerateStatus("已生成");
        respVO.setGenerateTime(LocalDateTime.now());
        respVO.setOperator(SecurityFrameworkUtils.getLoginUserNickname());
        respVO.setExportCount(0);

        // 4. 核心：分步查询各个业务表数据
        // 4.1 设备监控统计
        Map<String, Object> deviceMap = cycleReportMapper.selectDeviceMonitorReport(stationId, statTimeStart, statTimeEnd);
        if (deviceMap != null) {
            respVO.setNormalDeviceNum(((Number) deviceMap.getOrDefault("normalDeviceNum", 0)).intValue());
            respVO.setAbnormalDeviceNum(((Number) deviceMap.getOrDefault("abnormalDeviceNum", 0)).intValue());
        } else {
            respVO.setNormalDeviceNum(0);
            respVO.setAbnormalDeviceNum(0);
            log.warn("设备监控统计查询返回null，stationId={}", stationId);
        }

        // 4.2 巡检任务统计
        Map<String, Object> taskMap = cycleReportMapper.selectInspectTaskReport(stationId, statTimeStart, statTimeEnd);
        respVO.setInspectTaskNum(((Number) taskMap.getOrDefault("inspectTaskNum", 0)).intValue());
        Object taskCompleteRate = taskMap.get("taskCompleteRate");
        respVO.setTaskCompleteRate(taskCompleteRate != null ?
                new BigDecimal(taskCompleteRate.toString()) : BigDecimal.ZERO);

        // 4.3 油车占位统计
        Map<String, Object> oilMap = cycleReportMapper.selectOilMonitorReport(stationId, statTimeStart, statTimeEnd);
        respVO.setOilWaitHandleNum(((Number) oilMap.getOrDefault("oilWaitHandleNum", 0)).intValue());
        Object oilHandleCompleteRate = oilMap.get("oilHandleCompleteRate");
        respVO.setOilHandleCompleteRate(oilHandleCompleteRate != null ?
                new BigDecimal(oilHandleCompleteRate.toString()) : BigDecimal.ZERO);

        // 4.4 巡检人员统计
        Map<String, Object> userMap = cycleReportMapper.selectInspectUserReport(stationId);
        respVO.setInspectUserOnlineNum(((Number) userMap.getOrDefault("inspectUserOnlineNum", 0)).intValue());

        // 4.5 资产信息统计
        Map<String, Object> assetMap = cycleReportMapper.selectAssetInfoReport(stationId);
        respVO.setAssetNormalNum(((Number) assetMap.getOrDefault("assetNormalNum", 0)).intValue());

        // 4.6 库存预警统计
        Map<String, Object> stockMap = cycleReportMapper.selectAssetStockReport(stationId);
        respVO.setStockWarnNum(((Number) stockMap.getOrDefault("stockWarnNum", 0)).intValue());

        // 5. 同比环比数据
        // 5.1 计算同比（本期 vs 去年同期）
        String yearOnYear = calculateYearOnYear(stationId, reportType, statTimeStart, statTimeEnd);
        respVO.setYearOnYearData(yearOnYear);
        // 5.2 计算环比（本期 vs 上一个周期）
        String chainRatio = calculateChainRatio(stationId, reportType, statTimeStart, statTimeEnd);
        respVO.setChainRatioData(chainRatio);

        // ========== 将报表数据存储到数据库 ==========
        this.saveOrUpdateReport(respVO, generateReqVO);

        return respVO;
    }

    @Override
    public CycleReportChartRespVO getCycleReportChart(CycleReportChartReqVO reqVO) {
        // 构建返回对象
        CycleReportChartRespVO respVO = new CycleReportChartRespVO();

        // 1. 获取卡片数据
        CycleReportChartRespVO.CardData cardData = getCardData(reqVO);
        respVO.setCardData(cardData);

        // 2. 获取地图数据
        List<CycleReportChartRespVO.MapData> mapData = cycleReportMapper.selectMapData(reqVO);
        // 为每个地图数据设置模拟的经纬度
        mapData = generateRandomCoordinates(mapData);
        respVO.setMapData(mapData);

        // 3. 获取柱状图数据
        List<CycleReportChartRespVO.BarData> barData = cycleReportMapper.selectBarData(reqVO);
        respVO.setBarData(barData);

        // 4. 获取折线图数据
        List<CycleReportChartRespVO.LineData> lineData = cycleReportMapper.selectLineData(reqVO);
        respVO.setLineData(lineData);

        return respVO;
    }

    /**
     * 为地图数据生成随机经纬度
     * 以福建省泉州市为中心，生成随机坐标
     */
    private List<CycleReportChartRespVO.MapData> generateRandomCoordinates(List<CycleReportChartRespVO.MapData> mapDataList) {
        if (mapDataList == null || mapDataList.isEmpty()) {
            return mapDataList;
        }

        // 泉州市中心坐标
        double baseLongitude = 118.605600;  // 经度
        double baseLatitude = 24.914700;    // 纬度

        Random random = new Random();

        for (CycleReportChartRespVO.MapData mapData : mapDataList) {
            // 在中心点附近随机偏移（经度偏移±0.1，纬度偏移±0.1）
            double offsetLng = (random.nextDouble() * 0.2) - 0.1;  // -0.1 到 0.1
            double offsetLat = (random.nextDouble() * 0.2) - 0.1;  // -0.1 到 0.1

            BigDecimal longitude = BigDecimal.valueOf(baseLongitude + offsetLng);
            BigDecimal latitude = BigDecimal.valueOf(baseLatitude + offsetLat);

            // 保留6位小数
            longitude = longitude.setScale(6, BigDecimal.ROUND_HALF_UP);
            latitude = latitude.setScale(6, BigDecimal.ROUND_HALF_UP);

            mapData.setLongitude(longitude);
            mapData.setLatitude(latitude);
        }

        return mapDataList;
    }

    private CycleReportChartRespVO.CardData getCardData(CycleReportChartReqVO reqVO) {
        CycleReportChartRespVO.CardData cardData = new CycleReportChartRespVO.CardData();

        LocalDateTime statTimeStart = reqVO.getStatTimeStart();
        LocalDateTime statTimeEnd = reqVO.getStatTimeEnd();

        // 1. 设备监控统计（统计全部场站，stationId传null）
        Map<String, Object> deviceMap = cycleReportMapper.selectDeviceMonitorReport(null, statTimeStart, statTimeEnd);
        if (deviceMap != null) {
            cardData.setNormalDeviceNum(((Number) deviceMap.getOrDefault("normalDeviceNum", 0)).intValue());
            cardData.setAbnormalDeviceNum(((Number) deviceMap.getOrDefault("abnormalDeviceNum", 0)).intValue());
        }

        // 2. 巡检任务统计（统计全部场站）
        Map<String, Object> taskMap = cycleReportMapper.selectInspectTaskReport(null, statTimeStart, statTimeEnd);
        cardData.setInspectTaskNum(((Number) taskMap.getOrDefault("inspectTaskNum", 0)).intValue());
        Object taskCompleteRate = taskMap.get("taskCompleteRate");
        cardData.setTaskCompleteRate(taskCompleteRate != null ?
                new BigDecimal(taskCompleteRate.toString()) : BigDecimal.ZERO);

        // 3. 油车占位统计（统计全部场站）
        Map<String, Object> oilMap = cycleReportMapper.selectOilMonitorReport(null, statTimeStart, statTimeEnd);
        cardData.setOilWaitHandleNum(((Number) oilMap.getOrDefault("oilWaitHandleNum", 0)).intValue());
        Object oilHandleCompleteRate = oilMap.get("oilHandleCompleteRate");
        cardData.setOilHandleCompleteRate(oilHandleCompleteRate != null ?
                new BigDecimal(oilHandleCompleteRate.toString()) : BigDecimal.ZERO);

        // 4. 巡检人员统计（统计全部场站）
        Map<String, Object> userMap = cycleReportMapper.selectInspectUserReport(null);
        cardData.setInspectUserOnlineNum(((Number) userMap.getOrDefault("inspectUserOnlineNum", 0)).intValue());

        // 5. 资产信息统计（统计全部场站）
        Map<String, Object> assetMap = cycleReportMapper.selectAssetInfoReport(null);
        cardData.setAssetNormalNum(((Number) assetMap.getOrDefault("assetNormalNum", 0)).intValue());

        // 6. 库存预警统计（统计全部场站）
        Map<String, Object> stockMap = cycleReportMapper.selectAssetStockReport(null);
        cardData.setStockWarnNum(((Number) stockMap.getOrDefault("stockWarnNum", 0)).intValue());

        return cardData;
    }

    /**
     * 保存或更新报表到数据库
     * 逻辑参考自 StationReportServiceImpl.addReport()
     * @param respVO 报表数据响应对象
     * @param generateReqVO 生成请求对象（包含原始周期等信息）
     */
    @Transactional(rollbackFor = Exception.class) // 添加事务，确保数据一致性
    public void saveOrUpdateReport(CycleReportRespVO respVO, CycleReportGenerateReqVO generateReqVO) {
        // 1. 将 VO 转换为 DO
        CycleReportDO reportDO = BeanUtils.toBean(respVO, CycleReportDO.class);

        // 2. 补全或修正 DO 中的字段
        // 报表生成时间、操作人等信息已在 respVO 中设置，直接复制

        // 3. 判断是否已存在同周期报表（防止重复）
        CycleReportDO existReport = cycleReportMapper.selectByUniqueCondition(
                generateReqVO.getReportCycle(),
                generateReqVO.getStationId(),
                respVO.getStatTimeStart(), // 使用自动计算或前端传入的时间
                respVO.getStatTimeEnd()
        );

        // 4. 存在则更新，不存在则插入
        if (existReport != null) {
            reportDO.setId(existReport.getId()); // 设置已存在记录的ID
            reportDO.setUpdater(SecurityFrameworkUtils.getLoginUserId() + ""); // 设置更新人
            cycleReportMapper.updateById(reportDO);
            log.info("[saveOrUpdateReport][更新巡检报表成功]，报表ID: {}, 周期: {}", reportDO.getId(), reportDO.getReportCycle());
        } else {
            reportDO.setCreator(SecurityFrameworkUtils.getLoginUserId() + ""); // 设置创建人
            reportDO.setUpdater(SecurityFrameworkUtils.getLoginUserId() + "");
            cycleReportMapper.insert(reportDO);
            log.info("[saveOrUpdateReport][新增巡检报表成功]，报表ID: {}, 周期: {}", reportDO.getId(), reportDO.getReportCycle());
        }
    }

    /**
     * 根据报表类型，自动计算 开始时间、结束时间
     * @param reportType 报表类型
     * @return Date[0] = 开始时间，Date[1] = 结束时间
     */
    private Date[] autoCalcReportTime(String reportType) {
        Date now = new Date();
        Date startTime = null;
        Date endTime = null;

        switch (reportType) {
            case "日报":
                // 今天 00:00:00 ~ 23:59:59
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
                break;
            case "周报":
                // 本周一 00:00:00 ~ 本周日 23:59:59
                startTime = DateUtil.beginOfWeek(now);
                endTime = DateUtil.endOfWeek(now);
                break;
            case "月报":
                // 本月1号 ~ 本月最后一天
                startTime = DateUtil.beginOfMonth(now);
                endTime = DateUtil.endOfMonth(now);
                break;
            case "季报":
                // 本季度第一天 ~ 本季度最后一天
                startTime = DateUtil.beginOfQuarter(now);
                endTime = DateUtil.endOfQuarter(now);
                break;
            case "半年报":
                // 上半年/下半年 自动计算
                int month = DateUtil.month(now) + 1;
                if (month <= 6) {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-01-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-06-30");
                } else {
                    startTime = DateUtil.parse(DateUtil.year(now) + "-07-01");
                    endTime = DateUtil.parse(DateUtil.year(now) + "-12-31");
                }
                break;
            case "年报":
                // 本年1月1日 ~ 12月31日
                startTime = DateUtil.beginOfYear(now);
                endTime = DateUtil.endOfYear(now);
                break;
            default:
                // 默认：今天
                startTime = DateUtil.beginOfDay(now);
                endTime = DateUtil.endOfDay(now);
        }
        return new Date[]{startTime, endTime};
    }

    /**
     * 计算环比数据
     * 对比逻辑：本期 vs 上一个相同周期
     * 示例：本月 vs 上月，本周 vs 上周，本日 vs 昨日
     *
     * @param stationId 场站ID
     * @param reportType 报表类型（日报/周报/月报/季报/半年报/年报）
     * @param statTimeStart 本期开始时间
     * @param statTimeEnd 本期结束时间
     * @return 环比数据字符串，如"环比增长 2.8%"或"环比下降 5.1%"
     */
    private String calculateChainRatio(Long stationId, String reportType,
                                       LocalDateTime statTimeStart, LocalDateTime statTimeEnd) {
        try {
            // 1. 计算上一个周期的起止时间
            LocalDateTime[] lastPeriodDates = getLastPeriodTimeRange(reportType, statTimeStart, statTimeEnd);
            if (lastPeriodDates == null) {
                return "--";
            }

            LocalDateTime lastStartTime = lastPeriodDates[0];
            LocalDateTime lastEndTime = lastPeriodDates[1];

            // 2. 查询本期的核心指标
            Map<String, Object> currentData = getCoreIndicators(stationId, statTimeStart, statTimeEnd);

            // 3. 查询上一个周期的核心指标
            Map<String, Object> lastPeriodData = getCoreIndicators(stationId, lastStartTime, lastEndTime);

            // 4. 计算环比变化
            return calculateChangeRate(currentData, lastPeriodData, "环比");
        } catch (Exception e) {
            log.error("[calculateChainRatio][计算环比数据异常]，场站ID: {}, 报表类型: {}, 异常: {}",
                    stationId, reportType, e.getMessage(), e);
            return "环比计算异常";
        }
    }

    /**
     * 计算同比数据
     * 对比逻辑：本期 vs 去年同期
     * 示例：本月 vs 去年同月，本周 vs 去年同周
     *
     * @param stationId 场站ID
     * @param reportType 报表类型
     * @param statTimeStart 本期开始时间
     * @param statTimeEnd 本期结束时间
     * @return 同比数据字符串
     */
    private String calculateYearOnYear(Long stationId, String reportType,
                                       LocalDateTime statTimeStart, LocalDateTime statTimeEnd) {
        try {
            // 1. 计算去年同期的起止时间
            LocalDateTime[] samePeriodLastYear = getSamePeriodLastYearRange(reportType, statTimeStart, statTimeEnd);
            if (samePeriodLastYear == null) {
                return "--";
            }

            LocalDateTime lastYearStart = samePeriodLastYear[0];
            LocalDateTime lastYearEnd = samePeriodLastYear[1];

            // 2. 查询本期的核心指标
            Map<String, Object> currentData = getCoreIndicators(stationId, statTimeStart, statTimeEnd);

            // 3. 查询去年同期的核心指标
            Map<String, Object> lastYearData = getCoreIndicators(stationId, lastYearStart, lastYearEnd);

            // 4. 计算同比变化
            return calculateChangeRate(currentData, lastYearData, "同比");
        } catch (Exception e) {
            log.error("[calculateYearOnYear][计算同比数据异常]，场站ID: {}, 报表类型: {}, 异常: {}",
                    stationId, reportType, e.getMessage(), e);
            return "同比计算异常";
        }
    }

    /**
     * 获取上一个周期的时间范围
     */
    private LocalDateTime[] getLastPeriodTimeRange(String reportType,
                                                   LocalDateTime currentStart, LocalDateTime currentEnd) {
        switch (reportType) {
            case "日报":
                // 昨天
                return new LocalDateTime[] {
                        currentStart.minusDays(1),
                        currentEnd.minusDays(1)
                };
            case "周报":
                // 上周
                return new LocalDateTime[] {
                        currentStart.minusWeeks(1),
                        currentEnd.minusWeeks(1)
                };
            case "月报":
                // 上月
                return new LocalDateTime[] {
                        currentStart.minusMonths(1),
                        currentEnd.minusMonths(1)
                };
            case "季报":
                // 上季度
                return new LocalDateTime[] {
                        currentStart.minusMonths(3),
                        currentEnd.minusMonths(3)
                };
            case "半年报":
                // 上半年/下半年
                return new LocalDateTime[] {
                        currentStart.minusMonths(6),
                        currentEnd.minusMonths(6)
                };
            case "年报":
                // 去年
                return new LocalDateTime[] {
                        currentStart.minusYears(1),
                        currentEnd.minusYears(1)
                };
            case "自定义报表":
                // 自定义报表：按照相同天数间隔计算上一个周期
                long daysBetween = java.time.Duration.between(currentStart, currentEnd).toDays();
                return new LocalDateTime[] {
                        currentStart.minusDays(daysBetween + 1),
                        currentEnd.minusDays(daysBetween + 1)
                };
            default:
                return null;
        }
    }

    /**
     * 获取去年同期的起止时间
     */
    private LocalDateTime[] getSamePeriodLastYearRange(String reportType,
                                                       LocalDateTime currentStart, LocalDateTime currentEnd) {
        // 所有类型都减去一年
        return new LocalDateTime[] {
                currentStart.minusYears(1),
                currentEnd.minusYears(1)
        };
    }

    /**
     * 获取核心指标数据
     * 这里可以根据需要选择计算环比的关键指标
     */
    private Map<String, Object> getCoreIndicators(Long stationId,
                                                  LocalDateTime startTime, LocalDateTime endTime) {
        Map<String, Object> indicators = new HashMap<>();

        try {
            // 1. 获取设备监控统计
            Map<String, Object> deviceMap = cycleReportMapper.selectDeviceMonitorReport(stationId, startTime, endTime);
            if (deviceMap != null) {
                int normalDeviceNum = ((Number) deviceMap.getOrDefault("normalDeviceNum", 0)).intValue();
                int abnormalDeviceNum = ((Number) deviceMap.getOrDefault("abnormalDeviceNum", 0)).intValue();
                int totalDeviceNum = normalDeviceNum + abnormalDeviceNum;

                indicators.put("normalDeviceNum", normalDeviceNum);
                indicators.put("abnormalDeviceNum", abnormalDeviceNum);
                indicators.put("totalDeviceNum", totalDeviceNum);
                // 设备正常率
                double deviceNormalRate = totalDeviceNum > 0 ?
                        normalDeviceNum * 100.0 / totalDeviceNum : 0.0;
                indicators.put("deviceNormalRate", deviceNormalRate);
            }

            // 2. 获取巡检任务统计
            Map<String, Object> taskMap = cycleReportMapper.selectInspectTaskReport(stationId, startTime, endTime);
            int taskNum = ((Number) taskMap.getOrDefault("inspectTaskNum", 0)).intValue();
            double completeRate = taskMap.get("taskCompleteRate") != null ?
                    new BigDecimal(taskMap.get("taskCompleteRate").toString()).doubleValue() : 0.0;

            indicators.put("inspectTaskNum", taskNum);
            indicators.put("taskCompleteRate", completeRate);

            // 3. 获取油车占位统计
            Map<String, Object> oilMap = cycleReportMapper.selectOilMonitorReport(stationId, startTime, endTime);
            int oilWaitHandleNum = ((Number) oilMap.getOrDefault("oilWaitHandleNum", 0)).intValue();
            double oilHandleRate = oilMap.get("oilHandleCompleteRate") != null ?
                    new BigDecimal(oilMap.get("oilHandleCompleteRate").toString()).doubleValue() : 0.0;

            indicators.put("oilWaitHandleNum", oilWaitHandleNum);
            indicators.put("oilHandleRate", oilHandleRate);

            // 4. 计算综合得分（可自定义权重）
            double totalScore = calculateTotalScore(indicators);
            indicators.put("totalScore", totalScore);

        } catch (Exception e) {
            log.error("[getCoreIndicators][获取核心指标异常]，场站ID: {}, 时间范围: {} - {}",
                    stationId, startTime, endTime, e);
        }

        return indicators;
    }

    /**
     * 计算变化率
     */
    private String calculateChangeRate(Map<String, Object> currentData,
                                       Map<String, Object> compareData, String type) {
        if (currentData == null || compareData == null ||
                currentData.isEmpty() || compareData.isEmpty()) {
            return type + "数据不足";
        }

        // 使用综合得分进行对比
        double currentScore = ((Number) currentData.getOrDefault("totalScore", 0.0)).doubleValue();
        double compareScore = ((Number) compareData.getOrDefault("totalScore", 0.0)).doubleValue();

        if (compareScore == 0) {
            return type + "无可比数据";
        }

        // 计算变化率
        double changeRate = ((currentScore - compareScore) / compareScore) * 100;

        // 格式化输出
        String direction = changeRate >= 0 ? "增长" : "下降";
        String rateStr = String.format("%.1f", Math.abs(changeRate));

        return type + direction + " " + rateStr + "%";
    }

    /**
     * 计算综合得分
     * 权重分配示例：
     * - 设备正常率：40%
     * - 任务完成率：30%
     * - 油车处理率：30%
     */
    private double calculateTotalScore(Map<String, Object> indicators) {
        double deviceNormalRate = ((Number) indicators.getOrDefault("deviceNormalRate", 0.0)).doubleValue();
        double taskCompleteRate = ((Number) indicators.getOrDefault("taskCompleteRate", 0.0)).doubleValue();
        double oilHandleRate = ((Number) indicators.getOrDefault("oilHandleRate", 0.0)).doubleValue();

        // 加权计算
        double totalScore = deviceNormalRate * 0.4 +
                taskCompleteRate * 0.3 +
                oilHandleRate * 0.3;

        return totalScore;
    }

    /**
     * 计算变化率 - 针对单个指标
     * 可选：如果需要显示每个指标的详细变化
     */
    private String calculateDetailChangeRate(Map<String, Object> currentData,
                                             Map<String, Object> compareData) {
        StringBuilder detail = new StringBuilder();

        // 1. 设备正常率变化
        double currentDeviceRate = ((Number) currentData.getOrDefault("deviceNormalRate", 0.0)).doubleValue();
        double compareDeviceRate = ((Number) compareData.getOrDefault("deviceNormalRate", 0.0)).doubleValue();
        if (compareDeviceRate > 0) {
            double deviceChange = ((currentDeviceRate - compareDeviceRate) / compareDeviceRate) * 100;
            detail.append("设备正常率").append(deviceChange >= 0 ? "+" : "").append(String.format("%.1f", deviceChange)).append("%; ");
        }

        // 2. 任务完成率变化
        double currentTaskRate = ((Number) currentData.getOrDefault("taskCompleteRate", 0.0)).doubleValue();
        double compareTaskRate = ((Number) compareData.getOrDefault("taskCompleteRate", 0.0)).doubleValue();
        if (compareTaskRate > 0) {
            double taskChange = ((currentTaskRate - compareTaskRate) / compareTaskRate) * 100;
            detail.append("任务完成率").append(taskChange >= 0 ? "+" : "").append(String.format("%.1f", taskChange)).append("%; ");
        }

        // 3. 油车处理率变化
        double currentOilRate = ((Number) currentData.getOrDefault("oilHandleRate", 0.0)).doubleValue();
        double compareOilRate = ((Number) compareData.getOrDefault("oilHandleRate", 0.0)).doubleValue();
        if (compareOilRate > 0) {
            double oilChange = ((currentOilRate - compareOilRate) / compareOilRate) * 100;
            detail.append("油车处理率").append(oilChange >= 0 ? "+" : "").append(String.format("%.1f", oilChange)).append("%");
        }

        return detail.toString();
    }



}