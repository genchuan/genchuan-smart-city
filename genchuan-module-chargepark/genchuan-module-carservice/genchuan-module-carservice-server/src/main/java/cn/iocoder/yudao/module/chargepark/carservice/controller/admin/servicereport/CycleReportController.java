package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportDetailRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportExportRow;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.service.servicereport.CycleReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.CREATE;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 用户服务报表 - 周期报表 Controller
 *
 * 07 文档要求 11 个接口:page / create / export / get / row-export / chart + 5 个钻取别名(card/line/bar/map/pie)
 *
 * 无独立建表,基于 11 张业务表即时聚合;生成结果放进程内 Map。
 */
@Tag(name = "用户服务报表 - 周期报表")
@RestController
@RequestMapping("/carservice/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 - 周期报表分页",
            description = "按 reportCycle / statStartTime / statEndTime / generateStatus 过滤已生成的报表。" +
                    "列表为进程内内存,重启丢失;自动过滤多租户")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO reqVO) {
        return success(cycleReportService.pageCycleReport(reqVO));
    }

    @GetMapping("/page-all")
    @Operation(summary = "按时间范围混合查询 - 区间内完整落入的日/周/月/季/半年/年报全部返回",
            description = "仅接受 statStartTime + statEndTime(含可选 pageNo/pageSize)。" +
                    "后端遍历 6 种周期,凡是完整落在区间内的窗口都纳入。按颗粒度从大到小 + 时间倒序排序。")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> pageAllCycleReport(
            @RequestParam("statStartTime")
            @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") java.time.LocalDateTime statStartTime,
            @RequestParam("statEndTime")
            @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") java.time.LocalDateTime statEndTime,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return success(cycleReportService.pageAllCycleReport(statStartTime, statEndTime, pageNo, pageSize));
    }

    @PostMapping("/create")
    @Operation(summary = "生成 - 按 statType + statStartTime + statEndTime 生成一份报表")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:create')")
    @ApiAccessLog(operateType = CREATE)
    public CommonResult<java.util.Map<String, Long>> createCycleReport(@Valid @RequestBody CycleReportCreateReqVO reqVO) {
        Long id = cycleReportService.createCycleReport(reqVO);
        // 按 07 文档样例包装 {id: xxx} + 自定义 msg
        CommonResult<java.util.Map<String, Long>> r = new CommonResult<>();
        r.setCode(0);
        r.setData(java.util.Collections.singletonMap("id", id));
        r.setMsg("报表生成成功");
        return r;
    }

    @GetMapping("/get")
    @Operation(summary = "查看 - 单条周期报表详情(含 detailData 明细,仅取前 50 条预览)")
    @Parameter(name = "id", description = "报表 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<CycleReportDetailRespVO> getCycleReport(@RequestParam("id") Long id) {
        return success(cycleReportService.getCycleReport(id));
    }

    @GetMapping("/compare-yoy")
    @Operation(summary = "同比分析 - 本期 vs 去年同期(钻取 yearOnYearGrowthRate 使用)")
    @Parameter(name = "id", description = "报表 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<java.util.Map<String, Object>> compareYoY(@RequestParam("id") Long id) {
        return success(cycleReportService.compareYoY(id));
    }

    @GetMapping("/compare-mom")
    @Operation(summary = "环比分析 - 本期 vs 上一等长周期(钻取 monthOnMonthGrowthRate 使用)")
    @Parameter(name = "id", description = "报表 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<java.util.Map<String, Object>> compareMoM(@RequestParam("id") Long id) {
        return success(cycleReportService.compareMoM(id));
    }

    @GetMapping("/detail")
    @Operation(summary = "查看 - 单维度明细分页(弹窗查全量数据)",
            description = "按 dimension 维度分页查询该报表时间窗内的业务明细。" +
                    "dimension 取值:rescue/reserve/complaint/findCar/spacePush/wording")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<java.util.Map<String, Object>>> getCycleReportDetail(
            @RequestParam("id") Long id,
            @RequestParam("dimension") String dimension,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        return success(cycleReportService.pageDetail(id, dimension, pageNo, pageSize));
    }

    @GetMapping("/chart")
    @Operation(summary = "数据可视化图表 - 卡片+折线+柱状+地图+饼图全维度")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getCycleReportChart(@Valid CycleReportChartReqVO reqVO) {
        return success(cycleReportService.chartCycleReport(reqVO));
    }

    /** 单次导出硬上限(兜底,与 Service 层 MAX_REPORTS_PER_TENANT 保持一致) */
    private static final int EXPORT_HARD_CAP = 1000;

    @GetMapping("/export")
    @Operation(summary = "导出 - 列表所有数据(Excel 格式)",
            description = "按 /page 相同参数拉全量列表,Excel 格式导出。单次最多导出 1000 条")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReport(@Valid CycleReportPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageNo(1);
        reqVO.setPageSize(EXPORT_HARD_CAP);
        List<CycleReportRespVO> list = cycleReportService.pageCycleReport(reqVO).getList();
        List<CycleReportExportRow> rows = list.stream().map(this::toExportRow).collect(Collectors.toList());
        String fileName = "周期报表-" + LocalDate.now() + ".xls";
        ExcelUtils.write(response, fileName, "周期报表", CycleReportExportRow.class, rows);
    }

    @GetMapping("/row-export")
    @Operation(summary = "导出(列表行)- 单条周期报表多 Sheet Excel(概要 + 6 维度全量明细)")
    @Parameter(name = "id", description = "报表 ID", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void rowExportCycleReport(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        CycleReportDetailRespVO detail = cycleReportService.getCycleReport(id);
        java.util.Map<String, java.util.List<java.util.Map<String, Object>>> full =
                cycleReportService.getFullDetailForExport(id);

        try (org.apache.poi.xssf.usermodel.XSSFWorkbook wb = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            // Sheet 1: 报表概要(key-value 形式)
            writeSummarySheet(wb, "报表概要", detail);
            // Sheet 2~7: 6 个维度全量明细
            String[][] sheets = {
                    {"救援明细", "rescueDetail"},
                    {"预约明细", "reserveDetail"},
                    {"投诉明细", "complaintDetail"},
                    {"寻车明细", "findCarDetail"},
                    {"空位推送明细", "spacePushDetail"},
                    {"话术明细", "wordingDetail"}
            };
            for (String[] s : sheets) {
                writeDetailSheet(wb, s[0], full.getOrDefault(s[1], java.util.Collections.emptyList()));
            }

            String fileName = java.net.URLEncoder.encode("周期报表-" + id + "-" + LocalDate.now() + ".xlsx",
                    java.nio.charset.StandardCharsets.UTF_8).replace("+", "%20");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            wb.write(response.getOutputStream());
        }
    }

    /** 写 Sheet 1:报表概要,以 key-value 两列形式展示基础字段 */
    private void writeSummarySheet(org.apache.poi.xssf.usermodel.XSSFWorkbook wb, String name, CycleReportDetailRespVO d) {
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet(name);
        String[][] fields = {
                {"报表 ID", String.valueOf(d.getId())},
                {"报表周期", nz(d.getReportCycle())},
                {"统计时段", nz(d.getStatTime())},
                {"救援完成率", d.getRescueCompleteRate() == null ? "-" : d.getRescueCompleteRate() + "%"},
                {"预约成功率", d.getReserveSuccessRate() == null ? "-" : d.getReserveSuccessRate() + "%"},
                {"投诉处理率", d.getComplaintHandleRate() == null ? "-" : d.getComplaintHandleRate() + "%"},
                {"寻车定位成功率", d.getFindCarSuccessRate() == null ? "-" : d.getFindCarSuccessRate() + "%"},
                {"空位推送成功率", d.getSpacePushSuccessRate() == null ? "-" : d.getSpacePushSuccessRate() + "%"},
                {"生效话术数", String.valueOf(d.getEffectiveWordingCount())},
                {"救援总量", String.valueOf(d.getRescueTotal())},
                {"预约总量", String.valueOf(d.getReserveTotal())},
                {"投诉总量", String.valueOf(d.getComplaintTotal())},
                {"空位推送总量", String.valueOf(d.getSpacePushTotal())},
                {"生成状态", nz(d.getGenerateStatus())},
                {"生成时间", d.getGenerateTime() == null ? "-" :
                        d.getGenerateTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))},
                {"操作人", nz(d.getOperator())},
                {"同比增长率", d.getYearOnYearGrowthRate() == null ? "-" : d.getYearOnYearGrowthRate() + "%"},
                {"环比增长率", d.getMonthOnMonthGrowthRate() == null ? "-" : d.getMonthOnMonthGrowthRate() + "%"},
                {"服务状态占比", nz(d.getServiceStatusRatio())},
                {"创建者", nz(d.getCreator())},
                {"创建时间", d.getCreateTime() == null ? "-" :
                        d.getCreateTime().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}
        };
        // 表头
        org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("字段");
        header.createCell(1).setCellValue("值");
        // 内容
        for (int i = 0; i < fields.length; i++) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(fields[i][0]);
            row.createCell(1).setCellValue(fields[i][1]);
        }
        sheet.setColumnWidth(0, 5000);
        sheet.setColumnWidth(1, 12000);
    }

    /** 写某维度明细 Sheet;列按第一行 Map 的 key 集合动态生成 */
    private void writeDetailSheet(org.apache.poi.xssf.usermodel.XSSFWorkbook wb, String name,
                                  java.util.List<java.util.Map<String, Object>> list) {
        org.apache.poi.ss.usermodel.Sheet sheet = wb.createSheet(name);
        if (list == null || list.isEmpty()) {
            sheet.createRow(0).createCell(0).setCellValue("无数据");
            return;
        }
        java.util.List<String> keys = new java.util.ArrayList<>(list.get(0).keySet());
        // 表头
        org.apache.poi.ss.usermodel.Row header = sheet.createRow(0);
        for (int i = 0; i < keys.size(); i++) {
            header.createCell(i).setCellValue(keys.get(i));
            sheet.setColumnWidth(i, 4500);
        }
        // 内容
        for (int r = 0; r < list.size(); r++) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(r + 1);
            java.util.Map<String, Object> m = list.get(r);
            for (int c = 0; c < keys.size(); c++) {
                Object v = m.get(keys.get(c));
                String s = v == null ? "" : v.toString();
                if (s.length() > 32760) s = s.substring(0, 32760); // Excel 单元格上限
                row.createCell(c).setCellValue(s);
            }
        }
    }

    private String nz(String s) { return s == null ? "-" : s; }

    // ========== 5 个钻取别名:客户文档明确"同 page 一样" ==========

    @GetMapping("/chart-drill-card")
    @Operation(summary = "卡片钻取 - 同 page 分页接口")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> drillCard(@Valid CycleReportPageReqVO reqVO) {
        return getCycleReportPage(reqVO);
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "折线图钻取 - 同 page 分页接口")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> drillLine(@Valid CycleReportPageReqVO reqVO) {
        return getCycleReportPage(reqVO);
    }

    @GetMapping("/chart-drill-bar")
    @Operation(summary = "柱状图钻取 - 同 page 分页接口")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> drillBar(@Valid CycleReportPageReqVO reqVO) {
        return getCycleReportPage(reqVO);
    }

    @GetMapping("/chart-drill-map")
    @Operation(summary = "地图钻取 - 同 page 分页接口")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> drillMap(@Valid CycleReportPageReqVO reqVO) {
        return getCycleReportPage(reqVO);
    }

    @GetMapping("/chart-drill-pie")
    @Operation(summary = "饼图钻取 - 同 page 分页接口")
    @PreAuthorize("@ss.hasPermission('carservice:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> drillPie(@Valid CycleReportPageReqVO reqVO) {
        return getCycleReportPage(reqVO);
    }

    /** VO → Excel 扁平行 */
    private CycleReportExportRow toExportRow(CycleReportRespVO v) {
        CycleReportExportRow r = new CycleReportExportRow();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        r.setId(v.getId() == null ? "" : v.getId().toString());
        r.setReportCycle(v.getReportCycle());
        r.setStatTime(v.getStatTime());
        r.setRescueCompleteRate(str(v.getRescueCompleteRate()));
        r.setReserveSuccessRate(str(v.getReserveSuccessRate()));
        r.setComplaintHandleRate(str(v.getComplaintHandleRate()));
        r.setFindCarSuccessRate(str(v.getFindCarSuccessRate()));
        r.setSpacePushSuccessRate(str(v.getSpacePushSuccessRate()));
        r.setEffectiveWordingCount(v.getEffectiveWordingCount() == null ? "" : v.getEffectiveWordingCount().toString());
        r.setRescueTotal(v.getRescueTotal() == null ? "" : v.getRescueTotal().toString());
        r.setReserveTotal(v.getReserveTotal() == null ? "" : v.getReserveTotal().toString());
        r.setComplaintTotal(v.getComplaintTotal() == null ? "" : v.getComplaintTotal().toString());
        r.setSpacePushTotal(v.getSpacePushTotal() == null ? "" : v.getSpacePushTotal().toString());
        r.setGenerateStatus(v.getGenerateStatus());
        r.setGenerateTime(v.getGenerateTime() == null ? "" : v.getGenerateTime().format(dtf));
        r.setOperator(v.getOperator());
        r.setYearOnYearGrowthRate(str(v.getYearOnYearGrowthRate()));
        r.setMonthOnMonthGrowthRate(str(v.getMonthOnMonthGrowthRate()));
        r.setServiceStatusRatio(v.getServiceStatusRatio());
        r.setCreator(v.getCreator());
        r.setCreateTime(v.getCreateTime() == null ? "" : v.getCreateTime().format(dtf));
        return r;
    }

    private String str(Object v) { return v == null ? "" : v.toString(); }

}
