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

@Tag(name = "管理后台 - 巡检任务")
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
        InspectTaskDO inspectTask = inspectTaskService.getInspectTask(id);
        return success(BeanUtils.toBean(inspectTask, InspectTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检任务分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:query')")
    public CommonResult<PageResult<InspectTaskRespVO>> getInspectTaskPage(@Valid InspectTaskPageReqVO pageReqVO) {
        PageResult<InspectTaskDO> pageResult = inspectTaskService.getInspectTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检任务 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTaskExcel(@Valid InspectTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectTaskDO> list = inspectTaskService.getInspectTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检任务.xls", "数据", InspectTaskRespVO.class,
                        BeanUtils.toBean(list, InspectTaskRespVO.class));
    }

}