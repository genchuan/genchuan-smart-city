package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.inspectop.service.cyclereport.CycleReportService;
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
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "巡查巡检 - 巡检运维报表")
@RestController
@RequestMapping("/inspectop/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @GetMapping("/page")
    @Operation(summary = "获得巡检运维报表存储分页")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CycleReportRespVO.class));
    }

    @PostMapping("/generate")
    @Operation(summary = "实时生成巡检运维报表")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:create')")
    public CommonResult<CycleReportRespVO> generateCycleReport(@Valid @RequestBody CycleReportGenerateReqVO generateReqVO) {
        CycleReportRespVO respVO = cycleReportService.generateCycleReport(generateReqVO);
        return success(respVO);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取巡检运维报表图表数据")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getCycleReportChart(@Valid CycleReportChartReqVO reqVO) {
        CycleReportChartRespVO respVO = cycleReportService.getCycleReportChart(reqVO);
        return success(respVO);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检运维报表存储 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CycleReportDO> list = cycleReportService.getCycleReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检运维报表存储.xls", "数据", CycleReportRespVO.class,
                BeanUtils.toBean(list, CycleReportRespVO.class));
    }


}