package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountyPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountyRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountySaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty.GridCountyDO;
import cn.iocoder.yudao.module.datacenter.service.grid.area.gridcounty.GridCountyService;

@Tag(name = "管理后台 - 县级及以上行政区划配置")
@RestController
@RequestMapping("/datacenter/grid-county")
@Validated
public class GridCountyController {

    @Resource
    private GridCountyService gridCountyService;

    @PostMapping("/create")
    @Operation(summary = "创建县级及以上行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:create')")
    public CommonResult<Long> createGridCounty(@Valid @RequestBody GridCountySaveReqVO createReqVO) {
        return success(gridCountyService.createGridCounty(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新县级及以上行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:update')")
    public CommonResult<Boolean> updateGridCounty(@Valid @RequestBody GridCountySaveReqVO updateReqVO) {
        gridCountyService.updateGridCounty(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除县级及以上行政区划配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:delete')")
    public CommonResult<Boolean> deleteGridCounty(@RequestParam("id") Long id) {
        gridCountyService.deleteGridCounty(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "根据ID查询县级及以上行政区划配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:query')")
    public CommonResult<GridCountyRespVO> getGridCounty(@RequestParam("id") Long id) {
        GridCountyDO gridCounty = gridCountyService.getGridCounty(id);
        return success(BeanUtils.toBean(gridCounty, GridCountyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得县级及以上行政区划配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:query')")
    public CommonResult<PageResult<GridCountyRespVO>> getGridCountyPage(@Valid GridCountyPageReqVO pageReqVO) {
        PageResult<GridCountyDO> pageResult = gridCountyService.getGridCountyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridCountyRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有县级及以上行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:query')")
    public CommonResult<List<GridCountyRespVO>> getGridCountyList() {
        List<GridCountyDO> list = gridCountyService.getGridCountyList();
        return success(BeanUtils.toBean(list, GridCountyRespVO.class));
    }

    @GetMapping("/list-by-parent")
    @Operation(summary = "根据上级行政区划ID查询下级行政区划列表")
    @Parameter(name = "parentAdminId", description = "上级行政区划ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:query')")
    public CommonResult<List<GridCountyRespVO>> getGridCountyListByParentId(@RequestParam("parentAdminId") String parentAdminId) {
        List<GridCountyDO> list = gridCountyService.getGridCountyListByParentId(parentAdminId);
        return success(BeanUtils.toBean(list, GridCountyRespVO.class));
    }

    @GetMapping("/list-by-level")
    @Operation(summary = "根据行政区划级别查询列表")
    @Parameter(name = "adminLevel", description = "行政区划级别(1-省级,2-市级,3-县级)", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:query')")
    public CommonResult<List<GridCountyRespVO>> getGridCountyListByLevel(@RequestParam("adminLevel") Integer adminLevel) {
        List<GridCountyDO> list = gridCountyService.getGridCountyListByLevel(adminLevel);
        return success(BeanUtils.toBean(list, GridCountyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出县级及以上行政区划配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-county:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridCountyExcel(@Valid GridCountyPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridCountyDO> list = gridCountyService.getGridCountyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "县级及以上行政区划配置.xls", "数据", GridCountyRespVO.class,
                BeanUtils.toBean(list, GridCountyRespVO.class));
    }

}