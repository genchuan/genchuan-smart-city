package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartBarDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartLineDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartPieDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.chart.CycleReportChartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "管理后台 - 周期报表图表钻取")
@RestController
@RequestMapping("/marketop/cycle-report/chart")
public class CycleReportChartController {

    @Resource
    private CycleReportChartService cycleReportChartService;

    @GetMapping("/line-drill")
    @Operation(summary = "折线图数据点跳转钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<Map<String, Object>>> lineDrill(@Valid CycleReportChartLineDrillReqVO reqVO) {
        return CommonResult.success(cycleReportChartService.lineDrill(reqVO));
    }

    @GetMapping("/bar-drill")
    @Operation(summary = "柱状图柱形跳转钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<Map<String, Object>>> barDrill(@Valid CycleReportChartBarDrillReqVO reqVO) {
        return CommonResult.success(cycleReportChartService.barDrill(reqVO));
    }

    @GetMapping("/pie-drill")
    @Operation(summary = "饼图扇区跳转钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<Map<String, Object>>> pieDrill(@Valid CycleReportChartPieDrillReqVO reqVO) {
        return CommonResult.success(cycleReportChartService.pieDrill(reqVO));
    }

}
