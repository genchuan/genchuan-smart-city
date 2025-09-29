package cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.patrolroute.PatrolRouteDO;
import cn.iocoder.yudao.module.datacenter.service.patrolroute.PatrolRouteService;

@Tag(name = "管理后台 - 巡查路线")
@RestController
@RequestMapping("/datacenter/patrol-route")
@Validated
public class PatrolRouteController {

    @Resource
    private PatrolRouteService patrolRouteService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查路线")
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:create')")
    public CommonResult<Long> createPatrolRoute(@Valid @RequestBody PatrolRouteSaveReqVO createReqVO) {
        return success(patrolRouteService.createPatrolRoute(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查路线")
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:update')")
    public CommonResult<Boolean> updatePatrolRoute(@Valid @RequestBody PatrolRouteSaveReqVO updateReqVO) {
        patrolRouteService.updatePatrolRoute(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查路线")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:delete')")
    public CommonResult<Boolean> deletePatrolRoute(@RequestParam("id") Long id) {
        patrolRouteService.deletePatrolRoute(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查路线")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:query')")
    public CommonResult<PatrolRouteRespVO> getPatrolRoute(@RequestParam("id") Long id) {
        PatrolRouteDO patrolRoute = patrolRouteService.getPatrolRoute(id);
        return success(BeanUtils.toBean(patrolRoute, PatrolRouteRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查路线分页")
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:query')")
    public CommonResult<PageResult<PatrolRouteRespVO>> getPatrolRoutePage(@Valid PatrolRoutePageReqVO pageReqVO) {
        PageResult<PatrolRouteDO> pageResult = patrolRouteService.getPatrolRoutePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PatrolRouteRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查路线 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:patrol-route:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPatrolRouteExcel(@Valid PatrolRoutePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PatrolRouteDO> list = patrolRouteService.getPatrolRoutePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查路线.xls", "数据", PatrolRouteRespVO.class,
                        BeanUtils.toBean(list, PatrolRouteRespVO.class));
    }

}