package cn.iocoder.yudao.module.datacenter.controller.admin.assetcategoryrule;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetcategoryrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetcategoryrule.AssetCategoryRuleDO;
import cn.iocoder.yudao.module.datacenter.service.assetcategoryrule.AssetCategoryRuleService;

@Tag(name = "管理后台 - 资产分类规则配置")
@RestController
@RequestMapping("/datacenter/asset-category-rule")
@Validated
public class AssetCategoryRuleController {

    @Resource
    private AssetCategoryRuleService assetCategoryRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:create')")
    public CommonResult<Long> createAssetCategoryRule(@Valid @RequestBody AssetCategoryRuleSaveReqVO createReqVO) {
        return success(assetCategoryRuleService.createAssetCategoryRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:update')")
    public CommonResult<Boolean> updateAssetCategoryRule(@Valid @RequestBody AssetCategoryRuleSaveReqVO updateReqVO) {
        assetCategoryRuleService.updateAssetCategoryRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分类规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:delete')")
    public CommonResult<Boolean> deleteAssetCategoryRule(@RequestParam("id") Long id) {
        assetCategoryRuleService.deleteAssetCategoryRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分类规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:query')")
    public CommonResult<AssetCategoryRuleRespVO> getAssetCategoryRule(@RequestParam("id") Long id) {
        AssetCategoryRuleDO assetCategoryRule = assetCategoryRuleService.getAssetCategoryRule(id);
        return success(BeanUtils.toBean(assetCategoryRule, AssetCategoryRuleRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资产分类规则配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:query')")
    public CommonResult<List<AssetCategoryRuleRespVO>> getAssetCategoryRuleList(@Valid AssetCategoryRuleListReqVO listReqVO) {
        List<AssetCategoryRuleDO> list = assetCategoryRuleService.getAssetCategoryRuleList(listReqVO);
        return success(BeanUtils.toBean(list, AssetCategoryRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分类规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-category-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCategoryRuleExcel(@Valid AssetCategoryRuleListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<AssetCategoryRuleDO> list = assetCategoryRuleService.getAssetCategoryRuleList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "资产分类规则配置.xls", "数据", AssetCategoryRuleRespVO.class,
                        BeanUtils.toBean(list, AssetCategoryRuleRespVO.class));
    }

}