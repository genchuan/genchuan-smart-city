package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetcheck.AssetCheckDO;
import cn.iocoder.yudao.module.inspectop.service.assetcheck.AssetCheckService;

@Tag(name = "管理后台 - 资产盘点")
@RestController
@RequestMapping("/inspectop/asset-check")
@Validated
public class AssetCheckController {

    @Resource
    private AssetCheckService assetCheckService;

    @PostMapping("/create")
    @Operation(summary = "创建资产盘点")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:create')")
    public CommonResult<Long> createAssetCheck(@Valid @RequestBody AssetCheckSaveReqVO createReqVO) {
        return success(assetCheckService.createAssetCheck(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产盘点")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:update')")
    public CommonResult<Boolean> updateAssetCheck(@Valid @RequestBody AssetCheckSaveReqVO updateReqVO) {
        assetCheckService.updateAssetCheck(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产盘点")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:delete')")
    public CommonResult<Boolean> deleteAssetCheck(@RequestParam("id") Long id) {
        assetCheckService.deleteAssetCheck(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除资产盘点")
                @PreAuthorize("@ss.hasPermission('inspectop:asset-check:delete')")
    public CommonResult<Boolean> deleteAssetCheckList(@RequestParam("ids") List<Long> ids) {
        assetCheckService.deleteAssetCheckListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产盘点")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:query')")
    public CommonResult<AssetCheckRespVO> getAssetCheck(@RequestParam("id") Long id) {
        AssetCheckDO assetCheck = assetCheckService.getAssetCheck(id);
        return success(BeanUtils.toBean(assetCheck, AssetCheckRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产盘点分页")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:query')")
    public CommonResult<PageResult<AssetCheckRespVO>> getAssetCheckPage(@Valid AssetCheckPageReqVO pageReqVO) {
        PageResult<AssetCheckDO> pageResult = assetCheckService.getAssetCheckPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AssetCheckRespVO.class));
    }

    @PostMapping("/create-simple")
    @Operation(summary = "创建资产盘点--新增接口")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:create')")
    public CommonResult<Long> createAssetCheckSimple(@Valid @RequestBody AssetCheckCreateReqVO createReqVO) {
        return success(assetCheckService.createAssetCheck(createReqVO));
    }

    @PutMapping("/execute")
    @Operation(summary = "执行")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:execute')")
    public CommonResult<Boolean> executeAssetCheck(@Valid @RequestBody AssetCheckExecuteReqVO executeReqVO) {
        assetCheckService.executeAssetCheck(executeReqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新盘点进度")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:update-progress')")
    public CommonResult<Boolean> updateAssetCheckProgress(@Valid @RequestBody AssetCheckUpdateProgressReqVO updateProgressReqVO) {
        assetCheckService.updateAssetCheckProgress(updateProgressReqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:confirm')")
    public CommonResult<Boolean> confirmAssetCheck(@Valid @RequestBody AssetCheckConfirmReqVO confirmReqVO) {
        assetCheckService.confirmAssetCheck(confirmReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取资产盘点图表统计数据")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:chart')")
    public CommonResult<AssetCheckChartRespVO> getAssetCheckChart(@Valid AssetCheckChartReqVO chartReqVO) {
        AssetCheckChartRespVO chartData = assetCheckService.getAssetCheckChart(chartReqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产盘点 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-check:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetCheckExcel(@Valid AssetCheckPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AssetCheckDO> list = assetCheckService.getAssetCheckPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产盘点.xls", "数据", AssetCheckRespVO.class,
                        BeanUtils.toBean(list, AssetCheckRespVO.class));
    }

}