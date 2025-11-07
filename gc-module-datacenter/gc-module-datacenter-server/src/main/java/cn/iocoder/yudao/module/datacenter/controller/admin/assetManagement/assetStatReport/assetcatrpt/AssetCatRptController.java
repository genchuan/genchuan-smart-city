package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetcatrpt.AssetCatRptDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetcatrpt.AssetCatRptService;

@Tag(name = "管理后台 - 资产分类统计")
@RestController
@RequestMapping("/datacenter/asset-cat-rpt")
@Validated
public class AssetCatRptController {

    @Resource
    private AssetCatRptService assetCatRptService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分类统计")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:create')")
    public CommonResult<Long> createAssetCatRpt(@Valid @RequestBody AssetCatRptSaveReqVO createReqVO) {
        return success(assetCatRptService.createAssetCatRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分类统计")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:update')")
    public CommonResult<Boolean> updateAssetCatRpt(@Valid @RequestBody AssetCatRptSaveReqVO updateReqVO) {
        assetCatRptService.updateAssetCatRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分类统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:delete')")
    public CommonResult<Boolean> deleteAssetCatRpt(@RequestParam("id") Long id) {
        assetCatRptService.deleteAssetCatRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分类统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:query')")
    public CommonResult<AssetCatRptRespVO> getAssetCatRpt(@RequestParam("id") Long id) {
        AssetCatRptDO assetCatRpt = assetCatRptService.getAssetCatRpt(id);
        return success(BeanUtils.toBean(assetCatRpt, AssetCatRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产分类统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:query')")
    public CommonResult<PageResult<AssetCatRptRespVO>> getAssetCatRptPage(@Valid AssetCatRptPageReqVO pageReqVO) {
        PageResult<AssetCatRptDO> pageResult = assetCatRptService.getAssetCatRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetCatRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分类统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCatRptExcel(@Valid AssetCatRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetCatRptDO> list = assetCatRptService.getAssetCatRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产分类统计.xls", "数据", AssetCatRptRespVO.class,
                        BeanUtils.toBean(list, AssetCatRptRespVO.class));
    }

}