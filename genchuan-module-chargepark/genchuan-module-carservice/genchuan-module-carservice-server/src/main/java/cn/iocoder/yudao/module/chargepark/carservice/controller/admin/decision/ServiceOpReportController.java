package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportCompareRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportExportRow;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.ServiceOpReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.decision.vo.TimeReportRespVO;
import jakarta.validation.Valid;
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
    @Operation(summary = "筛选/刷新 - 服务运营报表台账",
            description = "按 timeScale 切分 statTime 区间,每个子窗口动态生成一条报表(包含救援完成率/预约成功率/投诉处理率 + 同比/环比)。" +
                    "不建专表,全部即时聚合。参数可选,不传时默认 timeScale=月 + statTime=[近 1 年, 现在]")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<ServiceOpReportRespVO>> getServiceOpReportPage(@Valid ServiceOpReportPageReqVO reqVO) {
        return success(serviceOpReportService.pageServiceOpReport(reqVO));
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
    public CommonResult<PageResult<ServiceOpReportRespVO>> drillServiceOpReportLine(@Valid ServiceOpReportPageReqVO reqVO) {
        return getServiceOpReportPage(reqVO);
    }

    @GetMapping("/chart-drill-bar")
    @Operation(summary = "各服务类型运营统计(柱状图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    public CommonResult<PageResult<ServiceOpReportRespVO>> drillServiceOpReportBar(@Valid ServiceOpReportPageReqVO reqVO) {
        return getServiceOpReportPage(reqVO);
    }

    @GetMapping("/row-export")
    @Operation(summary = "导出(列表行) - 单个报表")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void rowExportServiceOpReport(
            @RequestParam("period") ReportPeriodEnum period,
            @RequestParam(value = "baseDate", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate baseDate,
            @RequestParam(value = "format", defaultValue = "excel") String format,
            HttpServletResponse response) throws IOException {
        // 行导出:只导一条报表,用对应的 period + 指定 baseDate(默认今天)生成窗口
        LocalDateTime base = (baseDate == null ? LocalDate.now() : baseDate).atStartOfDay();
        ServiceOpReportPageReqVO reqVO = new ServiceOpReportPageReqVO();
        reqVO.setTimeScale(period.getLabel().replace("报", ""));
        reqVO.setStatTime(new LocalDateTime[]{base, base});
        reqVO.setPageNo(1);
        reqVO.setPageSize(1);
        exportServiceOpReport(reqVO, format, response);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 服务运营报表列表(Excel/PDF 双格式)",
            description = "按 /page 相同参数拉全量列表(不分页),用 format=excel|pdf 切换格式。" +
                    "导出字段包含报表ID/类型/时间尺度/统计周期/生成时间/三率/同比环比/创建者。" +
                    "不传 format 默认 excel。")
    @PreAuthorize("@ss.hasPermission('carservice:service-op-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportServiceOpReport(@Valid ServiceOpReportPageReqVO reqVO,
                                      @RequestParam(value = "format", defaultValue = "excel") String format,
                                      HttpServletResponse response) throws IOException {
        // 拉全量列表(把 pageSize 设大,取 total 范围)
        reqVO.setPageNo(1);
        reqVO.setPageSize(Integer.MAX_VALUE);
        List<ServiceOpReportRespVO> list = serviceOpReportService.pageServiceOpReport(reqVO).getList();
        List<ServiceOpReportExportRow> rows = list.stream().map(this::toExportRow).collect(java.util.stream.Collectors.toList());

        String baseName = "服务运营报表-" + java.time.LocalDate.now();
        if ("pdf".equalsIgnoreCase(format)) {
            // PDF 表头(ExcelProperty 的 label → PDF 列标题)
            LinkedHashMap<String, String> pdfHeaders = new LinkedHashMap<>();
            pdfHeaders.put("id", "报表 ID");
            pdfHeaders.put("reportType", "报表类型");
            pdfHeaders.put("timeScale", "时间尺度");
            pdfHeaders.put("statPeriod", "统计周期");
            pdfHeaders.put("createTime", "生成时间");
            pdfHeaders.put("rescueFinishRate", "救援完成率");
            pdfHeaders.put("reserveSuccessRate", "预约成功率");
            pdfHeaders.put("complaintHandleRate", "投诉处理率");
            pdfHeaders.put("yoyRescueFinishRate", "同比-救援");
            pdfHeaders.put("yoyReserveSuccessRate", "同比-预约");
            pdfHeaders.put("qoqRescueFinishRate", "环比-救援");
            pdfHeaders.put("qoqReserveSuccessRate", "环比-预约");
            pdfHeaders.put("creator", "创建者");
            PdfUtils.write(response, baseName + ".pdf", "服务运营报表", pdfHeaders, rows);
        } else {
            ExcelUtils.write(response, baseName + ".xls", "服务运营报表", ServiceOpReportExportRow.class, rows);
        }
    }

    /** 把响应 VO 拍平成导出行(同比/环比 Map 拆成独立列) */
    private ServiceOpReportExportRow toExportRow(ServiceOpReportRespVO vo) {
        ServiceOpReportExportRow r = new ServiceOpReportExportRow();
        r.setId(String.valueOf(vo.getId()));
        r.setReportType(vo.getReportType());
        r.setTimeScale(vo.getTimeScale());
        r.setStatPeriod(vo.getStatPeriod());
        r.setCreateTime(vo.getCreateTime() == null ? "" :
                vo.getCreateTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        r.setRescueFinishRate(vo.getRescueFinishRate() == null ? "" : vo.getRescueFinishRate().toString());
        r.setReserveSuccessRate(vo.getReserveSuccessRate() == null ? "" : vo.getReserveSuccessRate().toString());
        r.setComplaintHandleRate(vo.getComplaintHandleRate() == null ? "" : vo.getComplaintHandleRate().toString());
        if (vo.getYoyData() != null) {
            r.setYoyRescueFinishRate(String.valueOf(vo.getYoyData().getOrDefault("rescueFinishRate", java.math.BigDecimal.ZERO)));
            r.setYoyReserveSuccessRate(String.valueOf(vo.getYoyData().getOrDefault("reserveSuccessRate", java.math.BigDecimal.ZERO)));
        }
        if (vo.getQoqData() != null) {
            r.setQoqRescueFinishRate(String.valueOf(vo.getQoqData().getOrDefault("rescueFinishRate", java.math.BigDecimal.ZERO)));
            r.setQoqReserveSuccessRate(String.valueOf(vo.getQoqData().getOrDefault("reserveSuccessRate", java.math.BigDecimal.ZERO)));
        }
        r.setCreator(vo.getCreator());
        return r;
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
