package cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.EventAnalysisPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.EventAnalysisRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.EventAnalysisSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventanalysis.EventAnalysisDO;
import cn.iocoder.yudao.module.smartcity.service.eventanalysis.EventAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 事件关联分析")
@RestController
@RequestMapping("/smartcity/event-analysis")
@Validated
public class EventAnalysisController {

    @Resource
    private EventAnalysisService eventAnalysisService;

    @PostMapping("/create")
    @Operation(summary = "创建事件关联分析")
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:create')")
    public CommonResult<Long> createEventAnalysis(@Valid @RequestBody EventAnalysisSaveReqVO createReqVO) {
        return success(eventAnalysisService.createEventAnalysis(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件关联分析")
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:update')")
    public CommonResult<Boolean> updateEventAnalysis(@Valid @RequestBody EventAnalysisSaveReqVO updateReqVO) {
        eventAnalysisService.updateEventAnalysis(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件关联分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:delete')")
    public CommonResult<Boolean> deleteEventAnalysis(@RequestParam("id") Long id) {
        eventAnalysisService.deleteEventAnalysis(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件关联分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:query')")
    public CommonResult<EventAnalysisRespVO> getEventAnalysis(@RequestParam("id") Long id) {
        EventAnalysisDO eventAnalysis = eventAnalysisService.getEventAnalysis(id);
        return success(BeanUtils.toBean(eventAnalysis, EventAnalysisRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件关联分析分页")
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:query')")
    public CommonResult<PageResult<EventAnalysisRespVO>> getEventAnalysisPage(@Valid EventAnalysisPageReqVO pageReqVO) {
        PageResult<EventAnalysisDO> pageResult = eventAnalysisService.getEventAnalysisPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventAnalysisRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件关联分析 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:event-analysis:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventAnalysisExcel(@Valid EventAnalysisPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventAnalysisDO> list = eventAnalysisService.getEventAnalysisPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件关联分析.xls", "数据", EventAnalysisRespVO.class,
                        BeanUtils.toBean(list, EventAnalysisRespVO.class));
    }

}