package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewRespVO;

import cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.caseview.MarketCaseViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 市监案件总览视图")
@RestController
@RequestMapping("/industry/market-case-view")
@Validated
public class MarketCaseViewController {
    @Resource
    private MarketCaseViewService marketCaseViewService;
    @GetMapping("/get")
    @Operation(summary = "获得市监案件总览视图")
    @PreAuthorize("@ss.hasPermission('industry:market-case-view:query')")
    public CommonResult<MarketCaseViewRespVO> getMarketCaseView(
            @Valid MarketCaseViewQueryReqVO marketCaseViewQueryReqVO
    ) {
        MarketCaseViewRespVO marketCaseViewRespVO = marketCaseViewService.getMarketCaseView(marketCaseViewQueryReqVO);
        return success(marketCaseViewRespVO);
    }


}
