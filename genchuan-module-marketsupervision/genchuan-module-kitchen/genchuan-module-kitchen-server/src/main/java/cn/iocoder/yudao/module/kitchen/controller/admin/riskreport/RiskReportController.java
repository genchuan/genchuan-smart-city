package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;

import cn.iocoder.yudao.module.kitchen.vrv.utils.common.pdf.VrvPdfGenerator;
import cn.iocoder.yudao.module.kitchen.vrv.utils.procom.aop.sysope.SysOpeLog;
import cn.iocoder.yudao.module.kitchen.service.riskreport.RiskReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 企业风险评估报告")
@RestController
@RequestMapping("/kitchen/risk-report")
@Validated
public class RiskReportController {

    @Resource
    private RiskReportService riskReportService;
    @PostMapping("/export-single-enterprise-pdf")
    @Operation(summary = "单企业月报独立导出PDF")
    public void exportSingleEnterprisePdf(
            @RequestBody EntReportPageResp resp,
            HttpServletResponse response
    ) throws Exception {
        // 前端传 1 条 → 包装成 list → 直接用现有的工具类导出
        List<EntReportPageResp> list = Collections.singletonList(resp);
        VrvPdfGenerator.listExportPdf(
                response,
                EntReportPageResp.class,
                "企业月度评估报告",
                list
        );
    }
    @GetMapping("/export-list-pdf")
    @Operation(summary = "(目前推荐使用）列表导出PDF（通用：月报/自定义报表）")
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业风险报表", operType = "导出列表PDF")
    public void exportListPdf(@Valid EntReportPageReq pageReqVO, HttpServletResponse response) throws Exception {
        // 1. 查询数据（和Excel完全一样）
//        pageReqVO.setPageSize(9999);
        List<EntReportPageResp> list = riskReportService.getEntReportPage(pageReqVO).getList();

        // 2. 导出 PDF（列表格式，直接用Excel实体类）
        VrvPdfGenerator.listExportPdf(
                response,
                EntReportPageResp.class,  // 你现有的VO，直接用
                "企业风险报表",           // PDF标题
                list                     // 列表数据
        );
    }
    @GetMapping("/export-pdf")
    @Operation(summary = "(勿用)（可用但不推荐）导出 PDF",hidden = true)
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业风险评估报告", operType = "批量导出PDF")
    public ResponseEntity<byte[]> exportPdf(@Valid EntReportPageReq pageReqVO) {
        return riskReportService.exportRiskReportPdf(pageReqVO);
    }
    @GetMapping("/export-excel")
    @Operation(summary = "导出 Excel")
    //@PreAuthorize("@ss.hasPermission('kitchen:risk-report:export')")
    @ApiAccessLog(operateType = EXPORT)
//    @SysOpeLog(operObject = "企业风险评估报告",operType = "批量导出")
    public void exportRoadArchiveExcel(@Valid EntReportPageReq pageReqVO,
                                       HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "导出Excel文件_";

//        pageReqVO.setPageSize(pageReqVO.getPageSize()!=0? pageReqVO.getPageSize() : 3);
        List<EntReportPageResp> list = riskReportService.getEntReportPage(pageReqVO).getList();

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
        ExcelUtils.write(response, "归档.xls", "数据", EntReportPageResp.class,
                BeanUtils.toBean(list, EntReportPageResp.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获取分页-企业风险报表")
    //@PreAuthorize("@ss.hasPermission('kitchen:risk-report:query')")
    public CommonResult<PageResult<EntReportPageResp>> getEntReportPage(@Valid EntReportPageReq pageReqVO) {
        PageResult<EntReportPageResp> pageResult = riskReportService.getEntReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EntReportPageResp.class));
    }


}
