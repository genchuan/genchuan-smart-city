package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.*;
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
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 资产分类管理")
@RestController
@RequestMapping("/datacenter/asset-cat-mng")
@Validated
public class AssetCatMngController {

    @Resource
    private AssetCatMngService assetCatMngService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:create')")
    public CommonResult<Long> createAssetCatMng(@Valid @RequestBody AssetCatMngSaveReqVO createReqVO) {
        return success(assetCatMngService.createAssetCatMng(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:update')")
    public CommonResult<Boolean> updateAssetCatMng(@Valid @RequestBody AssetCatMngSaveReqVO updateReqVO) {
        assetCatMngService.updateAssetCatMng(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:delete')")
    public CommonResult<Boolean> deleteAssetCatMng(@RequestParam("id") Long id) {
        assetCatMngService.deleteAssetCatMng(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:query')")
    public CommonResult<AssetCatMngRespVO> getAssetCatMng(@RequestParam("id") Long id) {
        AssetCatMngDO assetCatMng = assetCatMngService.getAssetCatMng(id);
        return success(BeanUtils.toBean(assetCatMng, AssetCatMngRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资产分类管理列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:query')")
    public CommonResult<List<AssetCatMngRespVO>> getAssetCatMngList(@Valid AssetCatMngListReqVO listReqVO) {
        List<AssetCatMngDO> list = assetCatMngService.getAssetCatMngList(listReqVO);
        return success(BeanUtils.toBean(list, AssetCatMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCatMngExcel(@Valid AssetCatMngListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<AssetCatMngDO> list = assetCatMngService.getAssetCatMngList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "资产分类管理.xls", "数据", AssetCatMngRespVO.class,
                        BeanUtils.toBean(list, AssetCatMngRespVO.class));
    }

    //======================== Excel 导入 =====================//
    @PostMapping("/import")
    @Operation(summary = "导入资产分类管理 Excel")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:import')")
    public CommonResult<AssetCatMngImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                             @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<AssetCatMngImportExcelVO> list = ExcelUtils.read(file, AssetCatMngImportExcelVO.class);
        return success(assetCatMngService.importAssetCatMngList(list, updateSupport));
    }

}