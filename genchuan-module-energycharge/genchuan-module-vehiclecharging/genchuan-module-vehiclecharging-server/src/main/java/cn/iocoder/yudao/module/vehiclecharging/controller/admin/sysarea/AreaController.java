package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sysarea.vo.AreaSaveReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sysarea.AreaDO;
import cn.iocoder.yudao.module.vehiclecharging.service.sysarea.AreaService;
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


@Tag(name = "汽车充电 - 统一行政区划配置表（树形结构）")
@RestController
@RequestMapping("/sys/area")
@Validated
public class AreaController {

    @Resource
    private AreaService areaService;

    @PostMapping("/create")
    @Operation(summary = "创建统一行政区划配置表（树形结构）")
    @PreAuthorize("@ss.hasPermission('sys:area:create')")
    public CommonResult<Long> createArea(@Valid @RequestBody AreaSaveReqVO createReqVO) {
        return success(areaService.createArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新统一行政区划配置表（树形结构）")
    @PreAuthorize("@ss.hasPermission('sys:area:update')")
    public CommonResult<Boolean> updateArea(@Valid @RequestBody AreaSaveReqVO updateReqVO) {
        areaService.updateArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除统一行政区划配置表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sys:area:delete')")
    public CommonResult<Boolean> deleteArea(@RequestParam("id") Long id) {
        areaService.deleteArea(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除统一行政区划配置表（树形结构）")
                @PreAuthorize("@ss.hasPermission('sys:area:delete')")
    public CommonResult<Boolean> deleteAreaList(@RequestParam("ids") List<Long> ids) {
        areaService.deleteAreaListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得统一行政区划配置表（树形结构）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sys:area:query')")
    public CommonResult<AreaRespVO> getArea(@RequestParam("id") Long id) {
        AreaDO area = areaService.getArea(id);
        return success(BeanUtils.toBean(area, AreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得统一行政区划配置表（树形结构）分页")
    @PreAuthorize("@ss.hasPermission('sys:area:query')")
    public CommonResult<PageResult<AreaRespVO>> getAreaPage(@Valid AreaPageReqVO pageReqVO) {
        PageResult<AreaDO> pageResult = areaService.getAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出统一行政区划配置表（树形结构） Excel")
    @PreAuthorize("@ss.hasPermission('sys:area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAreaExcel(@Valid AreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AreaDO> list = areaService.getAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "统一行政区划配置表（树形结构）.xls", "数据", AreaRespVO.class,
                        BeanUtils.toBean(list, AreaRespVO.class));
    }

}