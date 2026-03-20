package cn.iocoder.yudao.module.envirhealth.controller.admin.task;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.TaskRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.task.vo.TaskSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDetailWithGarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDetailWithPublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.task.TaskDetailWithPublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.service.task.TaskService;
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

@Tag(name = "环境卫生管理 - 任务")
@RestController
@RequestMapping("/envirhealth/task")
@Validated
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    @Operation(summary = "创建任务")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:create')")
    public CommonResult<Long> createTask(@Valid @RequestBody TaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:update')")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody TaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:task:delete')")
    public CommonResult<Boolean> deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:query')")
    public CommonResult<TaskRespVO> getTask(@RequestParam("id") Long id) {
        TaskDO task = taskService.getTask(id);
        return success(BeanUtils.toBean(task, TaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:query')")
    public CommonResult<PageResult<TaskRespVO>> getTaskPage(@Valid TaskPageReqVO pageReqVO) {
        PageResult<TaskDO> pageResult = taskService.getTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskExcel(@Valid TaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskDO> list = taskService.getTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务.xls", "数据", TaskRespVO.class,
                        BeanUtils.toBean(list, TaskRespVO.class));
    }

    @GetMapping("/detail-page-public-toilet")
    @Operation(summary = "获得任务公厕详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:query')")
    public CommonResult<PageResult<TaskDetailWithPublicToiletDO>> getTaskDetailPageWithPublicToilet(
            @Valid TaskPageReqVO pageReqVO) {
        PageResult<TaskDetailWithPublicToiletDO> pageResult =
                taskService.getTaskDetailPageWithPublicToilet(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/detail-page-garbage-transfer")
    @Operation(summary = "获得垃圾转运详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:query')")
    public CommonResult<PageResult<TaskDetailWithGarbageTransferDO>> getTaskDetailPageWithGarbageTransfer(
            @Valid TaskPageReqVO pageReqVO) {
        PageResult<TaskDetailWithGarbageTransferDO> pageResult =
                taskService.getTaskDetailPageWithGarbageTransfer(pageReqVO);

        return success(pageResult);
    }

    @GetMapping("/detail-page-public-institution")
    @Operation(summary = "获得公共机构详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:task:query')")
    public CommonResult<PageResult<TaskDetailWithPublicInstitutionDO>> getTaskDetailPageWithPublicInstitutionDO(
            @Valid TaskPageReqVO pageReqVO) {
        PageResult<TaskDetailWithPublicInstitutionDO> pageResult =
                taskService.getTaskDetailPageWithPublicInstitution(pageReqVO);

        return success(pageResult);
    }
}