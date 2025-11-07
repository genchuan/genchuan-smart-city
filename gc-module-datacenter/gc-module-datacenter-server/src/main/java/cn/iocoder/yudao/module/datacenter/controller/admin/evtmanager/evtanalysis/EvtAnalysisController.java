package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.EvtAnalysisSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtanalysis.EvtAnalysisDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtanalysis.EvtAnalysisService;

@Tag(name = "管理后台 - 事件研判分析")
@RestController
@RequestMapping("/datacenter/evt-analysis")
@Validated
public class EvtAnalysisController {

    @Resource
    private EvtAnalysisService evtAnalysisService;

    @PostMapping("/create")
    @Operation(summary = "创建事件研判分析")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:create')")
    public CommonResult<Long> createEvtAnalysis(@Valid @RequestBody EvtAnalysisSaveReqVO createReqVO) {
        return success(evtAnalysisService.createEvtAnalysis(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件研判分析")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:update')")
    public CommonResult<Boolean> updateEvtAnalysis(@Valid @RequestBody EvtAnalysisSaveReqVO updateReqVO) {
        evtAnalysisService.updateEvtAnalysis(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件研判分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:delete')")
    public CommonResult<Boolean> deleteEvtAnalysis(@RequestParam("id") Long id) {
        evtAnalysisService.deleteEvtAnalysis(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件研判分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:query')")
    public CommonResult<EvtAnalysisRespVO> getEvtAnalysis(@RequestParam("id") Long id) {
        EvtAnalysisDO evtAnalysis = evtAnalysisService.getEvtAnalysis(id);
        return success(BeanUtils.toBean(evtAnalysis, EvtAnalysisRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件研判分析分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:query')")
    public CommonResult<PageResult<EvtAnalysisRespVO>> getEvtAnalysisPage(@Valid EvtAnalysisPageReqVO pageReqVO) {
        PageResult<EvtAnalysisDO> pageResult = evtAnalysisService.getEvtAnalysisPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtAnalysisRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件研判分析 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-analysis:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtAnalysisExcel(@Valid EvtAnalysisPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtAnalysisDO> list = evtAnalysisService.getEvtAnalysisPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件研判分析.xls", "数据", EvtAnalysisRespVO.class,
                        BeanUtils.toBean(list, EvtAnalysisRespVO.class));
    }

}