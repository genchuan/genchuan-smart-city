package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataService;

@Tag(name = "管理后台 - 资产空间数据")
@RestController
@RequestMapping("/datacenter/asset-spatial-data")
@Validated
public class AssetSpatialDataController {

    @Resource
    private AssetSpatialDataService assetSpatialDataService;

    @PostMapping("/create")
    @Operation(summary = "创建资产空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:create')")
    public CommonResult<Long> createAssetSpatialData(@Valid @RequestBody AssetSpatialDataSaveReqVO createReqVO) {
        return success(assetSpatialDataService.createAssetSpatialData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:update')")
    public CommonResult<Boolean> updateAssetSpatialData(@Valid @RequestBody AssetSpatialDataSaveReqVO updateReqVO) {
        assetSpatialDataService.updateAssetSpatialData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产空间数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:delete')")
    public CommonResult<Boolean> deleteAssetSpatialData(@RequestParam("id") Long id) {
        assetSpatialDataService.deleteAssetSpatialData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产空间数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:query')")
    public CommonResult<AssetSpatialDataRespVO> getAssetSpatialData(@RequestParam("id") Long id) {
        AssetSpatialDataDO assetSpatialData = assetSpatialDataService.getAssetSpatialData(id);
        return success(BeanUtils.toBean(assetSpatialData, AssetSpatialDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产空间数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:query')")
    public CommonResult<PageResult<AssetSpatialDataRespVO>> getAssetSpatialDataPage(@Valid AssetSpatialDataPageReqVO pageReqVO) {
        PageResult<AssetSpatialDataDO> pageResult = assetSpatialDataService.getAssetSpatialDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetSpatialDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产空间数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetSpatialDataExcel(@Valid AssetSpatialDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetSpatialDataDO> list = assetSpatialDataService.getAssetSpatialDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产空间数据.xls", "数据", AssetSpatialDataRespVO.class,
                        BeanUtils.toBean(list, AssetSpatialDataRespVO.class));
    }

}