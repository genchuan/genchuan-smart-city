package cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.griddataqualck.GridDataQualCkDO;
import cn.iocoder.yudao.module.datacenter.service.grid.data.griddataqualck.GridDataQualCkService;

@Tag(name = "管理后台 - 网格数据质量检查")
@RestController
@RequestMapping("/datacenter/grid-data-qual-ck")
@Validated
public class GridDataQualCkController {

    @Resource
    private GridDataQualCkService gridDataQualCkService;

    @PostMapping("/create")
    @Operation(summary = "创建网格数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:create')")
    public CommonResult<Long> createGridDataQualCk(@Valid @RequestBody GridDataQualCkSaveReqVO createReqVO) {
        return success(gridDataQualCkService.createGridDataQualCk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:update')")
    public CommonResult<Boolean> updateGridDataQualCk(@Valid @RequestBody GridDataQualCkSaveReqVO updateReqVO) {
        gridDataQualCkService.updateGridDataQualCk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格数据质量检查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:delete')")
    public CommonResult<Boolean> deleteGridDataQualCk(@RequestParam("id") Long id) {
        gridDataQualCkService.deleteGridDataQualCk(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格数据质量检查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:query')")
    public CommonResult<GridDataQualCkRespVO> getGridDataQualCk(@RequestParam("id") Long id) {
        GridDataQualCkDO gridDataQualCk = gridDataQualCkService.getGridDataQualCk(id);
        return success(BeanUtils.toBean(gridDataQualCk, GridDataQualCkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格数据质量检查分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:query')")
    public CommonResult<PageResult<GridDataQualCkRespVO>> getGridDataQualCkPage(@Valid GridDataQualCkPageReqVO pageReqVO) {
        PageResult<GridDataQualCkDO> pageResult = gridDataQualCkService.getGridDataQualCkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridDataQualCkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格数据质量检查 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-data-qual-ck:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridDataQualCkExcel(@Valid GridDataQualCkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridDataQualCkDO> list = gridDataQualCkService.getGridDataQualCkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格数据质量检查.xls", "数据", GridDataQualCkRespVO.class,
                        BeanUtils.toBean(list, GridDataQualCkRespVO.class));
    }

}