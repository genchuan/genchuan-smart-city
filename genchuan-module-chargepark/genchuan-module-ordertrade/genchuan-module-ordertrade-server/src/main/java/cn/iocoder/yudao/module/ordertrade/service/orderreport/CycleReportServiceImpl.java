package cn.iocoder.yudao.module.ordertrade.service.orderreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartRespVO.CardData;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.orderreport.CycleReportDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.orderreport.CycleReportMapper;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.orderreport.CycleReportStatsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.CYCLE_REPORT_NOT_EXISTS;

@Service
@Validated
public class CycleReportServiceImpl implements CycleReportService {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private CycleReportMapper cycleReportMapper;

    @Resource
    private CycleReportStatsMapper statsMapper;

    // ─── 生成报表（实时聚合 + 持久化） ──────────────────────────────────────────

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createCycleReport(CycleReportCreateReqVO req) {
        LocalDateTime start = req.getStatStartTime();
        LocalDateTime end   = req.getStatEndTime();

        CycleReportDO do_ = new CycleReportDO();
        do_.setReportCycle(req.getReportCycle());
        do_.setStatTime(start.format(FMT) + "-" + end.format(FMT));
        do_.setStatStartTime(start);
        do_.setStatEndTime(end);
        do_.setRemark(req.getRemark());
        do_.setGenerateStatus("已生成");

        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        do_.setOperator(loginUserId != null ? String.valueOf(loginUserId) : "system");
        do_.setExportCount(0);

        // 实时聚合统计指标
        Integer orderCount = statsMapper.selectOrderCount(start, end);
        Integer paidCount  = statsMapper.selectPaidOrderCount(start, end);
        BigDecimal revenue = statsMapper.selectRevenue(start, end);

        do_.setCycleOrderCount(orderCount);
        do_.setCycleRevenue(revenue);
        do_.setPayRate(calcRate(paidCount, orderCount));
        do_.setChargeQuantity(BigDecimal.ZERO);
        do_.setLendCount(statsMapper.selectLendCount(start, end));
        do_.setRefundAmount(statsMapper.selectRefundAmount(start, end));
        do_.setWaitHandleAbnormalCount(statsMapper.selectWaitHandleAbnormalCount());
        do_.setCollectCompleteRate(statsMapper.selectCollectCompleteRate(start, end));
        do_.setCheckAccuracyRate(statsMapper.selectCheckAccuracyRate(start, end));

        // 同比（去年同期）
        LocalDateTime yoyStart = start.minusYears(1);
        LocalDateTime yoyEnd   = end.minusYears(1);
        do_.setYearOnYear(calcRatio(revenue, statsMapper.selectRevenue(yoyStart, yoyEnd)));

        // 环比（上一相同天数周期）
        long days = ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate()) + 1;
        LocalDateTime qoqEnd   = start.minusSeconds(1);
        LocalDateTime qoqStart = qoqEnd.minusDays(days - 1).withHour(0).withMinute(0).withSecond(0);
        do_.setChainRatio(calcRatio(revenue, statsMapper.selectRevenue(qoqStart, qoqEnd)));

