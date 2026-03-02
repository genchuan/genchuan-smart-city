/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.tasktype;

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

import cn.iocoder.yudao.module.envirhealth.controller.admin.tasktype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.tasktype.TaskTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.tasktype.TaskTypeService;

@Tag(name = "环境卫生管理 - 任务类型字典表")
@RestController
@RequestMapping("/envirhealth/task-type")
@Validated
public class TaskTypeController {

    @Resource
    private TaskTypeService taskTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建任务类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:create')")
    public CommonResult<Long> createTaskType(@Valid @RequestBody TaskTypeSaveReqVO createReqVO) {
        return success(taskTypeService.createTaskType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:update')")
    public CommonResult<Boolean> updateTaskType(@Valid @RequestBody TaskTypeSaveReqVO updateReqVO) {
        taskTypeService.updateTaskType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:delete')")
    public CommonResult<Boolean> deleteTaskType(@RequestParam("id") Long id) {
        taskTypeService.deleteTaskType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:query')")
    public CommonResult<TaskTypeRespVO> getTaskType(@RequestParam("id") Long id) {
        TaskTypeDO taskType = taskTypeService.getTaskType(id);
        return success(BeanUtils.toBean(taskType, TaskTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:query')")
    public CommonResult<PageResult<TaskTypeRespVO>> getTaskTypePage(@Valid TaskTypePageReqVO pageReqVO) {
        PageResult<TaskTypeDO> pageResult = taskTypeService.getTaskTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:task-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskTypeExcel(@Valid TaskTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskTypeDO> list = taskTypeService.getTaskTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务类型字典表.xls", "数据", TaskTypeRespVO.class,
                        BeanUtils.toBean(list, TaskTypeRespVO.class));
    }

}*/
