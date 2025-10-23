package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo.AssetRelRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo.AssetRelRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrule.vo.AssetRelRuleSaveReqVO;
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


import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrule.AssetRelRuleDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetrelrule.AssetRelRuleService;

@Tag(name = "管理后台 - 资产关联规则配置")
@RestController
@RequestMapping("/datacenter/asset-rel-rule")
@Validated
public class AssetRelRuleController {

    @Resource
    private AssetRelRuleService assetRelRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建资产关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:create')")
    public CommonResult<Long> createAssetRelRule(@Valid @RequestBody AssetRelRuleSaveReqVO createReqVO) {
        return success(assetRelRuleService.createAssetRelRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产关联规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:update')")
    public CommonResult<Boolean> updateAssetRelRule(@Valid @RequestBody AssetRelRuleSaveReqVO updateReqVO) {
        assetRelRuleService.updateAssetRelRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产关联规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:delete')")
    public CommonResult<Boolean> deleteAssetRelRule(@RequestParam("id") Long id) {
        assetRelRuleService.deleteAssetRelRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产关联规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:query')")
    public CommonResult<AssetRelRuleRespVO> getAssetRelRule(@RequestParam("id") Long id) {
        AssetRelRuleDO assetRelRule = assetRelRuleService.getAssetRelRule(id);
        return success(BeanUtils.toBean(assetRelRule, AssetRelRuleRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资产关联规则配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:query')")
    public CommonResult<List<AssetRelRuleRespVO>> getAssetRelRuleList(@Valid AssetRelRuleListReqVO listReqVO) {
        List<AssetRelRuleDO> list = assetRelRuleService.getAssetRelRuleList(listReqVO);
        return success(BeanUtils.toBean(list, AssetRelRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产关联规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-rel-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetRelRuleExcel(@Valid AssetRelRuleListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<AssetRelRuleDO> list = assetRelRuleService.getAssetRelRuleList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "资产关联规则配置.xls", "数据", AssetRelRuleRespVO.class,
                        BeanUtils.toBean(list, AssetRelRuleRespVO.class));
    }

}