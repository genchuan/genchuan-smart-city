package cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.maintenancetasks.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenancetasks.MaintenanceTasksDO;
import cn.iocoder.yudao.module.smartcity.service.maintenancetasks.MaintenanceTasksService;

@Tag(name = "管理后台 - 养护任务")
@RestController
@RequestMapping("/smartcity/maintenance-tasks")
@Validated
public class MaintenanceTasksController {

    @Resource
    private MaintenanceTasksService maintenanceTasksService;

    @PostMapping("/create")
    @Operation(summary = "创建养护任务")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:create')")
    public CommonResult<Long> createMaintenanceTasks(@Valid @RequestBody MaintenanceTasksSaveReqVO createReqVO) {
        return success(maintenanceTasksService.createMaintenanceTasks(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新养护任务")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:update')")
    public CommonResult<Boolean> updateMaintenanceTasks(@Valid @RequestBody MaintenanceTasksSaveReqVO updateReqVO) {
        maintenanceTasksService.updateMaintenanceTasks(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除养护任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:delete')")
    public CommonResult<Boolean> deleteMaintenanceTasks(@RequestParam("id") Long id) {
        maintenanceTasksService.deleteMaintenanceTasks(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得养护任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:query')")
    public CommonResult<MaintenanceTasksRespVO> getMaintenanceTasks(@RequestParam("id") Long id) {
        MaintenanceTasksDO maintenanceTasks = maintenanceTasksService.getMaintenanceTasks(id);
        return success(BeanUtils.toBean(maintenanceTasks, MaintenanceTasksRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得养护任务分页")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:query')")
    public CommonResult<PageResult<MaintenanceTasksRespVO>> getMaintenanceTasksPage(@Valid MaintenanceTasksPageReqVO pageReqVO) {
        PageResult<MaintenanceTasksDO> pageResult = maintenanceTasksService.getMaintenanceTasksPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MaintenanceTasksRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出养护任务 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:maintenance-tasks:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMaintenanceTasksExcel(@Valid MaintenanceTasksPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MaintenanceTasksDO> list = maintenanceTasksService.getMaintenanceTasksPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "养护任务.xls", "数据", MaintenanceTasksRespVO.class,
                        BeanUtils.toBean(list, MaintenanceTasksRespVO.class));
    }

}