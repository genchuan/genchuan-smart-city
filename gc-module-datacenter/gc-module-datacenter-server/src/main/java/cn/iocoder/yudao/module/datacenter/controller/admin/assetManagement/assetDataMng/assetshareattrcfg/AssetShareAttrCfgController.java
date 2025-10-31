package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.*;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 资产共享属性配置")
@RestController
@RequestMapping("/datacenter/asset-share-attr-cfg")
@Validated
public class AssetShareAttrCfgController {

    @Resource
    private AssetShareAttrCfgService assetShareAttrCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产共享属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:create')")
    public CommonResult<Long> createAssetShareAttrCfg(@Valid @RequestBody AssetShareAttrCfgSaveReqVO createReqVO) {
        return success(assetShareAttrCfgService.createAssetShareAttrCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产共享属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:update')")
    public CommonResult<Boolean> updateAssetShareAttrCfg(@Valid @RequestBody AssetShareAttrCfgSaveReqVO updateReqVO) {
        assetShareAttrCfgService.updateAssetShareAttrCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产共享属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:delete')")
    public CommonResult<Boolean> deleteAssetShareAttrCfg(@RequestParam("id") Long id) {
        assetShareAttrCfgService.deleteAssetShareAttrCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产共享属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:query')")
    public CommonResult<AssetShareAttrCfgRespVO> getAssetShareAttrCfg(@RequestParam("id") Long id) {
        AssetShareAttrCfgDO assetShareAttrCfg = assetShareAttrCfgService.getAssetShareAttrCfg(id);
        return success(BeanUtils.toBean(assetShareAttrCfg, AssetShareAttrCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产共享属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:query')")
    public CommonResult<PageResult<AssetShareAttrCfgRespVO>> getAssetShareAttrCfgPage(@Valid AssetShareAttrCfgPageReqVO pageReqVO) {
        PageResult<AssetShareAttrCfgDO> pageResult = assetShareAttrCfgService.getAssetShareAttrCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetShareAttrCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产共享属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetShareAttrCfgExcel(@Valid AssetShareAttrCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetShareAttrCfgDO> list = assetShareAttrCfgService.getAssetShareAttrCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产共享属性配置.xls", "数据", AssetShareAttrCfgRespVO.class,
                        BeanUtils.toBean(list, AssetShareAttrCfgRespVO.class));
    }
    //======================== Excel 导入 =====================//
    @PostMapping("/import")
    @Operation(summary = "导入资产共享属性配置 Excel")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:import')")
    public CommonResult<AssetShareAttrCfgImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                                   @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<AssetShareAttrCfgImportExcelVO> list = ExcelUtils.read(file, AssetShareAttrCfgImportExcelVO.class);
        return success(assetShareAttrCfgService.importExcel(list, updateSupport));
    }

    //======================== Excel 导入模板 =====================//
    @GetMapping("/import-template")
    @Operation(summary = "下载资产共享属性配置导入模板")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-share-attr-cfg:import')")
    @ApiAccessLog(operateType = EXPORT)
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 创建模板数据，两条示例数据
        List<AssetShareAttrCfgImportExcelVO> list = Arrays.asList(
                AssetShareAttrCfgImportExcelVO.builder()
                        .assetShareAttrId("SHARE_ATTR_001")
                        .relAssetId("ASSET001")
                        .relAssetName("共享资产001")
                        .attrName("文档权限")
                        .attrCode("doc_permission")
                        .attrValue("读写")
                        .shareObjType("用户")
                        .shareObjId("USER001")
                        .shareObjName("张三")
                        .sharePerm("读写")
                        .cfgTime(LocalDateTime.now())
                        .operUser("管理员")
                        .updateUser("管理员")
                        .updatedTime(LocalDateTime.now())
                        .build(),
                AssetShareAttrCfgImportExcelVO.builder()
                        .assetShareAttrId("SHARE_ATTR_002")
                        .relAssetId("ASSET002")
                        .relAssetName("共享资产002")
                        .attrName("数据访问")
                        .attrCode("data_access")
                        .attrValue("只读")
                        .shareObjType("部门")
                        .shareObjId("DEPT001")
                        .shareObjName("技术部")
                        .sharePerm("只读")
                        .cfgTime(LocalDateTime.now())
                        .operUser("李四")
                        .updateUser("李四")
                        .updatedTime(LocalDateTime.now())
                        .build()
        );

        // 导出模板
        ExcelUtils.write(response, "资产共享属性配置导入模板.xls", "共享属性配置",
                AssetShareAttrCfgImportExcelVO.class, list);
    }

}