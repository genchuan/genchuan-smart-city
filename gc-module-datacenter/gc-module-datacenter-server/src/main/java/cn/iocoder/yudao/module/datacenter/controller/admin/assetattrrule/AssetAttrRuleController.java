package cn.iocoder.yudao.module.datacenter.controller.admin.assetattrrule;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.assetattrrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetattrrule.AssetAttrRuleDO;
import cn.iocoder.yudao.module.datacenter.service.assetattrrule.AssetAttrRuleService;

@Tag(name = "管理后台 - 资产属性规则配置")
@RestController
@RequestMapping("/datacenter/asset-attr-rule")
@Validated
public class AssetAttrRuleController {

    @Resource
    private AssetAttrRuleService assetAttrRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建资产属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:create')")
    public CommonResult<Long> createAssetAttrRule(@Valid @RequestBody AssetAttrRuleSaveReqVO createReqVO) {
        return success(assetAttrRuleService.createAssetAttrRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:update')")
    public CommonResult<Boolean> updateAssetAttrRule(@Valid @RequestBody AssetAttrRuleSaveReqVO updateReqVO) {
        assetAttrRuleService.updateAssetAttrRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产属性规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:delete')")
    public CommonResult<Boolean> deleteAssetAttrRule(@RequestParam("id") Long id) {
        assetAttrRuleService.deleteAssetAttrRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产属性规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:query')")
    public CommonResult<AssetAttrRuleRespVO> getAssetAttrRule(@RequestParam("id") Long id) {
        AssetAttrRuleDO assetAttrRule = assetAttrRuleService.getAssetAttrRule(id);
        return success(BeanUtils.toBean(assetAttrRule, AssetAttrRuleRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得资产属性规则配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:query')")
    public CommonResult<List<AssetAttrRuleRespVO>> getAssetAttrRuleList(@Valid AssetAttrRuleListReqVO listReqVO) {
        List<AssetAttrRuleDO> list = assetAttrRuleService.getAssetAttrRuleList(listReqVO);
        return success(BeanUtils.toBean(list, AssetAttrRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产属性规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetAttrRuleExcel(@Valid AssetAttrRuleListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<AssetAttrRuleDO> list = assetAttrRuleService.getAssetAttrRuleList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "资产属性规则配置.xls", "数据", AssetAttrRuleRespVO.class,
                        BeanUtils.toBean(list, AssetAttrRuleRespVO.class));
    }

}