package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridstreet.GridStreetDO;
import cn.iocoder.yudao.module.datacenter.service.grid.area.gridstreet.GridStreetService;

@Tag(name = "管理后台 - 街道（镇、乡）行政区划配置")
@RestController
@RequestMapping("/datacenter/grid-street")
@Validated
public class GridStreetController {

    @Resource
    private GridStreetService gridStreetService;

    @PostMapping("/create")
    @Operation(summary = "创建街道（镇、乡）行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:create')")
    public CommonResult<Long> createGridStreet(@Valid @RequestBody GridStreetSaveReqVO createReqVO) {
        return success(gridStreetService.createGridStreet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新街道（镇、乡）行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:update')")
    public CommonResult<Boolean> updateGridStreet(@Valid @RequestBody GridStreetSaveReqVO updateReqVO) {
        gridStreetService.updateGridStreet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除街道（镇、乡）行政区划配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:delete')")
    public CommonResult<Boolean> deleteGridStreet(@RequestParam("id") Long id) {
        gridStreetService.deleteGridStreet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得街道（镇、乡）行政区划配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:query')")
    public CommonResult<GridStreetRespVO> getGridStreet(@RequestParam("id") Long id) {
        GridStreetDO gridStreet = gridStreetService.getGridStreet(id);
        return success(BeanUtils.toBean(gridStreet, GridStreetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得街道（镇、乡）行政区划配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:query')")
    public CommonResult<PageResult<GridStreetRespVO>> getGridStreetPage(@Valid GridStreetPageReqVO pageReqVO) {
        PageResult<GridStreetDO> pageResult = gridStreetService.getGridStreetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridStreetRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有街道（镇、乡）行政区划配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:query')")
    public CommonResult<List<GridStreetRespVO>> getGridStreetList() {
        List<GridStreetDO> list = gridStreetService.getGridStreetList();
        return success(BeanUtils.toBean(list, GridStreetRespVO.class));
    }

    @GetMapping("/list-by-county")
    @Operation(summary = "根据县级行政区ID查询街道列表")
    @Parameter(name = "countyAdminId", description = "县级行政区ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:query')")
    public CommonResult<List<GridStreetRespVO>> getGridStreetListByCountyId(@RequestParam("countyAdminId") String countyAdminId) {
        List<GridStreetDO> list = gridStreetService.getGridStreetListByCountyId(countyAdminId);
        return success(BeanUtils.toBean(list, GridStreetRespVO.class));
    }

    @GetMapping("/list-by-level")
    @Operation(summary = "根据街道级别查询列表")
    @Parameter(name = "streetLevel", description = "街道级别(1-街道,2-镇,3-乡)", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:query')")
    public CommonResult<List<GridStreetRespVO>> getGridStreetListByLevel(@RequestParam("streetLevel") Integer streetLevel) {
        List<GridStreetDO> list = gridStreetService.getGridStreetListByLevel(streetLevel);
        return success(BeanUtils.toBean(list, GridStreetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出街道（镇、乡）行政区划配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-street:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridStreetExcel(@Valid GridStreetPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridStreetDO> list = gridStreetService.getGridStreetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "街道（镇、乡）行政区划配置.xls", "数据", GridStreetRespVO.class,
                BeanUtils.toBean(list, GridStreetRespVO.class));
    }

}