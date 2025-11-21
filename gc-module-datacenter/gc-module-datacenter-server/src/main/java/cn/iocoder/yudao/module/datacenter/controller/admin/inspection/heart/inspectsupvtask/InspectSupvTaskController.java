package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtask.InspectSupvTaskDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtask.InspectSupvTaskService;

@Tag(name = "管理后台 - 巡查巡检重点督办任务")
@RestController
@RequestMapping("/datacenter/inspect-supv-task")
@Validated
public class InspectSupvTaskController {

    @Resource
    private InspectSupvTaskService inspectSupvTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检重点督办任务")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:create')")
    public CommonResult<Long> createInspectSupvTask(@Valid @RequestBody InspectSupvTaskSaveReqVO createReqVO) {
        return success(inspectSupvTaskService.createInspectSupvTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检重点督办任务")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:update')")
    public CommonResult<Boolean> updateInspectSupvTask(@Valid @RequestBody InspectSupvTaskSaveReqVO updateReqVO) {
        inspectSupvTaskService.updateInspectSupvTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检重点督办任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:delete')")
    public CommonResult<Boolean> deleteInspectSupvTask(@RequestParam("id") Long id) {
        inspectSupvTaskService.deleteInspectSupvTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检重点督办任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:query')")
    public CommonResult<InspectSupvTaskRespVO> getInspectSupvTask(@RequestParam("id") Long id) {
        InspectSupvTaskDO inspectSupvTask = inspectSupvTaskService.getInspectSupvTask(id);
        return success(BeanUtils.toBean(inspectSupvTask, InspectSupvTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检重点督办任务分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:query')")
    public CommonResult<PageResult<InspectSupvTaskRespVO>> getInspectSupvTaskPage(@Valid InspectSupvTaskPageReqVO pageReqVO) {
        PageResult<InspectSupvTaskDO> pageResult = inspectSupvTaskService.getInspectSupvTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectSupvTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检重点督办任务 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectSupvTaskExcel(@Valid InspectSupvTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectSupvTaskDO> list = inspectSupvTaskService.getInspectSupvTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检重点督办任务.xls", "数据", InspectSupvTaskRespVO.class,
                        BeanUtils.toBean(list, InspectSupvTaskRespVO.class));
    }

}