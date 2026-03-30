package cn.iocoder.yudao.module.evaluate.controller.admin.task;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.task.vo.TaskSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.task.TaskDO;
import cn.iocoder.yudao.module.evaluate.service.task.TaskService;
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

@Tag(name = "评价任务执行 - 评价任务")
@RestController
@RequestMapping("/evaluate/task")
@Validated
public class TaskController {

    @Resource
    private TaskService taskService;

    @PostMapping("/create")
    @Operation(summary = "创建评价任务")
    @PreAuthorize("@ss.hasPermission('evaluate:task:create')")
    public CommonResult<Long> createTask(@Valid @RequestBody TaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价任务")
    @PreAuthorize("@ss.hasPermission('evaluate:task:update')")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody TaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:task:delete')")
    public CommonResult<Boolean> deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评价任务")
                @PreAuthorize("@ss.hasPermission('evaluate:task:delete')")
    public CommonResult<Boolean> deleteTaskList(@RequestParam("ids") List<Long> ids) {
        taskService.deleteTaskListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:task:query')")
    public CommonResult<TaskRespVO> getTask(@RequestParam("id") Long id) {
        TaskDO task = taskService.getTask(id);
        return success(BeanUtils.toBean(task, TaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价任务分页")
    @PreAuthorize("@ss.hasPermission('evaluate:task:query')")
    public CommonResult<PageResult<TaskRespVO>> getTaskPage(@Valid TaskPageReqVO pageReqVO) {
        PageResult<TaskDO> pageResult = taskService.getTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价任务 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskExcel(@Valid TaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskDO> list = taskService.getTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价任务.xls", "数据", TaskRespVO.class,
                        BeanUtils.toBean(list, TaskRespVO.class));
    }
//====【【【【【【【【【==========================xin
    // 这个接口会调用getTaskPage方法，自定义对象名称逻辑会生效
    @Operation(summary = "分页查询评价任务（含自定义对象名称）")
    @GetMapping("/pageRelevant")
    public CommonResult<PageResult<TaskRespVO>> getTaskPagerelevant(TaskPageReqVO reqVO) {
        PageResult<TaskRespVO> result = taskService.getTaskPagerelevant(reqVO); // 调用核心方法
        return success(result);
    }
}