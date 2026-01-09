package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewRespVO;

import cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.overview.MarketOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 市监全域数据地图")
@RestController
@RequestMapping("/industry/market-overview")
@Validated
public class MarketOverviewController {
    @Resource
    private MarketOverviewService marketOverviewService;
    @GetMapping("/get")
    @Operation(summary = "获得市监全域数据地图")
    @PreAuthorize("@ss.hasPermission('industry:market-overview:query')")
    public CommonResult<MarketOverviewRespVO> getMarketOverview(
            @Valid MarketOverviewQueryReqVO marketOverviewQueryReqVO
    ) {
        MarketOverviewRespVO marketOverviewRespVO = marketOverviewService.getMarketOverview(marketOverviewQueryReqVO);
        return success(marketOverviewRespVO);
    }


}