        cycleReportMapper.insert(do_);
        return do_.getId();
    }

    // ─── 分页查询（查 DB） ───────────────────────────────────────────────────────

    @Override
    public PageResult<CycleReportRespVO> getCycleReportPage(CycleReportPageReqVO req) {
        PageResult<CycleReportDO> page = cycleReportMapper.selectPage(req);
        List<CycleReportRespVO> list = page.getList().stream()
                .map(do_ -> BeanUtils.toBean(do_, CycleReportRespVO.class))
                .collect(Collectors.toList());
        return new PageResult<>(list, page.getTotal());
    }

    // ─── 单条详情 ────────────────────────────────────────────────────────────────

    @Override
    public CycleReportRespVO getCycleReport(Long id) {
        CycleReportDO do_ = cycleReportMapper.selectById(id);
        if (do_ == null) throw exception(CYCLE_REPORT_NOT_EXISTS);
        return BeanUtils.toBean(do_, CycleReportRespVO.class);
    }

    // ─── 图表数据（解析 statTime 字符串，实时聚合 ECharts） ──────────────────────

    @Override
    public CycleReportChartRespVO getCycleReportChart(CycleReportChartReqVO req) {
        // statTime 格式：2026-04-22 00:00:00-2026-04-22 23:59:59
        String[] parts = req.getStatTime().split("-(?=\\d{4})");
        LocalDateTime start = LocalDateTime.parse(parts[0].trim(), FMT);
        LocalDateTime end   = LocalDateTime.parse(parts[1].trim(), FMT);

        CycleReportChartRespVO resp = new CycleReportChartRespVO();

        // 卡片
        CardData card = new CardData();
        Integer orderCount = statsMapper.selectOrderCount(start, end);
        Integer paidCount  = statsMapper.selectPaidOrderCount(start, end);
        card.setCycleOrderCount(orderCount);
        card.setCycleRevenue(statsMapper.selectRevenue(start, end));
        card.setPayRate(calcRate(paidCount, orderCount));
        card.setChargeQuantity(BigDecimal.ZERO);
        card.setLendCount(statsMapper.selectLendCount(start, end));
        card.setRefundAmount(statsMapper.selectRefundAmount(start, end));
        card.setWaitHandleAbnormalCount(statsMapper.selectWaitHandleAbnormalCount());
        card.setCollectCompleteRate(statsMapper.selectCollectCompleteRate(start, end));
        card.setCheckAccuracyRate(statsMapper.selectCheckAccuracyRate(start, end));
        resp.setCardData(card);

        // 折线图
        List<Map<String, Object>> lineData = new ArrayList<>();
        lineData.add(series("订单量趋势",   statsMapper.selectOrderCountTrend(start, end), "count"));
        lineData.add(series("营收趋势",     statsMapper.selectRevenueTrend(start, end),    "revenue"));
        lineData.add(series("退款金额趋势", statsMapper.selectRefundTrend(start, end),     "refundAmount"));
        lineData.add(series("异常订单趋势", statsMapper.selectAbnormalTrend(start, end),   "count"));
        resp.setLineData(lineData);

        // 柱状图
        List<Map<String, Object>> barData = new ArrayList<>();
        barData.add(named("各类型订单量分布", statsMapper.selectOrderTypeDistribution(start, end)));
        barData.add(named("各场站订单量分布", statsMapper.selectStationDistribution(start, end)));
        resp.setBarData(barData);

        // 饼图
        List<Map<String, Object>> pieData = new ArrayList<>();
        pieData.add(named("订单状态占比", statsMapper.selectStatusDistribution(start, end)));
        pieData.add(named("支付方式占比", statsMapper.selectPayMethodDistribution(start, end)));
        pieData.add(named("异常类型占比", statsMapper.selectAbnormalTypeDistribution(start, end)));
        resp.setPieData(pieData);

        return resp;
    }

    // ─── 工具 ────────────────────────────────────────────────────────────────────

    private BigDecimal calcRate(Integer part, Integer total) {
        if (total == null || total == 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(part == null ? 0 : part)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(total), 2, RoundingMode.HALF_UP);
    }

    private String calcRatio(BigDecimal current, BigDecimal base) {
        if (base == null || base.compareTo(BigDecimal.ZERO) == 0) return null;
        if (current == null) current = BigDecimal.ZERO;
        BigDecimal rate = current.subtract(base)
                .divide(base, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);
        return (rate.compareTo(BigDecimal.ZERO) >= 0 ? "+" : "") + rate + "%";
    }

    private Map<String, Object> series(String name, List<Map<String, Object>> rows, String valueKey) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("name", name);
        m.put("data", rows.stream().map(r -> r.get(valueKey)).collect(Collectors.toList()));
        return m;
    }

    private Map<String, Object> named(String name, List<Map<String, Object>> rows) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("name", name);
        m.put("data", rows);
        return m;
    }
}
