package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplateRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplateSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpttemplate.EvalRptTemplateDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpttemplate.EvalRptTemplateService;

@Tag(name = "管理后台 - 报告模板配置")
@RestController
@RequestMapping("/datacenter/eval-rpt-template")
@Validated
public class EvalRptTemplateController {

    @Resource
    private EvalRptTemplateService evalRptTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建报告模板配置")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:create')")
    public CommonResult<Long> createEvalRptTemplate(@Valid @RequestBody EvalRptTemplateSaveReqVO createReqVO) {
        return success(evalRptTemplateService.createEvalRptTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报告模板配置")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:update')")
    public CommonResult<Boolean> updateEvalRptTemplate(@Valid @RequestBody EvalRptTemplateSaveReqVO updateReqVO) {
        evalRptTemplateService.updateEvalRptTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报告模板配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:delete')")
    public CommonResult<Boolean> deleteEvalRptTemplate(@RequestParam("id") Long id) {
        evalRptTemplateService.deleteEvalRptTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报告模板配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:query')")
    public CommonResult<EvalRptTemplateRespVO> getEvalRptTemplate(@RequestParam("id") Long id) {
        EvalRptTemplateDO evalRptTemplate = evalRptTemplateService.getEvalRptTemplate(id);
        return success(BeanUtils.toBean(evalRptTemplate, EvalRptTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报告模板配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:query')")
    public CommonResult<PageResult<EvalRptTemplateRespVO>> getEvalRptTemplatePage(@Valid EvalRptTemplatePageReqVO pageReqVO) {
        PageResult<EvalRptTemplateDO> pageResult = evalRptTemplateService.getEvalRptTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRptTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报告模板配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRptTemplateExcel(@Valid EvalRptTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRptTemplateDO> list = evalRptTemplateService.getEvalRptTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报告模板配置.xls", "数据", EvalRptTemplateRespVO.class,
                        BeanUtils.toBean(list, EvalRptTemplateRespVO.class));
    }

}