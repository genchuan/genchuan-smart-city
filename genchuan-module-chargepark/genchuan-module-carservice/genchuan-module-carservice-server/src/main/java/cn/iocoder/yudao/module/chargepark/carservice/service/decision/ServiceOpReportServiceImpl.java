package cn.iocoder.yudao.module.chargepark.carservice.service.decision;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.TimeReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.reserve.vo.ReserveListChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.serviceconfig.vo.WordingMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.reserve.ReserveListDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.ChargeParkMapMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.SpacePushMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.DisputeMediateMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.SuggestionMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.UserAppealMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.decision.ReportStatMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.PathPlanMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.SpaceLocationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue.RescueInfoMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.reserve.ReserveListMapper;
import cn.iocoder.yudao.module.chargepark.carservice.enums.ReportPeriodEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.carguide.SpacePushStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.DisputeMediateStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.SuggestionStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.complaint.UserAppealStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.rescue.RescueStatusEnum;
import cn.iocoder.yudao.module.chargepark.carservice.enums.reserve.ReserveStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 决策分析 / 服务运营报表 Service 实现
 *
 * 12 个 chartXxx 接口的扁平字段响应,严格按 05 接口文档命名。
 * 数据源:复用 ReportStatMapper 的 GROUP BY DATE 聚合 SQL(P8.8 性能优化)
 *        + 各业务表的 selectCount(轻量级条件计数)。
 *
 * 所有 chart 方法都加 @Cacheable 30s,与 P8.8 阶段的缓存策略保持一致。
 */
@Service
public class ServiceOpReportServiceImpl implements ServiceOpReportService {

    /** 默认趋势图回看天数 */
    private static final int TREND_DAYS = 30;
    /** chartRescue 的位置列表最大返回数(防止全表扫描) */
    private static final int RESCUE_LOC_LIMIT = 200;

    @Resource private RescueInfoMapper rescueInfoMapper;
    @Resource private ChargeParkMapMapper chargeParkMapMapper;
    @Resource private NearStationMapper nearStationMapper;
    @Resource private SpacePushMapper spacePushMapper;
    @Resource private ReserveListMapper reserveListMapper;
    @Resource private SpaceLocationMapper spaceLocationMapper;
    @Resource private PathPlanMapper pathPlanMapper;
    @Resource private SuggestionMapper suggestionMapper;
    @Resource private UserAppealMapper userAppealMapper;
    @Resource private DisputeMediateMapper disputeMediateMapper;
    @Resource private ReportStatMapper reportStatMapper;

