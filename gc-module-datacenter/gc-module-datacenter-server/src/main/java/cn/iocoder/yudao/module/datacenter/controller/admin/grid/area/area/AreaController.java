package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.area.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.area.AreaDO;
import cn.iocoder.yudao.module.datacenter.service.grid.area.area.AreaService;

@Tag(name = "管理后台 - 统一行政区划配置表（树形结构）")
@RestController
@RequestMapping("/datacenter/area")
@Validated
public class AreaController {

    @Resource
    private AreaService areaService;

    @PostMapping("/create")
    @Operation(summary = "创建统一行政区划配置表（树形结构）")
    @PreAuthorize("@ss.hasPermission('datacenter:area:create')")
    public CommonResult<Long> createArea(@Valid @RequestBody AreaSaveReqVO createReqVO) {
        return success(areaService.createArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新统一行政区划配置表（树形结构）")
    @PreAuthorize("@ss.hasPermission('datacenter:area:update')")
    public CommonResult<Boolean> updateArea(@Valid @RequestBody AreaSaveReqVO updateReqVO) {
        areaService.updateArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除统一行政区划配置表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:area:delete')")
    public CommonResult<Boolean> deleteArea(@RequestParam("id") Long id) {
        areaService.deleteArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得统一行政区划配置表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<AreaRespVO> getArea(@RequestParam("id") Long id) {
        AreaDO area = areaService.getArea(id);
        return success(BeanUtils.toBean(area, AreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得统一行政区划配置表（树形结构）分页")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<PageResult<AreaRespVO>> getAreaPage(@Valid AreaPageReqVO pageReqVO) {
        PageResult<AreaDO> pageResult = areaService.getAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaRespVO.class));
    }

    // ========== 新增的树形结构相关接口 ==========

    @GetMapping("/children")
    @Operation(summary = "获得子级行政区划列表")
    @Parameter(name = "parentId", description = "父级ID", required = true, example = "0")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<List<AreaRespVO>> getChildAreaList(@RequestParam("parentId") Long parentId) {
        List<AreaDO> list = areaService.getChildAreaList(parentId);
        return success(BeanUtils.toBean(list, AreaRespVO.class));
    }

    @GetMapping("/tree")
    @Operation(summary = "获得完整的行政区划树")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<List<AreaTreeRespVO>> getAreaTree() {
        List<AreaTreeRespVO> tree = areaService.getAreaTree();
        return success(tree);
    }

    @GetMapping("/tree/{parentId}")
    @Operation(summary = "获得指定节点的子树")
    @Parameter(name = "parentId", description = "父级ID", required = true, example = "0")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<List<AreaTreeRespVO>> getAreaTree(@PathVariable("parentId") Long parentId) {
        List<AreaTreeRespVO> tree = areaService.getAreaTree(parentId);
        return success(tree);
    }

    @GetMapping("/ancestors")
    @Operation(summary = "获得所有祖先节点列表")
    @Parameter(name = "id", description = "节点ID", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<List<AreaRespVO>> getAncestorList(@RequestParam("id") Long id) {
        List<AreaDO> list = areaService.getAncestorList(id);
        return success(BeanUtils.toBean(list, AreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出统一行政区划配置表（树形结构） Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaExcel(@Valid AreaPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaDO> list = areaService.getAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "统一行政区划配置表（树形结构）.xls", "数据", AreaRespVO.class,
                BeanUtils.toBean(list, AreaRespVO.class));
    }

    @GetMapping("/dropdown")
    @Operation(summary = "获得所有已生效的乡镇和社区数据")
    @PreAuthorize("@ss.hasPermission('datacenter:area:query')")
    public CommonResult<AreaDropdownRespVO> getAreaDropdownList() {
        AreaDropdownRespVO dropdownData = areaService.getAreaDropdownList();
        return success(dropdownData);
    }

}