package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetasset.AssetAssetDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetasset.AssetAssetService;

@Tag(name = "管理后台 - 资产关联资产")
@RestController
@RequestMapping("/datacenter/asset-asset")
@Validated
public class AssetAssetController {

    @Resource
    private AssetAssetService assetAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:create')")
    public CommonResult<Long> createAssetAsset(@Valid @RequestBody AssetAssetSaveReqVO createReqVO) {
        return success(assetAssetService.createAssetAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:update')")
    public CommonResult<Boolean> updateAssetAsset(@Valid @RequestBody AssetAssetSaveReqVO updateReqVO) {
        assetAssetService.updateAssetAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联资产")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:delete')")
    public CommonResult<Boolean> deleteAssetAsset(@RequestParam("id") Long id) {
        assetAssetService.deleteAssetAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联资产")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:query')")
    public CommonResult<AssetAssetRespVO> getAssetAsset(@RequestParam("id") Long id) {
        AssetAssetDO assetAsset = assetAssetService.getAssetAsset(id);
        return success(BeanUtils.toBean(assetAsset, AssetAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产关联资产分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:query')")
    public CommonResult<PageResult<AssetAssetRespVO>> getAssetAssetPage(@Valid AssetAssetPageReqVO pageReqVO) {
        PageResult<AssetAssetDO> pageResult = assetAssetService.getAssetAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联资产 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetAssetExcel(@Valid AssetAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetAssetDO> list = assetAssetService.getAssetAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产关联资产.xls", "数据", AssetAssetRespVO.class,
                        BeanUtils.toBean(list, AssetAssetRespVO.class));
    }

}