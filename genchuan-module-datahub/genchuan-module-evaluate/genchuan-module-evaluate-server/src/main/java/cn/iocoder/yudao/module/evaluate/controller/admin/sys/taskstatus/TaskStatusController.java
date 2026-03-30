package cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.taskstatus.vo.TaskStatusSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.taskstatus.TaskStatusDO;
import cn.iocoder.yudao.module.evaluate.service.taskstatus.TaskStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 任务状态字典")
@RestController
@RequestMapping("/evaluate/task-status")
@Validated
public class TaskStatusController {

    @Resource
    private TaskStatusService taskStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建任务状态字典")
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:create')")
    public CommonResult<Long> createTaskStatus(@Valid @RequestBody TaskStatusSaveReqVO createReqVO) {
        return success(taskStatusService.createTaskStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务状态字典")
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:update')")
    public CommonResult<Boolean> updateTaskStatus(@Valid @RequestBody TaskStatusSaveReqVO updateReqVO) {
        taskStatusService.updateTaskStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:delete')")
    public CommonResult<Boolean> deleteTaskStatus(@RequestParam("id") Long id) {
        taskStatusService.deleteTaskStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:query')")
    public CommonResult<TaskStatusRespVO> getTaskStatus(@RequestParam("id") Long id) {
        TaskStatusDO taskStatus = taskStatusService.getTaskStatus(id);
        return success(BeanUtils.toBean(taskStatus, TaskStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务状态字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:query')")
    public CommonResult<PageResult<TaskStatusRespVO>> getTaskStatusPage(@Valid TaskStatusPageReqVO pageReqVO) {
        PageResult<TaskStatusDO> pageResult = taskStatusService.getTaskStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:task-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskStatusExcel(@Valid TaskStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskStatusDO> list = taskStatusService.getTaskStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务状态字典.xls", "数据", TaskStatusRespVO.class,
                        BeanUtils.toBean(list, TaskStatusRespVO.class));
    }

}