package cn.iocoder.yudao.module.chargepark.carservice.service.servicereport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportDetailRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportRespVO;
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
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.serviceconfig.WordingMgmtDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.servicereport.CycleReportDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.ChargeParkMapMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.SpacePushMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.DisputeMediateMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.SuggestionMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.complaint.UserAppealMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.PathPlanMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar.SpaceLocationMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue.RescueInfoMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.reserve.ReserveListMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.serviceconfig.WordingMgmtMapper;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.servicereport.CycleReportMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.CYCLE_REPORT_DETAIL_DATA_INVALID;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.CYCLE_REPORT_NOT_EXISTS;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.SERVICE_OP_REPORT_PARAM_INVALID;
import cn.iocoder.yudao.module.chargepark.carservice.enums.servicereport.CycleReportCycleEnum;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 周期报表 Service 实现 —— 无表模式(2026-04-23 重构)
 *
 * 设计要点:
 * 1. 不建任何"报表快照表",进入页面即时聚合 11 张业务表返回。
 * 2. 列表是"虚拟列表":按 reportCycle + statStartTime + statEndTime 切窗(周报=ISO 周、月报=自然月...),
 *    每个窗口现算一份完整报表(5 率 4 总 + 同比 + 环比)。
 * 3. 单条报表 id 用纯函数编码:
 *        id = cycleCode × 10^12 + startEpochDay × 10^4 + durationDays
 *    前端拿 id 调 /get / /detail / /row-export / /compare-yoy / /compare-mom 时,
 *    后端直接解码得到 (cycle, start, end),再反查业务表,无需查库。
 * 4. "生成"按钮在新模式下等价于"当前筛选的首窗 id",不写入任何表。
 * 5. 字段语义:
 *        generateTime = 查询时刻;
 *        operator     = 当前登录用户昵称;
 *        generateStatus 恒为 "已生成"。
 */
@Slf4j
@Service
@Validated
public class CycleReportServiceImpl implements CycleReportService {

    // ---- 编码常量:id = cycle × 10^12 + epochDay × 10^4 + durationDays ----
    private static final long CYCLE_MULT  = 1_000_000_000_000L;
    private static final long DAY_MULT    = 10_000L;
    private static final long DURATION_MOD = 10_000L;

    /** 单次查询最多返回的窗口数(兜底:选全年+日报理论 366 窗,接口仍会受 /page 自身 pageSize 截断) */
    private static final int MAX_WINDOWS = 1000;

