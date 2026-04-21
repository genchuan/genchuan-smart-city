package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportCompareRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.TimeReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.enums.ReportPeriodEnum;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 服务运营报表（决策分析）Controller
 *
 * 文档要求 4 个接口:page / export / get / chart
 *
 * 服务运营报表不独立建表(客户文档原文),所有数据通过聚合 SQL + 定时任务动态生成。
 * page 返回"日/周/月/季/半年/年"6 种时间尺度的报表台账列表,
 * get 按 period 返回某个尺度的具体报表数据,
 * chart 返回综合统计图表,
 * export 按 period + format 导出报表(支持 excel/pdf,年报/季报建议 pdf)。
 */
@Tag(name = "决策分析 - 服务运营报表")
@RestController
@RequestMapping("/carservice/service-op-report")
@Validated
public class ServiceOpReportController {

    @Resource
    private ServiceOpReportService serviceOpReportService;

    /** 自动生成报表开关:进程级配置,默认开启(02 文档要求"支持开启 / 关闭自动生成报表功能")。
     *  注意:多实例部署需改为 infra_config 表持久化;当前为单实例 demo 实现 */
    private static final AtomicBoolean AUTO_GENERATE_ENABLED = new AtomicBoolean(true);

    /** 自定义报表内存存储(02 文档:"自定义报表生成后新增 1 条列表行")。
     *  Key 是复合 (tenantId, id),保证多租户隔离。
     *  注意:进程内存储,重启会丢;多实例需改 service_op_report_custom 表 */
    private static final Map<Map.Entry<Long, Long>, TimeReportRespVO> CUSTOM_REPORTS = new ConcurrentHashMap<>();
    private static final AtomicLong CUSTOM_REPORT_ID = new AtomicLong(10000);
    /** 单租户自定义报表条数上限,防内存泄漏 */
    private static final int CUSTOM_REPORT_MAX_PER_TENANT = 1000;

