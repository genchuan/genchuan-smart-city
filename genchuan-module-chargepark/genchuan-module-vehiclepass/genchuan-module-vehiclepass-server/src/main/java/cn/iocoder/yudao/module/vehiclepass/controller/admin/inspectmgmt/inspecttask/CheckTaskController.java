package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskBatchDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskDispatchReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskClaimReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskTransferReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskArchiveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.InspectTaskChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo.CheckTaskSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.inspecttask.CheckTaskDO;
import cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.inspecttask.CheckTaskService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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


@Tag(name = "管理后台 - 稽查任务")
@RestController
@RequestMapping("/vehiclepass/inspect-task")
@Validated
public class CheckTaskController {

    @Resource
    private CheckTaskService taskService;

    @PostMapping("/create")
    @Operation(summary = "创建稽查任务")
    @PreAuthorize("@ss.hasPermission('check:task:create')")
    public CommonResult<Long> createTask(@Valid @RequestBody CheckTaskSaveReqVO createReqVO) {
        return success(taskService.createTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新稽查任务")
    @PreAuthorize("@ss.hasPermission('check:task:update')")
    public CommonResult<Boolean> updateTask(@Valid @RequestBody CheckTaskSaveReqVO updateReqVO) {
        taskService.updateTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除稽查任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('check:task:delete')")
    public CommonResult<Boolean> deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除稽查任务")
    @PreAuthorize("@ss.hasPermission('check:task:delete')")
    public CommonResult<Boolean> deleteTaskList(@RequestParam("ids") List<Long> ids) {
        taskService.deleteTaskListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得稽查任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('check:task:query')")
    public CommonResult<CheckTaskRespVO> getTask(@RequestParam("id") Long id) {
        CheckTaskDO task = taskService.getTask(id);
        return success(BeanUtils.toBean(task, CheckTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得稽查任务分页")
    @PreAuthorize("@ss.hasPermission('check:task:query')")
    public CommonResult<PageResult<CheckTaskRespVO>> getTaskPage(@Valid CheckTaskPageReqVO pageReqVO) {
        return success(taskService.getTaskPageWithJoin(pageReqVO));
    }

    @PostMapping("/batch-dispatch")
    @Operation(summary = "批量派发稽查任务")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:batch-dispatch')")
    public CommonResult<Boolean> batchDispatch(@Valid @RequestBody InspectTaskBatchDispatchReqVO reqVO) {
        taskService.batchDispatch(reqVO);
        return success(true);
    }

    @PutMapping("/dispatch")
    @Operation(summary = "派发稽查任务")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:dispatch')")
    public CommonResult<Boolean> dispatch(@Valid @RequestBody InspectTaskDispatchReqVO reqVO) {
        taskService.dispatch(reqVO);
        return success(true);
    }

    @PutMapping("/claim")
    @Operation(summary = "认领稽查任务")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:claim')")
    public CommonResult<Boolean> claim(@Valid @RequestBody InspectTaskClaimReqVO reqVO) {
        taskService.claim(reqVO.getId());
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新稽查任务进度")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:update-progress')")
    public CommonResult<Boolean> updateProgress(@Valid @RequestBody InspectTaskUpdateProgressReqVO reqVO) {
        taskService.updateProgress(reqVO);
        return success(true);
    }

    @PutMapping("/transfer")
    @Operation(summary = "转派稽查任务")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:transfer')")
    public CommonResult<Boolean> transfer(@Valid @RequestBody InspectTaskTransferReqVO reqVO) {
        taskService.transfer(reqVO);
        return success(true);
    }

    @PutMapping("/archive")
    @Operation(summary = "归档稽查任务")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:archive')")
    public CommonResult<Boolean> archive(@Valid @RequestBody InspectTaskArchiveReqVO reqVO) {
        taskService.archive(reqVO.getId());
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "稽查任务统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:inspect-task:chart')")
    public CommonResult<InspectTaskChartRespVO> getChart(@Valid InspectTaskChartReqVO reqVO) {
        return success(taskService.getChart(reqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出稽查任务 Excel")
    @PreAuthorize("@ss.hasPermission('check:task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskExcel(@Valid CheckTaskPageReqVO pageReqVO,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<CheckTaskRespVO> pageResult = taskService.getTaskPageWithJoin(pageReqVO);
        ExcelUtils.write(response, "稽查任务.xls", "数据", CheckTaskRespVO.class, pageResult.getList());
    }

}