package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.coremetrics.MarketCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 市监核心指标")
@RestController
@RequestMapping("/industry/market-core-metrics")
@Validated
public class MarketCoreMetricsController {
    @Resource
    private MarketCoreMetricsService marketCoreMetricsService;
    @GetMapping("/get")
    @Operation(summary = "获得市监核心指标")
    @PreAuthorize("@ss.hasPermission('industry:market-core-metrics:query')")
    public CommonResult<MarketCoreMetricsRespVO> getMarketCoreMetrics(
            @Valid MarketCoreMetricsQueryReqVO marketCoreMetricsQueryReqVO
    ) {
        MarketCoreMetricsRespVO marketCoreMetricsRespVO = marketCoreMetricsService.getMarketCoreMetrics(marketCoreMetricsQueryReqVO);
        return success(marketCoreMetricsRespVO);
    }


}
