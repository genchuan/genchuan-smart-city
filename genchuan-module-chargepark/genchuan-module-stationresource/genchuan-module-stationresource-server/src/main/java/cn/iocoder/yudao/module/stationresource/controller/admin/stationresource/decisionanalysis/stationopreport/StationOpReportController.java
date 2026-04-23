package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops.StationOpReportChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops.StationReportQueryDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.decisionanalysis.stationopreport.StationOpReportDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.decisionanalysis.stationopreport.StationOpReportService;
import io.swagger.v3.oas.annotations.Hidden;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 场站运营报表")
@RestController
@RequestMapping("/stationresource/station-op-report")
@Validated
//@Hidden
public class StationOpReportController {

    @Resource
    private StationOpReportService stationOpReportService;

    //============================新版报表======================================================
    /**
     * 万能综合统计报表（一套接口支持所有周期）
     */
//    @PostMapping("/statistics")
//    public AjaxResult statistics(@RequestBody StationReportQueryDTO dto) {
//        return AjaxResult.success(stationReportService.generalReportStatistics(dto));
//    }



    // ==================== 列表查询 ====================
//    @GetMapping("/page")
//    @Operation(summary = "获得场站运营报表分页")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:query')")
//    public CommonResult<PageResult<StationOpReportRespVO>> getStationOpReportPage(StationOpReportPageReqVO reqVO) {
//        return CommonResult.success(stationOpReportService.getReportPage(reqVO));
//    }
    @GetMapping("/chart")
    @Operation(summary = "场站运营分析图表（折线+柱状+卡片）")
    @Parameter(name = "reportId", description = "报表ID", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:query')")
    public CommonResult<StationOpReportChartRespVO> getReportChart(@RequestParam Long reportId) {
        StationOpReportChartRespVO chart = stationOpReportService.getReportChart(reportId);
        return success(chart);
    }

    @GetMapping("/page")
    @Operation(summary = "获取场站运营报表分页")
    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:query')")
    public CommonResult<PageResult<StationOpReportRespVO>> getReportPage(@Valid StationOpReportPageReqVO pageReqVO) {
        PageResult<StationOpReportDO> pageResult = stationOpReportService.getReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StationOpReportRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获取报表详情")
    @Parameter(name = "id", description = "报表ID", required = true)
    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:query')")
    public CommonResult<StationOpReportRespVO> getReport(@RequestParam("id") Long id) {
        StationOpReportDO report = stationOpReportService.getReport(id);
        return success(BeanUtils.toBean(report, StationOpReportRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场站运营报表 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportExcel(@Valid StationOpReportPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "场站运营报表_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StationOpReportDO> list = stationOpReportService.getReportPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils.write
        ExcelUtils.write(response, "场站运营报表.xls", "数据", StationOpReportRespVO.class,
                BeanUtils.toBean(list, StationOpReportRespVO.class));
    }
}
