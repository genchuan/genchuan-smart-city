package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import cn.iocoder.yudao.module.inspectop.service.inspecttask.InspectTaskService;

@Tag(name = "巡查巡检 - 巡检任务")
@RestController
@RequestMapping("/inspectop/inspect-task")
@Validated
public class InspectTaskController {

    @Resource
    private InspectTaskService inspectTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检任务")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:create')")
    public CommonResult<Long> createInspectTask(@Valid @RequestBody InspectTaskSaveReqVO createReqVO) {
        return success(inspectTaskService.createInspectTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检任务")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:update')")
    public CommonResult<Boolean> updateInspectTask(@Valid @RequestBody InspectTaskSaveReqVO updateReqVO) {
        inspectTaskService.updateInspectTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:delete')")
    public CommonResult<Boolean> deleteInspectTask(@RequestParam("id") Long id) {
        inspectTaskService.deleteInspectTask(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检任务")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:delete')")
    public CommonResult<Boolean> deleteInspectTaskList(@RequestParam("ids") List<Long> ids) {
        inspectTaskService.deleteInspectTaskListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:query')")
    public CommonResult<InspectTaskRespVO> getInspectTask(@RequestParam("id") Long id) {
        // 1. 获取DO对象
        InspectTaskDO inspectTask = inspectTaskService.getInspectTask(id);

        // 2. 转换为VO对象
        InspectTaskRespVO vo = BeanUtils.toBean(inspectTask, InspectTaskRespVO.class);

        // 3. 【新增】转换状态字典值为中文
        vo.setStatus(convertTaskStatus(vo.getStatus()));

        return success(vo);
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检任务分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:query')")
    public CommonResult<PageResult<InspectTaskRespVO>> getInspectTaskPage(@Valid InspectTaskPageReqVO pageReqVO) {
        // 使用新的关联查询方法
        PageResult<InspectTaskRespVO> pageResult = inspectTaskService.getInspectTaskPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/batch-dispatch")
    @Operation(summary = "批量派发")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:batch-dispatch')")
    public CommonResult<Boolean> batchDispatchInspectTask(@Valid @RequestBody InspectTaskBatchDispatchReqVO batchDispatchReqVO) {
        inspectTaskService.batchDispatchInspectTask(batchDispatchReqVO);
        return success(true);
    }

    @PutMapping("/dispatch")
    @Operation(summary = "派发")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:dispatch')")
    public CommonResult<Boolean> dispatchInspectTask(@Valid @RequestBody InspectTaskBatchDispatchReqVO batchDispatchReqVO) {
        inspectTaskService.batchDispatchInspectTask(batchDispatchReqVO);
        return success(true);
    }

    @PutMapping("/claim")
    @Operation(summary = "认领")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:claim')")
    public CommonResult<Boolean> claimInspectTask(@Valid @RequestBody InspectTaskClaimReqVO claimReqVO) {
        inspectTaskService.claimInspectTask(claimReqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新巡检任务进度")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:update-progress')")
    public CommonResult<Boolean> updateInspectTaskProgress(@Valid @RequestBody InspectTaskUpdateProgressReqVO updateProgressReqVO) {
        inspectTaskService.updateInspectTaskProgress(updateProgressReqVO);
        return success(true);
    }

    @PutMapping("/transfer")
    @Operation(summary = "转派")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:transfer')")
    public CommonResult<Boolean> transferInspectTask(@Valid @RequestBody InspectTaskTransferReqVO transferReqVO) {
        inspectTaskService.transferInspectTask(transferReqVO);
        return success(true);
    }

    @PutMapping("/archive")
    @Operation(summary = "归档")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:archive')")
    public CommonResult<Boolean> archiveInspectTask(@Valid @RequestBody InspectTaskArchiveReqVO archiveReqVO) {
        inspectTaskService.archiveInspectTask(archiveReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "统计图表数据")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:chart')")
    public CommonResult<InspectTaskChartRespVO> getInspectTaskChart(@RequestParam(value = "timeRange", required = false) String[] timeRange) {
        InspectTaskChartRespVO chartData = inspectTaskService.getInspectTaskChartData(timeRange);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检任务 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTaskExcel(@Valid InspectTaskPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

        // 获取分页结果，直接得到VO列表
        List<InspectTaskRespVO> list = inspectTaskService.getInspectTaskPage(pageReqVO).getList();

        // 【新增】对VO列表中的字典值进行转换（数字 -> 中文）
        convertTaskDictValues(list);

        // 导出 Excel
        ExcelUtils.write(response, "巡检任务.xls", "数据", InspectTaskRespVO.class, list);
    }

    /**
     * 【新增】转换任务字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 status 字段。
     * @param voList 巡检任务响应VO列表
     */
    private void convertTaskDictValues(List<InspectTaskRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (InspectTaskRespVO vo : voList) {
            // 转换任务状态
            vo.setStatus(convertTaskStatus(vo.getStatus()));
        }
    }

    /**
     * 【新增】转换巡检任务状态字典值
     * 根据您提供的映射：1-待派发，2-待认领，3-处理中，4-已完成
     * @param statusCode 状态编码（例如 "1", "2", "3", "4"）
     * @return 对应的中文状态描述
     */
    private String convertTaskStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "待派发";
            case "2":
                return "待认领";
            case "3":
                return "处理中";
            case "4":
                return "已完成";
            default:
                // 如果遇到未知编码，返回原编码以便排查
                return statusCode;
        }
    }
}