package cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportChartRespVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportCreateReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo.CycleReportRespVO;
import cn.iocoder.yudao.module.ordertrade.service.orderreport.CycleReportService;
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

@Tag(name = "订单交易 - 订单交易报表 - 周期报表")
@RestController
@RequestMapping("/ordertrade/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @PostMapping("/create")
    @Operation(summary = "生成 - 手动生成自定义周期报表")
    public CommonResult<Long> createCycleReport(@Valid @RequestBody CycleReportCreateReqVO createReqVO) {
        return success(cycleReportService.createCycleReport(createReqVO));
    }

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 - 周期报表分页列表")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        return success(cycleReportService.getCycleReportPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "查看 - 单条周期报表详情")
    @Parameter(name = "id", description = "报表记录 ID", required = true)
    public CommonResult<CycleReportRespVO> getCycleReport(@RequestParam("id") Long id) {
        return success(cycleReportService.getCycleReport(id));
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 导出周期报表 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CycleReportRespVO> list = cycleReportService.getCycleReportPage(pageReqVO).getList();
        ExcelUtils.write(response, "周期报表.xls", "数据", CycleReportRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "图表 - 卡片+折线+柱状+饼图")
    @PreAuthorize("@ss.hasPermission('ordertrade:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getCycleReportChart(@Valid CycleReportChartReqVO chartReqVO) {
        return success(cycleReportService.getCycleReportChart(chartReqVO));
    }
}
