package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.MarketOpReportDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.marketopreport.MarketOpReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 营销运营报表")
@RestController
@RequestMapping("/marketop/market-op-report")
public class MarketOpReportController {

    @Resource
    private MarketOpReportService marketOpReportService;

    @GetMapping("/page")
    @Operation(summary = "获得营销运营报表分页")
    @PreAuthorize("@ss.hasPermission('marketop:market-op-report:query')")
    public CommonResult<PageResult<MarketOpReportRespVO>> getPage(MarketOpReportPageReqVO reqVO) {
        PageResult<MarketOpReportDO> pageResult = marketOpReportService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, MarketOpReportRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得营销运营报表详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:market-op-report:query')")
    public CommonResult<MarketOpReportRespVO> get(@RequestParam("id") Long id) {
        MarketOpReportDO report = marketOpReportService.get(id);
        return CommonResult.success(BeanUtils.toBean(report, MarketOpReportRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出营销运营报表")
    @PreAuthorize("@ss.hasPermission('marketop:market-op-report:query')")
    public void export(MarketOpReportPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<MarketOpReportDO> pageResult = marketOpReportService.getPage(reqVO);
        List<MarketOpReportRespVO> list = BeanUtils.toBean(pageResult.getList(), MarketOpReportRespVO.class);
        ExcelUtils.write(response, "营销运营报表.xlsx", "数据", MarketOpReportRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "营销运营分析图表")
    @Parameter(name = "reportId", description = "报表ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:market-op-report:query')")
    public CommonResult<MarketOpReportChartRespVO> getChart(@RequestParam("reportId") Long reportId) {
        return CommonResult.success(marketOpReportService.getChart(reportId));
    }

}
