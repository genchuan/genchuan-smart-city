package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.service.sharingreport.SharingReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;

import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "汽车充电 - 分账报表")
@RestController
@RequestMapping("/vehiclecharging/sharing-report")
@Validated
public class SharingReportController {

    @Resource
    private SharingReportService sharingReportService;

    @GetMapping("/page")
    @Operation(summary = "分账报表分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:query')")
    public CommonResult<PageResult<SharingReportPageRespVO>> getSharingReportPage(@Valid SharingReportPageReqVO pageReqVO) {
        PageResult<SharingReportPageRespVO> pageResult = sharingReportService.getSharingReportPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/exportBatch")
    @Operation(summary = "批量导出分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBatchSharingReport(@RequestParam("ids") List<Long> ids, HttpServletResponse response) throws IOException {
        sharingReportService.exportBatchReport(ids, response);
    }

    @PostMapping("/customCreate")
    @Operation(summary = "自定义报表生成")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:create')")
    public CommonResult<Long> customCreate(@Valid @RequestBody SharingReportCustomCreateReqVO reqVO) {
        Long reportId = sharingReportService.createSharingReport(reqVO);
        return success(reportId);
    }

    @GetMapping("/exportSingle")
    @Operation(summary = "导出单条分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSingleSharingReport(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        // 调用 Service 生成 Excel 并写入响应
        sharingReportService.exportSingleReport(id, response);
    }

    @GetMapping("/print")
    @Operation(summary = "打印分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing-report:print')")
    public CommonResult<Map<String, Object>> print(@RequestParam("id") Long id) {
        Map<String, Object> printData = sharingReportService.getPrintData(id);
        return success(printData);
    }

    @GetMapping("/chart")
    @Operation(summary = "分账报表统计分析图")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<SharingReportSummaryRespVO> chart(@Valid SharingReportChartReqVO reqVO) {
        return success(sharingReportService.getChartSummary(reqVO));
    }

    @GetMapping("/chart/timeTrend")
    @Operation(summary = "各时间尺度分账金额、结算单数趋势")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<SharingReportTimeTrendRespVO> timeTrend(@Valid SharingReportTimeTrendReqVO reqVO) {
        return success(sharingReportService.getTimeTrend(reqVO));
    }

    @GetMapping("/chart/cooperatorTimeAmount")
    @Operation(summary = "各合作方各时间尺度分账金额")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<SharingReportCooperatorTimeAmountRespVO> cooperatorTimeAmount(@Valid SharingReportCooperatorTimeAmountReqVO reqVO) {
        return success(sharingReportService.getCooperatorTimeAmount(reqVO));
    }

    @GetMapping("/chart/cooperatorRatio")
    @Operation(summary = "各合作方分账金额占比")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<SharingReportCooperatorRatioRespVO> cooperatorRatio(@Valid SharingReportCooperatorRatioReqVO reqVO) {
        return success(sharingReportService.getCooperatorRatio(reqVO));
    }

    @GetMapping("/chart/timeCount")
    @Operation(summary = "各时间尺度分账统计")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<SharingReportTimeCountRespVO> timeCount(@Valid SharingReportTimeCountReqVO reqVO) {
        return success(sharingReportService.getTimeCount(reqVO));
    }

//    @PostMapping("/recreate")
//    @Operation(summary = "重新生成自定义报表")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:create')")
//    public CommonResult<Long> recreateCustomReport(@RequestParam("id") Long id) {
//        Long reportId = sharingReportService.recreateCustomReport(id);
//        return success(reportId);
//    }

}
