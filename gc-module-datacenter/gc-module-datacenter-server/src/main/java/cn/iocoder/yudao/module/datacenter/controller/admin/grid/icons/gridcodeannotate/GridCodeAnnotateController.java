package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotateRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotateSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridcodeannotate.GridCodeAnnotateDO;
import cn.iocoder.yudao.module.datacenter.service.grid.icons.gridcodeannotate.GridCodeAnnotateService;

@Tag(name = "管理后台 - 网格标识码注记配置")
@RestController
@RequestMapping("/datacenter/grid-code-annotate")
@Validated
public class GridCodeAnnotateController {

    @Resource
    private GridCodeAnnotateService gridCodeAnnotateService;

    @PostMapping("/create")
    @Operation(summary = "创建网格标识码注记配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:create')")
    public CommonResult<Long> createGridCodeAnnotate(@Valid @RequestBody GridCodeAnnotateSaveReqVO createReqVO) {
        return success(gridCodeAnnotateService.createGridCodeAnnotate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格标识码注记配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:update')")
    public CommonResult<Boolean> updateGridCodeAnnotate(@Valid @RequestBody GridCodeAnnotateSaveReqVO updateReqVO) {
        gridCodeAnnotateService.updateGridCodeAnnotate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格标识码注记配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:delete')")
    public CommonResult<Boolean> deleteGridCodeAnnotate(@RequestParam("id") Long id) {
        gridCodeAnnotateService.deleteGridCodeAnnotate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格标识码注记配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:query')")
    public CommonResult<GridCodeAnnotateRespVO> getGridCodeAnnotate(@RequestParam("id") Long id) {
        GridCodeAnnotateDO gridCodeAnnotate = gridCodeAnnotateService.getGridCodeAnnotate(id);
        return success(BeanUtils.toBean(gridCodeAnnotate, GridCodeAnnotateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格标识码注记配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:query')")
    public CommonResult<PageResult<GridCodeAnnotateRespVO>> getGridCodeAnnotatePage(@Valid GridCodeAnnotatePageReqVO pageReqVO) {
        PageResult<GridCodeAnnotateDO> pageResult = gridCodeAnnotateService.getGridCodeAnnotatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridCodeAnnotateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格标识码注记配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-code-annotate:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridCodeAnnotateExcel(@Valid GridCodeAnnotatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridCodeAnnotateDO> list = gridCodeAnnotateService.getGridCodeAnnotatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格标识码注记配置.xls", "数据", GridCodeAnnotateRespVO.class,
                        BeanUtils.toBean(list, GridCodeAnnotateRespVO.class));
    }

}