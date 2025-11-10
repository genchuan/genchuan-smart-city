package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 资产服务端属性配置")
@RestController
@RequestMapping("/datacenter/asset-server-attr-cfg")
@Validated
public class AssetServerAttrCfgController {

    @Resource
    private AssetServerAttrCfgService assetServerAttrCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产服务端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:create')")
    public CommonResult<Long> createAssetServerAttrCfg(@Valid @RequestBody AssetServerAttrCfgSaveReqVO createReqVO) {
        return success(assetServerAttrCfgService.createAssetServerAttrCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产服务端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:update')")
    public CommonResult<Boolean> updateAssetServerAttrCfg(@Valid @RequestBody AssetServerAttrCfgSaveReqVO updateReqVO) {
        assetServerAttrCfgService.updateAssetServerAttrCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产服务端属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:delete')")
    public CommonResult<Boolean> deleteAssetServerAttrCfg(@RequestParam("id") Long id) {
        assetServerAttrCfgService.deleteAssetServerAttrCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产服务端属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:query')")
    public CommonResult<AssetServerAttrCfgRespVO> getAssetServerAttrCfg(@RequestParam("id") Long id) {
        AssetServerAttrCfgDO assetServerAttrCfg = assetServerAttrCfgService.getAssetServerAttrCfg(id);
        return success(BeanUtils.toBean(assetServerAttrCfg, AssetServerAttrCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产服务端属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:query')")
    public CommonResult<PageResult<AssetServerAttrCfgRespVO>> getAssetServerAttrCfgPage(@Valid AssetServerAttrCfgPageReqVO pageReqVO) {
        PageResult<AssetServerAttrCfgDO> pageResult = assetServerAttrCfgService.getAssetServerAttrCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetServerAttrCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产服务端属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetServerAttrCfgExcel(@Valid AssetServerAttrCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetServerAttrCfgDO> list = assetServerAttrCfgService.getAssetServerAttrCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产服务端属性配置.xls", "数据", AssetServerAttrCfgRespVO.class,
                        BeanUtils.toBean(list, AssetServerAttrCfgRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获取资产客户端属性列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:query')")
    public CommonResult<List<AssetServerAttrCfgSimpleRespVO>> getAssetServerAttrList(){
        List<AssetServerAttrCfgSimpleRespVO> list = assetServerAttrCfgService.getAssetServerAttrList();
        return success(list);
    }

    //======================== Excel 导入 =====================//
    @PostMapping("/import")
    @Operation(summary = "导入资产服务端属性配置 Excel")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:import')")
    public CommonResult<AssetServerAttrCfgImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
                                                                    @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception{
        List<AssetServerAttrCfgImportExcelVO> list = ExcelUtils.read(file, AssetServerAttrCfgImportExcelVO.class);
        return success(assetServerAttrCfgService.importAssetServerAttrCfgList(list, updateSupport));
    }
    //======================== Excel 导入模板 =====================//
    @GetMapping("/import-template")
    @Operation(summary = "下载资产服务端属性配置导入模板")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-server-attr-cfg:import')")
    @ApiAccessLog(operateType = EXPORT)
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 创建模板数据，两条示例数据
        List<AssetServerAttrCfgImportExcelVO> list = Arrays.asList(
                AssetServerAttrCfgImportExcelVO.builder()
                        .assetServerAttrId("SERVER_ATTR_001")
                        .relAssetId("ASSET001")
                        .relAssetName("服务器设备001")
                        .attrName("CPU使用率")
                        .attrCode("cpu_usage")
                        .attrDataType("百分比")
                        .attrValue("75.5")
                        .collectFreq("1h")
                        .lastCollectTime(LocalDateTime.now())
                        .cfgTime(LocalDateTime.now())
                        .operUser("张三")
                        .updateUser("张三")
                        .updatedTime(LocalDateTime.now())
                        .build(),
                AssetServerAttrCfgImportExcelVO.builder()
                        .assetServerAttrId("SERVER_ATTR_002")
                        .relAssetId("ASSET002")
                        .relAssetName("网络设备002")
                        .attrName("内存使用量")
                        .attrCode("memory_usage")
                        .attrDataType("MB")
                        .attrValue("2048")
                        .collectFreq("10min")
                        .lastCollectTime(LocalDateTime.now())
                        .cfgTime(LocalDateTime.now())
                        .operUser("李四")
                        .updateUser("李四")
                        .updatedTime(LocalDateTime.now())
                        .build()
        );

        // 导出模板
        ExcelUtils.write(response, "资产服务端属性配置导入模板.xls", "服务端属性配置",
                AssetServerAttrCfgImportExcelVO.class, list);
    }

}