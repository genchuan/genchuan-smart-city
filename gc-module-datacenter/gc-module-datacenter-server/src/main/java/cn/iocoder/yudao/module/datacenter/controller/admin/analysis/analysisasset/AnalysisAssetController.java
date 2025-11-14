package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.AnalysisAssetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisasset.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisasset.AnalysisAssetDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysisasset.AnalysisAssetService;

@Tag(name = "管理后台 - 按资产分域分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-asset")
@Validated
public class AnalysisAssetController {

    @Resource
    private AnalysisAssetService analysisAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建按资产分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:create')")
    public CommonResult<Long> createAnalysisAsset(@Valid @RequestBody AnalysisAssetSaveReqVO createReqVO) {
        return success(analysisAssetService.createAnalysisAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按资产分域分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:update')")
    public CommonResult<Boolean> updateAnalysisAsset(@Valid @RequestBody AnalysisAssetSaveReqVO updateReqVO) {
        analysisAssetService.updateAnalysisAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按资产分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:delete')")
    public CommonResult<Boolean> deleteAnalysisAsset(@RequestParam("id") Long id) {
        analysisAssetService.deleteAnalysisAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按资产分域分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:query')")
    public CommonResult<AnalysisAssetRespVO> getAnalysisAsset(@RequestParam("id") Long id) {
        AnalysisAssetDO analysisAsset = analysisAssetService.getAnalysisAsset(id);
        return success(BeanUtils.toBean(analysisAsset, AnalysisAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按资产分域分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:query')")
    public CommonResult<PageResult<AnalysisAssetRespVO>> getAnalysisAssetPage(@Valid AnalysisAssetPageReqVO pageReqVO) {
        PageResult<AnalysisAssetDO> pageResult = analysisAssetService.getAnalysisAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按资产分域分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisAssetExcel(@Valid AnalysisAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisAssetDO> list = analysisAssetService.getAnalysisAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按资产分域分析研判统计.xls", "数据", AnalysisAssetRespVO.class,
                        BeanUtils.toBean(list, AnalysisAssetRespVO.class));
    }

}