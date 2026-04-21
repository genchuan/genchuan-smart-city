package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspectreport.InspectReportDO;
import cn.iocoder.yudao.module.inspectop.service.inspectreport.InspectReportService;

@Tag(name = "巡查巡检 - 巡检上报")
@RestController
@RequestMapping("/inspectop/inspect-report")
@Validated
public class InspectReportController {

    @Resource
    private InspectReportService inspectReportService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:create')")
    public CommonResult<Long> createInspectReport(@Valid @RequestBody InspectReportSaveReqVO createReqVO) {
        return success(inspectReportService.createInspectReport(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:update')")
    public CommonResult<Boolean> updateInspectReport(@Valid @RequestBody InspectReportSaveReqVO updateReqVO) {
        inspectReportService.updateInspectReport(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检上报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:delete')")
    public CommonResult<Boolean> deleteInspectReport(@RequestParam("id") Long id) {
        inspectReportService.deleteInspectReport(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检上报")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:delete')")
    public CommonResult<Boolean> deleteInspectReportList(@RequestParam("ids") List<Long> ids) {
        inspectReportService.deleteInspectReportListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检上报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:query')")
    public CommonResult<InspectReportRespVO> getInspectReport(@RequestParam("id") Long id) {
        InspectReportDO inspectReport = inspectReportService.getInspectReport(id);
        return success(BeanUtils.toBean(inspectReport, InspectReportRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检上报分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:query')")
    public CommonResult<PageResult<InspectReportRespVO>> getInspectReportPage(@Valid InspectReportPageReqVO pageReqVO) {
        PageResult<InspectReportRespVO> pageResult = inspectReportService.getInspectReportPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/batch-audit")
    @Operation(summary = "批量审核巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:batch-audit')")
    public CommonResult<Boolean> batchAuditInspectReport(@Valid @RequestBody InspectReportBatchAuditReqVO batchAuditReqVO) {
        inspectReportService.batchAuditInspectReport(batchAuditReqVO);
        return success(true);
    }

    @PutMapping("/approve")
    @Operation(summary = "通过巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:approve')")
    public CommonResult<Boolean> approveInspectReport(@Valid @RequestBody InspectReportApproveReqVO approveReqVO) {
        inspectReportService.approveInspectReport(approveReqVO);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:reject')")
    public CommonResult<Boolean> rejectInspectReport(@Valid @RequestBody InspectReportRejectReqVO rejectReqVO) {
        inspectReportService.rejectInspectReport(rejectReqVO);
        return success(true);
    }

    @PutMapping("/process")
    @Operation(summary = "执行巡检上报")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:process')")
    public CommonResult<Boolean> processInspectReport(@Valid @RequestBody InspectReportProcessReqVO processReqVO) {
        inspectReportService.processInspectReport(processReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取巡检上报图表数据")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:chart')")
    public CommonResult<InspectReportChartRespVO> getInspectReportChartData(
            @RequestParam(value = "timeRange", required = false) String[] timeRange) {
        return success(inspectReportService.getInspectReportChartData(timeRange));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检上报 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectReportExcel(@Valid InspectReportPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectReportRespVO> list = inspectReportService.getInspectReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检上报.xls", "数据", InspectReportRespVO.class,
                        BeanUtils.toBean(list, InspectReportRespVO.class));
    }

}