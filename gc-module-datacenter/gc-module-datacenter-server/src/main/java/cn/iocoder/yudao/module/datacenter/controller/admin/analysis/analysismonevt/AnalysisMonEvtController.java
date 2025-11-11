package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.AnalysisMonEvtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismonevt.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismonevt.AnalysisMonEvtDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysismonevt.AnalysisMonEvtService;

@Tag(name = "管理后台 - 按监测事件分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-mon-evt")
@Validated
public class AnalysisMonEvtController {

    @Resource
    private AnalysisMonEvtService analysisMonEvtService;

    @PostMapping("/create")
    @Operation(summary = "创建按监测事件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:create')")
    public CommonResult<Long> createAnalysisMonEvt(@Valid @RequestBody AnalysisMonEvtSaveReqVO createReqVO) {
        return success(analysisMonEvtService.createAnalysisMonEvt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按监测事件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:update')")
    public CommonResult<Boolean> updateAnalysisMonEvt(@Valid @RequestBody AnalysisMonEvtSaveReqVO updateReqVO) {
        analysisMonEvtService.updateAnalysisMonEvt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按监测事件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:delete')")
    public CommonResult<Boolean> deleteAnalysisMonEvt(@RequestParam("id") Long id) {
        analysisMonEvtService.deleteAnalysisMonEvt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按监测事件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:query')")
    public CommonResult<AnalysisMonEvtRespVO> getAnalysisMonEvt(@RequestParam("id") Long id) {
        AnalysisMonEvtDO analysisMonEvt = analysisMonEvtService.getAnalysisMonEvt(id);
        return success(BeanUtils.toBean(analysisMonEvt, AnalysisMonEvtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按监测事件分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:query')")
    public CommonResult<PageResult<AnalysisMonEvtRespVO>> getAnalysisMonEvtPage(@Valid AnalysisMonEvtPageReqVO pageReqVO) {
        PageResult<AnalysisMonEvtDO> pageResult = analysisMonEvtService.getAnalysisMonEvtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisMonEvtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按监测事件分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-evt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisMonEvtExcel(@Valid AnalysisMonEvtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisMonEvtDO> list = analysisMonEvtService.getAnalysisMonEvtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按监测事件分析研判统计.xls", "数据", AnalysisMonEvtRespVO.class,
                        BeanUtils.toBean(list, AnalysisMonEvtRespVO.class));
    }

}