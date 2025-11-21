package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.*;
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

    @GetMapping("/page")
    @Operation(summary = "获得资产分类管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:query')")
    public CommonResult<PageResult<AssetCatMngRespVO>> getAssetCatMngPage(@Valid AssetCatMngPageReqVO pageReqVO) {
        PageResult<AssetCatMngDO> pageResult = assetCatMngService.getAssetCatMngPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetCatMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCatMngExcel(@Valid AssetCatMngPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetCatMngDO> list = assetCatMngService.getAssetCatMngPage(pageReqVO).getList();
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

    //======================== Excel 导入模板 =====================//
    @GetMapping("/import-template")
    @Operation(summary = "下载资产分类管理导入模板")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:import')")
    @ApiAccessLog(operateType = EXPORT)
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 创建模板数据，使用正确的数据类型
        List<AssetCatMngImportExcelVO> list = Arrays.asList(
                AssetCatMngImportExcelVO.builder()
                        .assetCatId("CAT001")
                        .relCatRuleId("RULE001")
                        .assetCatCode("01")
                        .assetCatName("市政设施")
                        .catLevel("1")  // 使用 Integer 类型
                        .parentCatId("0")
                        .parentCatName("无")
                        .catDesc("市政相关设施分类")
                        .enableStatus("1")  // 使用 Integer 类型
                        .createUser("管理员")
                        .createdTime(LocalDateTime.now())
                        .updateUser("管理员")
                        .updatedTime(LocalDateTime.now())
                        .build(),
                AssetCatMngImportExcelVO.builder()
                        .assetCatId("CAT002")
                        .relCatRuleId("RULE001")
                        .assetCatCode("0101")
                        .assetCatName("道路设施")
                        .catLevel("1")  // 使用 Integer 类型
                        .parentCatId("CAT001")
                        .parentCatName("市政设施")
                        .catDesc("道路相关设施")
                        .enableStatus("1")  // 使用 Integer 类型
                        .createUser("管理员")
                        .createdTime(LocalDateTime.now())
                        .updateUser("管理员")
                        .updatedTime(LocalDateTime.now())
                        .build()
        );

        // 导出模板
        ExcelUtils.write(response, "资产分类管理导入模板.xls", "资产分类数据",
                AssetCatMngImportExcelVO.class, list);
    }

    /**
     *
     * @return 获取启用的资产分类列表
     */
    @GetMapping("/enabled-list")
    @Operation(summary = "获取启用的资产分类列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:query')")
    public CommonResult<List<AssetCategorySimpleVO>> getEnabledAssetCategories() {
        List<AssetCategorySimpleVO> categories = assetCatMngService.getEnabledAssetCategories();
        return success(categories);
    }
    /**
     *
     * @return 获取资产分类列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取资产分类列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-mng:query')")
    public CommonResult<List<AssetCategorySimpleVO>> getAssetCatList() {
        List<AssetCategorySimpleVO> list = assetCatMngService.getAssetCatList();
        return success(list);
    }

}