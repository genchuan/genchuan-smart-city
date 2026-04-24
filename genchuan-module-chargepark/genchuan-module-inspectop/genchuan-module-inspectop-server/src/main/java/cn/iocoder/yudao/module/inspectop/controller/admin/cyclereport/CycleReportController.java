package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.service.cyclereport.CycleReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "巡查巡检 - 巡检运维报表")
@RestController
@RequestMapping("/inspectop/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @GetMapping("/page")
    @Operation(summary = "获得巡检运维报表分页")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        PageResult<CycleReportRespVO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        return success(pageResult);
    }

    @PostMapping("/generate")
    @Operation(summary = "实时生成巡检运维报表")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:create')")
    public CommonResult<CycleReportRespVO> generateCycleReport(@Valid @RequestBody CycleReportGenerateReqVO generateReqVO) {
        CycleReportRespVO respVO = cycleReportService.generateCycleReport(generateReqVO);
        return success(respVO);
    }
}