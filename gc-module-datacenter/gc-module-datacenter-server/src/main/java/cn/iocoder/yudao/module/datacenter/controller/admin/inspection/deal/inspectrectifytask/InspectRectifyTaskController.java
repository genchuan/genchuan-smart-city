package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytask.InspectRectifyTaskDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytask.InspectRectifyTaskService;

@Tag(name = "管理后台 - 巡查巡检整改任务派发")
@RestController
@RequestMapping("/datacenter/inspect-rectify-task")
@Validated
public class InspectRectifyTaskController {

    @Resource
    private InspectRectifyTaskService inspectRectifyTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检整改任务派发")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:create')")
    public CommonResult<Long> createInspectRectifyTask(@Valid @RequestBody InspectRectifyTaskSaveReqVO createReqVO) {
        return success(inspectRectifyTaskService.createInspectRectifyTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检整改任务派发")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:update')")
    public CommonResult<Boolean> updateInspectRectifyTask(@Valid @RequestBody InspectRectifyTaskSaveReqVO updateReqVO) {
        inspectRectifyTaskService.updateInspectRectifyTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检整改任务派发")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:delete')")
    public CommonResult<Boolean> deleteInspectRectifyTask(@RequestParam("id") Long id) {
        inspectRectifyTaskService.deleteInspectRectifyTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检整改任务派发")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:query')")
    public CommonResult<InspectRectifyTaskRespVO> getInspectRectifyTask(@RequestParam("id") Long id) {
        InspectRectifyTaskDO inspectRectifyTask = inspectRectifyTaskService.getInspectRectifyTask(id);
        return success(BeanUtils.toBean(inspectRectifyTask, InspectRectifyTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检整改任务派发分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:query')")
    public CommonResult<PageResult<InspectRectifyTaskRespVO>> getInspectRectifyTaskPage(@Valid InspectRectifyTaskPageReqVO pageReqVO) {
        PageResult<InspectRectifyTaskDO> pageResult = inspectRectifyTaskService.getInspectRectifyTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRectifyTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检整改任务派发 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRectifyTaskExcel(@Valid InspectRectifyTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRectifyTaskDO> list = inspectRectifyTaskService.getInspectRectifyTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检整改任务派发.xls", "数据", InspectRectifyTaskRespVO.class,
                        BeanUtils.toBean(list, InspectRectifyTaskRespVO.class));
    }

}