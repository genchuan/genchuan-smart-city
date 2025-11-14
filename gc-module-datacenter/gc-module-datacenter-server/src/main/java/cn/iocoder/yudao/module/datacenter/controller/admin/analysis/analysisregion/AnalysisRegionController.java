package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.AnalysisRegionSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisregion.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisregion.AnalysisRegionDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysisregion.AnalysisRegionService;

@Tag(name = "管理后台 - 按行政区划分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-region")
@Validated
public class AnalysisRegionController {

    @Resource
    private AnalysisRegionService analysisRegionService;

    @PostMapping("/create")
    @Operation(summary = "创建按行政区划分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:create')")
    public CommonResult<Long> createAnalysisRegion(@Valid @RequestBody AnalysisRegionSaveReqVO createReqVO) {
        return success(analysisRegionService.createAnalysisRegion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按行政区划分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:update')")
    public CommonResult<Boolean> updateAnalysisRegion(@Valid @RequestBody AnalysisRegionSaveReqVO updateReqVO) {
        analysisRegionService.updateAnalysisRegion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按行政区划分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:delete')")
    public CommonResult<Boolean> deleteAnalysisRegion(@RequestParam("id") Long id) {
        analysisRegionService.deleteAnalysisRegion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按行政区划分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:query')")
    public CommonResult<AnalysisRegionRespVO> getAnalysisRegion(@RequestParam("id") Long id) {
        AnalysisRegionDO analysisRegion = analysisRegionService.getAnalysisRegion(id);
        return success(BeanUtils.toBean(analysisRegion, AnalysisRegionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按行政区划分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:query')")
    public CommonResult<PageResult<AnalysisRegionRespVO>> getAnalysisRegionPage(@Valid AnalysisRegionPageReqVO pageReqVO) {
        PageResult<AnalysisRegionDO> pageResult = analysisRegionService.getAnalysisRegionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisRegionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按行政区划分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-region:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisRegionExcel(@Valid AnalysisRegionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisRegionDO> list = analysisRegionService.getAnalysisRegionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按行政区划分析研判统计.xls", "数据", AnalysisRegionRespVO.class,
                        BeanUtils.toBean(list, AnalysisRegionRespVO.class));
    }

}