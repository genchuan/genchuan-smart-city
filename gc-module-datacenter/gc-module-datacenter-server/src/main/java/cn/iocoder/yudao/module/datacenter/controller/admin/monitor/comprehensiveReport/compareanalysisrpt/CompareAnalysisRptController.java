package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.compareanalysisrpt.vo.CompareAnalysisRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.compareanalysisrpt.CompareAnalysisRptService;

@Tag(name = "管理后台 - 综合对比分析报表")
@RestController
@RequestMapping("/datacenter/compare-analysis-rpt")
@Validated
public class CompareAnalysisRptController {

    @Resource
    private CompareAnalysisRptService compareAnalysisRptService;

    @PostMapping("/create")
    @Operation(summary = "创建综合对比分析报表")
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:create')")
    public CommonResult<Long> createCompareAnalysisRpt(@Valid @RequestBody CompareAnalysisRptSaveReqVO createReqVO) {
        return success(compareAnalysisRptService.createCompareAnalysisRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新综合对比分析报表")
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:update')")
    public CommonResult<Boolean> updateCompareAnalysisRpt(@Valid @RequestBody CompareAnalysisRptSaveReqVO updateReqVO) {
        compareAnalysisRptService.updateCompareAnalysisRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除综合对比分析报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:delete')")
    public CommonResult<Boolean> deleteCompareAnalysisRpt(@RequestParam("id") Long id) {
        compareAnalysisRptService.deleteCompareAnalysisRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得综合对比分析报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:query')")
    public CommonResult<CompareAnalysisRptRespVO> getCompareAnalysisRpt(@RequestParam("id") Long id) {
        CompareAnalysisRptDO compareAnalysisRpt = compareAnalysisRptService.getCompareAnalysisRpt(id);
        return success(BeanUtils.toBean(compareAnalysisRpt, CompareAnalysisRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得综合对比分析报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:query')")
    public CommonResult<PageResult<CompareAnalysisRptRespVO>> getCompareAnalysisRptPage(@Valid CompareAnalysisRptPageReqVO pageReqVO) {
        PageResult<CompareAnalysisRptDO> pageResult = compareAnalysisRptService.getCompareAnalysisRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CompareAnalysisRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出综合对比分析报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:compare-analysis-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompareAnalysisRptExcel(@Valid CompareAnalysisRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CompareAnalysisRptDO> list = compareAnalysisRptService.getCompareAnalysisRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "综合对比分析报表.xls", "数据", CompareAnalysisRptRespVO.class,
                        BeanUtils.toBean(list, CompareAnalysisRptRespVO.class));
    }

}