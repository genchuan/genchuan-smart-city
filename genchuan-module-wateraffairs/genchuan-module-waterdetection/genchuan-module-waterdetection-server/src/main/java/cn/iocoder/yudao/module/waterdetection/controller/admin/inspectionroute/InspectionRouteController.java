package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectionroute.InspectionRouteDO;
import cn.iocoder.yudao.module.waterdetection.service.inspectionroute.InspectionRouteService;

@Tag(name = "管理后台 - 巡检路线规划与优化")
@RestController
@RequestMapping("/waterdetection/inspection-route")
@Validated
public class InspectionRouteController {

    @Resource
    private InspectionRouteService inspectionRouteService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检路线规划与优化")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:create')")
    public CommonResult<Long> createInspectionRoute(@Valid @RequestBody InspectionRouteSaveReqVO createReqVO) {
        return success(inspectionRouteService.createInspectionRoute(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检路线规划与优化")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:update')")
    public CommonResult<Boolean> updateInspectionRoute(@Valid @RequestBody InspectionRouteSaveReqVO updateReqVO) {
        inspectionRouteService.updateInspectionRoute(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检路线规划与优化")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:delete')")
    public CommonResult<Boolean> deleteInspectionRoute(@RequestParam("id") Long id) {
        inspectionRouteService.deleteInspectionRoute(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检路线规划与优化")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:query')")
    public CommonResult<InspectionRouteRespVO> getInspectionRoute(@RequestParam("id") Long id) {
        InspectionRouteDO inspectionRoute = inspectionRouteService.getInspectionRoute(id);
        return success(BeanUtils.toBean(inspectionRoute, InspectionRouteRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检路线规划与优化分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:query')")
    public CommonResult<PageResult<InspectionRouteRespVO>> getInspectionRoutePage(@Valid InspectionRoutePageReqVO pageReqVO) {
        PageResult<InspectionRouteDO> pageResult = inspectionRouteService.getInspectionRoutePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionRouteRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检路线规划与优化 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-route:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionRouteExcel(@Valid InspectionRoutePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionRouteDO> list = inspectionRouteService.getInspectionRoutePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检路线规划与优化.xls", "数据", InspectionRouteRespVO.class,
                        BeanUtils.toBean(list, InspectionRouteRespVO.class));
    }

}