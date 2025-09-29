package cn.iocoder.yudao.module.datacenter.controller.admin.routeversion;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.routeversion.RouteVersionDO;
import cn.iocoder.yudao.module.datacenter.service.routeversion.RouteVersionService;

@Tag(name = "管理后台 - 路线版本")
@RestController
@RequestMapping("/datacenter/route-version")
@Validated
public class RouteVersionController {

    @Resource
    private RouteVersionService routeVersionService;

    @PostMapping("/create")
    @Operation(summary = "创建路线版本")
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:create')")
    public CommonResult<Long> createRouteVersion(@Valid @RequestBody RouteVersionSaveReqVO createReqVO) {
        return success(routeVersionService.createRouteVersion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新路线版本")
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:update')")
    public CommonResult<Boolean> updateRouteVersion(@Valid @RequestBody RouteVersionSaveReqVO updateReqVO) {
        routeVersionService.updateRouteVersion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除路线版本")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:delete')")
    public CommonResult<Boolean> deleteRouteVersion(@RequestParam("id") Long id) {
        routeVersionService.deleteRouteVersion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得路线版本")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:query')")
    public CommonResult<RouteVersionRespVO> getRouteVersion(@RequestParam("id") Long id) {
        RouteVersionDO routeVersion = routeVersionService.getRouteVersion(id);
        return success(BeanUtils.toBean(routeVersion, RouteVersionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得路线版本分页")
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:query')")
    public CommonResult<PageResult<RouteVersionRespVO>> getRouteVersionPage(@Valid RouteVersionPageReqVO pageReqVO) {
        PageResult<RouteVersionDO> pageResult = routeVersionService.getRouteVersionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RouteVersionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出路线版本 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:route-version:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRouteVersionExcel(@Valid RouteVersionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RouteVersionDO> list = routeVersionService.getRouteVersionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "路线版本.xls", "数据", RouteVersionRespVO.class,
                        BeanUtils.toBean(list, RouteVersionRespVO.class));
    }

}