package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgBatchUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgService;

@Tag(name = "管理后台 - 资产关联规则配置")
@RestController
@RequestMapping("/datacenter/asset-rel-rule-cfg")
@Validated
public class AssetRelRuleCfgController {

    @Resource
    private AssetRelRuleCfgService assetRelRuleCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:create')")
    public CommonResult<Long> createAssetRelRuleCfg(@Valid @RequestBody AssetRelRuleCfgSaveReqVO createReqVO) {
        return success(assetRelRuleCfgService.createAssetRelRuleCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:update')")
    public CommonResult<Boolean> updateAssetRelRuleCfg(@Valid @RequestBody AssetRelRuleCfgSaveReqVO updateReqVO) {
        assetRelRuleCfgService.updateAssetRelRuleCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:delete')")
    public CommonResult<Boolean> deleteAssetRelRuleCfg(@RequestParam("id") Long id) {
        assetRelRuleCfgService.deleteAssetRelRuleCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:query')")
    public CommonResult<AssetRelRuleCfgRespVO> getAssetRelRuleCfg(@RequestParam("id") Long id) {
        AssetRelRuleCfgDO assetRelRuleCfg = assetRelRuleCfgService.getAssetRelRuleCfg(id);
        return success(BeanUtils.toBean(assetRelRuleCfg, AssetRelRuleCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产关联规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:query')")
    public CommonResult<PageResult<AssetRelRuleCfgRespVO>> getAssetRelRuleCfgPage(@Valid AssetRelRuleCfgPageReqVO pageReqVO) {
        PageResult<AssetRelRuleCfgDO> pageResult = assetRelRuleCfgService.getAssetRelRuleCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetRelRuleCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetRelRuleCfgExcel(@Valid AssetRelRuleCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetRelRuleCfgDO> list = assetRelRuleCfgService.getAssetRelRuleCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产关联规则配置.xls", "数据", AssetRelRuleCfgRespVO.class,
                        BeanUtils.toBean(list, AssetRelRuleCfgRespVO.class));
    }

    /**
     * 批量设置‘是否必选’
     *
     * @param reqVO
     * @return
     */
    @PutMapping("/batch-update-required")
    @Operation(summary = "批量设置‘是否必选’")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule-cfg:update')")
    public CommonResult<Boolean> batchUpdateIsRequired(
        @Valid @RequestBody AssetRelRuleCfgBatchUpdateReqVO reqVO){
        assetRelRuleCfgService.batchUpdateIsRequired(reqVO);
        return success(true);
    }

}