package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSimpleRespVO;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetService;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetSaveReqVO;
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
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "管理后台 - 资产")
@RestController
@RequestMapping("/datacenter/thingsboard/asset")
@Validated
public class AssetController {

    @Resource
    private AssetService assetService;

    @PostMapping("/create")
    @Operation(summary = "创建资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:create')")
    public CommonResult<String> createAsset(@Valid @RequestBody AssetSaveReqVO createReqVO) {
        return success(assetService.createAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:update')")
    public CommonResult<Boolean> updateAsset(@Valid @RequestBody AssetSaveReqVO updateReqVO) {
        assetService.updateAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset:delete')")
    public CommonResult<Boolean> deleteAsset(@RequestParam("id") String id) {
        assetService.deleteAsset(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除资产")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:delete')")
    public CommonResult<Boolean> deleteAssetList(@RequestParam("ids") List<String> ids) {
        assetService.deleteAssetListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<AssetInfo> getAsset(@RequestParam("id") String id) {
        AssetInfo asset = assetService.getAsset(id);
        return success(BeanUtils.toBean(asset, AssetInfo.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<PageResult<AssetRespVO>> getAssetPage(@Valid AssetPageReqVO pageReqVO) {
        PageResult<Asset> pageResult = assetService.getAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetExcel(@Valid AssetPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Asset> list = assetService.getAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产.xls", "数据", AssetRespVO.class,
                BeanUtils.toBean(list, AssetRespVO.class));
    }
    /**
     * 获取资产简单信息
     */
    @GetMapping("/list")
    @Operation(summary = "获取资产简单信息列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset:query')")
    public CommonResult<List<AssetSimpleRespVO>> getAssetList(){
        List<AssetSimpleRespVO> list = assetService.getAssetList();
        return success(list);
    }

}