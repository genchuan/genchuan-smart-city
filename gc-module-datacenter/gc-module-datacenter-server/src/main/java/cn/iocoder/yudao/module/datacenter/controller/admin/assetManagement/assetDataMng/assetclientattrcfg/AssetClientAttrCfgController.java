package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.AssetClientAttrCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgService;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "管理后台 - 资产客户端属性配置")
@RestController
@RequestMapping("/datacenter/asset-client-attr-cfg")
@Validated
public class AssetClientAttrCfgController {

    @Resource
    private AssetClientAttrCfgService assetClientAttrCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产客户端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:create')")
    public CommonResult<Long> createAssetClientAttrCfg(@Valid @RequestBody AssetClientAttrCfgSaveReqVO createReqVO) {
        return success(assetClientAttrCfgService.createAssetClientAttrCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产客户端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:update')")
    public CommonResult<Boolean> updateAssetClientAttrCfg(@Valid @RequestBody AssetClientAttrCfgSaveReqVO updateReqVO) {
        assetClientAttrCfgService.updateAssetClientAttrCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产客户端属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:delete')")
    public CommonResult<Boolean> deleteAssetClientAttrCfg(@RequestParam("id") Long id) {
        assetClientAttrCfgService.deleteAssetClientAttrCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产客户端属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:query')")
    public CommonResult<AssetClientAttrCfgRespVO> getAssetClientAttrCfg(@RequestParam("id") Long id) {
        AssetClientAttrCfgDO assetClientAttrCfg = assetClientAttrCfgService.getAssetClientAttrCfg(id);
        return success(BeanUtils.toBean(assetClientAttrCfg, AssetClientAttrCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产客户端属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:query')")
    public CommonResult<PageResult<AssetClientAttrCfgRespVO>> getAssetClientAttrCfgPage(@Valid AssetClientAttrCfgPageReqVO pageReqVO) {
        PageResult<AssetClientAttrCfgDO> pageResult = assetClientAttrCfgService.getAssetClientAttrCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetClientAttrCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产客户端属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetClientAttrCfgExcel(@Valid AssetClientAttrCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetClientAttrCfgDO> list = assetClientAttrCfgService.getAssetClientAttrCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产客户端属性配置.xls", "数据", AssetClientAttrCfgRespVO.class,
                        BeanUtils.toBean(list, AssetClientAttrCfgRespVO.class));
    }

    /**
     *获取资产客户端属性列表
     * @return 获取资产客户端属性列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取资产客户端属性列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:query')")
    public CommonResult<List<AssetClientAttrCfgSimpleRespVO>> getAssetClientAttrList(){
        List<AssetClientAttrCfgSimpleRespVO> list = assetClientAttrCfgService.getAssetClientAttrList();
        return success(list);
    }

    //======================== Excel 导入 =====================//
    @PostMapping("/import")
    @Operation(summary = "导入资产客户端属性配置 Excel")
    @Parameters({
            @Parameter(name = "file", description = "Excel 文件", required = true),
            @Parameter(name = "updateSupport", description = "是否支持更新，默认为 false", example = "true")
    })
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:import')")
    public CommonResult<AssetClientAttrCfgImportRespVO> importExcel(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "updateSupport", required = false, defaultValue = "false") Boolean updateSupport) throws Exception {
        List<AssetClientAttrCfgImportExcelVO> list = ExcelUtils.read(file, AssetClientAttrCfgImportExcelVO.class);
        return success(assetClientAttrCfgService.importAssetClientAttrCfgList(list, updateSupport));
    }
    //======================== Excel 导入模板 =====================//
    @GetMapping("/import-template")
    @Operation(summary = "下载资产客户端属性配置导入模板")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-client-attr-cfg:import')")
    @ApiAccessLog(operateType = EXPORT)
    public void importTemplate(HttpServletResponse response) throws IOException {
        // 创建模板数据，两条示例数据
        List<AssetClientAttrCfgImportExcelVO> list = Arrays.asList(
                AssetClientAttrCfgImportExcelVO.builder()
                        .assetClientAttrId("CLIENT_ATTR_001")
                        .relAssetId("ASSET001")
                        .relAssetName("客户端设备001")
                        .attrName("应用版本")
                        .attrCode("app_version")
                        .attrDataType("字符串")
                        .attrValue("v2.1.0")
                        .attrPurpose("版本监控")
                        .clientIp("192.168.1.100")
                        .cfgTime(LocalDateTime.now())
                        .operUser("张三")
                        .updateUser("张三")
                        .updatedTime(LocalDateTime.now())
                        .build(),
                AssetClientAttrCfgImportExcelVO.builder()
                        .assetClientAttrId("CLIENT_ATTR_002")
                        .relAssetId("ASSET002")
                        .relAssetName("移动终端002")
                        .attrName("设备状态")
                        .attrCode("device_status")
                        .attrDataType("枚举")
                        .attrValue("在线")
                        .attrPurpose("状态监控")
                        .clientIp("192.168.1.101")
                        .cfgTime(LocalDateTime.now())
                        .operUser("李四")
                        .updateUser("李四")
                        .updatedTime(LocalDateTime.now())
                        .build()
        );

        // 导出模板
        ExcelUtils.write(response, "资产客户端属性配置导入模板.xls", "客户端属性配置",
                AssetClientAttrCfgImportExcelVO.class, list);
    }

}