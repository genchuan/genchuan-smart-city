package cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.taskdispatch.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.taskdispatch.TaskDispatchDO;
import cn.iocoder.yudao.module.waterdetection.service.taskdispatch.TaskDispatchService;

@Tag(name = "管理后台 - 任务派发")
@RestController
@RequestMapping("/waterdetection/task-dispatch")
@Validated
public class TaskDispatchController {

    @Resource
    private TaskDispatchService taskDispatchService;

    @PostMapping("/create")
    @Operation(summary = "创建任务派发")
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:create')")
    public CommonResult<Long> createTaskDispatch(@Valid @RequestBody TaskDispatchSaveReqVO createReqVO) {
        return success(taskDispatchService.createTaskDispatch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务派发")
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:update')")
    public CommonResult<Boolean> updateTaskDispatch(@Valid @RequestBody TaskDispatchSaveReqVO updateReqVO) {
        taskDispatchService.updateTaskDispatch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务派发")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:delete')")
    public CommonResult<Boolean> deleteTaskDispatch(@RequestParam("id") Long id) {
        taskDispatchService.deleteTaskDispatch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务派发")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:query')")
    public CommonResult<TaskDispatchRespVO> getTaskDispatch(@RequestParam("id") Long id) {
        TaskDispatchDO taskDispatch = taskDispatchService.getTaskDispatch(id);
        return success(BeanUtils.toBean(taskDispatch, TaskDispatchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务派发分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:query')")
    public CommonResult<PageResult<TaskDispatchRespVO>> getTaskDispatchPage(@Valid TaskDispatchPageReqVO pageReqVO) {
        PageResult<TaskDispatchDO> pageResult = taskDispatchService.getTaskDispatchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskDispatchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务派发 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:task-dispatch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskDispatchExcel(@Valid TaskDispatchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskDispatchDO> list = taskDispatchService.getTaskDispatchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务派发.xls", "数据", TaskDispatchRespVO.class,
                        BeanUtils.toBean(list, TaskDispatchRespVO.class));
    }

}