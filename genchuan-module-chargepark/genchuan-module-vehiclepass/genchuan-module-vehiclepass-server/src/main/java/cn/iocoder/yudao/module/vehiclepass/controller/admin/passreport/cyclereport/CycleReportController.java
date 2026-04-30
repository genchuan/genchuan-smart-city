package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportCreateRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.vehiclepass.service.passreport.cyclereport.CycleReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 周期报表")
@RestController
@RequestMapping("/vehiclepass/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @PostMapping("/create")
    @Operation(summary = "生成周期报表")
    @PreAuthorize("@ss.hasPermission('vehiclepass:cycle-report:create')")
    public CommonResult<CycleReportCreateRespVO> createCycleReport(@Valid @RequestBody CycleReportCreateReqVO createReqVO) {
        return success(cycleReportService.createCycleReport(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 - 周期报表分页列表")
    @PreAuthorize("@ss.hasPermission('vehiclepass:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        return success(cycleReportService.getCycleReportPage(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出周期报表 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclepass:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        List<CycleReportRespVO> list = cycleReportService.getCycleReportList(pageReqVO);
        ExcelUtils.write(response, "周期报表.xls", "数据", CycleReportRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "车辆通行周期报表")
    @PreAuthorize("@ss.hasPermission('vehiclepass:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getChart(@Valid CycleReportChartReqVO reqVO) {
        return success(cycleReportService.getChart(reqVO));
    }

}