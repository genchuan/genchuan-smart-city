package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.asset.AssetDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.asset.AssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 资产-thingsboard")
@RestController
@RequestMapping("/park/asset")
@Validated
public class AssetController {

    @Resource
    private AssetService assetService;

    @PostMapping("/create")
    @Operation(summary = "创建资产-thingsboard")
    @PreAuthorize("@ss.hasPermission('park:asset:create')")
    public CommonResult<Long> createAsset(@Valid @RequestBody AssetSaveReqVO createReqVO) {
        return success(assetService.createAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产-thingsboard")
    @PreAuthorize("@ss.hasPermission('park:asset:update')")
    public CommonResult<Boolean> updateAsset(@Valid @RequestBody AssetSaveReqVO updateReqVO) {
        assetService.updateAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产-thingsboard")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:asset:delete')")
    public CommonResult<Boolean> deleteAsset(@RequestParam("id") Long id) {
        assetService.deleteAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产-thingsboard")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:asset:query')")
    public CommonResult<AssetRespVO> getAsset(@RequestParam("id") Long id) {
        AssetDO asset = assetService.getAsset(id);
        return success(BeanUtils.toBean(asset, AssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产-thingsboard分页")
    @PreAuthorize("@ss.hasPermission('park:asset:query')")
    public CommonResult<PageResult<AssetRespVO>> getAssetPage(@Valid AssetPageReqVO pageReqVO) {
        PageResult<AssetDO> pageResult = assetService.getAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产-thingsboard Excel")
    @PreAuthorize("@ss.hasPermission('park:asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetExcel(@Valid AssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetDO> list = assetService.getAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产-thingsboard.xls", "数据", AssetRespVO.class,
                        BeanUtils.toBean(list, AssetRespVO.class));
    }

}
