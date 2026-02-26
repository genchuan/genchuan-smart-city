package cn.iocoder.yudao.module.envir.controller.admin.route;

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

import cn.iocoder.yudao.module.envir.controller.admin.route.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.route.RouteDO;
import cn.iocoder.yudao.module.envir.service.route.RouteService;

@Tag(name = "管理后台 - 路线")
@RestController
@RequestMapping("/envir/route")
@Validated
public class RouteController {

    @Resource
    private RouteService routeService;

    @PostMapping("/create")
    @Operation(summary = "创建路线")
    @PreAuthorize("@ss.hasPermission('envir:route:create')")
    public CommonResult<Long> createRoute(@Valid @RequestBody RouteSaveReqVO createReqVO) {
        return success(routeService.createRoute(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新路线")
    @PreAuthorize("@ss.hasPermission('envir:route:update')")
    public CommonResult<Boolean> updateRoute(@Valid @RequestBody RouteSaveReqVO updateReqVO) {
        routeService.updateRoute(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:route:delete')")
    public CommonResult<Boolean> deleteRoute(@RequestParam("id") Long id) {
        routeService.deleteRoute(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得路线")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:route:query')")
    public CommonResult<RouteRespVO> getRoute(@RequestParam("id") Long id) {
        RouteDO route = routeService.getRoute(id);
        return success(BeanUtils.toBean(route, RouteRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得路线分页")
    @PreAuthorize("@ss.hasPermission('envir:route:query')")
    public CommonResult<PageResult<RouteRespVO>> getRoutePage(@Valid RoutePageReqVO pageReqVO) {
        PageResult<RouteDO> pageResult = routeService.getRoutePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RouteRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出路线 Excel")
    @PreAuthorize("@ss.hasPermission('envir:route:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRouteExcel(@Valid RoutePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RouteDO> list = routeService.getRoutePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "路线.xls", "数据", RouteRespVO.class,
                        BeanUtils.toBean(list, RouteRespVO.class));
    }

}