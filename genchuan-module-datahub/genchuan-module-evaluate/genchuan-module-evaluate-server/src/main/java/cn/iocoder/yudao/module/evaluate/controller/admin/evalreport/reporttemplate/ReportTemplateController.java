package cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplatePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplateRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo.ReportTemplateSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.reporttemplate.ReportTemplateDO;
import cn.iocoder.yudao.module.evaluate.service.reporttemplate.ReportTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 报告模板")
@RestController
@RequestMapping("/evaluate/report-template")
@Validated
public class ReportTemplateController {

    @Resource
    private ReportTemplateService reportTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建报告模板")
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:create')")
    public CommonResult<Long> createReportTemplate(@Valid @RequestBody ReportTemplateSaveReqVO createReqVO) {
        return success(reportTemplateService.createReportTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报告模板")
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:update')")
    public CommonResult<Boolean> updateReportTemplate(@Valid @RequestBody ReportTemplateSaveReqVO updateReqVO) {
        reportTemplateService.updateReportTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报告模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:delete')")
    public CommonResult<Boolean> deleteReportTemplate(@RequestParam("id") Long id) {
        reportTemplateService.deleteReportTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报告模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:query')")
    public CommonResult<ReportTemplateRespVO> getReportTemplate(@RequestParam("id") Long id) {
        ReportTemplateDO reportTemplate = reportTemplateService.getReportTemplate(id);
        return success(BeanUtils.toBean(reportTemplate, ReportTemplateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报告模板分页")
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:query')")
    public CommonResult<PageResult<ReportTemplateRespVO>> getReportTemplatePage(@Valid ReportTemplatePageReqVO pageReqVO) {
        PageResult<ReportTemplateDO> pageResult = reportTemplateService.getReportTemplatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReportTemplateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报告模板 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:report-template:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReportTemplateExcel(@Valid ReportTemplatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReportTemplateDO> list = reportTemplateService.getReportTemplatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报告模板.xls", "数据", ReportTemplateRespVO.class,
                        BeanUtils.toBean(list, ReportTemplateRespVO.class));
    }

}