package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.*;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;

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
    //======================== Excel 导入 =====================//
    @PostMapping("/import")
    @Operation(summary = "导入资产空间数据 Excel")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:import')")
    public CommonResult<AssetSpatialDataImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                                  @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<AssetSpatialDataImportExcelVO> list = ExcelUtils.read(file, AssetSpatialDataImportExcelVO.class);
        return success(assetSpatialDataService.importAssetSpatialDataList(list, updateSupport));
    }

    //======================== Excel 导入模板 =====================//
    @GetMapping("/import-template")
    @Operation(summary = "下载资产空间数据导入模板")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-spatial-data:import')")
    @ApiAccessLog(operateType = EXPORT)
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 创建模板数据，两条示例数据
        List<AssetSpatialDataImportExcelVO> list = Arrays.asList(
                AssetSpatialDataImportExcelVO.builder()
                        .assetSpatialId("SPATIAL011")
                        .relAssetId("ASSET001")
                        .relAssetName("市政道路001")
                        .coordSystemType("WGS84")
                        .coordX(new BigDecimal("116.397428"))
                        .coordY(new BigDecimal("39.90923"))
                        .elevation(new BigDecimal("45.5"))
                        .boundaryCoords("116.3974,39.9092;116.3975,39.9093;116.3976,39.9091")
                        .spatialDataSource("GPS测量")
                        .inputTime(LocalDateTime.now())
                        .operUser("张三")
                        .updateUser("张三")
                        .updatedTime(LocalDateTime.now())
                        .build(),
                AssetSpatialDataImportExcelVO.builder()
                        .assetSpatialId("SPATIAL022")
                        .relAssetId("ASSET002")
                        .relAssetName("桥梁设施002")
                        .coordSystemType("GCJ02")
                        .coordX(new BigDecimal("116.406605"))
                        .coordY(new BigDecimal("39.921984"))
                        .elevation(new BigDecimal("32.8"))
                        .boundaryCoords("116.4066,39.9219;116.4067,39.9220;116.4068,39.9218")
                        .spatialDataSource("人工录入")
                        .inputTime(LocalDateTime.now())
                        .operUser("李四")
                        .updateUser("李四")
                        .updatedTime(LocalDateTime.now())
                        .build()
        );

        // 导出模板
        ExcelUtils.write(response, "资产空间数据导入模板.xls", "空间数据",
                AssetSpatialDataImportExcelVO.class, list);
    }

}