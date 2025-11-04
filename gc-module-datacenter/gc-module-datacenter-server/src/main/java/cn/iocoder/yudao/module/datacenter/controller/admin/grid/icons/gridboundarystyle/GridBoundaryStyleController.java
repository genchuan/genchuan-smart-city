package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStylePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStyleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStyleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridboundarystyle.GridBoundaryStyleDO;
import cn.iocoder.yudao.module.datacenter.service.grid.icons.gridboundarystyle.GridBoundaryStyleService;

@Tag(name = "管理后台 - 网格边界线样式配置")
@RestController
@RequestMapping("/datacenter/grid-boundary-style")
@Validated
public class GridBoundaryStyleController {

    @Resource
    private GridBoundaryStyleService gridBoundaryStyleService;

    @PostMapping("/create")
    @Operation(summary = "创建网格边界线样式配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:create')")
    public CommonResult<Long> createGridBoundaryStyle(@Valid @RequestBody GridBoundaryStyleSaveReqVO createReqVO) {
        return success(gridBoundaryStyleService.createGridBoundaryStyle(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格边界线样式配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:update')")
    public CommonResult<Boolean> updateGridBoundaryStyle(@Valid @RequestBody GridBoundaryStyleSaveReqVO updateReqVO) {
        gridBoundaryStyleService.updateGridBoundaryStyle(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格边界线样式配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:delete')")
    public CommonResult<Boolean> deleteGridBoundaryStyle(@RequestParam("id") Long id) {
        gridBoundaryStyleService.deleteGridBoundaryStyle(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格边界线样式配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:query')")
    public CommonResult<GridBoundaryStyleRespVO> getGridBoundaryStyle(@RequestParam("id") Long id) {
        GridBoundaryStyleDO gridBoundaryStyle = gridBoundaryStyleService.getGridBoundaryStyle(id);
        return success(BeanUtils.toBean(gridBoundaryStyle, GridBoundaryStyleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格边界线样式配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:query')")
    public CommonResult<PageResult<GridBoundaryStyleRespVO>> getGridBoundaryStylePage(@Valid GridBoundaryStylePageReqVO pageReqVO) {
        PageResult<GridBoundaryStyleDO> pageResult = gridBoundaryStyleService.getGridBoundaryStylePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridBoundaryStyleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格边界线样式配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-boundary-style:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridBoundaryStyleExcel(@Valid GridBoundaryStylePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridBoundaryStyleDO> list = gridBoundaryStyleService.getGridBoundaryStylePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格边界线样式配置.xls", "数据", GridBoundaryStyleRespVO.class,
                        BeanUtils.toBean(list, GridBoundaryStyleRespVO.class));
    }

}