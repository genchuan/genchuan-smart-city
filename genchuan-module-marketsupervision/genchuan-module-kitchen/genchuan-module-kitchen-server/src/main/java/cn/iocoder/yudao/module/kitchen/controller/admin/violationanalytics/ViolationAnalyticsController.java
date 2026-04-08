package cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.drill.ViolationAnalyticsDrillResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.violationanalytics.vo.page.ViolationAnalyticsPageResp;
import cn.iocoder.yudao.module.kitchen.service.violationanalytics.ViolationAnalyticsService;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.excel.VrvExcelUtils;
import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import cn.iocoder.yudao.module.kitchen.vrv.utils.procom.aop.sysope.SysOpeLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 企业违规数据分析")
@RestController
@RequestMapping("/kitchen/violation-analytics")
@Validated
public class ViolationAnalyticsController {
    @Resource
    private ViolationAnalyticsService violationAnalyticsService;

    @GetMapping("/export-excel2")
    @Operation(summary = "(勿用）导出 Excel2",hidden = true)
    //@PreAuthorize("@ss.hasPermission('kitchen:violation-analytics:export')")
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业违规数据分析",operType = "批量导出")
    public void exportRoadArchiveExcel2(@Valid ViolationAnalyticsPageReq pageReqVO,
                                       HttpServletResponse response) throws Exception {
        List<ViolationAnalyticsPageResp> list = violationAnalyticsService.getViolationAnalyticsPage(pageReqVO).getList();
        VrvExcelUtils.listExportExcelSimple(response,list);
    }
    @GetMapping("/export-list-pdf")
    @Operation(summary = "(目前推荐使用）列表导出PDF（通用：月报/自定义报表）")
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业风险报表", operType = "导出列表PDF")
    public void exportListPdf(@Valid ViolationAnalyticsPageReq pageReqVO, HttpServletResponse response) throws Exception {
        // 1. 查询数据（和Excel完全一样）
//        pageReqVO.setPageSize(9999);
        List<ViolationAnalyticsPageResp> list = violationAnalyticsService.getViolationAnalyticsPage(pageReqVO).getList();

        // 2. 导出 PDF（列表格式，直接用Excel实体类）
        VrvPdfGenerator.listExportPdf(
                response,
                ViolationAnalyticsPageResp.class,  // 现有的VO，直接用
                "企业违规数据分析",           // PDF标题
                list                     // 列表数据
        );
    }
    @GetMapping("/export-excel")
    @Operation(summary = "导出 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:violation-analytics:export')")
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业违规数据分析",operType = "批量导出")
    public void exportRoadArchiveExcel(@Valid ViolationAnalyticsPageReq pageReqVO,
                                       HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "导出Excel文件_";

//        pageReqVO.setPageSize(pageReqVO.getPageSize()!=0? pageReqVO.getPageSize() : 3);
        List<ViolationAnalyticsPageResp> list = violationAnalyticsService.getViolationAnalyticsPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString(); // 例如 "2026-03-10"
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils.write（保持原方法不改）
        ExcelUtils.write(response, "归档.xls", "数据", ViolationAnalyticsPageResp.class,
                BeanUtils.toBean(list, ViolationAnalyticsPageResp.class));
    }
    @GetMapping("/drill")
    @Operation(summary = "钻取-企业违规数据分析")
    //@PreAuthorize("@ss.hasPermission('kitchen:violation-analytics:query')")
    public CommonResult<ViolationAnalyticsDrillResp> getViolationAnalyticsDrill(@Valid ViolationAnalyticsDrillReq req) {
        ViolationAnalyticsDrillResp drillResult = violationAnalyticsService.getViolationAnalyticsDrill(req);
        return success(BeanUtils.toBean(drillResult, ViolationAnalyticsDrillResp.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获取分页-企业违规数据分析")
    //@PreAuthorize("@ss.hasPermission('kitchen:violation-analytics:query')")
    public CommonResult<PageResult<ViolationAnalyticsPageResp>> getViolationAnalyticsPage(@Valid ViolationAnalyticsPageReq pageReqVO) {
        PageResult<ViolationAnalyticsPageResp> pageResult = violationAnalyticsService.getViolationAnalyticsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ViolationAnalyticsPageResp.class));
    }
}
