package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import cn.iocoder.yudao.module.inspectop.service.assetstock.AssetStockService;

@Tag(name = "管理后台 - 库存管理")
@RestController
@RequestMapping("/inspectop/asset-stock")
@Validated
public class AssetStockController {

    @Resource
    private AssetStockService assetStockService;

    @PostMapping("/create")
    @Operation(summary = "创建库存管理")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:create')")
    public CommonResult<Long> createAssetStock(@Valid @RequestBody AssetStockSaveReqVO createReqVO) {
        return success(assetStockService.createAssetStock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新库存管理")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:update')")
    public CommonResult<Boolean> updateAssetStock(@Valid @RequestBody AssetStockSaveReqVO updateReqVO) {
        assetStockService.updateAssetStock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除库存管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:delete')")
    public CommonResult<Boolean> deleteAssetStock(@RequestParam("id") Long id) {
        assetStockService.deleteAssetStock(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除库存管理")
                @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:delete')")
    public CommonResult<Boolean> deleteAssetStockList(@RequestParam("ids") List<Long> ids) {
        assetStockService.deleteAssetStockListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库存管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:query')")
    public CommonResult<AssetStockRespVO> getAssetStock(@RequestParam("id") Long id) {
        AssetStockDO assetStock = assetStockService.getAssetStock(id);
        return success(BeanUtils.toBean(assetStock, AssetStockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得库存管理分页")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:query')")
    public CommonResult<PageResult<AssetStockRespVO>> getAssetStockPage(@Valid AssetStockPageReqVO pageReqVO) {
        PageResult<AssetStockRespVO> pageResult = assetStockService.getAssetStockPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/allocate")
    @Operation(summary = "调配库存")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:allocate')")
    public CommonResult<Boolean> allocateAssetStock(@Valid @RequestBody AssetStockAllocateReqVO allocateReqVO) {
        assetStockService.allocateAssetStock(allocateReqVO);
        return success(true);
    }

    @PutMapping("/alarm")
    @Operation(summary = "更新库存告警状态")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:alarm')")
    public CommonResult<Boolean> alarmAssetStock(@Valid @RequestBody AssetStockAlarmReqVO alarmReqVO) {
        assetStockService.alarmAssetStock(alarmReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取库存统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:chart')")
    public CommonResult<AssetStockChartRespVO> getAssetStockChart(@Valid AssetStockChartReqVO reqVO) {
        AssetStockChartRespVO chartData = assetStockService.getAssetStockChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出库存管理 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:asset-stock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAssetStockExcel(@Valid AssetStockPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 这里不再需要BeanUtils转换，因为Service已经返回了VO
        List<AssetStockRespVO> list = assetStockService.getAssetStockPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "库存管理.xls", "数据", AssetStockRespVO.class, list);
    }
}