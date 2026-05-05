package cn.iocoder.yudao.module.chargepark.carservice.service.decision;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.ChargeParkMapChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealChartRespVO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportRespVO;
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
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.inspectop.api.space.SpaceMonitorApi;
import cn.iocoder.yudao.module.inspectop.api.space.dto.SpaceMonitorRespDTO;
import cn.iocoder.yudao.module.stationresource.api.parking.ParkingSpaceInfoApi;
import cn.iocoder.yudao.module.stationresource.api.parking.dto.ParkingSpaceInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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
@Slf4j
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
    @Resource private StationInfoApi stationInfoApi;
    @Resource private ParkingSpaceInfoApi parkingSpaceInfoApi;
    @Resource private SpaceMonitorApi spaceMonitorApi;

    // ===================================================================
    // chart 接口实现(12 个,严格按 05 接口文档字段命名)
    // ===================================================================

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-rescue#30s")
    public RescueInfoChartRespVO chartRescue(LocalDateTime startTime, LocalDateTime endTime) {
        RescueInfoChartRespVO resp = new RescueInfoChartRespVO();
        long total = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, startTime)
                .leIfPresent(RescueInfoDO::getCreateTime, endTime));
        long waitDispatch = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .eq(RescueInfoDO::getStatus, "待派发")
                .geIfPresent(RescueInfoDO::getCreateTime, startTime)
                .leIfPresent(RescueInfoDO::getCreateTime, endTime));
        long waitClaim = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .eq(RescueInfoDO::getStatus, "待认领")
                .geIfPresent(RescueInfoDO::getCreateTime, startTime)
                .leIfPresent(RescueInfoDO::getCreateTime, endTime));
        long completed = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .eq(RescueInfoDO::getStatus, "已完成")
                .geIfPresent(RescueInfoDO::getCreateTime, startTime)
                .leIfPresent(RescueInfoDO::getCreateTime, endTime));
        resp.setWaitDispatchCount((int) waitDispatch);
        resp.setWaitClaimCount((int) waitClaim);
        resp.setWaitRescueCount((int) (waitDispatch + waitClaim));
        resp.setFinishRate(toRate(completed, total));
        resp.setTrendList(toTrendList(reportStatMapper.rescueInfoDailyCount(
                startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay(),
                endTime)));
        // 救援位置列表:按 startTime/endTime 过滤,未传则返最近 N 条
        List<RescueInfoDO> recent = rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, startTime)
                .leIfPresent(RescueInfoDO::getCreateTime, endTime)
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
            log.warn("[fillLonLat] 坐标解析失败 location={}", location);
            m.put("lon", null);
            m.put("lat", null);
        }
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-charge-park-map#30s",
            unless = "#result == null || #result.stationSpaceList == null || #result.stationSpaceList.isEmpty()")
    public ChargeParkMapChartRespVO chartChargeParkMap(LocalDateTime startTime, LocalDateTime endTime) {
        ChargeParkMapChartRespVO resp = new ChargeParkMapChartRespVO();

        // 查询成功率 + 平均响应时长(保留原有聚合 SQL,按 charge_park_map 查询记录)
        Map<String, Object> agg = reportStatMapper.chargeParkMapAggregate(startTime, endTime);
        long total = numLong(agg, "total");
        long success = numLong(agg, "success_cnt");
        double avgResp = numDouble(agg, "avg_resp");
        // 前端按百分比数值直接展示(返 1 会显示 "1%"),这里统一乘 100 到 0~100 区间
        resp.setQuerySuccessRate(toRate(success, total).multiply(BigDecimal.valueOf(100)));
        resp.setAvgResponseDuration((int) Math.round(avgResp));

        // 场站分布 & 热力图数据 → 通过 Feign 从 stationresource 模块取,下游挂掉时降级为空列表
        List<StationInfoRespDTO> stations = safeListStations();

        List<Map<String, Object>> spaceList = stations.stream().map(s -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("lon", s.getLon());
            m.put("lat", s.getLat());
            m.put("stationName", s.getName());
            m.put("emptySpace", nz(s.getEmptySpace()));
            return m;
        }).collect(Collectors.toList());
        resp.setStationSpaceList(spaceList);

        // 热力图:value = 使用率 = spaceCount / spaceTotal,0-1 小数
        List<Map<String, Object>> heatList = stations.stream().map(s -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("lon", s.getLon());
            m.put("lat", s.getLat());
            int tot = nz(s.getSpaceTotal());
            int used = nz(s.getSpaceCount());
            double value = tot == 0 ? 0.0
                    : BigDecimal.valueOf((double) used / tot).setScale(2, RoundingMode.HALF_UP).doubleValue();
            m.put("value", value);
            return m;
        }).collect(Collectors.toList());
        resp.setHeatMapData(heatList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-near-station#30s",
            unless = "#result == null || #result.stationLocationList == null || #result.stationLocationList.isEmpty()")
    public NearStationChartRespVO chartNearStation(LocalDateTime startTime, LocalDateTime endTime,
                                                    Double lon, Double lat) {
        NearStationChartRespVO resp = new NearStationChartRespVO();
        List<StationInfoRespDTO> stations = safeListStations();

        // 地图点位:全部场站直接渲染,不按距离过滤
        List<Map<String, Object>> stationList = stations.stream()
                .filter(s -> s.getLon() != null && s.getLat() != null)
                .map(s -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("id", s.getId());
                    m.put("lon", s.getLon());
                    m.put("lat", s.getLat());
                    m.put("stationName", s.getName());
                    m.put("hasEmpty", nz(s.getEmptySpace()) > 0);
                    return m;
                }).collect(Collectors.toList());
        resp.setStationLocationList(stationList);

        // 距离分布柱状图:以"当前位置"(前端传入的 lon/lat)为参考,所有场站按到当前位置的距离分桶
        // 未传当前位置时,退化为查询时间窗内最近一条 near_station 的查询位置作为参考点
        Double refLon = lon, refLat = lat;
        if (refLon == null || refLat == null) {
            List<NearStationDO> queries = nearStationMapper.selectList(
                    new LambdaQueryWrapperX<NearStationDO>()
                            .geIfPresent(NearStationDO::getCreateTime, startTime)
                            .leIfPresent(NearStationDO::getCreateTime, endTime)
                            .orderByDesc(NearStationDO::getCreateTime).last("LIMIT 1"));
            if (!queries.isEmpty()) {
                double[] p = parseLonLat(queries.get(0).getQueryLocation());
                if (p != null) { refLon = p[0]; refLat = p[1]; }
            }
        }
        resp.setDistanceCountList(buildDistanceBuckets(refLon, refLat, stations));

        // 卡片:周边场站数 = 全部场站数;空位场站数 = 空位数 > 0 的场站数(均不限距离)
        resp.setTotalStationCount((int) stations.stream()
                .filter(s -> s.getLon() != null && s.getLat() != null)
                .count());
        resp.setEmptyStationCount((int) stations.stream()
                .filter(s -> s.getLon() != null && s.getLat() != null)
                .filter(s -> nz(s.getEmptySpace()) > 0)
                .count());
        return resp;
    }

    /** 4 个固定桶:0-1km / 1-3km / 3-5km / >5km。基于"当前位置"(refLon/refLat)对所有场站精确计算距离 */
    private List<Map<String, Object>> buildDistanceBuckets(Double refLon, Double refLat,
                                                           List<StationInfoRespDTO> stations) {
        String[] labels = {"0-1km", "1-3km", "3-5km", ">5km"};
        long[] counts = new long[4];
        if (refLon != null && refLat != null) {
            for (StationInfoRespDTO s : stations) {
                if (s.getLon() == null || s.getLat() == null) continue;
                double dKm = haversineKm(refLon, refLat, s.getLon().doubleValue(), s.getLat().doubleValue());
                counts[pickBucket(dKm)]++;
            }
        }
        List<Map<String, Object>> result = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("distance", labels[i]);
            m.put("count", (int) counts[i]);
            result.add(m);
        }
        return result;
    }

    /** @return 0/1/2/3 对应 0-1km / 1-3km / 3-5km / >5km */
    private int pickBucket(double km) {
        if (km <= 1) return 0;
        if (km <= 3) return 1;
        if (km <= 5) return 2;
        return 3;
    }

    private double[] parseLonLat(String location) {
        if (location == null || location.isEmpty()) return null;
        String[] parts = location.split(",");
        if (parts.length != 2) return null;
        try {
            return new double[]{Double.parseDouble(parts[0].trim()), Double.parseDouble(parts[1].trim())};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Haversine 大圆距离(km),地球平均半径 6371km */
    private double haversineKm(double lon1, double lat1, double lon2, double lat2) {
        double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /** Feign 调用 stationresource 失败时降级为空列表,不让一个下游模块拖垮本模块 */
    private List<StationInfoRespDTO> safeListStations() {
        try {
            return unwrapList(stationInfoApi.listStations());
        } catch (Exception ex) {
            log.warn("[safeListStations] stationresource RPC 调用失败,降级返回空列表", ex);
            return new ArrayList<>();
        }
    }

    /** Feign 调用 inspectop 失败时降级为空列表 */
    private List<SpaceMonitorRespDTO> safeListLatestSpaceMonitors() {
        try {
            return unwrapList(spaceMonitorApi.listLatestSpaceMonitors());
        } catch (Exception ex) {
            log.warn("[safeListLatestSpaceMonitors] inspectop RPC 调用失败,降级返回空列表", ex);
            return new ArrayList<>();
        }
    }

    private int nz(Integer v) { return v == null ? 0 : v; }

    private <T> T unwrap(CommonResult<T> result) {
        return result == null ? null : result.getData();
    }

    private <T> List<T> unwrapList(CommonResult<List<T>> result) {
        List<T> data = result == null ? null : result.getData();
        return data == null ? new ArrayList<>() : data;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-space-push#30s")
    public SpacePushChartRespVO chartSpacePush(LocalDateTime startTime, LocalDateTime endTime) {
        SpacePushChartRespVO resp = new SpacePushChartRespVO();
        // 卡片统计与折线图聚合维度一致：以 push_time 为准、限定近 30 天，剔除尚未推送（push_time 为 null）
        LocalDateTime effectiveStart = startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay();
        LambdaQueryWrapperX<SpacePushDO> totalWrapper = new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getPushTime, effectiveStart)
                .leIfPresent(SpacePushDO::getPushTime, endTime);
        totalWrapper.isNotNull(SpacePushDO::getPushTime);
        long total = spacePushMapper.selectCount(totalWrapper);

        LambdaQueryWrapperX<SpacePushDO> successWrapper = new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getPushTime, effectiveStart)
                .leIfPresent(SpacePushDO::getPushTime, endTime);
        successWrapper.isNotNull(SpacePushDO::getPushTime).eq(SpacePushDO::getPushResult, "成功");
        long success = spacePushMapper.selectCount(successWrapper);

        resp.setTotalPushCount((int) total);
        resp.setPushSuccessRate(toRate(success, total));
        resp.setPushTrendList(toTrendList(reportStatMapper.spacePushDailyCount(effectiveStart, endTime)));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-reserve#30s")
    public ReserveListChartRespVO chartReserve(LocalDateTime startTime, LocalDateTime endTime) {
        ReserveListChartRespVO resp = new ReserveListChartRespVO();
        long total = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getReserveTime, startTime)
                .leIfPresent(ReserveListDO::getReserveTime, endTime));
        long succeeded = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成"))
                .geIfPresent(ReserveListDO::getReserveTime, startTime)
                .leIfPresent(ReserveListDO::getReserveTime, endTime));
        resp.setTotalReserveCount((int) total);
        resp.setReserveSuccessRate(toRate(succeeded, total));
        resp.setReserveTrendList(toTrendList(reportStatMapper.reserveListDailyCount(
                startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay(),
                endTime)));
        List<Map<String, Object>> typeRows = reportStatMapper.reserveListTypeDistribution(startTime, endTime);
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
    @Cacheable(cacheNames = "carservice:report:chart-space-location#30s",
            unless = "#result == null || #result.spaceLocationList == null || #result.spaceLocationList.isEmpty()")
    public SpaceLocationChartRespVO chartSpaceLocation(LocalDateTime startTime, LocalDateTime endTime) {
        SpaceLocationChartRespVO resp = new SpaceLocationChartRespVO();
        long total = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, startTime)
                .leIfPresent(SpaceLocationDO::getCreateTime, endTime));
        long success = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<SpaceLocationDO>()
                .eq(SpaceLocationDO::getLocationResult, "成功")
                .geIfPresent(SpaceLocationDO::getCreateTime, startTime)
                .leIfPresent(SpaceLocationDO::getCreateTime, endTime));
        resp.setTotalQueryCount((int) total);
        resp.setLocationSuccessRate(toRate(success, total));
        // 车位位置展示:时间窗内的 space_location 查询事件,每条拼 {lon, lat, spaceNo, plateNo}
        List<SpaceLocationDO> queries = spaceLocationMapper.selectList(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, startTime)
                .leIfPresent(SpaceLocationDO::getCreateTime, endTime));
        Set<Long> spaceIds = queries.stream().map(SpaceLocationDO::getSpaceId)
                .filter(Objects::nonNull).collect(Collectors.toSet());
        // 两次 Feign 批量拉:车位监测坐标 + 车位编号
        Map<Long, SpaceMonitorRespDTO> monitorMap = safeSpaceMonitorMap(spaceIds);
        Map<Long, ParkingSpaceInfoRespDTO> parkingMap = safeParkingSpaceMap(spaceIds);
        List<Map<String, Object>> spaceLocationList = queries.stream().map(q -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("id", q.getId());
            m.put("spaceId", q.getSpaceId());
            SpaceMonitorRespDTO mon = monitorMap.get(q.getSpaceId());
            m.put("lon", mon == null ? null : mon.getLongitude());
            m.put("lat", mon == null ? null : mon.getLatitude());
            ParkingSpaceInfoRespDTO info = parkingMap.get(q.getSpaceId());
            m.put("spaceNo", info == null ? null : info.getSpaceNo());
            m.put("plateNo", q.getPlateNo());
            m.put("locationResult", q.getLocationResult());
            return m;
        }).collect(Collectors.toList());
        resp.setSpaceLocationList(spaceLocationList);
        return resp;
    }

    /** 按 spaceIds 批量拉车位监测(去重最新坐标),下游异常降级空 map */
    private Map<Long, SpaceMonitorRespDTO> safeSpaceMonitorMap(Set<Long> spaceIds) {
        if (spaceIds == null || spaceIds.isEmpty()) return Collections.emptyMap();
        List<SpaceMonitorRespDTO> all = safeListLatestSpaceMonitors();
        return all.stream()
                .filter(sm -> sm.getSpaceId() != null && spaceIds.contains(sm.getSpaceId()))
                .collect(Collectors.toMap(SpaceMonitorRespDTO::getSpaceId, sm -> sm, (a, b) -> a));
    }

    /** 按 spaceIds 批量拉 parking_space_info(拿 spaceNo),下游异常降级空 map */
    private Map<Long, ParkingSpaceInfoRespDTO> safeParkingSpaceMap(Set<Long> spaceIds) {
        if (spaceIds == null || spaceIds.isEmpty()) return Collections.emptyMap();
        try {
            return parkingSpaceInfoApi.getSpaceMap(spaceIds);
        } catch (Exception ex) {
            log.warn("[safeParkingSpaceMap] stationresource ParkingSpaceInfo RPC 调用失败,降级空 map", ex);
            return Collections.emptyMap();
        }
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-path-plan#30s")
    public PathPlanChartRespVO chartPathPlan(LocalDateTime startTime, LocalDateTime endTime) {
        PathPlanChartRespVO resp = new PathPlanChartRespVO();
        Map<String, Object> agg = reportStatMapper.pathPlanAggregate(startTime, endTime);
        long total = numLong(agg, "total");
        long success = numLong(agg, "success_cnt");
        resp.setTotalPlanCount((int) total);
        resp.setPlanSuccessRate(toRate(success, total));
        // 路径规划地图:严格按 05 接口文档 sample,每条 = {path: [[lon,lat],[lon,lat],...]}
        // DB 只存 start/end 两点,path 数组长度=2,前端用高德 SDK 补中间 waypoints
        List<PathPlanDO> recent = pathPlanMapper.selectList(new LambdaQueryWrapperX<PathPlanDO>()
                .geIfPresent(PathPlanDO::getCreateTime, startTime)
                .leIfPresent(PathPlanDO::getCreateTime, endTime)
                .orderByDesc(PathPlanDO::getId).last("LIMIT 200"));
        List<Map<String, Object>> pathList = recent.stream().map(r -> {
            Map<String, Object> m = new LinkedHashMap<>();
            List<double[]> path = new ArrayList<>(2);
            double[] startXY = parseLonLat(r.getStartLocation());
            double[] endXY = parseLonLat(r.getEndLocation());
            if (startXY != null) path.add(startXY);
            if (endXY != null) path.add(endXY);
            m.put("path", path);
            return m;
        }).collect(Collectors.toList());
        resp.setPathList(pathList);
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-suggestion#30s")
    public SuggestionChartRespVO chartSuggestion(LocalDateTime startTime, LocalDateTime endTime) {
        SuggestionChartRespVO resp = new SuggestionChartRespVO();
        long total = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, startTime)
                .leIfPresent(SuggestionDO::getCreateTime, endTime));
        long pending = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .eq(SuggestionDO::getStatus, "待处理")
                .geIfPresent(SuggestionDO::getCreateTime, startTime)
                .leIfPresent(SuggestionDO::getCreateTime, endTime));
        long completed = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .eq(SuggestionDO::getStatus, "已完成")
                .geIfPresent(SuggestionDO::getCreateTime, startTime)
                .leIfPresent(SuggestionDO::getCreateTime, endTime));
        resp.setWaitHandleCount((int) pending);
        resp.setHandleFinishRate(toRate(completed, total));
        resp.setSuggestionTrendList(toTrendList(reportStatMapper.suggestionDailyCount(
                startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay(),
                endTime)));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-user-appeal#30s")
    public UserAppealChartRespVO chartUserAppeal(LocalDateTime startTime, LocalDateTime endTime) {
        UserAppealChartRespVO resp = new UserAppealChartRespVO();
        long total = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, startTime)
                .leIfPresent(UserAppealDO::getCreateTime, endTime));
        long pending = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .in(UserAppealDO::getStatus, Arrays.asList("待审核", "待处置"))
                .geIfPresent(UserAppealDO::getCreateTime, startTime)
                .leIfPresent(UserAppealDO::getCreateTime, endTime));
        long completed = userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .eq(UserAppealDO::getStatus, "已完成")
                .geIfPresent(UserAppealDO::getCreateTime, startTime)
                .leIfPresent(UserAppealDO::getCreateTime, endTime));
        resp.setWaitAppealCount((int) pending);
        resp.setHandleFinishRate(toRate(completed, total));
        resp.setAppealTrendList(toTrendList(reportStatMapper.userAppealDailyCount(
                startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay(),
                endTime)));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-dispute-mediate#30s")
    public DisputeMediateChartRespVO chartDisputeMediate(LocalDateTime startTime, LocalDateTime endTime) {
        DisputeMediateChartRespVO resp = new DisputeMediateChartRespVO();
        long total = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, startTime)
                .leIfPresent(DisputeMediateDO::getCreateTime, endTime));
        long pending = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .eq(DisputeMediateDO::getStatus, "待调解")
                .geIfPresent(DisputeMediateDO::getCreateTime, startTime)
                .leIfPresent(DisputeMediateDO::getCreateTime, endTime));
        long completed = disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .eq(DisputeMediateDO::getStatus, "已完成")
                .geIfPresent(DisputeMediateDO::getCreateTime, startTime)
                .leIfPresent(DisputeMediateDO::getCreateTime, endTime));
        resp.setWaitMediateCount((int) pending);
        resp.setMediateFinishRate(toRate(completed, total));
        resp.setDisputeTrendList(toTrendList(reportStatMapper.disputeMediateDailyCount(
                startTime != null ? startTime : LocalDate.now().minusDays(TREND_DAYS).atStartOfDay(),
                endTime)));
        return resp;
    }

    @Override
    @Cacheable(cacheNames = "carservice:report:chart-wording-mgmt#30s")
    public WordingMgmtChartRespVO chartWordingMgmt() {
        WordingMgmtChartRespVO resp = new WordingMgmtChartRespVO();
        // chart-wording-mgmt 接口无时间参数,传 null 不过滤(全量统计当前生效/类型分布)
        Map<String, Object> agg = reportStatMapper.wordingMgmtAggregate(null, null);
        long total = numLong(agg, "total");
        long enabled = numLong(agg, "enabled_cnt");
        resp.setEnableWordingCount((int) enabled);
        // matchRate = 已生效话术数 / 总话术数,反映话术库配置完整度
        resp.setMatchRate(toRate(enabled, total));
        List<Map<String, Object>> typeRows = reportStatMapper.wordingMgmtTypeDistribution(null, null);
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
    public ServiceOpReportChartRespVO chartServiceOpReport(LocalDateTime startTime, LocalDateTime endTime) {
        ServiceOpReportChartRespVO resp = new ServiceOpReportChartRespVO();
        // 默认近半年,给月度趋势图留足月份
        LocalDateTime start = startTime != null ? startTime : LocalDate.now().minusMonths(6).atStartOfDay();
        LocalDateTime end = endTime;

        // ---- operateTrendList:按 %Y-%m 聚合三率 ----
        Map<String, long[]> rescueMap = monthlyRateToMap(reportStatMapper.rescueInfoMonthlyRate(start, end));
        Map<String, long[]> reserveMap = monthlyRateToMap(reportStatMapper.reserveListMonthlyRate(start, end));
        // 投诉 = suggestion + user_appeal + dispute_mediate(与 computeRates 口径一致)
        Map<String, long[]> complaintMap = mergeMonthly(
                reportStatMapper.suggestionMonthlyRate(start, end),
                reportStatMapper.userAppealMonthlyRate(start, end),
                reportStatMapper.disputeMediateMonthlyRate(start, end));
        // 合并月份集合,按时间升序
        java.util.TreeSet<String> months = new java.util.TreeSet<>();
        months.addAll(rescueMap.keySet());
        months.addAll(reserveMap.keySet());
        months.addAll(complaintMap.keySet());
        List<Map<String, Object>> trend = new ArrayList<>();
        for (String ym : months) {
            long[] r = rescueMap.getOrDefault(ym, new long[2]);
            long[] v = reserveMap.getOrDefault(ym, new long[2]);
            long[] c = complaintMap.getOrDefault(ym, new long[2]);
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("date", ym);
            row.put("rescueFinishRate", toRate(r[1], r[0]));
            row.put("reserveSuccessRate", toRate(v[1], v[0]));
            row.put("complaintHandleRate", toRate(c[1], c[0]));
            trend.add(row);
        }
        resp.setOperateTrendList(trend);

        // ---- serviceTypeCountList:3 类 {type, count}(受时间窗影响)----
        long rescueCnt = rescueInfoMapper.selectCount(rangeWrapper(start, end));
        long reserveCnt = reserveListMapper.selectCount(rangeWrapper(start, end));
        long complaintCnt = suggestionMapper.selectCount(rangeWrapper(start, end))
                + userAppealMapper.selectCount(rangeWrapper(start, end))
                + disputeMediateMapper.selectCount(rangeWrapper(start, end));
        List<Map<String, Object>> serviceTypes = new ArrayList<>();
        for (Object[] o : new Object[][]{
                {"救援服务", rescueCnt}, {"预约服务", reserveCnt}, {"投诉服务", complaintCnt}}) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("type", o[0]);
            m.put("count", o[1]);
            serviceTypes.add(m);
        }
        resp.setServiceTypeCountList(serviceTypes);

        // ---- coreIndex:三率(复用 computeRates,口径与 page / computeRates 一致)----
        Rates rates = computeRates(start, end);
        Map<String, Object> core = new LinkedHashMap<>();
        core.put("rescueFinishRate", rates.rescueFinishRate);
        core.put("reserveSuccessRate", rates.reserveSuccessRate);
        core.put("complaintHandleRate", rates.complaintHandleRate);
        resp.setCoreIndex(core);
        return resp;
    }

    /** 单表月度率结果 → {ym: [total, done]} */
    private Map<String, long[]> monthlyRateToMap(List<Map<String, Object>> rows) {
        Map<String, long[]> m = new LinkedHashMap<>();
        for (Map<String, Object> r : rows) {
            String ym = String.valueOf(r.get("ym"));
            m.put(ym, new long[]{numLong(r, "total"), numLong(r, "done_cnt")});
        }
        return m;
    }

    /** 多个表的月度率按 ym 求和合并(用于投诉 = 意见+申诉+调解) */
    @SafeVarargs
    private final Map<String, long[]> mergeMonthly(List<Map<String, Object>>... lists) {
        Map<String, long[]> merged = new LinkedHashMap<>();
        for (List<Map<String, Object>> rows : lists) {
            for (Map<String, Object> r : rows) {
                String ym = String.valueOf(r.get("ym"));
                long[] cur = merged.computeIfAbsent(ym, k -> new long[2]);
                cur[0] += numLong(r, "total");
                cur[1] += numLong(r, "done_cnt");
            }
        }
        return merged;
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
    /**
     * 算比率,返回 0~1 之间的小数(如 0.97),保留 2 位精度。
     * 所有 chart 接口的 *Rate 字段按 05 文档统一格式。
     */
    private BigDecimal toRate(long part, long total) {
        if (total <= 0) return BigDecimal.ZERO;
        return new BigDecimal((double) part / total).setScale(2, RoundingMode.HALF_UP);
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

    // ===================================================================
    // 服务运营报表分页 - 动态聚合,不建专表(遵循客户"不独立新建数据表"架构)
    // ===================================================================

    @Override
    @Cacheable(cacheNames = "carservice:report:serviceopreport-page#30s",
            unless = "#result == null || #result.list == null || #result.list.isEmpty()")
    public PageResult<ServiceOpReportRespVO> pageServiceOpReport(ServiceOpReportPageReqVO reqVO) {
        // 1. 参数默认处理
        ReportPeriodEnum period = ReportPeriodEnum.fromTimeScale(reqVO.getTimeScale());
        LocalDateTime[] range = reqVO.getStatTime();
        LocalDateTime rangeStart = (range != null && range.length >= 1 && range[0] != null)
                ? range[0] : LocalDateTime.now().minusYears(1);
        LocalDateTime rangeEnd = (range != null && range.length >= 2 && range[1] != null)
                ? range[1] : LocalDateTime.now();
        String reportType = reqVO.getReportType() != null ? reqVO.getReportType() : period.getLabel();

        // 2. 按 period 切分 [rangeStart, rangeEnd] 成多个 anchor(纯日期游标,不查 DB)
        List<LocalDate> anchors = new ArrayList<>();
        LocalDate cursor = period.startOf(rangeStart.toLocalDate()).toLocalDate();
        LocalDate endDate = rangeEnd.toLocalDate();
        while (!cursor.isAfter(endDate)) {
            anchors.add(cursor);
            cursor = period.nextPeriod(cursor);
        }

        // 3. 先按 pageNo/pageSize 截取当前页的 anchor 列表
        //    避免一次性对全部 anchor 调 computeRates(每个 anchor = 30 次 count SQL,
        //    N 月份×30 上百条 SQL,性能灾难,前端转圈)
        int pageNo = reqVO.getPageNo() == null ? 1 : reqVO.getPageNo();
        int pageSize = reqVO.getPageSize() == null ? 10 : reqVO.getPageSize();
        int from = Math.min((pageNo - 1) * pageSize, anchors.size());
        int to = Math.min(from + pageSize, anchors.size());
        List<LocalDate> pageAnchors = anchors.subList(from, to);

        // 4. 一次性加载全范围业务数据,内存按 period 分组,避免 per-anchor 30 次 count SQL
        //    总 SQL 数 = 5 次 selectList(rescue/reserve/suggestion/userAppeal/disputeMediate) 不随 pageSize 变化
        LocalDateTime loadStart = pageAnchors.isEmpty() ? rangeStart
                : period.startOf(pageAnchors.get(0).minusYears(1));            // 最早到 yoy 窗口起点
        LocalDateTime loadEnd = pageAnchors.isEmpty() ? rangeEnd
                : period.endOf(pageAnchors.get(pageAnchors.size() - 1));       // 最晚到当前最新 anchor
        Map<String, PeriodStat> rescueMap = loadAndGroupRescue(loadStart, loadEnd, period);
        Map<String, PeriodStat> reserveMap = loadAndGroupReserve(loadStart, loadEnd, period);
        Map<String, PeriodStat> complaintMap = loadAndGroupComplaint(loadStart, loadEnd, period);

        List<ServiceOpReportRespVO> pageList = new ArrayList<>(pageAnchors.size());
        for (LocalDate anchor : pageAnchors) {
            pageList.add(buildFromMaps(reportType, period, anchor, rescueMap, reserveMap, complaintMap));
        }

        PageResult<ServiceOpReportRespVO> result = new PageResult<>();
        result.setList(pageList);
        result.setTotal((long) anchors.size()); // total 是所有 anchor 数,供前端算总页数
        return result;
    }

    /** 按 period 聚合后的单元格数据 */
    private static class PeriodStat {
        long total = 0;
        long done = 0;
    }

    private Map<String, PeriodStat> loadAndGroupRescue(LocalDateTime start, LocalDateTime end, ReportPeriodEnum period) {
        List<RescueInfoDO> list = rescueInfoMapper.selectList(rangeWrapper(start, end));
        Map<String, PeriodStat> m = new java.util.HashMap<>();
        for (RescueInfoDO r : list) {
            if (r.getCreateTime() == null) continue;
            String key = period.formatStatPeriod(r.getCreateTime().toLocalDate());
            PeriodStat ps = m.computeIfAbsent(key, k -> new PeriodStat());
            ps.total++;
            if ("已完成".equals(r.getStatus())) ps.done++;
        }
        return m;
    }

    private Map<String, PeriodStat> loadAndGroupReserve(LocalDateTime start, LocalDateTime end, ReportPeriodEnum period) {
        List<ReserveListDO> list = reserveListMapper.selectList(rangeWrapper(start, end));
        Map<String, PeriodStat> m = new java.util.HashMap<>();
        Set<String> successStatuses = new java.util.HashSet<>(Arrays.asList("已生效", "已完成"));
        for (ReserveListDO r : list) {
            if (r.getCreateTime() == null) continue;
            String key = period.formatStatPeriod(r.getCreateTime().toLocalDate());
            PeriodStat ps = m.computeIfAbsent(key, k -> new PeriodStat());
            ps.total++;
            if (successStatuses.contains(r.getStatus())) ps.done++;
        }
        return m;
    }

    /** complaint 合并 suggestion + userAppeal + disputeMediate 三张表 */
    private Map<String, PeriodStat> loadAndGroupComplaint(LocalDateTime start, LocalDateTime end, ReportPeriodEnum period) {
        Map<String, PeriodStat> m = new java.util.HashMap<>();
        for (SuggestionDO r : suggestionMapper.selectList(rangeWrapper(start, end))) {
            if (r.getCreateTime() == null) continue;
            String key = period.formatStatPeriod(r.getCreateTime().toLocalDate());
            PeriodStat ps = m.computeIfAbsent(key, k -> new PeriodStat());
            ps.total++;
            if ("已完成".equals(r.getStatus())) ps.done++;
        }
        for (UserAppealDO r : userAppealMapper.selectList(rangeWrapper(start, end))) {
            if (r.getCreateTime() == null) continue;
            String key = period.formatStatPeriod(r.getCreateTime().toLocalDate());
            PeriodStat ps = m.computeIfAbsent(key, k -> new PeriodStat());
            ps.total++;
            if ("已完成".equals(r.getStatus())) ps.done++;
        }
        for (DisputeMediateDO r : disputeMediateMapper.selectList(rangeWrapper(start, end))) {
            if (r.getCreateTime() == null) continue;
            String key = period.formatStatPeriod(r.getCreateTime().toLocalDate());
            PeriodStat ps = m.computeIfAbsent(key, k -> new PeriodStat());
            ps.total++;
            if ("已完成".equals(r.getStatus())) ps.done++;
        }
        return m;
    }

    private Rates ratesFromMaps(ReportPeriodEnum period, LocalDate anchor,
                                Map<String, PeriodStat> rescueMap,
                                Map<String, PeriodStat> reserveMap,
                                Map<String, PeriodStat> complaintMap) {
        String key = period.formatStatPeriod(anchor);
        Rates r = new Rates();
        PeriodStat rescue = rescueMap.get(key);
        if (rescue != null) r.rescueFinishRate = toRate(rescue.done, rescue.total);
        PeriodStat reserve = reserveMap.get(key);
        if (reserve != null) r.reserveSuccessRate = toRate(reserve.done, reserve.total);
        PeriodStat complaint = complaintMap.get(key);
        if (complaint != null) r.complaintHandleRate = toRate(complaint.done, complaint.total);
        return r;
    }

    private ServiceOpReportRespVO buildFromMaps(String reportType, ReportPeriodEnum period, LocalDate anchor,
                                                Map<String, PeriodStat> rescueMap,
                                                Map<String, PeriodStat> reserveMap,
                                                Map<String, PeriodStat> complaintMap) {
        Rates cur = ratesFromMaps(period, anchor, rescueMap, reserveMap, complaintMap);
        Rates qoq = ratesFromMaps(period, period.previousPeriod(anchor), rescueMap, reserveMap, complaintMap);
        Rates yoy = ratesFromMaps(period, anchor.minusYears(1), rescueMap, reserveMap, complaintMap);

        ServiceOpReportRespVO vo = new ServiceOpReportRespVO();
        vo.setId((long) Math.abs(Objects.hash(reportType, period.formatStatPeriod(anchor))));
        vo.setReportType(reportType);
        vo.setTimeScale(period.getLabel().replace("报", ""));
        vo.setStatPeriod(period.formatStatPeriod(anchor));
        vo.setCreateTime(LocalDateTime.now());
        vo.setRescueFinishRate(cur.rescueFinishRate);
        vo.setReserveSuccessRate(cur.reserveSuccessRate);
        vo.setComplaintHandleRate(cur.complaintHandleRate);
        vo.setYoyData(deltaMap(cur, yoy));
        vo.setQoqData(deltaMap(cur, qoq));
        vo.setCreator("system");
        return vo;
    }

    /** 按时间窗口生成单条报表,包含 3 个核心指标 + 同比/环比 */
    private ServiceOpReportRespVO buildServiceOpReport(String reportType, ReportPeriodEnum period, LocalDate anchor) {
        // 当前窗口
        LocalDateTime curStart = period.startOf(anchor);
        LocalDateTime curEnd = period.endOf(anchor);
        Rates cur = computeRates(curStart, curEnd);
        // 环比:上一周期
        LocalDate prevAnchor = period.previousPeriod(anchor);
        Rates qoq = computeRates(period.startOf(prevAnchor), period.endOf(prevAnchor));
        // 同比:去年同期
        LocalDate yoyAnchor = anchor.minusYears(1);
        Rates yoy = computeRates(period.startOf(yoyAnchor), period.endOf(yoyAnchor));

        ServiceOpReportRespVO vo = new ServiceOpReportRespVO();
        // 虚拟 ID:取正数(Objects.hash 返回 int 可能为负)。reportType+statPeriod 稳定 hash 保证多次查询 ID 一致
        vo.setId((long) Math.abs(Objects.hash(reportType, period.formatStatPeriod(anchor))));
        vo.setReportType(reportType);
        vo.setTimeScale(period.getLabel().replace("报", ""));  // "月报" → "月"
        vo.setStatPeriod(period.formatStatPeriod(anchor));
        vo.setCreateTime(LocalDateTime.now());
        vo.setRescueFinishRate(cur.rescueFinishRate);
        vo.setReserveSuccessRate(cur.reserveSuccessRate);
        vo.setComplaintHandleRate(cur.complaintHandleRate);
        vo.setYoyData(deltaMap(cur, yoy));
        vo.setQoqData(deltaMap(cur, qoq));
        vo.setCreator("system");
        return vo;
    }

    /** 单窗口的 3 个核心指标聚合 */
    private Rates computeRates(LocalDateTime start, LocalDateTime end) {
        Rates r = new Rates();
        // 救援完成率
        long rescueTotal = rescueInfoMapper.selectCount(rangeWrapper(start, end));
        long rescueCompleted = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, start)
                .leIfPresent(RescueInfoDO::getCreateTime, end)
                .eq(RescueInfoDO::getStatus, "已完成"));
        r.rescueFinishRate = toRate(rescueCompleted, rescueTotal);
        // 预约成功率
        long reserveTotal = reserveListMapper.selectCount(rangeWrapper(start, end));
        long reserveSuccess = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, start)
                .leIfPresent(ReserveListDO::getCreateTime, end)
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成")));
        r.reserveSuccessRate = toRate(reserveSuccess, reserveTotal);
        // 投诉处理率 = (suggestion + userAppeal + disputeMediate) 已完成 / 三者总数
        long compTotal = suggestionMapper.selectCount(rangeWrapper(start, end))
                + userAppealMapper.selectCount(rangeWrapper(start, end))
                + disputeMediateMapper.selectCount(rangeWrapper(start, end));
        long compDone = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, start)
                .leIfPresent(SuggestionDO::getCreateTime, end)
                .eq(SuggestionDO::getStatus, "已完成"))
                + userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, start)
                .leIfPresent(UserAppealDO::getCreateTime, end)
                .eq(UserAppealDO::getStatus, "已完成"))
                + disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, start)
                .leIfPresent(DisputeMediateDO::getCreateTime, end)
                .eq(DisputeMediateDO::getStatus, "已完成"));
        r.complaintHandleRate = toRate(compDone, compTotal);
        return r;
    }

    /** 计算 delta:当前 - 基线(BigDecimal 保留 2 位小数)。
     *  严格按客户 05 文档 sample 只出 2 个字段:rescueFinishRate / reserveSuccessRate。
     *  complaintHandleRate 客户样例没列,不返(如果客户后续要再补) */
    private Map<String, BigDecimal> deltaMap(Rates cur, Rates baseline) {
        Map<String, BigDecimal> m = new LinkedHashMap<>();
        m.put("rescueFinishRate", cur.rescueFinishRate.subtract(baseline.rescueFinishRate).setScale(2, RoundingMode.HALF_UP));
        m.put("reserveSuccessRate", cur.reserveSuccessRate.subtract(baseline.reserveSuccessRate).setScale(2, RoundingMode.HALF_UP));
        return m;
    }

    /** 3 个指标的容器 */
    private static class Rates {
        BigDecimal rescueFinishRate = BigDecimal.ZERO;
        BigDecimal reserveSuccessRate = BigDecimal.ZERO;
        BigDecimal complaintHandleRate = BigDecimal.ZERO;
    }

}
