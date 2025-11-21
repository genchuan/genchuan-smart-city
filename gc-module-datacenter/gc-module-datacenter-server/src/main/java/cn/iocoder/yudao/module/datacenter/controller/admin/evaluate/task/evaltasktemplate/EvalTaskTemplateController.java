package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplateRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplateSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltasktemplate.EvalTaskTemplateDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltasktemplate.EvalTaskTemplateService;

@Tag(name = "管理后台 - 任务模板管理")
@RestController
@RequestMapping("/datacenter/eval-task-template")
@Validated
public class EvalTaskTemplateController {

    @Resource
    private EvalTaskTemplateService evalTaskTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建任务模板管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:create')")
    public CommonResult<Long> createEvalTaskTemplate(@Valid @RequestBody EvalTaskTemplateSaveReqVO createReqVO) {
        return success(evalTaskTemplateService.createEvalTaskTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新任务模板管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:update')")
    public CommonResult<Boolean> updateEvalTaskTemplate(@Valid @RequestBody EvalTaskTemplateSaveReqVO updateReqVO) {
        evalTaskTemplateService.updateEvalTaskTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除任务模板管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:delete')")
    public CommonResult<Boolean> deleteEvalTaskTemplate(@RequestParam("id") Long id) {
        evalTaskTemplateService.deleteEvalTaskTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得任务模板管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:query')")
    public CommonResult<EvalTaskTemplateRespVO> getEvalTaskTemplate(@RequestParam("id") Long id) {
        EvalTaskTemplateDO evalTaskTemplate = evalTaskTemplateService.getEvalTaskTemplate(id);
        return success(BeanUtils.toBean(evalTaskTemplate, EvalTaskTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得任务模板管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:query')")
    public CommonResult<PageResult<EvalTaskTemplateRespVO>> getEvalTaskTemplatePage(@Valid EvalTaskTemplatePageReqVO pageReqVO) {
        PageResult<EvalTaskTemplateDO> pageResult = evalTaskTemplateService.getEvalTaskTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalTaskTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出任务模板管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-task-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalTaskTemplateExcel(@Valid EvalTaskTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalTaskTemplateDO> list = evalTaskTemplateService.getEvalTaskTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "任务模板管理.xls", "数据", EvalTaskTemplateRespVO.class,
                        BeanUtils.toBean(list, EvalTaskTemplateRespVO.class));
    }

}