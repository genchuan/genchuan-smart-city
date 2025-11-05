package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.gridspatialdata.vo.GridSpatialDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.gridspatialdata.GridSpatialDataDO;
import cn.iocoder.yudao.module.datacenter.service.grid.data.gridspatialdata.GridSpatialDataService;

@Tag(name = "管理后台 - 网格空间数据")
@RestController
@RequestMapping("/datacenter/grid-spatial-data")
@Validated
public class GridSpatialDataController {

    @Resource
    private GridSpatialDataService gridSpatialDataService;

    @PostMapping("/create")
    @Operation(summary = "创建网格空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:create')")
    public CommonResult<Long> createGridSpatialData(@Valid @RequestBody GridSpatialDataSaveReqVO createReqVO) {
        return success(gridSpatialDataService.createGridSpatialData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:update')")
    public CommonResult<Boolean> updateGridSpatialData(@Valid @RequestBody GridSpatialDataSaveReqVO updateReqVO) {
        gridSpatialDataService.updateGridSpatialData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格空间数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:delete')")
    public CommonResult<Boolean> deleteGridSpatialData(@RequestParam("id") Long id) {
        gridSpatialDataService.deleteGridSpatialData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格空间数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:query')")
    public CommonResult<GridSpatialDataRespVO> getGridSpatialData(@RequestParam("id") Long id) {
        GridSpatialDataDO gridSpatialData = gridSpatialDataService.getGridSpatialData(id);
        return success(BeanUtils.toBean(gridSpatialData, GridSpatialDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格空间数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:query')")
    public CommonResult<PageResult<GridSpatialDataRespVO>> getGridSpatialDataPage(@Valid GridSpatialDataPageReqVO pageReqVO) {
        PageResult<GridSpatialDataDO> pageResult = gridSpatialDataService.getGridSpatialDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridSpatialDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格空间数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-spatial-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridSpatialDataExcel(@Valid GridSpatialDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridSpatialDataDO> list = gridSpatialDataService.getGridSpatialDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格空间数据.xls", "数据", GridSpatialDataRespVO.class,
                        BeanUtils.toBean(list, GridSpatialDataRespVO.class));
    }

}