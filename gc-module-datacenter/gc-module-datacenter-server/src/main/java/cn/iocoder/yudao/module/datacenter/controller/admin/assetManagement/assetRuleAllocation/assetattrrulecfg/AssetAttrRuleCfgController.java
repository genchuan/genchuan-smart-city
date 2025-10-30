package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgBatchUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgService;

@Tag(name = "管理后台 - 资产属性规则配置")
@RestController
@RequestMapping("/datacenter/asset-attr-rule-cfg")
@Validated
public class AssetAttrRuleCfgController {

    @Resource
    private AssetAttrRuleCfgService assetAttrRuleCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:create')")
    public CommonResult<Long> createAssetAttrRuleCfg(@Valid @RequestBody AssetAttrRuleCfgSaveReqVO createReqVO) {
        return success(assetAttrRuleCfgService.createAssetAttrRuleCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产属性规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:update')")
    public CommonResult<Boolean> updateAssetAttrRuleCfg(@Valid @RequestBody AssetAttrRuleCfgSaveReqVO updateReqVO) {
        assetAttrRuleCfgService.updateAssetAttrRuleCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产属性规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:delete')")
    public CommonResult<Boolean> deleteAssetAttrRuleCfg(@RequestParam("id") Long id) {
        assetAttrRuleCfgService.deleteAssetAttrRuleCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产属性规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:query')")
    public CommonResult<AssetAttrRuleCfgRespVO> getAssetAttrRuleCfg(@RequestParam("id") Long id) {
        AssetAttrRuleCfgDO assetAttrRuleCfg = assetAttrRuleCfgService.getAssetAttrRuleCfg(id);
        return success(BeanUtils.toBean(assetAttrRuleCfg, AssetAttrRuleCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产属性规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:query')")
    public CommonResult<PageResult<AssetAttrRuleCfgRespVO>> getAssetAttrRuleCfgPage(@Valid AssetAttrRuleCfgPageReqVO pageReqVO) {
        PageResult<AssetAttrRuleCfgDO> pageResult = assetAttrRuleCfgService.getAssetAttrRuleCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetAttrRuleCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产属性规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetAttrRuleCfgExcel(@Valid AssetAttrRuleCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetAttrRuleCfgDO> list = assetAttrRuleCfgService.getAssetAttrRuleCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产属性规则配置.xls", "数据", AssetAttrRuleCfgRespVO.class,
                        BeanUtils.toBean(list, AssetAttrRuleCfgRespVO.class));
    }

    /**
     * 批量设置‘是否必选’
     *
     * @param reqVO
     * @return
     */
    @PutMapping("/batch-update-required")
    @Operation(summary = "批量设置‘是否必选’")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-attr-rule-cfg:update')")
    public CommonResult<Boolean> batchUpdateIsRequired(
            @Valid @RequestBody AssetAttrRuleCfgBatchUpdateReqVO reqVO) {
        assetAttrRuleCfgService.batchUpdateIsRequired(reqVO);
        return success(true);
    }

}