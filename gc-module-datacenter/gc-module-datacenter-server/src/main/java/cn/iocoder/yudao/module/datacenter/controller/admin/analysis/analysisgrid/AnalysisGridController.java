package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.AnalysisGridSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisgrid.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisgrid.AnalysisGridDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysisgrid.AnalysisGridService;

@Tag(name = "管理后台 - 按网格分域分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-grid")
@Validated
public class AnalysisGridController {

    @Resource
    private AnalysisGridService analysisGridService;

    @PostMapping("/create")
    @Operation(summary = "创建按网格分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:create')")
    public CommonResult<Long> createAnalysisGrid(@Valid @RequestBody AnalysisGridSaveReqVO createReqVO) {
        return success(analysisGridService.createAnalysisGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按网格分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:update')")
    public CommonResult<Boolean> updateAnalysisGrid(@Valid @RequestBody AnalysisGridSaveReqVO updateReqVO) {
        analysisGridService.updateAnalysisGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按网格分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:delete')")
    public CommonResult<Boolean> deleteAnalysisGrid(@RequestParam("id") Long id) {
        analysisGridService.deleteAnalysisGrid(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按网格分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:query')")
    public CommonResult<AnalysisGridRespVO> getAnalysisGrid(@RequestParam("id") Long id) {
        AnalysisGridDO analysisGrid = analysisGridService.getAnalysisGrid(id);
        return success(BeanUtils.toBean(analysisGrid, AnalysisGridRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按网格分域分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:query')")
    public CommonResult<PageResult<AnalysisGridRespVO>> getAnalysisGridPage(@Valid AnalysisGridPageReqVO pageReqVO) {
        PageResult<AnalysisGridDO> pageResult = analysisGridService.getAnalysisGridPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisGridRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按网格分域分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-grid:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisGridExcel(@Valid AnalysisGridPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisGridDO> list = analysisGridService.getAnalysisGridPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按网格分域分析研判统计.xls", "数据", AnalysisGridRespVO.class,
                        BeanUtils.toBean(list, AnalysisGridRespVO.class));
    }

}