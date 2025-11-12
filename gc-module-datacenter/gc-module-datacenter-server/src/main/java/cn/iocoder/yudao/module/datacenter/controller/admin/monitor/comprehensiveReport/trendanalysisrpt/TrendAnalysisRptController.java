package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.trendanalysisrpt.vo.TrendAnalysisRptSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.trendanalysisrpt.TrendAnalysisRptService;

@Tag(name = "管理后台 - 综合趋势分析报表")
@RestController
@RequestMapping("/datacenter/trend-analysis-rpt")
@Validated
public class TrendAnalysisRptController {

    @Resource
    private TrendAnalysisRptService trendAnalysisRptService;

    @PostMapping("/create")
    @Operation(summary = "创建综合趋势分析报表")
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:create')")
    public CommonResult<Long> createTrendAnalysisRpt(@Valid @RequestBody TrendAnalysisRptSaveReqVO createReqVO) {
        return success(trendAnalysisRptService.createTrendAnalysisRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新综合趋势分析报表")
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:update')")
    public CommonResult<Boolean> updateTrendAnalysisRpt(@Valid @RequestBody TrendAnalysisRptSaveReqVO updateReqVO) {
        trendAnalysisRptService.updateTrendAnalysisRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除综合趋势分析报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:delete')")
    public CommonResult<Boolean> deleteTrendAnalysisRpt(@RequestParam("id") Long id) {
        trendAnalysisRptService.deleteTrendAnalysisRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得综合趋势分析报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:query')")
    public CommonResult<TrendAnalysisRptRespVO> getTrendAnalysisRpt(@RequestParam("id") Long id) {
        TrendAnalysisRptDO trendAnalysisRpt = trendAnalysisRptService.getTrendAnalysisRpt(id);
        return success(BeanUtils.toBean(trendAnalysisRpt, TrendAnalysisRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得综合趋势分析报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:query')")
    public CommonResult<PageResult<TrendAnalysisRptRespVO>> getTrendAnalysisRptPage(@Valid TrendAnalysisRptPageReqVO pageReqVO) {
        PageResult<TrendAnalysisRptDO> pageResult = trendAnalysisRptService.getTrendAnalysisRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TrendAnalysisRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出综合趋势分析报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:trend-analysis-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTrendAnalysisRptExcel(@Valid TrendAnalysisRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TrendAnalysisRptDO> list = trendAnalysisRptService.getTrendAnalysisRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "综合趋势分析报表.xls", "数据", TrendAnalysisRptRespVO.class,
                        BeanUtils.toBean(list, TrendAnalysisRptRespVO.class));
    }

}