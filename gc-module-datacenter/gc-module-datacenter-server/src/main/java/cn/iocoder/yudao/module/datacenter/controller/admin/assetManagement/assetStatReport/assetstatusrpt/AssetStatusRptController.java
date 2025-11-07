package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptService;

@Tag(name = "管理后台 - 资产状态统计")
@RestController
@RequestMapping("/datacenter/asset-status-rpt")
@Validated
public class AssetStatusRptController {

    @Resource
    private AssetStatusRptService assetStatusRptService;

    @PostMapping("/create")
    @Operation(summary = "创建资产状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:create')")
    public CommonResult<Long> createAssetStatusRpt(@Valid @RequestBody AssetStatusRptSaveReqVO createReqVO) {
        return success(assetStatusRptService.createAssetStatusRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:update')")
    public CommonResult<Boolean> updateAssetStatusRpt(@Valid @RequestBody AssetStatusRptSaveReqVO updateReqVO) {
        assetStatusRptService.updateAssetStatusRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产状态统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:delete')")
    public CommonResult<Boolean> deleteAssetStatusRpt(@RequestParam("id") Long id) {
        assetStatusRptService.deleteAssetStatusRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产状态统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:query')")
    public CommonResult<AssetStatusRptRespVO> getAssetStatusRpt(@RequestParam("id") Long id) {
        AssetStatusRptDO assetStatusRpt = assetStatusRptService.getAssetStatusRpt(id);
        return success(BeanUtils.toBean(assetStatusRpt, AssetStatusRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产状态统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:query')")
    public CommonResult<PageResult<AssetStatusRptRespVO>> getAssetStatusRptPage(@Valid AssetStatusRptPageReqVO pageReqVO) {
        PageResult<AssetStatusRptDO> pageResult = assetStatusRptService.getAssetStatusRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetStatusRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产状态统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-status-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetStatusRptExcel(@Valid AssetStatusRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetStatusRptDO> list = assetStatusRptService.getAssetStatusRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产状态统计.xls", "数据", AssetStatusRptRespVO.class,
                        BeanUtils.toBean(list, AssetStatusRptRespVO.class));
    }

}