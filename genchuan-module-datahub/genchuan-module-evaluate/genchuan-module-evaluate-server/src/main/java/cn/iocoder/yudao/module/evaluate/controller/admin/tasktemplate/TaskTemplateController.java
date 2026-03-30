package cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.tasktemplate.vo.TaskTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.tasktemplate.TaskTemplateDO;
import cn.iocoder.yudao.module.evaluate.service.tasktemplate.TaskTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价任务执行 - 评价任务模板")
@RestController
@RequestMapping("/evaluate/task-template")
@Validated
@Slf4j
public class TaskTemplateController {

    @Resource
    private TaskTemplateService taskTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建评价任务模板")
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:create')")
    public CommonResult<Long> createTaskTemplate(@Valid @RequestBody TaskTemplateSaveReqVO createReqVO) {
        return success(taskTemplateService.createTaskTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价任务模板")
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:update')")
    public CommonResult<Boolean> updateTaskTemplate(@Valid @RequestBody TaskTemplateSaveReqVO updateReqVO) {
        taskTemplateService.updateTaskTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价任务模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:delete')")
    public CommonResult<Boolean> deleteTaskTemplate(@RequestParam("id") Long id) {
        taskTemplateService.deleteTaskTemplate(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除评价任务模板")
                @PreAuthorize("@ss.hasPermission('evaluate:task-template:delete')")
    public CommonResult<Boolean> deleteTaskTemplateList(@RequestParam("ids") List<Long> ids) {
        taskTemplateService.deleteTaskTemplateListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价任务模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:query')")
    public CommonResult<TaskTemplateRespVO> getTaskTemplate(@RequestParam("id") Long id) {
        TaskTemplateDO taskTemplate = taskTemplateService.getTaskTemplate(id);
        return success(BeanUtils.toBean(taskTemplate, TaskTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价任务模板分页")
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:query')")
    public CommonResult<PageResult<TaskTemplateRespVO>> getTaskTemplatePage(@Valid TaskTemplatePageReqVO pageReqVO) {
        PageResult<TaskTemplateDO> pageResult = taskTemplateService.getTaskTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TaskTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价任务模板 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:task-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTaskTemplateExcel(@Valid TaskTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TaskTemplateDO> list = taskTemplateService.getTaskTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价任务模板.xls", "数据", TaskTemplateRespVO.class,
                        BeanUtils.toBean(list, TaskTemplateRespVO.class));
    }
//----------------------------------------xin

    /**
     * 分页查询评价任务模板
     *
     * @param reqVO 分页查询条件
     * @return 分页结果（包含模板基础信息+关联表名称字段）
     */
    @Operation(summary = "分页查询评价任务模板", description = "支持模板名称、编码、各关联ID等筛选，返回包含关联表名称的完整信息")
    @GetMapping("/pagefenye")
    public CommonResult<PageResult<TaskTemplateRespVO>> getTaskTemplatePageList(@Validated TaskTemplatePageReqVO reqVO) {
        log.info("分页查询评价任务模板，请求参数：{}", reqVO);
        PageResult<TaskTemplateRespVO> pageResult = taskTemplateService.getTaskTemplatePagelian(reqVO);
        log.info("分页查询评价任务模板成功，总条数：{}", pageResult.getTotal());
        return success(pageResult);
    }
}