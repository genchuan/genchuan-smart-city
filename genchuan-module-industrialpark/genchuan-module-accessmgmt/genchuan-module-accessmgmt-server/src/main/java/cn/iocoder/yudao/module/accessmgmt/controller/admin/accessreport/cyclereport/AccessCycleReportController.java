package cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.accessreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.accessreport.cyclereport.AccessCycleReportService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通行周期报表")
@RestController
@RequestMapping("/accessmgmt/access-cycle-report")
@Validated
@Hidden
public class AccessCycleReportController {

    @Resource
    private AccessCycleReportService accessCycleReportService;

    @GetMapping("/page")
    @Operation(summary = "获得通行周期报表分页")
    @PreAuthorize("@ss.hasPermission('access-cycle-report:query')")
    public CommonResult<PageResult<AccessCycleReportRespVO>> getAccessCycleReportPage(@Valid AccessCycleReportPageReqVO pageReqVO) {
        PageResult<AccessCycleReportRespVO> pageResult = accessCycleReportService.getAccessCycleReportPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @Operation(summary = "获得通行周期报表详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('access-cycle-report:query')")
    public CommonResult<AccessCycleReportRespVO> getAccessCycleReport(@RequestParam("id") Long id) {
        AccessCycleReportRespVO respVO = accessCycleReportService.getAccessCycleReport(id);
        return success(respVO);
    }

    @PostMapping("/generate")
    @Operation(summary = "生成通行周期报表")
    @PreAuthorize("@ss.hasPermission('access-cycle-report:generate')")
    public CommonResult<AccessCycleReportGenerateRespVO> generateAccessCycleReport(@Valid @RequestBody AccessCycleReportGenerateReqVO reqVO) {
        AccessCycleReportGenerateRespVO respVO = accessCycleReportService.generateAccessCycleReport(reqVO);
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出通行周期报表 Excel")
    @PreAuthorize("@ss.hasPermission('access-cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAccessCycleReportExcel(@Valid AccessCycleReportPageReqVO pageReqVO,
                                               HttpServletResponse response) throws IOException {
        String inputFileName = "通行周期报表_";

        List<AccessCycleReportRespVO> list = accessCycleReportService.getAccessCycleReportList(pageReqVO);

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        ExcelUtils.write(response, "通行周期报表.xls", "数据", AccessCycleReportRespVO.class,
                BeanUtils.toBean(list, AccessCycleReportRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "通行周期报表态势")
    @PreAuthorize("@ss.hasPermission('access-cycle-report:query')")
    public CommonResult<AccessCycleReportChartRespVO> getAccessCycleReportChart(
            @Parameter(name = "id", description = "报表ID", required = true) @RequestParam("id") Long id) {
        AccessCycleReportChartRespVO chartVO = accessCycleReportService.getAccessCycleReportChart(id);
        return success(chartVO);
    }

}
