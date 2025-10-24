package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgService;

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

}