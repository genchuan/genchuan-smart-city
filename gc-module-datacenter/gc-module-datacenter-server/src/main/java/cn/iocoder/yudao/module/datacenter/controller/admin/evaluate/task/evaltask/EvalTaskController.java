package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltask.EvalTaskDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltask.EvalTaskService;

@Tag(name = "管理后台 - 任务管理")
@RestController
@RequestMapping("/datacenter/eval-task")
@Validated
public class EvalTaskController {

    @Resource
    private EvalTaskService evalTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建任务管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:create')")
    public CommonResult<Long> createEvalTask(@Valid @RequestBody EvalTaskSaveReqVO createReqVO) {
        return success(evalTaskService.createEvalTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:update')")
    public CommonResult<Boolean> updateEvalTask(@Valid @RequestBody EvalTaskSaveReqVO updateReqVO) {
        evalTaskService.updateEvalTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:delete')")
    public CommonResult<Boolean> deleteEvalTask(@RequestParam("id") Long id) {
        evalTaskService.deleteEvalTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:query')")
    public CommonResult<EvalTaskRespVO> getEvalTask(@RequestParam("id") Long id) {
        EvalTaskDO evalTask = evalTaskService.getEvalTask(id);
        return success(BeanUtils.toBean(evalTask, EvalTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:query')")
    public CommonResult<PageResult<EvalTaskRespVO>> getEvalTaskPage(@Valid EvalTaskPageReqVO pageReqVO) {
        PageResult<EvalTaskDO> pageResult = evalTaskService.getEvalTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalTaskExcel(@Valid EvalTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalTaskDO> list = evalTaskService.getEvalTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务管理.xls", "数据", EvalTaskRespVO.class,
                        BeanUtils.toBean(list, EvalTaskRespVO.class));
    }

}