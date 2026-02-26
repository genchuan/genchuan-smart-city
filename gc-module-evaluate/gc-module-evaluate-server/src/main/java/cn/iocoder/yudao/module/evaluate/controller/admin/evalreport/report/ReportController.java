package cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo.ReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.report.ReportDO;
import cn.iocoder.yudao.module.evaluate.service.report.ReportService;
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

@Tag(name = "管理后台 - 评价报告")
@RestController
@RequestMapping("/evaluate/report")
@Validated
public class ReportController {

    @Resource
    private ReportService reportService;

    @PostMapping("/create")
    @Operation(summary = "创建评价报告")
    @PreAuthorize("@ss.hasPermission('evaluate:report:create')")
    public CommonResult<Long> createReport(@Valid @RequestBody ReportSaveReqVO createReqVO) {
        return success(reportService.createReport(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价报告")
    @PreAuthorize("@ss.hasPermission('evaluate:report:update')")
    public CommonResult<Boolean> updateReport(@Valid @RequestBody ReportSaveReqVO updateReqVO) {
        reportService.updateReport(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价报告")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:report:delete')")
    public CommonResult<Boolean> deleteReport(@RequestParam("id") Long id) {
        reportService.deleteReport(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价报告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:report:query')")
    public CommonResult<ReportRespVO> getReport(@RequestParam("id") Long id) {
        ReportDO report = reportService.getReport(id);
        return success(BeanUtils.toBean(report, ReportRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价报告分页")
    @PreAuthorize("@ss.hasPermission('evaluate:report:query')")
    public CommonResult<PageResult<ReportRespVO>> getReportPage(@Valid ReportPageReqVO pageReqVO) {
        PageResult<ReportDO> pageResult = reportService.getReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReportRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价报告 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReportExcel(@Valid ReportPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReportDO> list = reportService.getReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价报告.xls", "数据", ReportRespVO.class,
                        BeanUtils.toBean(list, ReportRespVO.class));
    }

}