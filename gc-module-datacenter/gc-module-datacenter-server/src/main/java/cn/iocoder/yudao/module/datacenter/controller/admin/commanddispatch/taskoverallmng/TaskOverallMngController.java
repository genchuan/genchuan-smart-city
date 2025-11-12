package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.taskoverallmng.vo.TaskOverallMngSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.taskoverallmng.TaskOverallMngDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.taskoverallmng.TaskOverallMngService;

@Tag(name = "管理后台 - 任务统筹管理")
@RestController
@RequestMapping("/datacenter/task-overall-mng")
@Validated
public class TaskOverallMngController {

    @Resource
    private TaskOverallMngService taskOverallMngService;

    @PostMapping("/create")
    @Operation(summary = "创建任务统筹管理")
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:create')")
    public CommonResult<Long> createTaskOverallMng(@Valid @RequestBody TaskOverallMngSaveReqVO createReqVO) {
        return success(taskOverallMngService.createTaskOverallMng(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务统筹管理")
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:update')")
    public CommonResult<Boolean> updateTaskOverallMng(@Valid @RequestBody TaskOverallMngSaveReqVO updateReqVO) {
        taskOverallMngService.updateTaskOverallMng(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务统筹管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:delete')")
    public CommonResult<Boolean> deleteTaskOverallMng(@RequestParam("id") Long id) {
        taskOverallMngService.deleteTaskOverallMng(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务统筹管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:query')")
    public CommonResult<TaskOverallMngRespVO> getTaskOverallMng(@RequestParam("id") Long id) {
        TaskOverallMngDO taskOverallMng = taskOverallMngService.getTaskOverallMng(id);
        return success(BeanUtils.toBean(taskOverallMng, TaskOverallMngRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务统筹管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:query')")
    public CommonResult<PageResult<TaskOverallMngRespVO>> getTaskOverallMngPage(@Valid TaskOverallMngPageReqVO pageReqVO) {
        PageResult<TaskOverallMngDO> pageResult = taskOverallMngService.getTaskOverallMngPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskOverallMngRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务统筹管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:task-overall-mng:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskOverallMngExcel(@Valid TaskOverallMngPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskOverallMngDO> list = taskOverallMngService.getTaskOverallMngPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务统筹管理.xls", "数据", TaskOverallMngRespVO.class,
                        BeanUtils.toBean(list, TaskOverallMngRespVO.class));
    }

}