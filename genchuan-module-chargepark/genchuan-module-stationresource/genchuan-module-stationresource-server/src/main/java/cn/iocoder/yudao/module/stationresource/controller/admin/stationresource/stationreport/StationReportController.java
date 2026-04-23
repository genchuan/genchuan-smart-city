package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.stationreport.StationReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 场站资源报表")
@RestController
@RequestMapping("/stationresource/station-op-report")
@Validated
public class StationReportController {

    @Resource
    private StationReportService stationReportService;


    @GetMapping("/chart")
    @Operation(summary = "获取场站资源周期报表图表数据", description = "适配Echarts全维度可视化数据")
    @PreAuthorize("@ss.hasPermission('stationresource:station-op-report:chart')")
    public CommonResult<StationOpReportChartRespVO> getReportChartData(@Validated StationOpReportChartReqVO reqVO) {
        StationOpReportChartRespVO respVO=stationReportService.getReportChartData(reqVO);
        return CommonResult.success(respVO);
    }
    @PostMapping("/create")
    @Operation(summary = "生成场站资源报表")
    @PreAuthorize("@ss.hasPermission('stationresource:station-report:create')")
    public CommonResult<Long> createReport(@Valid @RequestBody StationOpReportCreateReqVO reqVO) {
        Long id =   stationReportService.addReport(reqVO);
        return success(id);
    }
    @GetMapping("/page2")
    @Operation(summary = "2-获得场站资源报表分页",hidden = true)
    @PreAuthorize("@ss.hasPermission('stationresource:station-report:query')")
    public CommonResult<StationReportDO> getReportPage2(@Valid StationOpReportCreateReqVO pageReqVO) {
        StationReportDO pageResult = stationReportService.getReportPage2(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/page")
    @Operation(summary = "获得场站资源报表分页")
    @PreAuthorize("@ss.hasPermission('stationresource:station-report:query')")
    public CommonResult<PageResult<StationReportRespVO>> getReportPage(@Valid StationOpReportPageReqVO pageReqVO) {
        PageResult<StationReportDO> pageResult = stationReportService.getPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StationReportRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出场站资源报表 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:station-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReportExcel(@Valid StationOpReportPageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "场站资源报表_";

        // 不分页，查询全部数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StationReportDO> list = stationReportService.getPage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");

        // 2、动态生成文件名，带上当前日期
        String dateStr = LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "场站资源报表.xls", "数据", StationReportRespVO.class,
                BeanUtils.toBean(list, StationReportRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得场站资源报表详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:station-report:query')")
    public CommonResult<StationReportRespVO> getReport(@RequestParam("id") Long id) {
        StationReportDO report = stationReportService.getReport(id);
        return success(BeanUtils.toBean(report, StationReportRespVO.class));
    }
    //================================上面是最新的==================================================








//    @GetMapping("/chart")
//    @Operation(summary = "场站资源报表数据可视化（图表）")
//    @PreAuthorize("@ss.hasPermission('stationresource:station-report:chart')")
//    public CommonResult<StationReportChartRespVO> getReportChart(@Valid StationReportChartReqVO reqVO) {
//        StationReportChartRespVO chartData = stationReportService.getReportChart(reqVO);
//        return success(chartData);
//    }

}
