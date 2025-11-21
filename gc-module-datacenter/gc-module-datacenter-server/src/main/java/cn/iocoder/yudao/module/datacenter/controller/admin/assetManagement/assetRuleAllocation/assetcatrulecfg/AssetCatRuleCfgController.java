package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgDO;
import cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgService;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.*;

@Tag(name = "管理后台 - 资产分类规则配置")
@RestController
@RequestMapping("/datacenter/asset-cat-rule-cfg")
@Validated
public class AssetCatRuleCfgController {

    @Resource
    private AssetCatRuleCfgService assetCatRuleCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:create')")
    public CommonResult<Long> createAssetCatRuleCfg(@Valid @RequestBody AssetCatRuleCfgSaveReqVO createReqVO) {
        return success(assetCatRuleCfgService.createAssetCatRuleCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分类规则配置")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:update')")
    public CommonResult<Boolean> updateAssetCatRuleCfg(@Valid @RequestBody AssetCatRuleCfgSaveReqVO updateReqVO) {
        assetCatRuleCfgService.updateAssetCatRuleCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分类规则配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:delete')")
    public CommonResult<Boolean> deleteAssetCatRuleCfg(@RequestParam("id") Long id) {
        assetCatRuleCfgService.deleteAssetCatRuleCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分类规则配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:query')")
    public CommonResult<AssetCatRuleCfgRespVO> getAssetCatRuleCfg(@RequestParam("id") Long id) {
        AssetCatRuleCfgDO assetCatRuleCfg = assetCatRuleCfgService.getAssetCatRuleCfg(id);
        return success(BeanUtils.toBean(assetCatRuleCfg, AssetCatRuleCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产分类规则配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:query')")
    public CommonResult<PageResult<AssetCatRuleCfgRespVO>> getAssetCatRuleCfgPage(@Valid AssetCatRuleCfgPageReqVO pageReqVO) {
        PageResult<AssetCatRuleCfgDO> pageResult = assetCatRuleCfgService.getAssetCatRuleCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetCatRuleCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分类规则配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCatRuleCfgExcel(@Valid AssetCatRuleCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetCatRuleCfgDO> list = assetCatRuleCfgService.getAssetCatRuleCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产分类规则配置.xls", "数据", AssetCatRuleCfgRespVO.class,
                        BeanUtils.toBean(list, AssetCatRuleCfgRespVO.class));
    }

    /**
     * 获取启用的资产分类规则名称及ID
     *
     * @return 启用的资产分类规则名称及ID
     */
    @GetMapping("/enabled-list")
    @Operation(summary = "获取启用的资产分类规则列表（用于字典）")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:query')")
    public CommonResult<List<AssetCatRuleCfgSimpleRespVO>> getEnabledAssetCatRuleList() {
        List<AssetCatRuleCfgSimpleRespVO> list = assetCatRuleCfgService.getEnabledAssetCatRuleList();
        return success(list);
    }

    /**
     * 获取资产分类规则列表
     *
     * @return 获取资产分类规则列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取资产分类规则列表")
    @PreAuthorize("@ss.hasPermission('datacenter:asset-cat-rule-cfg:query')")
    public CommonResult<List<AssetCatRuleCfgSimpleRespVO>> getAssetCatRuleList() {
        List<AssetCatRuleCfgSimpleRespVO> list = assetCatRuleCfgService.getAssetCatRuleList();
        return success(list);
    }

}