package cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.div.unitgriddiv.vo.*;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.div.unitgriddiv.UnitGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.grid.div.unitgriddiv.UnitGridDivService;

@Tag(name = "管理后台 - 单元网格划分")
@RestController
@RequestMapping("/datacenter/unit-grid-div")
@Validated
public class UnitGridDivController {

    @Resource
    private UnitGridDivService unitGridDivService;

    @PostMapping("/create")
    @Operation(summary = "创建单元网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:create')")
    public CommonResult<Long> createUnitGridDiv(@Valid @RequestBody UnitGridDivSaveReqVO createReqVO) {
        return success(unitGridDivService.createUnitGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新单元网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:update')")
    public CommonResult<Boolean> updateUnitGridDiv(@Valid @RequestBody UnitGridDivSaveReqVO updateReqVO) {
        unitGridDivService.updateUnitGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除单元网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:delete')")
    public CommonResult<Boolean> deleteUnitGridDiv(@RequestParam("id") Long id) {
        unitGridDivService.deleteUnitGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得单元网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<UnitGridDivRespVO> getUnitGridDiv(@RequestParam("id") Long id) {
        UnitGridDivDO unitGridDiv = unitGridDivService.getUnitGridDiv(id);
        return success(BeanUtils.toBean(unitGridDiv, UnitGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得单元网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<PageResult<UnitGridDivRespVO>> getUnitGridDivPage(@Valid UnitGridDivPageReqVO pageReqVO) {
        PageResult<UnitGridDivDO> pageResult = unitGridDivService.getUnitGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UnitGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出单元网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUnitGridDivExcel(@Valid UnitGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UnitGridDivDO> list = unitGridDivService.getUnitGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "单元网格划分.xls", "数据", UnitGridDivRespVO.class,
                        BeanUtils.toBean(list, UnitGridDivRespVO.class));
    }

    @PostMapping("/validate-area")
    @Operation(summary = "校验单元网格面积")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<Boolean> validateUnitGridArea(
            @RequestParam("area") @NotNull(message = "面积不能为空") Integer area,
            @RequestParam("commId") @NotEmpty(message = "社区ID不能为空") String commId) {
        Boolean isValid = unitGridDivService.validateArea(area, commId);
        return success(isValid);
    }

    @PostMapping("/validate-boundary")
    @Operation(summary = "校验单元网格边界")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<UnitGridBoundaryValidateRespVO> validateUnitGridBoundary(
            @Valid @RequestBody UnitGridBoundaryValidateReqVO validateReqVO) {
        UnitGridBoundaryValidateRespVO result = unitGridDivService.validateBoundary(validateReqVO);
        return success(result);
    }

    @PostMapping("/import-coordinates")
    @Operation(summary = "导入坐标创建单元网格")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:create')")
    public CommonResult<Long> importUnitGridCoordinates(
            @Valid @RequestBody UnitGridCoordinateImportReqVO importReqVO) {
        Long id = unitGridDivService.importCoordinates(importReqVO);
        return success(id);
    }

    @GetMapping("/list-by-comm")
    @Operation(summary = "获取社区下的单元网格列表")
    @Parameter(name = "commId", description = "社区ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<List<UnitGridSimpleRespVO>> getUnitGridsByComm(
            @RequestParam("commId") String commId) {
        List<UnitGridSimpleRespVO> list = unitGridDivService.getUnitGridsByComm(commId);
        return success(list);
    }

    @GetMapping("/check-overlap")
    @Operation(summary = "检查边界重叠")
    @PreAuthorize("@ss.hasPermission('datacenter:unit-grid-div:query')")
    public CommonResult<List<UnitGridSimpleRespVO>> checkBoundaryOverlap(
            @RequestParam("boundaryCoords") @NotEmpty(message = "边界坐标不能为空") String boundaryCoords,
            @RequestParam(value = "excludeId", required = false) Long excludeId) {
        List<UnitGridSimpleRespVO> overlapGrids = unitGridDivService.checkBoundaryOverlap(boundaryCoords, excludeId);
        return success(overlapGrids);
    }

}