    private static final DateTimeFormatter CUSTOM_RANGE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** 复用单例的 ObjectMapper,避免每次 toMapList 都新建(性能) */
    private static final ObjectMapper MAPPER = new ObjectMapper();
    static {
        com.fasterxml.jackson.datatype.jsr310.JavaTimeModule jtm = new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule();
        jtm.addSerializer(LocalDateTime.class,
                new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        jtm.addSerializer(java.time.LocalDate.class,
                new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        MAPPER.registerModule(jtm);
        MAPPER.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Resource private RescueInfoMapper rescueInfoMapper;
    @Resource private ReserveListMapper reserveListMapper;
    @Resource private SuggestionMapper suggestionMapper;
    @Resource private UserAppealMapper userAppealMapper;
    @Resource private DisputeMediateMapper disputeMediateMapper;
    @Resource private SpacePushMapper spacePushMapper;
    @Resource private SpaceLocationMapper spaceLocationMapper;
    @Resource private WordingMgmtMapper wordingMgmtMapper;
    @Resource private ChargeParkMapMapper chargeParkMapMapper;
    @Resource private NearStationMapper nearStationMapper;
    @Resource private PathPlanMapper pathPlanMapper;
    @Resource private AdminUserApi adminUserApi;
    @Resource private CycleReportMapper cycleReportMapper;

    // =========================================================================
    // 对外接口
    // =========================================================================

    @Override
    public PageResult<CycleReportRespVO> pageCycleReport(CycleReportPageReqVO reqVO) {
        // 直接查 cycle_report 表(冻结快照)
        PageResult<CycleReportDO> page = cycleReportMapper.selectPage(reqVO);
        PageResult<CycleReportRespVO> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setList(page.getList().stream().map(this::toRespVO).collect(Collectors.toList()));
        return result;
    }

    @Override
    public PageResult<CycleReportRespVO> pageAllCycleReport(LocalDateTime statStartTime,
                                                            LocalDateTime statEndTime,
                                                            Integer pageNo, Integer pageSize) {
        if (statStartTime == null || statEndTime == null || statEndTime.isBefore(statStartTime)) {
            throw new IllegalArgumentException("统计时间范围不合法");
        }
        int pn = pageNo == null || pageNo < 1 ? 1 : pageNo;
        int ps = pageSize == null || pageSize < 1 ? 20 : Math.min(pageSize, 200);
        PageResult<CycleReportDO> page = cycleReportMapper.selectPageByTimeRange(statStartTime, statEndTime, pn, ps);
        PageResult<CycleReportRespVO> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setList(page.getList().stream().map(this::toRespVO).collect(Collectors.toList()));
        return result;
    }

    /** 切窗但**只纳入完全落在区间内**的窗口(不切半窗、不兜底 CUSTOM) */
    private List<Window> splitFullWindowsOnly(CycleReportCycleEnum cycle, LocalDateTime rs, LocalDateTime re) {
        List<Window> wins = new ArrayList<>();
        LocalDate rsDate = rs.toLocalDate(), reDate = re.toLocalDate();
        LocalDate cursor = rsDate;
        int safety = 0;
        while (!cursor.isAfter(reDate) && safety++ < MAX_WINDOWS) {
            LocalDate[] w = currentWindowOf(cycle, cursor);
            if (!w[0].isBefore(rsDate) && !w[1].isAfter(reDate)) {
                wins.add(new Window(cycle, w[0].atStartOfDay(), w[1].atTime(23, 59, 59)));
            }
            cursor = w[1].plusDays(1);
        }
        return wins;
    }

    @Override
    public Long createCycleReport(CycleReportCreateReqVO reqVO) {
        LocalDateTime start = reqVO.getStatStartTime();
        LocalDateTime end = reqVO.getStatEndTime();
        if (end.isBefore(start)) {
            throw exception(SERVICE_OP_REPORT_PARAM_INVALID);
        }
        CycleReportCycleEnum cycle = CycleReportCycleEnum.fromLabel(reqVO.getStatType());
        // 每次调用都插入一条新快照,不做幂等查重;由调用方决定何时触发

        // 现场聚合数据 → 复用已有 buildRespVO,然后把聚合结果拷到 DO + detail_data JSON
        String operator = currentLoginName();
        LocalDateTime now = LocalDateTime.now();
        CycleReportRespVO vo = buildRespVO(cycle, start, end, operator, now);

        CycleReportDO row = new CycleReportDO();
        row.setReportCycle(cycle.getLabel());
        row.setStatStartTime(start);
        row.setStatEndTime(end);
        row.setStatTimeLabel(vo.getStatTime());
        row.setRescueCompleteRate(vo.getRescueCompleteRate());
        row.setReserveSuccessRate(vo.getReserveSuccessRate());
        row.setComplaintHandleRate(vo.getComplaintHandleRate());
        row.setFindCarSuccessRate(vo.getFindCarSuccessRate());
        row.setSpacePushSuccessRate(vo.getSpacePushSuccessRate());
        row.setRescueTotal(vo.getRescueTotal());
        row.setReserveTotal(vo.getReserveTotal());
        row.setComplaintTotal(vo.getComplaintTotal());
        row.setSpacePushTotal(vo.getSpacePushTotal());
        row.setEffectiveWordingCount(vo.getEffectiveWordingCount());
        row.setYearOnYearGrowthRate(vo.getYearOnYearGrowthRate());
        row.setMonthOnMonthGrowthRate(vo.getMonthOnMonthGrowthRate());
        row.setServiceStatusRatio(vo.getServiceStatusRatio());
        row.setGenerateStatus("已生成");
        row.setGenerateTime(now);
        row.setOperator(operator);
        row.setOperatorUserId(SecurityFrameworkUtils.getLoginUserId());
        try {
            row.setDetailData(MAPPER.writeValueAsString(buildDetailData(start, end)));
        } catch (Exception ex) {
            log.warn("[createCycleReport] detail_data 序列化失败,置空", ex);
            row.setDetailData(null);
        }
        cycleReportMapper.insert(row);
        return row.getId();
    }

    /** DO → 列表 VO 的映射 */
    private CycleReportRespVO toRespVO(CycleReportDO d) {
        CycleReportRespVO v = new CycleReportRespVO();
        v.setId(d.getId());
        v.setReportCycle(d.getReportCycle());
        v.setStatTime(d.getStatTimeLabel());
        v.setRescueCompleteRate(d.getRescueCompleteRate());
        v.setReserveSuccessRate(d.getReserveSuccessRate());
        v.setComplaintHandleRate(d.getComplaintHandleRate());
        v.setFindCarSuccessRate(d.getFindCarSuccessRate());
        v.setSpacePushSuccessRate(d.getSpacePushSuccessRate());
        v.setEffectiveWordingCount(d.getEffectiveWordingCount());
        v.setRescueTotal(d.getRescueTotal());
        v.setReserveTotal(d.getReserveTotal());
        v.setComplaintTotal(d.getComplaintTotal());
        v.setSpacePushTotal(d.getSpacePushTotal());
        v.setGenerateStatus(d.getGenerateStatus());
        v.setGenerateTime(d.getGenerateTime());
        v.setOperator(d.getOperator());
        v.setOperatorUserId(d.getOperatorUserId());
        v.setYearOnYearGrowthRate(d.getYearOnYearGrowthRate());
        v.setMonthOnMonthGrowthRate(d.getMonthOnMonthGrowthRate());
        v.setServiceStatusRatio(d.getServiceStatusRatio());
        v.setCreator(d.getCreator());
        v.setCreateTime(d.getCreateTime());
        v.setUpdateTime(d.getUpdateTime());
        return v;
    }

    /** DB 载入 id,拆回 Window(供 /get /chart /compare /pageDetail 等后续复用现有私有聚合方法) */
    private Window loadWindowFromDB(Long id) {
        CycleReportDO d = cycleReportMapper.selectById(id);
        if (d == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
        return new Window(CycleReportCycleEnum.fromLabel(d.getReportCycle()),
                d.getStatStartTime(), d.getStatEndTime());
    }

    @Override
    public CycleReportDetailRespVO getCycleReport(Long id) {
        CycleReportDO d = cycleReportMapper.selectById(id);
        if (d == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
        CycleReportRespVO base = toRespVO(d);
        CycleReportDetailRespVO detail = new CycleReportDetailRespVO();
        copyRespFields(base, detail);
        // detail_data 为空 → 老 seed 数据,本期尚未聚合真实明细,直接给空 map(不再静默回退实时算避免数据漂移)
        if (d.getDetailData() == null || d.getDetailData().isEmpty()) {
            detail.setDetailData(new LinkedHashMap<>());
            return detail;
        }
        // 快照存在时必须能解析成功,否则数据损坏,抛业务异常让运维看见
        try {
            detail.setDetailData(MAPPER.readValue(d.getDetailData(),
                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, List<Map<String, Object>>>>() {}));
        } catch (Exception ex) {
            log.error("[getCycleReport] detail_data 解析失败 id={}", id, ex);
            throw exception(CYCLE_REPORT_DETAIL_DATA_INVALID);
        }
        return detail;
    }

    @Override
    public Map<String, Object> compareYoY(Long id) {
        Window w = loadWindowFromDB(id);
        LocalDateTime prevStart = w.start.minusYears(1);
        LocalDateTime prevEnd = w.end.minusYears(1);
        return buildCompareResult(w.start, w.end, prevStart, prevEnd, "yoy");
    }

    @Override
    public Map<String, Object> compareMoM(Long id) {
        Window w = loadWindowFromDB(id);
        long seconds = java.time.Duration.between(w.start, w.end).toSeconds();
        LocalDateTime prevEnd = w.start;
        LocalDateTime prevStart = w.start.minusSeconds(seconds);
        return buildCompareResult(w.start, w.end, prevStart, prevEnd, "mom");
    }

    @Override
    public Map<String, List<Map<String, Object>>> getFullDetailForExport(Long id) {
        Window w = loadWindowFromDB(id);
        LocalDateTime s = w.start, e = w.end;
        Map<String, List<Map<String, Object>>> d = new LinkedHashMap<>();
        d.put("rescueDetail", toMapList(rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s).leIfPresent(RescueInfoDO::getCreateTime, e))));
        d.put("reserveDetail", toMapList(reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s).leIfPresent(ReserveListDO::getCreateTime, e))));
        List<Object> complaints = new ArrayList<>();
        complaints.addAll(suggestionMapper.selectList(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, s).leIfPresent(SuggestionDO::getCreateTime, e)));
        complaints.addAll(userAppealMapper.selectList(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, s).leIfPresent(UserAppealDO::getCreateTime, e)));
        complaints.addAll(disputeMediateMapper.selectList(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s).leIfPresent(DisputeMediateDO::getCreateTime, e)));
        d.put("complaintDetail", toMapList(complaints));
        d.put("findCarDetail", toMapList(spaceLocationMapper.selectList(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, s).leIfPresent(SpaceLocationDO::getCreateTime, e))));
        d.put("spacePushDetail", toMapList(spacePushMapper.selectList(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s).leIfPresent(SpacePushDO::getCreateTime, e))));
        d.put("wordingDetail", toMapList(wordingMgmtMapper.selectList(new LambdaQueryWrapperX<WordingMgmtDO>())));
        return d;
    }

    @Override
    public PageResult<Map<String, Object>> pageDetail(Long id, String dimension, Integer pageNo, Integer pageSize) {
        Window w = loadWindowFromDB(id);
        LocalDateTime s = w.start, e = w.end;
        int pn = pageNo == null || pageNo < 1 ? 1 : pageNo;
        int ps = pageSize == null || pageSize < 1 ? 20 : Math.min(pageSize, 200);
        int from = (pn - 1) * ps;

        List<?> fullList;
        long total;
        switch (dimension == null ? "" : dimension) {
            case "rescue":
                fullList = rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                        .geIfPresent(RescueInfoDO::getCreateTime, s).leIfPresent(RescueInfoDO::getCreateTime, e));
                break;
            case "reserve":
                fullList = reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                        .geIfPresent(ReserveListDO::getCreateTime, s).leIfPresent(ReserveListDO::getCreateTime, e));
                break;
            case "complaint":
                List<Object> merged = new ArrayList<>();
                merged.addAll(suggestionMapper.selectList(new LambdaQueryWrapperX<SuggestionDO>()
                        .geIfPresent(SuggestionDO::getCreateTime, s).leIfPresent(SuggestionDO::getCreateTime, e)));
                merged.addAll(userAppealMapper.selectList(new LambdaQueryWrapperX<UserAppealDO>()
                        .geIfPresent(UserAppealDO::getCreateTime, s).leIfPresent(UserAppealDO::getCreateTime, e)));
                merged.addAll(disputeMediateMapper.selectList(new LambdaQueryWrapperX<DisputeMediateDO>()
                        .geIfPresent(DisputeMediateDO::getCreateTime, s).leIfPresent(DisputeMediateDO::getCreateTime, e)));
                fullList = merged;
                break;
            case "findCar":
                fullList = spaceLocationMapper.selectList(new LambdaQueryWrapperX<SpaceLocationDO>()
                        .geIfPresent(SpaceLocationDO::getCreateTime, s).leIfPresent(SpaceLocationDO::getCreateTime, e));
                break;
            case "spacePush":
                fullList = spacePushMapper.selectList(new LambdaQueryWrapperX<SpacePushDO>()
                        .geIfPresent(SpacePushDO::getCreateTime, s).leIfPresent(SpacePushDO::getCreateTime, e));
                break;
            case "wording":
                fullList = wordingMgmtMapper.selectList(new LambdaQueryWrapperX<WordingMgmtDO>());
                break;
            default:
                throw new IllegalArgumentException("无效的 dimension: " + dimension +
                        " (取值:rescue/reserve/complaint/findCar/spacePush/wording)");
        }
        total = fullList.size();
        int to = Math.min((int) total, from + ps);
        // SubList 无默认构造器,包一层真 ArrayList 以免后续任何序列化场景出错
        List<?> slice = from < to ? new ArrayList<>(fullList.subList(from, to)) : new ArrayList<>();

        PageResult<Map<String, Object>> page = new PageResult<>();
        page.setList(toMapList(slice));
        page.setTotal(total);
        return page;
    }

    @Override
    public CycleReportChartRespVO chartCycleReport(CycleReportChartReqVO reqVO) {
        LocalDateTime start = reqVO.getStatStartTime();
        LocalDateTime end = reqVO.getStatEndTime();
        // 图表接口默认窗口:和列表一致,避免无参时返回全表
        if (start == null || end == null) {
            CycleReportCycleEnum c = CycleReportCycleEnum.fromLabel(reqVO.getReportCycle());
            LocalDateTime[] r = resolveDefaultRange(c, start, end);
            start = r[0]; end = r[1];
        }
        CycleReportChartRespVO resp = new CycleReportChartRespVO();

        // ---- 卡片 ----
        Map<String, Object> card = new LinkedHashMap<>();
        card.put("rescueCompleteRate", rescueCompleteRate(start, end));
        card.put("reserveSuccessRate", reserveSuccessRate(start, end));
        card.put("complaintHandleRate", complaintHandleRate(start, end));
        card.put("findCarSuccessRate", findCarSuccessRate(start, end));
        card.put("spacePushSuccessRate", spacePushSuccessRate(start, end));
        card.put("effectiveWordingCount", effectiveWordingCountNow());
        resp.setCardData(card);

        // ---- 折线图(7 条) ----
        List<Map<String, Object>> line = new ArrayList<>();
        line.add(seriesOf("救援量趋势", rescueTrend(start, end)));
        line.add(seriesOf("预约量趋势", reserveTrend(start, end)));
        line.add(seriesOf("投诉量趋势", complaintTrend(start, end)));
        line.add(seriesOf("空位推送量趋势", spacePushTrend(start, end)));
        line.add(seriesOf("充停地图查询量趋势", chargeParkMapTrend(start, end)));
        line.add(seriesOf("周边场站查询量趋势", nearStationTrend(start, end)));
        line.add(seriesOf("路径规划量趋势", pathPlanTrend(start, end)));
        resp.setLineData(line);

        // ---- 柱状图 ----
        List<Map<String, Object>> bar = new ArrayList<>();
        bar.add(seriesOf("救援类型分布", distributionRescue(start, end)));
        bar.add(seriesOf("预约类型分布", distributionReserve(start, end)));
        bar.add(seriesOf("纠纷类型分布", distributionDispute(start, end)));
        resp.setBarData(bar);

        // ---- 地图 ----
        List<Map<String, Object>> map = new ArrayList<>();
        map.add(seriesOf("救援位置分布", rescueLocations(start, end)));
        map.add(seriesOf("场站车位分布", spaceLocations(start, end)));
        resp.setMapData(map);

        // ---- 饼图 ----
        List<Map<String, Object>> pie = new ArrayList<>();
        pie.add(seriesOf("话术类型占比", wordingTypeRatio()));
        pie.add(seriesOf("服务状态占比", serviceStatusRatioList(start, end)));
        resp.setPieData(pie);

        return resp;
    }

    // =========================================================================
    // id 编解码
    // =========================================================================

    /** id = cycleCode × 10^12 + startEpochDay × 10^4 + durationDays(含首含尾) */
    private Long encodeId(CycleReportCycleEnum cycle, LocalDateTime start, LocalDateTime end) {
        long epochDay = start.toLocalDate().toEpochDay();
        long durationDays = end.toLocalDate().toEpochDay() - epochDay + 1;
        if (durationDays < 1) durationDays = 1;
        if (durationDays >= DURATION_MOD) durationDays = DURATION_MOD - 1;
        return cycleCode(cycle) * CYCLE_MULT + epochDay * DAY_MULT + durationDays;
    }

    /** 解码 id → Window(cycle + [start, end]) */
    private Window decodeId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("报表 id 不合法:" + id);
        }
        int code = (int) (id / CYCLE_MULT);
        long epochDay = (id % CYCLE_MULT) / DAY_MULT;
        long durationDays = id % DURATION_MOD;
        CycleReportCycleEnum cycle = cycleFromCode(code);
        LocalDate startDate = LocalDate.ofEpochDay(epochDay);
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = startDate.plusDays(durationDays - 1).atTime(23, 59, 59);
        return new Window(cycle, start, end);
    }

    private static long cycleCode(CycleReportCycleEnum c) {
        switch (c) {
            case DAILY: return 1L;
            case WEEKLY: return 2L;
            case MONTHLY: return 3L;
            case QUARTERLY: return 4L;
            case SEMI_ANNUAL: return 5L;
            case ANNUAL: return 6L;
            case CUSTOM: return 7L;
            default: return 7L;
        }
    }

    private static CycleReportCycleEnum cycleFromCode(int code) {
        switch (code) {
            case 1: return CycleReportCycleEnum.DAILY;
            case 2: return CycleReportCycleEnum.WEEKLY;
            case 3: return CycleReportCycleEnum.MONTHLY;
            case 4: return CycleReportCycleEnum.QUARTERLY;
            case 5: return CycleReportCycleEnum.SEMI_ANNUAL;
            case 6: return CycleReportCycleEnum.ANNUAL;
            case 7: return CycleReportCycleEnum.CUSTOM;
            default: return CycleReportCycleEnum.CUSTOM;
        }
    }

    // =========================================================================
    // 默认时段 & 切窗
    // =========================================================================

    /** 进入页面未传 statStartTime/statEndTime 时,默认"当期"完整窗口 */
    private LocalDateTime[] resolveDefaultRange(CycleReportCycleEnum cycle, LocalDateTime s, LocalDateTime e) {
        if (s != null && e != null) return new LocalDateTime[]{s, e};
        LocalDate today = LocalDate.now();
        LocalDate[] win = currentWindowOf(cycle, today);
        LocalDateTime ds = win[0].atStartOfDay();
        LocalDateTime de = win[1].atTime(23, 59, 59);
        return new LocalDateTime[]{
                s != null ? s : ds,
                e != null ? e : de
        };
    }

    /** 以 base 日期所属的周期窗口(起止日,含两端) */
    private LocalDate[] currentWindowOf(CycleReportCycleEnum cycle, LocalDate base) {
        switch (cycle) {
            case DAILY:
                return new LocalDate[]{base, base};
            case WEEKLY: {
                LocalDate monday = base.with(DayOfWeek.MONDAY);
                return new LocalDate[]{monday, monday.plusDays(6)};
            }
            case MONTHLY: {
                LocalDate first = base.withDayOfMonth(1);
                return new LocalDate[]{first, first.plusMonths(1).minusDays(1)};
            }
            case QUARTERLY: {
                int qStartMonth = ((base.getMonthValue() - 1) / 3) * 3 + 1;
                LocalDate first = LocalDate.of(base.getYear(), qStartMonth, 1);
                return new LocalDate[]{first, first.plusMonths(3).minusDays(1)};
            }
            case SEMI_ANNUAL: {
                int hStart = base.getMonthValue() <= 6 ? 1 : 7;
                LocalDate first = LocalDate.of(base.getYear(), hStart, 1);
                return new LocalDate[]{first, first.plusMonths(6).minusDays(1)};
            }
            case ANNUAL: {
                LocalDate first = LocalDate.of(base.getYear(), 1, 1);
                return new LocalDate[]{first, LocalDate.of(base.getYear(), 12, 31)};
            }
            case CUSTOM:
            default:
                return new LocalDate[]{base, base};
        }
    }

    /** 在 [rangeStart, rangeEnd] 内按 cycle 切出若干完整周期窗口;自定义 → 单窗 */
    private List<Window> splitWindows(CycleReportCycleEnum cycle, LocalDateTime rangeStart, LocalDateTime rangeEnd) {
        List<Window> wins = new ArrayList<>();
        if (rangeStart == null || rangeEnd == null || rangeEnd.isBefore(rangeStart)) {
            return wins;
        }
        if (cycle == CycleReportCycleEnum.CUSTOM) {
            wins.add(new Window(cycle, rangeStart, rangeEnd));
            return wins;
        }
        LocalDate rs = rangeStart.toLocalDate();
        LocalDate re = rangeEnd.toLocalDate();
        LocalDate cursor = rs;
        int safety = 0;
        while (!cursor.isAfter(re) && safety++ < MAX_WINDOWS) {
            LocalDate[] w = currentWindowOf(cycle, cursor);
            // 窗口完全包含在 [rs, re] 之外的跳过(一般不会出现)
            LocalDate wStart = w[0], wEnd = w[1];
            // 仅保留"窗口完全落在筛选区间内"的,避免列表出现半截周期
            if (!wStart.isBefore(rs) && !wEnd.isAfter(re)) {
                wins.add(new Window(cycle, wStart.atStartOfDay(), wEnd.atTime(23, 59, 59)));
            } else if (wins.isEmpty() && cursor.equals(rs) && wStart.isBefore(rs)) {
                // 特殊情况:筛选起点在某个窗口中段 → 包含该半窗(start 用 rs)
                LocalDate clippedEnd = wEnd.isAfter(re) ? re : wEnd;
                wins.add(new Window(cycle, rs.atStartOfDay(), clippedEnd.atTime(23, 59, 59)));
            }
            // 前进到下一窗起点
            cursor = wEnd.plusDays(1);
        }
        // 兜底:若无任何窗口被纳入(比如区间比一个周期还短),退化为 custom 单窗
        if (wins.isEmpty()) {
            wins.add(new Window(CycleReportCycleEnum.CUSTOM, rangeStart, rangeEnd));
        }
        return wins;
    }

    // =========================================================================
    // 构建 RespVO
    // =========================================================================

    /** 对给定窗口现算一份 RespVO(不落表) */
    private CycleReportRespVO buildRespVO(CycleReportCycleEnum cycle, LocalDateTime start, LocalDateTime end,
                                          String operator, LocalDateTime now) {
        CycleReportRespVO v = new CycleReportRespVO();
        v.setId(encodeId(cycle, start, end));
        v.setReportCycle(cycle.getLabel());
        v.setStatTime(formatStatTime(cycle, start, end));

        // 5 率 + 1 生效话术数
        BigDecimal rescueRate = rescueCompleteRate(start, end);
        v.setRescueCompleteRate(rescueRate);
        v.setReserveSuccessRate(reserveSuccessRate(start, end));
        v.setComplaintHandleRate(complaintHandleRate(start, end));
        v.setFindCarSuccessRate(findCarSuccessRate(start, end));
        v.setSpacePushSuccessRate(spacePushSuccessRate(start, end));
        v.setEffectiveWordingCount(effectiveWordingCountNow());

        // 4 总量
        v.setRescueTotal((int) rescueTotal(start, end));
        v.setReserveTotal((int) reserveTotal(start, end));
        v.setComplaintTotal((int) complaintTotal(start, end));
        v.setSpacePushTotal(spacePushMapper.selectCount(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, start)
                .leIfPresent(SpacePushDO::getCreateTime, end)).intValue());

        // 同比/环比(按救援完成率百分点差)
        long duration = java.time.Duration.between(start, end).toSeconds();
        LocalDateTime yoyStart = start.minusYears(1);
        LocalDateTime yoyEnd = yoyStart.plusSeconds(duration);
        LocalDateTime momStart = start.minusSeconds(duration);
        LocalDateTime momEnd = start;
        v.setYearOnYearGrowthRate(deltaPercent(rescueRate, rescueCompleteRate(yoyStart, yoyEnd)));
        v.setMonthOnMonthGrowthRate(deltaPercent(rescueRate, rescueCompleteRate(momStart, momEnd)));

        v.setServiceStatusRatio(buildServiceStatusRatio(start, end));

        // 元信息
        v.setGenerateStatus("已生成");
        v.setGenerateTime(now);
        v.setOperator(operator);
        v.setOperatorUserId(SecurityFrameworkUtils.getLoginUserId());
        v.setCreator("system");
        v.setCreateTime(now);
        v.setUpdateTime(now);
        return v;
    }

    /** 把父类字段拷到子类 DetailRespVO */
    private void copyRespFields(CycleReportRespVO src, CycleReportDetailRespVO dst) {
        dst.setId(src.getId());
        dst.setReportCycle(src.getReportCycle());
        dst.setStatTime(src.getStatTime());
        dst.setRescueCompleteRate(src.getRescueCompleteRate());
        dst.setReserveSuccessRate(src.getReserveSuccessRate());
        dst.setComplaintHandleRate(src.getComplaintHandleRate());
        dst.setFindCarSuccessRate(src.getFindCarSuccessRate());
        dst.setSpacePushSuccessRate(src.getSpacePushSuccessRate());
        dst.setEffectiveWordingCount(src.getEffectiveWordingCount());
        dst.setRescueTotal(src.getRescueTotal());
        dst.setReserveTotal(src.getReserveTotal());
        dst.setComplaintTotal(src.getComplaintTotal());
        dst.setSpacePushTotal(src.getSpacePushTotal());
        dst.setGenerateStatus(src.getGenerateStatus());
        dst.setGenerateTime(src.getGenerateTime());
        dst.setOperator(src.getOperator());
        dst.setOperatorUserId(src.getOperatorUserId());
        dst.setYearOnYearGrowthRate(src.getYearOnYearGrowthRate());
        dst.setMonthOnMonthGrowthRate(src.getMonthOnMonthGrowthRate());
        dst.setServiceStatusRatio(src.getServiceStatusRatio());
        dst.setCreator(src.getCreator());
        dst.setCreateTime(src.getCreateTime());
        dst.setUpdateTime(src.getUpdateTime());
    }

    /** statTime 字符串:统一展示起止日期区间,如 "2026-04-01 至 2026-06-30" */
    private String formatStatTime(CycleReportCycleEnum cycle, LocalDateTime start, LocalDateTime end) {
        LocalDate s = start.toLocalDate();
        LocalDate e = end.toLocalDate();
        if (cycle == CycleReportCycleEnum.DAILY) {
            return s.toString();
        }
        return s.format(CUSTOM_RANGE_FMT) + " 至 " + e.format(CUSTOM_RANGE_FMT);
    }

    // =========================================================================
    // 同比/环比
    // =========================================================================

    private Map<String, Object> buildCompareResult(LocalDateTime curS, LocalDateTime curE,
                                                   LocalDateTime prevS, LocalDateTime prevE, String type) {
        Map<String, Object> current = buildMetricsMap(curS, curE);
        Map<String, Object> previous = buildMetricsMap(prevS, prevE);
        current.put("statWindow", fmtWindow(curS, curE));
        previous.put("statWindow", fmtWindow(prevS, prevE));

        Map<String, Object> delta = new LinkedHashMap<>();
        String[] rateKeys = {"rescueCompleteRate", "reserveSuccessRate", "complaintHandleRate", "findCarSuccessRate", "spacePushSuccessRate"};
        for (String k : rateKeys) delta.put(k, subtract(current.get(k), previous.get(k)));
        String[] totalKeys = {"rescueTotal", "reserveTotal", "complaintTotal", "spacePushTotal"};
        for (String k : totalKeys) delta.put(k, subtract(current.get(k), previous.get(k)));

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("compareType", type);
        out.put("current", current);
        out.put("previous", previous);
        out.put("delta", delta);
        return out;
    }

    private Map<String, Object> buildMetricsMap(LocalDateTime s, LocalDateTime e) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("rescueCompleteRate", rescueCompleteRate(s, e));
        m.put("reserveSuccessRate", reserveSuccessRate(s, e));
        m.put("complaintHandleRate", complaintHandleRate(s, e));
        m.put("findCarSuccessRate", findCarSuccessRate(s, e));
        m.put("spacePushSuccessRate", spacePushSuccessRate(s, e));
        m.put("rescueTotal", rescueTotal(s, e));
        m.put("reserveTotal", reserveTotal(s, e));
        m.put("complaintTotal", complaintTotal(s, e));
        m.put("spacePushTotal", spacePushMapper.selectCount(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s).leIfPresent(SpacePushDO::getCreateTime, e)));
        return m;
    }

    private String fmtWindow(LocalDateTime s, LocalDateTime e) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return s.format(f) + " ~ " + e.format(f);
    }

    private BigDecimal subtract(Object a, Object b) {
        BigDecimal ba = toBigDecimal(a), bb = toBigDecimal(b);
        return ba.subtract(bb).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal toBigDecimal(Object v) {
        if (v == null) return BigDecimal.ZERO;
        if (v instanceof BigDecimal) return (BigDecimal) v;
        if (v instanceof Number) return BigDecimal.valueOf(((Number) v).doubleValue());
        try { return new BigDecimal(v.toString()); } catch (Exception ignore) { return BigDecimal.ZERO; }
    }

    // =========================================================================
    // 指标聚合
    // =========================================================================

    private BigDecimal rescueCompleteRate(LocalDateTime s, LocalDateTime e) {
        long total = rescueTotal(s, e);
        long done = rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e)
                .eq(RescueInfoDO::getStatus, "已完成"));
        return ratePercent(done, total);
    }

    private long rescueTotal(LocalDateTime s, LocalDateTime e) {
        return rescueInfoMapper.selectCount(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e));
    }

    private BigDecimal reserveSuccessRate(LocalDateTime s, LocalDateTime e) {
        long total = reserveTotal(s, e);
        long done = reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e)
                .in(ReserveListDO::getStatus, Arrays.asList("已生效", "已完成")));
        return ratePercent(done, total);
    }

    private long reserveTotal(LocalDateTime s, LocalDateTime e) {
        return reserveListMapper.selectCount(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e));
    }

    private BigDecimal complaintHandleRate(LocalDateTime s, LocalDateTime e) {
        long total = complaintTotal(s, e);
        long done = suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, s)
                .leIfPresent(SuggestionDO::getCreateTime, e)
                .eq(SuggestionDO::getStatus, "已完成"))
                + userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, s)
                .leIfPresent(UserAppealDO::getCreateTime, e)
                .eq(UserAppealDO::getStatus, "已完成"))
                + disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s)
                .leIfPresent(DisputeMediateDO::getCreateTime, e)
                .eq(DisputeMediateDO::getStatus, "已完成"));
        return ratePercent(done, total);
    }

    private long complaintTotal(LocalDateTime s, LocalDateTime e) {
        return suggestionMapper.selectCount(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, s)
                .leIfPresent(SuggestionDO::getCreateTime, e))
                + userAppealMapper.selectCount(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, s)
                .leIfPresent(UserAppealDO::getCreateTime, e))
                + disputeMediateMapper.selectCount(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s)
                .leIfPresent(DisputeMediateDO::getCreateTime, e));
    }

    private BigDecimal findCarSuccessRate(LocalDateTime s, LocalDateTime e) {
        long total = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, s)
                .leIfPresent(SpaceLocationDO::getCreateTime, e));
        long ok = spaceLocationMapper.selectCount(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, s)
                .leIfPresent(SpaceLocationDO::getCreateTime, e)
                .eq(SpaceLocationDO::getLocationResult, "成功"));
        return ratePercent(ok, total);
    }

    private BigDecimal spacePushSuccessRate(LocalDateTime s, LocalDateTime e) {
        long total = spacePushMapper.selectCount(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s)
                .leIfPresent(SpacePushDO::getCreateTime, e));
        long ok = spacePushMapper.selectCount(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s)
                .leIfPresent(SpacePushDO::getCreateTime, e)
                .eq(SpacePushDO::getStatus, "已推送"));
        return ratePercent(ok, total);
    }

    private int effectiveWordingCountNow() {
        return wordingMgmtMapper.selectCount(new LambdaQueryWrapperX<WordingMgmtDO>()
                .eq(WordingMgmtDO::getStatus, "已生效")).intValue();
    }

    // =========================================================================
    // 图表聚合:折线/柱状/地图/饼
    // =========================================================================

    private List<Map<String, Object>> rescueTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e)), RescueInfoDO::getCreateTime);
    }

    private List<Map<String, Object>> reserveTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e)), ReserveListDO::getCreateTime);
    }

    private List<Map<String, Object>> complaintTrend(LocalDateTime s, LocalDateTime e) {
        List<LocalDateTime> all = new ArrayList<>();
        suggestionMapper.selectList(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, s)
                .leIfPresent(SuggestionDO::getCreateTime, e))
                .forEach(v -> all.add(v.getCreateTime()));
        userAppealMapper.selectList(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, s)
                .leIfPresent(UserAppealDO::getCreateTime, e))
                .forEach(v -> all.add(v.getCreateTime()));
        disputeMediateMapper.selectList(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s)
                .leIfPresent(DisputeMediateDO::getCreateTime, e))
                .forEach(v -> all.add(v.getCreateTime()));
        return dailyCountRaw(all);
    }

    private List<Map<String, Object>> spacePushTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(spacePushMapper.selectList(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s)
                .leIfPresent(SpacePushDO::getCreateTime, e)), SpacePushDO::getCreateTime);
    }

    private List<Map<String, Object>> chargeParkMapTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(chargeParkMapMapper.selectList(new LambdaQueryWrapperX<ChargeParkMapDO>()
                .geIfPresent(ChargeParkMapDO::getQueryTime, s)
                .leIfPresent(ChargeParkMapDO::getQueryTime, e)), ChargeParkMapDO::getQueryTime);
    }

    private List<Map<String, Object>> nearStationTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(nearStationMapper.selectList(new LambdaQueryWrapperX<NearStationDO>()
                .geIfPresent(NearStationDO::getQueryTime, s)
                .leIfPresent(NearStationDO::getQueryTime, e)), NearStationDO::getQueryTime);
    }

    private List<Map<String, Object>> pathPlanTrend(LocalDateTime s, LocalDateTime e) {
        return dailyCount(pathPlanMapper.selectList(new LambdaQueryWrapperX<PathPlanDO>()
                .geIfPresent(PathPlanDO::getPlanTime, s)
                .leIfPresent(PathPlanDO::getPlanTime, e)), PathPlanDO::getPlanTime);
    }

    private List<Map<String, Object>> distributionRescue(LocalDateTime s, LocalDateTime e) {
        return groupBy(rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e)), RescueInfoDO::getRescueType);
    }

    private List<Map<String, Object>> distributionReserve(LocalDateTime s, LocalDateTime e) {
        return groupBy(reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e)), ReserveListDO::getReserveType);
    }

    private List<Map<String, Object>> distributionDispute(LocalDateTime s, LocalDateTime e) {
        return groupBy(disputeMediateMapper.selectList(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s)
                .leIfPresent(DisputeMediateDO::getCreateTime, e)), DisputeMediateDO::getStatus);
    }

    private List<Map<String, Object>> rescueLocations(LocalDateTime s, LocalDateTime e) {
        return rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e))
                .stream()
                .limit(200)
                .map(r -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("userId", r.getUserId());
                    m.put("rescueType", r.getRescueType());
                    m.put("status", r.getStatus());
                    m.put("location", r.getLocation());
                    m.put("locationName", r.getLocationName());
                    return m;
                }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> spaceLocations(LocalDateTime s, LocalDateTime e) {
        return spaceLocationMapper.selectList(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, s)
                .leIfPresent(SpaceLocationDO::getCreateTime, e))
                .stream()
                .limit(200)
                .map(r -> {
                    Map<String, Object> m = new LinkedHashMap<>();
                    m.put("plateNo", maskPlate(r.getPlateNo()));
                    m.put("locationResult", r.getLocationResult());
                    return m;
                }).collect(Collectors.toList());
    }

    private String maskPlate(String plate) {
        if (plate == null || plate.length() <= 4) return plate;
        int keepHead = 2, keepTail = 2;
        int masked = plate.length() - keepHead - keepTail;
        StringBuilder sb = new StringBuilder(plate.substring(0, keepHead));
        for (int i = 0; i < masked; i++) sb.append('*');
        sb.append(plate.substring(plate.length() - keepTail));
        return sb.toString();
    }

    private List<Map<String, Object>> wordingTypeRatio() {
        return groupBy(wordingMgmtMapper.selectList(new LambdaQueryWrapperX<>()), WordingMgmtDO::getType);
    }

    private List<Map<String, Object>> serviceStatusRatioList(LocalDateTime s, LocalDateTime e) {
        Map<String, Long> acc = new LinkedHashMap<>();
        rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e))
                .forEach(r -> acc.merge(nz(r.getStatus()), 1L, Long::sum));
        reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e))
                .forEach(r -> acc.merge(nz(r.getStatus()), 1L, Long::sum));
        List<Map<String, Object>> out = new ArrayList<>();
        acc.forEach((k, v) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("type", k);
            m.put("count", v);
            out.add(m);
        });
        return out;
    }

    // =========================================================================
    // 工具方法
    // =========================================================================

    private Map<String, List<Map<String, Object>>> buildDetailData(LocalDateTime s, LocalDateTime e) {
        Map<String, List<Map<String, Object>>> d = new LinkedHashMap<>();
        d.put("rescueDetail", toMapList(rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e)).stream().limit(50).collect(Collectors.toList())));
        d.put("reserveDetail", toMapList(reserveListMapper.selectList(new LambdaQueryWrapperX<ReserveListDO>()
                .geIfPresent(ReserveListDO::getCreateTime, s)
                .leIfPresent(ReserveListDO::getCreateTime, e)).stream().limit(50).collect(Collectors.toList())));
        List<Object> complaints = new ArrayList<>();
        complaints.addAll(suggestionMapper.selectList(new LambdaQueryWrapperX<SuggestionDO>()
                .geIfPresent(SuggestionDO::getCreateTime, s)
                .leIfPresent(SuggestionDO::getCreateTime, e)));
        complaints.addAll(userAppealMapper.selectList(new LambdaQueryWrapperX<UserAppealDO>()
                .geIfPresent(UserAppealDO::getCreateTime, s)
                .leIfPresent(UserAppealDO::getCreateTime, e)));
        complaints.addAll(disputeMediateMapper.selectList(new LambdaQueryWrapperX<DisputeMediateDO>()
                .geIfPresent(DisputeMediateDO::getCreateTime, s)
                .leIfPresent(DisputeMediateDO::getCreateTime, e)));
        d.put("complaintDetail", toMapList(complaints.stream().limit(50).collect(Collectors.toList())));
        d.put("findCarDetail", toMapList(spaceLocationMapper.selectList(new LambdaQueryWrapperX<SpaceLocationDO>()
                .geIfPresent(SpaceLocationDO::getCreateTime, s)
                .leIfPresent(SpaceLocationDO::getCreateTime, e)).stream().limit(50).collect(Collectors.toList())));
        d.put("spacePushDetail", toMapList(spacePushMapper.selectList(new LambdaQueryWrapperX<SpacePushDO>()
                .geIfPresent(SpacePushDO::getCreateTime, s)
                .leIfPresent(SpacePushDO::getCreateTime, e)).stream().limit(50).collect(Collectors.toList())));
        d.put("wordingDetail", toMapList(wordingMgmtMapper.selectList(new LambdaQueryWrapperX<WordingMgmtDO>())
                .stream().limit(50).collect(Collectors.toList())));
        return d;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> toMapList(List<?> list) {
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object o : list) {
            try {
                out.add(MAPPER.convertValue(o, Map.class));
            } catch (Exception ex) {
                log.warn("Failed to convert {} to map: {}", o.getClass().getSimpleName(), ex.getMessage());
            }
        }
        return out;
    }

    private String buildServiceStatusRatio(LocalDateTime s, LocalDateTime e) {
        Map<String, Long> acc = new LinkedHashMap<>();
        rescueInfoMapper.selectList(new LambdaQueryWrapperX<RescueInfoDO>()
                .geIfPresent(RescueInfoDO::getCreateTime, s)
                .leIfPresent(RescueInfoDO::getCreateTime, e))
                .forEach(r -> acc.merge(nz(r.getStatus()), 1L, Long::sum));
        long total = acc.values().stream().mapToLong(Long::longValue).sum();
        if (total == 0) return "无数据";
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Long> en : acc.entrySet()) {
            if (!first) sb.append(", ");
            first = false;
            long pct = Math.round(en.getValue() * 100.0 / total);
            sb.append(en.getKey()).append(":").append(pct).append("%");
        }
        return sb.toString();
    }

    private BigDecimal ratePercent(long part, long total) {
        if (total <= 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(part * 100.0 / total).setScale(1, RoundingMode.HALF_UP);
    }

    private BigDecimal deltaPercent(BigDecimal cur, BigDecimal base) {
        if (cur == null) cur = BigDecimal.ZERO;
        if (base == null) base = BigDecimal.ZERO;
        return cur.subtract(base).setScale(1, RoundingMode.HALF_UP);
    }

    private Map<String, Object> seriesOf(String name, Object data) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("name", name);
        m.put("data", data == null ? new ArrayList<>() : data);
        return m;
    }

    private <T> List<Map<String, Object>> dailyCount(List<T> rows, java.util.function.Function<T, LocalDateTime> getter) {
        List<LocalDateTime> times = new ArrayList<>();
        for (T r : rows) times.add(getter.apply(r));
        return dailyCountRaw(times);
    }

    private List<Map<String, Object>> dailyCountRaw(List<LocalDateTime> times) {
        Map<String, Long> acc = new java.util.TreeMap<>();
        for (LocalDateTime t : times) {
            if (t == null) continue;
            String d = t.toLocalDate().toString();
            acc.merge(d, 1L, Long::sum);
        }
        List<Map<String, Object>> out = new ArrayList<>();
        acc.forEach((k, v) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("date", k);
            m.put("count", v);
            out.add(m);
        });
        return out;
    }

    private <T> List<Map<String, Object>> groupBy(List<T> rows, java.util.function.Function<T, String> getter) {
        Map<String, Long> acc = new LinkedHashMap<>();
        for (T r : rows) {
            String k = nz(getter.apply(r));
            acc.merge(k, 1L, Long::sum);
        }
        List<Map<String, Object>> out = new ArrayList<>();
        acc.forEach((k, v) -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("type", k);
            m.put("count", v);
            out.add(m);
        });
        return out;
    }

    private String nz(String s) { return s == null ? "未分类" : s; }

    private String currentLoginName() {
        try {
            Long uid = SecurityFrameworkUtils.getLoginUserId();
            if (uid == null) return "system";
            AdminUserRespDTO u = adminUserApi.getUser(uid).getCheckedData();
            if (u != null && u.getNickname() != null && !u.getNickname().isEmpty()) {
                return u.getNickname();
            }
            return uid.toString();
        } catch (Exception ex) {
            return "system";
        }
    }

    /** 时间窗口 POJO */
    private static class Window {
        final CycleReportCycleEnum cycle;
        final LocalDateTime start;
        final LocalDateTime end;
        Window(CycleReportCycleEnum cycle, LocalDateTime start, LocalDateTime end) {
            this.cycle = cycle;
            this.start = start;
            this.end = end;
        }
    }
}
