package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.AssetServerAttrCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgService;

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

}