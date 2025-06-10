package cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.eventstatisticalanalysis.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventstatisticalanalysis.EventStatisticalAnalysisDO;
import cn.iocoder.yudao.module.smartcity.service.eventstatisticalanalysis.EventStatisticalAnalysisService;

@Tag(name = "管理后台 - 事件统计分析")
@RestController
@RequestMapping("/smartcity/event-statistical-analysis")
@Validated
public class EventStatisticalAnalysisController {

    @Resource
    private EventStatisticalAnalysisService eventStatisticalAnalysisService;

    @PostMapping("/create")
    @Operation(summary = "创建事件统计分析")
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:create')")
    public CommonResult<Long> createEventStatisticalAnalysis(@Valid @RequestBody EventStatisticalAnalysisSaveReqVO createReqVO) {
        return success(eventStatisticalAnalysisService.createEventStatisticalAnalysis(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件统计分析")
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:update')")
    public CommonResult<Boolean> updateEventStatisticalAnalysis(@Valid @RequestBody EventStatisticalAnalysisSaveReqVO updateReqVO) {
        eventStatisticalAnalysisService.updateEventStatisticalAnalysis(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件统计分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:delete')")
    public CommonResult<Boolean> deleteEventStatisticalAnalysis(@RequestParam("id") Long id) {
        eventStatisticalAnalysisService.deleteEventStatisticalAnalysis(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件统计分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:query')")
    public CommonResult<EventStatisticalAnalysisRespVO> getEventStatisticalAnalysis(@RequestParam("id") Long id) {
        EventStatisticalAnalysisDO eventStatisticalAnalysis = eventStatisticalAnalysisService.getEventStatisticalAnalysis(id);
        return success(BeanUtils.toBean(eventStatisticalAnalysis, EventStatisticalAnalysisRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件统计分析分页")
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:query')")
    public CommonResult<PageResult<EventStatisticalAnalysisRespVO>> getEventStatisticalAnalysisPage(@Valid EventStatisticalAnalysisPageReqVO pageReqVO) {
        PageResult<EventStatisticalAnalysisDO> pageResult = eventStatisticalAnalysisService.getEventStatisticalAnalysisPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventStatisticalAnalysisRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件统计分析 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:event-statistical-analysis:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventStatisticalAnalysisExcel(@Valid EventStatisticalAnalysisPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventStatisticalAnalysisDO> list = eventStatisticalAnalysisService.getEventStatisticalAnalysisPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件统计分析.xls", "数据", EventStatisticalAnalysisRespVO.class,
                        BeanUtils.toBean(list, EventStatisticalAnalysisRespVO.class));
    }

}