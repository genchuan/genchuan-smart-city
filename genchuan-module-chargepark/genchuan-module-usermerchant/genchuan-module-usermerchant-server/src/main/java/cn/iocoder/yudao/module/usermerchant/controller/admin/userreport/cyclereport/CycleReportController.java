package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport;

import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport.CycleReportService;
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

@Tag(name = "管理后台 - 周期报表存储")
@RestController
@RequestMapping("/usermerchant/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @GetMapping("/page")
    @Operation(summary = "获得周期报表存储分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:query')")
    public CommonResult<PageResult<CycleReportPageRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CycleReportPageRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "生成周期报表（实时统计并存储）")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:create')")
    public CommonResult<CycleReportCreateRespVO> generateCycleReport(@Valid @RequestBody CycleReportCreateReqVO createReqVO) {
        return success(cycleReportService.createCycleReport(createReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出周期报表存储")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CycleReportDO> list = cycleReportService.getCycleReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "周期报表存储.xls", "数据", CycleReportPageRespVO.class,
                BeanUtils.toBean(list, CycleReportPageRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得周期报表存储")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:query')")
    public CommonResult<CycleReportGetRespVO> getCycleReport(@RequestParam("id") Long id) {
        CycleReportGetRespVO cycleReport = cycleReportService.getCycleReport(id);
        return success(BeanUtils.toBean(cycleReport, CycleReportGetRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "实时获取周期报表图表数据（不存储）")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getChartData(@Valid CycleReportChartReqVO reqVO) {
        CycleReportChartRespVO respVO = cycleReportService.getChartData(reqVO);
        return success(respVO);
    }

}