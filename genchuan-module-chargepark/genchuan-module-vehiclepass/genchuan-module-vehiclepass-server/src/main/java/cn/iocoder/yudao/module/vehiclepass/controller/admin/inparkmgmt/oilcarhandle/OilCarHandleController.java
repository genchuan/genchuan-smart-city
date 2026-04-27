package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.*;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle.OilCarHandleDO;
import cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.oilcarhandle.OilCarHandleService;
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

@Tag(name = "管理后台 - 油车占位处置")
@RestController
@RequestMapping("/vehiclepass/oil-car-handle")
@Validated
public class OilCarHandleController {

    @Resource
    private OilCarHandleService carHandleService;

    @PostMapping("/create")
    @Operation(summary = "创建油车占位处置")
    @PreAuthorize("@ss.hasPermission('oil:car-handle:create')")
    public CommonResult<Long> createCarHandle(@Valid @RequestBody OilCarHandleSaveReqVO createReqVO) {
        return success(carHandleService.createCarHandle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新油车占位处置")
    @PreAuthorize("@ss.hasPermission('oil:car-handle:update')")
    public CommonResult<Boolean> updateCarHandle(@Valid @RequestBody OilCarHandleSaveReqVO updateReqVO) {
        carHandleService.updateCarHandle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除油车占位处置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('oil:car-handle:delete')")
    public CommonResult<Boolean> deleteCarHandle(@RequestParam("id") Long id) {
        carHandleService.deleteCarHandle(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除油车占位处置")
    @PreAuthorize("@ss.hasPermission('oil:car-handle:delete')")
    public CommonResult<Boolean> deleteCarHandleList(@RequestParam("ids") List<Long> ids) {
        carHandleService.deleteCarHandleListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得油车占位处置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('oil:car-handle:query')")
    public CommonResult<OilCarHandleRespVO> getCarHandle(@RequestParam("id") Long id) {
        OilCarHandleDO carHandle = carHandleService.getCarHandle(id);
        return success(BeanUtils.toBean(carHandle, OilCarHandleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得油车占位处置分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:query')")
    public CommonResult<PageResult<OilCarHandleRespVO>> getCarHandlePage(@Valid OilCarHandlePageReqVO pageReqVO) {
        return success(carHandleService.getCarHandlePageWithJoin(pageReqVO));
    }

    @PostMapping("/batch-handle")
    @Operation(summary = "批量处置油车占位")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:batch-handle')")
    public CommonResult<Boolean> batchHandle(@Valid @RequestBody OilCarHandleBatchHandleReqVO reqVO) {
        carHandleService.batchHandle(reqVO);
        return success(true);
    }

    @PutMapping("/handle")
    @Operation(summary = "处置油车占位")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:handle')")
    public CommonResult<Boolean> handle(@Valid @RequestBody OilCarHandleHandleReqVO reqVO) {
        carHandleService.handle(reqVO);
        return success(true);
    }

    @PutMapping("/ignore")
    @Operation(summary = "忽略油车占位")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:ignore')")
    public CommonResult<Boolean> ignore(@Valid @RequestBody OilCarHandleIgnoreReqVO reqVO) {
        carHandleService.ignore(reqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新处置进度")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:update-progress')")
    public CommonResult<Boolean> updateProgress(@Valid @RequestBody OilCarHandleUpdateProgressReqVO reqVO) {
        carHandleService.updateProgress(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取油车占位处置统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:oil-car-handle:query')")
    public CommonResult<OilCarHandleChartRespVO> getChart(@Valid OilCarHandleChartReqVO reqVO) {
        return success(carHandleService.getChart(reqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出油车占位处置 Excel")
    @PreAuthorize("@ss.hasPermission('oil:car-handle:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCarHandleExcel(@Valid OilCarHandlePageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<OilCarHandleRespVO> pageResult = carHandleService.getCarHandlePageWithJoin(pageReqVO);
        ExcelUtils.write(response, "油车占位处置.xls", "数据", OilCarHandleRespVO.class, pageResult.getList());
    }

}