    // ===================================================================
    // chart 接口实现(12 个,严格按 05 接口文档字段命名)
    // ===================================================================

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-rescue#30s")
    public RescueInfoChartRespVO chartRescue() {
        RescueInfoChartRespVO resp = new RescueInfoChartRespVO();
        long total = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<>());
        long waiting = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .in(RescueInfoDO::getStatus, Arrays.asList("待派发", "待认领")));
        long completed = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .eq(RescueInfoDO::getStatus, "已完成"));
        resp.setWaitRescueCount((int) waiting);
        resp.setFinishRate(toRate(completed, total));
        resp.setTrendList(toTrendList(reportStatMapper.rescueInfoDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        // 救援位置列表:限制 LIMIT 防止大表全扫
        List<RescueInfoDO> recent = rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .ge(RescueInfoDO::getCreateTime, LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())
                .orderByDesc(RescueInfoDO::getId)
                .last("LIMIT " + RESCUE_LOC_LIMIT));
        List<Map<String, Object>> locList = recent.stream().map(r -> {
            Map<String, Object> m = new LinkedHashMap<>();
            fillLonLat(m, r.getLocation());
            m.put("status", r.getStatus());
            m.put("id", r.getId());
            return m;
        }).collect(Collectors.toList());
        resp.setRescueLocationList(locList);
        return resp;
    }

    /**
     * 把 "118.675324,24.896541" 格式的 location 字符串 split 后填入 lon/lat 字段。
     * 04 数据库表文档约定 location VARCHAR 存"地址或经纬度信息",本项目统一采用"lon,lat"坐标字符串。
     * 格式不合法时,lon/lat 返回 null(前端需要兜底)。
     */
    private void fillLonLat(Map<String, Object> m, String location) {
        if (location == null || location.isEmpty()) {
            m.put("lon", null);
            m.put("lat", null);
            return;
        }
        String[] parts = location.split(",");
        if (parts.length != 2) {
            m.put("lon", null);
            m.put("lat", null);
            return;
        }
        try {
            m.put("lon", Double.parseDouble(parts[0].trim()));
            m.put("lat", Double.parseDouble(parts[1].trim()));
        } catch (NumberFormatException e) {
            m.put("lon", null);
            m.put("lat", null);
        }
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-charge-park-map#30s")
    public ChargeParkMapChartRespVO chartChargeParkMap() {
        ChargeParkMapChartRespVO resp = new ChargeParkMapChartRespVO();
        Map<String, Object> agg = reportStatMapper.chargeParkMapAggregate();
        long total = numLong(agg, "total");
        long success = numLong(agg, "success_cnt");
        double avgResp = numDouble(agg, "avg_resp");
        resp.setQuerySuccessRate(toRate(success, total));
        resp.setAvgResponseDuration((int) Math.round(avgResp));
        // 从 charge_park_map 查询记录的 query_location 聚合坐标,适配地图渲染
        List<ChargeParkMapDO> recent = chargeParkMapMapper.selectList(new LambdaQueryWrapperX<ChargeParkMapDO>()
                .orderByDesc(ChargeParkMapDO::getId).last("LIMIT 200"));
        List<Map<String, Object>> spaceList = recent.stream().map(r -> {
            Map<String, Object> m = new LinkedHashMap<>();
            fillLonLat(m, r.getQueryLocation());
            m.put("id", r.getId());
            m.put("userId", r.getUserId());
            return m;
        }).collect(Collectors.toList());
        resp.setStationSpaceList(spaceList);
        // 热力图数据:按坐标 + 查询次数聚合,简单实现用 query_location 出现频次
        resp.setHeatMapData(spaceList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-near-station#30s")
    public NearStationChartRespVO chartNearStation() {
        NearStationChartRespVO resp = new NearStationChartRespVO();
        Map<String, Object> agg = reportStatMapper.nearStationAggregate();
        resp.setTotalStationCount((int) numLong(agg, "station_sum"));
        resp.setEmptyStationCount((int) numLong(agg, "empty_sum"));
        // 从 near_station 查询记录的 query_location 提取坐标,适配地图渲染
        List<NearStationDO> recent = nearStationMapper.selectList(new LambdaQueryWrapperX<NearStationDO>()
                .orderByDesc(NearStationDO::getId).last("LIMIT 200"));
        List<Map<String, Object>> stationList = recent.stream().map(r -> {
            Map<String, Object> m = new LinkedHashMap<>();
            fillLonLat(m, r.getQueryLocation());
            m.put("id", r.getId());
            m.put("stationCount", r.getStationCount());
            m.put("emptyStationCount", r.getEmptyStationCount());
            return m;
        }).collect(Collectors.toList());
        resp.setStationLocationList(stationList);
        resp.setDistanceCountList(new ArrayList<>());   // 距离分布柱状图,待场站表 distance 字段上线后填充
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-space-push#30s")
    public SpacePushChartRespVO chartSpacePush() {
        SpacePushChartRespVO resp = new SpacePushChartRespVO();
        long total = spacePushMapper.selectCount(new LambdaQueryWrapperX<>());
        long success = spacePushMapper.selectCount(new LambdaQueryWrapperX<SpacePushDO>()
                .eq(SpacePushDO::getPushResult, "成功"));
        resp.setTotalPushCount((int) total);
        resp.setPushSuccessRate(toRate(success, total));
        resp.setPushTrendList(toTrendList(reportStatMapper.spacePushDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-reserve#30s")
    public ReserveListChartRespVO chartReserve() {
        ReserveListChartRespVO resp = new ReserveListChartRespVO();
        long total = reserveListMapper.selectCount(new LambdaQueryWrapperX<>());
        long succeeded = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成")));
        resp.setTotalReserveCount((int) total);
        resp.setReserveSuccessRate(toRate(succeeded, total));
        resp.setReserveTrendList(toTrendList(reportStatMapper.reserveListDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        List<Map<String, Object>> typeRows = reportStatMapper.reserveListTypeDistribution();
        List<Map<String, Object>> typeList = typeRows.stream().map(row -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("reserveType", row.get("k"));
            m.put("count", numLong(row, "cnt"));
            return m;
        }).collect(Collectors.toList());
        resp.setReserveTypeCountList(typeList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-space-location#30s")
    public SpaceLocationChartRespVO chartSpaceLocation() {
        SpaceLocationChartRespVO resp = new SpaceLocationChartRespVO();
        long total = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<>());
        long success = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<SpaceLocationDO>()
                .eq(SpaceLocationDO::getLocationResult, "成功"));
        resp.setTotalQueryCount((int) total);
        resp.setLocationSuccessRate(toRate(success, total));
        resp.setSpaceLocationList(new ArrayList<>()); // 占位,待车位表上线后填充
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-path-plan#30s")
    public PathPlanChartRespVO chartPathPlan() {
        PathPlanChartRespVO resp = new PathPlanChartRespVO();
        Map<String, Object> agg = reportStatMapper.pathPlanAggregate();
        long total = numLong(agg, "total");
        long success = numLong(agg, "success_cnt");
        resp.setTotalPlanCount((int) total);
        resp.setPlanSuccessRate(toRate(success, total));
        // 路径规划地图:返回 start/end 两点坐标,前端根据起终点绘制路径
        List<PathPlanDO> recent = pathPlanMapper.selectList(new LambdaQueryWrapperX<PathPlanDO>()
                .orderByDesc(PathPlanDO::getId).last("LIMIT 200"));
        List<Map<String, Object>> pathList = recent.stream().map(r -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", r.getId());
            // 起点坐标
            Map<String, Object> start = new LinkedHashMap<>();
            fillLonLat(start, r.getStartLocation());
            m.put("start", start);
            // 终点坐标
            Map<String, Object> end = new LinkedHashMap<>();
            fillLonLat(end, r.getEndLocation());
            m.put("end", end);
            m.put("pathLength", r.getPathLength());
            m.put("expectDuration", r.getExpectDuration());
            return m;
        }).collect(Collectors.toList());
        resp.setPathList(pathList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-suggestion#30s")
    public SuggestionChartRespVO chartSuggestion() {
        SuggestionChartRespVO resp = new SuggestionChartRespVO();
        long total = suggestionMapper.selectCount(new LambdaQueryWrapperX<>());
        long pending = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .eq(SuggestionDO::getStatus, "待处理"));
        long completed = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .eq(SuggestionDO::getStatus, "已完成"));
        resp.setWaitHandleCount((int) pending);
        resp.setHandleFinishRate(toRate(completed, total));
        resp.setSuggestionTrendList(toTrendList(reportStatMapper.suggestionDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-user-appeal#30s")
    public UserAppealChartRespVO chartUserAppeal() {
        UserAppealChartRespVO resp = new UserAppealChartRespVO();
        long total = userAppealMapper.selectCount(new LambdaQueryWrapperX<>());
        long pending = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .in(UserAppealDO::getStatus, Arrays.asList("待审核", "待处置")));
        long completed = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .eq(UserAppealDO::getStatus, "已完成"));
        resp.setWaitAppealCount((int) pending);
        resp.setHandleFinishRate(toRate(completed, total));
        resp.setAppealTrendList(toTrendList(reportStatMapper.userAppealDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-dispute-mediate#30s")
    public DisputeMediateChartRespVO chartDisputeMediate() {
        DisputeMediateChartRespVO resp = new DisputeMediateChartRespVO();
        long total = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<>());
        long pending = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .eq(DisputeMediateDO::getStatus, "待调解"));
        long completed = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .eq(DisputeMediateDO::getStatus, "已完成"));
        resp.setWaitMediateCount((int) pending);
        resp.setMediateFinishRate(toRate(completed, total));
        resp.setDisputeTrendList(toTrendList(reportStatMapper.disputeMediateDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-wording-mgmt#30s")
    public WordingMgmtChartRespVO chartWordingMgmt() {
        WordingMgmtChartRespVO resp = new WordingMgmtChartRespVO();
        Map<String, Object> agg = reportStatMapper.wordingMgmtAggregate();
        resp.setEnableWordingCount((int) numLong(agg, "enabled_cnt"));
        // matchRate 占位:需要客服会话日志数据源,待客服会话模块上线后接入
        resp.setMatchRate(BigDecimal.ZERO);
        List<Map<String, Object>> typeRows = reportStatMapper.wordingMgmtTypeDistribution();
        List<Map<String, Object>> typeList = typeRows.stream().map(row -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("type", row.get("k"));
            m.put("count", numLong(row, "cnt"));
            return m;
        }).collect(Collectors.toList());
        resp.setTypeCountList(typeList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-service-op-report#30s")
    public ServiceOpReportChartRespVO chartServiceOpReport() {
        ServiceOpReportChartRespVO resp = new ServiceOpReportChartRespVO();
        // 服务运营趋势(综合,这里以救援趋势为代表)
        resp.setOperateTrendList(toTrendList(reportStatMapper.rescueInfoDailyCount(
                LocalDate.now().minusDays(TREND_DAYS).atStartOfDay())));
        // 服务类型分布
        long rescue = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<>());
        long reserve = reserveListMapper.selectCount(new LambdaQueryWrapperX<>());
        long sug = suggestionMapper.selectCount(new LambdaQueryWrapperX<>());
        long appeal = userAppealMapper.selectCount(new LambdaQueryWrapperX<>());
        long dispute = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<>());
        long push = spacePushMapper.selectCount(new LambdaQueryWrapperX<>());
        List<Map<String, Object>> serviceTypes = new ArrayList<>();
        for (Object[] o : new Object[][]{
                {"救援", rescue}, {"预约", reserve}, {"意见", sug},
                {"申诉", appeal}, {"调解", dispute}, {"推送", push}}) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("serviceType", o[0]);
            m.put("count", o[1]);
            serviceTypes.add(m);
        }
        resp.setServiceTypeCountList(serviceTypes);
        // 核心指标
        Map<String, Object> core = new LinkedHashMap<>();
        long rescueCompleted = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .eq(RescueInfoDO::getStatus, "已完成"));
        long reserveSuccess = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成")));
        long appealHandled = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .eq(UserAppealDO::getStatus, "已完成"));
        core.put("rescueFinishRate", toRate(rescueCompleted, rescue));
        core.put("reserveSuccessRate", toRate(reserveSuccess, reserve));
        core.put("appealHandleRate", toRate(appealHandled, appeal));
        resp.setCoreIndex(core);
        return resp;
    }

    // ===================================================================
    // 自定义报表 + 时间尺度报表
    // ===================================================================

    @Override
    public Map<String, Object> customReport(LocalDateTime startTime, LocalDateTime endTime, Map<String, Object> filters) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("startTime", startTime);
        result.put("endTime", endTime);
        Object moduleObj = filters == null ? null : filters.get("module");
        String module = moduleObj == null ? "all" : moduleObj.toString();
        switch (module) {
            case "rescue":
                result.put("rescue", statRescueRange(startTime, endTime));
                break;
            case "reserve":
                result.put("reserve", statReserveRange(startTime, endTime));
                break;
            case "all":
            default:
                result.put("rescue", statRescueRange(startTime, endTime));
                result.put("reserve", statReserveRange(startTime, endTime));
                result.put("suggestion", suggestionMapper.selectCount(rangeWrapper(startTime, endTime)));
                result.put("userAppeal", userAppealMapper.selectCount(rangeWrapper(startTime, endTime)));
                result.put("disputeMediate", disputeMediateMapper.selectCount(rangeWrapper(startTime, endTime)));
                result.put("spacePush", spacePushMapper.selectCount(rangeWrapper(startTime, endTime)));
                break;
        }
        return result;
    }

    @Override
    public TimeReportRespVO generateReport(ReportPeriodEnum period, LocalDate baseDate) {
        LocalDate base = baseDate == null ? LocalDate.now() : baseDate;
        LocalDateTime start = period.startOf(base);
        LocalDateTime end = period.endOf(base);

        TimeReportRespVO resp = new TimeReportRespVO();
        resp.setPeriod(period.name());
        resp.setPeriodLabel(period.getLabel());
        resp.setStartTime(start);
        resp.setEndTime(end);
        resp.setGenerateTime(LocalDateTime.now());

        Map<String, Object> mods = new LinkedHashMap<>();
        mods.put("rescue", statRescueRange(start, end));
        mods.put("reserve", statReserveRange(start, end));
        mods.put("suggestion", suggestionMapper.selectCount(rangeWrapper(start, end)));
        mods.put("userAppeal", userAppealMapper.selectCount(rangeWrapper(start, end)));
        mods.put("disputeMediate", disputeMediateMapper.selectCount(rangeWrapper(start, end)));
        mods.put("spacePush", spacePushMapper.selectCount(rangeWrapper(start, end)));
        mods.put("chargeParkMap", chargeParkMapMapper.selectCount(rangeWrapper(start, end)));
        mods.put("nearStation", nearStationMapper.selectCount(rangeWrapper(start, end)));
        mods.put("spaceLocation", spaceLocationMapper.selectCount(rangeWrapper(start, end)));
        mods.put("pathPlan", pathPlanMapper.selectCount(rangeWrapper(start, end)));
        resp.setModules(mods);
        return resp;
    }

    private Map<String, Object> statRescueRange(LocalDateTime start, LocalDateTime end) {
        long total = rescueInfoMapper.selectCount(rangeWrapper(start, end));
        long completed = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, start)
                .leIfPresent(RescueInfoDO::getCreateTime, end)
                .eq(RescueInfoDO::getStatus, "已完成"));
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("total", total);
        m.put("completed", completed);
        m.put("completionRate", toRate(completed, total));
        return m;
    }

    private Map<String, Object> statReserveRange(LocalDateTime start, LocalDateTime end) {
        long total = reserveListMapper.selectCount(rangeWrapper(start, end));
        long success = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, start)
                .leIfPresent(ReserveListDO::getCreateTime, end)
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成")));
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("total", total);
        m.put("success", success);
        m.put("successRate", toRate(success, total));
        return m;
    }

    // ===================================================================
    // 工具方法
    // ===================================================================

    /** 把 ReportStatMapper 的 [{day, cnt}] 转为前端 [{date, count}] */
    private List<Map<String, Object>> toTrendList(List<Map<String, Object>> rows) {
        List<Map<String, Object>> out = new ArrayList<>();
        if (rows == null) return out;
        for (Map<String, Object> r : rows) {
            Map<String, Object> m = new LinkedHashMap<>();
            Object day = r.get("day");
            m.put("date", day == null ? "" : day.toString());
            m.put("count", numLong(r, "cnt"));
            out.add(m);
        }
        return out;
    }

    /** part/total → BigDecimal 百分比(保留 2 位) */
    private BigDecimal toRate(long part, long total) {
        if (total <= 0) return BigDecimal.ZERO;
        return new BigDecimal(part * 100.0 / total).setScale(2, RoundingMode.HALF_UP);
    }

    private long numLong(Map<String, Object> row, String key) {
        if (row == null) return 0L;
        Object v = row.get(key);
        if (v == null) return 0L;
        if (v instanceof Number) return ((Number) v).longValue();
        try {
            return Long.parseLong(v.toString());
        } catch (NumberFormatException ex) {
            return 0L;
        }
    }

    private double numDouble(Map<String, Object> row, String key) {
        if (row == null) return 0.0;
        Object v = row.get(key);
        if (v == null) return 0.0;
        if (v instanceof Number) return ((Number) v).doubleValue();
        try {
            return Double.parseDouble(v.toString());
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }

    /** 时间区间 wrapper(基于 createTime,raw SQL 兼容所有 DO) */
    @SuppressWarnings("rawtypes")
    private <T> LambdaQueryWrapperX<T> rangeWrapper(LocalDateTime start, LocalDateTime end) {
        LambdaQueryWrapperX<T> w = new LambdaQueryWrapperX<>();
        if (start != null) {
            w.apply("create_time >= {0}", start);
        }
        if (end != null) {
            w.apply("create_time <= {0}", end);
        }
        return w;
    }

    // ===================================================================
    // count-by-status 系列(给前端卡片角标用)
    // 一次 GROUP BY 完成所有状态计数,缺失状态补 0,自动带 yudao 多租户隔离
    // ===================================================================

    /** 把 GROUP BY 的 [{k,cnt}] 结果按枚举顺序填充,缺失状态补 0 */
    private Map<String, Long> toCountByStatus(List<Map<String, Object>> rows, String... statusOrder) {
        Map<String, Long> raw = new LinkedHashMap<>();
        if (rows != null) {
            for (Map<String, Object> r : rows) {
                Object k = r.get("k");
                if (k != null) {
                    raw.put(k.toString(), numLong(r, "cnt"));
                }
            }
        }
        Map<String, Long> result = new LinkedHashMap<>();
        for (String status : statusOrder) {
            result.put(status, raw.getOrDefault(status, 0L));
        }
        return result;
    }

    @Override
    public Map<String, Long> countRescueInfoByStatus() {
        return toCountByStatus(reportStatMapper.rescueInfoCountByStatus(),
                Arrays.stream(RescueStatusEnum.values()).map(RescueStatusEnum::getLabel).toArray(String[]::new));
    }

    @Override
    public Map<String, Long> countReserveListByStatus() {
        return toCountByStatus(reportStatMapper.reserveListCountByStatus(),
                Arrays.stream(ReserveStatusEnum.values()).map(ReserveStatusEnum::getLabel).toArray(String[]::new));
    }

    @Override
    public Map<String, Long> countSpacePushByStatus() {
        return toCountByStatus(reportStatMapper.spacePushCountByStatus(),
                Arrays.stream(SpacePushStatusEnum.values()).map(SpacePushStatusEnum::getLabel).toArray(String[]::new));
    }

    @Override
    public Map<String, Long> countSuggestionByStatus() {
        return toCountByStatus(reportStatMapper.suggestionCountByStatus(),
                Arrays.stream(SuggestionStatusEnum.values()).map(SuggestionStatusEnum::getLabel).toArray(String[]::new));
    }

    @Override
    public Map<String, Long> countUserAppealByStatus() {
        return toCountByStatus(reportStatMapper.userAppealCountByStatus(),
                Arrays.stream(UserAppealStatusEnum.values()).map(UserAppealStatusEnum::getLabel).toArray(String[]::new));
    }

    @Override
    public Map<String, Long> countDisputeMediateByStatus() {
        return toCountByStatus(reportStatMapper.disputeMediateCountByStatus(),
                Arrays.stream(DisputeMediateStatusEnum.values()).map(DisputeMediateStatusEnum::getLabel).toArray(String[]::new));
    }

}