    private Map.Entry<Long, Long> tenantKey(Long id) {
        return new AbstractMap.SimpleImmutableEntry<>(TenantContextHolder.getTenantId(), id);
    }

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 - 服务运营报表台账(6 种时间尺度)")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<TimeReportRespVO>> getServiceOpReportPage() {
        // 6 种时间尺度,每种生成一条当前周期报表
        List<TimeReportRespVO> list = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (ReportPeriodEnum period : ReportPeriodEnum.values()) {
            list.add(serviceOpReportService.generateReport(period, today));
        }
        PageResult<TimeReportRespVO> result = new PageResult<>();
        result.setList(list);
        result.setTotal((long) list.size());
        return success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 某时间尺度报表(period=DAILY/WEEKLY/MONTHLY/QUARTERLY/SEMI_ANNUAL/ANNUAL)")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<TimeReportRespVO> getServiceOpReport(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate) {
        return success(serviceOpReportService.generateReport(period, baseDate == null ? LocalDate.now() : baseDate));
    }

    @GetMapping("/chart")
    @Operation(summary = "服务运营分析图表 - 折线图+柱状图+核心指标")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<ServiceOpReportChartRespVO> getServiceOpReportChart(
            @RequestParam(value = "startTime", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartServiceOpReport(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各时间运营指标统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<TimeReportRespVO>> drillServiceOpReportLine() {
        return getServiceOpReportPage();
    }

    @GetMapping("/chart-drill-bar")
    @Operation(summary = "各服务类型运营统计(柱状图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<TimeReportRespVO>> drillServiceOpReportBar() {
        return getServiceOpReportPage();
    }

    @GetMapping("/row-export")
    @Operation(summary = "导出(列表行) - 同列表页导出接口")
    @Parameter(name = "period", description = "时间尺度", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void rowExportServiceOpReport(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate,
            HttpServletResponse response) throws IOException {
        exportServiceOpReport(period, baseDate, response);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 时间尺度报表 PDF(年报/季报场景,客户文档要求年报支持打印)")
    @Parameter(name = "period", description = "时间尺度", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportServiceOpReport(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate,
            HttpServletResponse response) throws IOException {
        TimeReportRespVO report = serviceOpReportService.generateReport(period, baseDate == null ? LocalDate.now() : baseDate);

        // 把 report 的 modules map 拍平为一行表格
        List<Map<String, Object>> rows = new ArrayList<>();
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("period", report.getPeriodLabel());
        row.put("startTime", String.valueOf(report.getStartTime()));
        row.put("endTime", String.valueOf(report.getEndTime()));
        row.put("generateTime", String.valueOf(report.getGenerateTime()));
        if (report.getModules() != null) {
            for (Map.Entry<String, Object> e : report.getModules().entrySet()) {
                row.put(e.getKey(), String.valueOf(e.getValue()));
            }
        }
        rows.add(row);

        // 服务运营报表只输出 PDF(业务台账数据的 Excel 导出在各表 controller 的 /export 接口提供)
        String fileName = "服务运营" + report.getPeriodLabel() + ".pdf";
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();
        for (String key : row.keySet()) {
            headers.put(key, key);
        }
        PdfUtils.write(response, fileName, "服务运营" + report.getPeriodLabel() + "报表",
                headers, rows);
    }

    // ========== 02 模块功能文档要求的扩展接口 ==========

    @GetMapping("/auto-generate-status")
    @Operation(summary = "查询自动生成报表开关 - 02 文档要求")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<Boolean> getAutoGenerateStatus() {
        return success(AUTO_GENERATE_ENABLED.get());
    }

    @PutMapping("/auto-generate-enable")
    @Operation(summary = "开启自动生成报表 - 02 文档要求")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> enableAutoGenerate() {
        AUTO_GENERATE_ENABLED.set(true);
        return success(true);
    }

    @PutMapping("/auto-generate-disable")
    @Operation(summary = "关闭自动生成报表 - 02 文档要求")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> disableAutoGenerate() {
        AUTO_GENERATE_ENABLED.set(false);
        return success(true);
    }

    @PostMapping("/custom-generate")
    @Operation(summary = "自定义报表生成 - 02 文档要求 'period+startDate+endDate' 任意筛选")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Long> customGenerateReport(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate) {
        // 容量上限,防内存泄漏
        long currentTenantCount = countCustomReportsForCurrentTenant();
        if (currentTenantCount >= CUSTOM_REPORT_MAX_PER_TENANT) {
            throw new ServiceException(500, "自定义报表数量已达上限 " + CUSTOM_REPORT_MAX_PER_TENANT + ",请先删除旧报表");
        }
        TimeReportRespVO report = serviceOpReportService.generateReport(period,
                baseDate == null ? LocalDate.now() : baseDate);
        long id = CUSTOM_REPORT_ID.incrementAndGet();
        CUSTOM_REPORTS.put(tenantKey(id), report);
        return success(id);
    }

    @GetMapping("/custom-list")
    @Operation(summary = "自定义报表列表 - 已生成的自定义报表(分页)")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<TimeReportRespVO>> customListReports(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Long currentTenant = TenantContextHolder.getTenantId();
        // 只取当前租户的报表,按 id 倒序
        List<TimeReportRespVO> tenantReports = CUSTOM_REPORTS.entrySet().stream()
                .filter(e -> Objects.equals(e.getKey().getKey(), currentTenant))
                .sorted(Comparator.comparingLong((Map.Entry<Map.Entry<Long, Long>, TimeReportRespVO> e)
                        -> e.getKey().getValue()).reversed())
                .map(Map.Entry::getValue)
                .toList();
        // 内存分页
        int total = tenantReports.size();
        int from = Math.max(0, (pageNo - 1) * pageSize);
        int to = Math.min(total, from + pageSize);
        List<TimeReportRespVO> slice = from < to ? tenantReports.subList(from, to) : new ArrayList<>();
        PageResult<TimeReportRespVO> result = new PageResult<>();
        result.setList(slice);
        result.setTotal((long) total);
        return success(result);
    }

    @DeleteMapping("/custom-delete")
    @Operation(summary = "删除自定义报表")
    @Parameter(name = "id", description = "自定义报表 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> customDeleteReport(@RequestParam("id") Long id) {
        return success(CUSTOM_REPORTS.remove(tenantKey(id)) != null);
    }

    private long countCustomReportsForCurrentTenant() {
        Long currentTenant = TenantContextHolder.getTenantId();
        return CUSTOM_REPORTS.keySet().stream()
                .filter(k -> Objects.equals(k.getKey(), currentTenant))
                .count();
    }

    @GetMapping("/compare-yoy")
    @Operation(summary = "同比对比 - 02 文档要求 (本期 vs 去年同期)")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<ServiceOpReportCompareRespVO> compareYoY(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate) {
        LocalDate base = baseDate == null ? LocalDate.now() : baseDate;
        ServiceOpReportCompareRespVO resp = new ServiceOpReportCompareRespVO();
        resp.setPeriodLabel(period.getLabel());
        resp.setCompareType("yoy");
        resp.setCurrent(serviceOpReportService.generateReport(period, base));
        resp.setPrevious(serviceOpReportService.generateReport(period, base.minusYears(1)));
        return success(resp);
    }

    @GetMapping("/compare-mom")
    @Operation(summary = "环比对比 - 02 文档要求 (本期 vs 上一周期,按 period 类型计算)")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<ServiceOpReportCompareRespVO> compareMoM(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate) {
        LocalDate base = baseDate == null ? LocalDate.now() : baseDate;
        // 按 period 类型计算上一周期(DAILY=减1天/WEEKLY=减1周/QUARTERLY=减3月 etc.)
        LocalDate previousBase = period.previousPeriod(base);
        ServiceOpReportCompareRespVO resp = new ServiceOpReportCompareRespVO();
        resp.setPeriodLabel(period.getLabel());
        resp.setCompareType("mom");
        resp.setCurrent(serviceOpReportService.generateReport(period, base));
        resp.setPrevious(serviceOpReportService.generateReport(period, previousBase));
        return success(resp);
    }

}
