package cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectiontask.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectiontask.InspectionTaskDO;
import cn.iocoder.yudao.module.waterdetection.service.inspectiontask.InspectionTaskService;

@Tag(name = "管理后台 - 巡检任务派发与执行")
@RestController
@RequestMapping("/waterdetection/inspection-task")
@Validated
public class InspectionTaskController {

    @Resource
    private InspectionTaskService inspectionTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检任务派发与执行")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:create')")
    public CommonResult<Long> createInspectionTask(@Valid @RequestBody InspectionTaskSaveReqVO createReqVO) {
        return success(inspectionTaskService.createInspectionTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检任务派发与执行")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:update')")
    public CommonResult<Boolean> updateInspectionTask(@Valid @RequestBody InspectionTaskSaveReqVO updateReqVO) {
        inspectionTaskService.updateInspectionTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检任务派发与执行")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:delete')")
    public CommonResult<Boolean> deleteInspectionTask(@RequestParam("id") Long id) {
        inspectionTaskService.deleteInspectionTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检任务派发与执行")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:query')")
    public CommonResult<InspectionTaskRespVO> getInspectionTask(@RequestParam("id") Long id) {
        InspectionTaskDO inspectionTask = inspectionTaskService.getInspectionTask(id);
        return success(BeanUtils.toBean(inspectionTask, InspectionTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检任务派发与执行分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:query')")
    public CommonResult<PageResult<InspectionTaskRespVO>> getInspectionTaskPage(@Valid InspectionTaskPageReqVO pageReqVO) {
        PageResult<InspectionTaskDO> pageResult = inspectionTaskService.getInspectionTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检任务派发与执行 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:inspection-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionTaskExcel(@Valid InspectionTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionTaskDO> list = inspectionTaskService.getInspectionTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检任务派发与执行.xls", "数据", InspectionTaskRespVO.class,
                        BeanUtils.toBean(list, InspectionTaskRespVO.class));
    }

}