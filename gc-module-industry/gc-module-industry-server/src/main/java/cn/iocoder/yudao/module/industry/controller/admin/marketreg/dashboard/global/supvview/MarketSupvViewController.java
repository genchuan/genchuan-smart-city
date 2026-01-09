package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewRespVO;

import cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.supvview.MarketSupvViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 市监对象分布视图")
@RestController
@RequestMapping("/industry/market-supv-view")
@Validated
public class MarketSupvViewController {
    @Resource
    private MarketSupvViewService marketSupvViewService;
    @GetMapping("/get")
    @Operation(summary = "获得监管对象分布视图")
    @PreAuthorize("@ss.hasPermission('industry:market-supv-view:query')")
    public CommonResult<MarketSupvViewRespVO> getMarketSupvView(
            @Valid MarketSupvViewQueryReqVO marketSupvViewQueryReqVO
    ) {
        MarketSupvViewRespVO marketSupvViewRespVO = marketSupvViewService.getMarketSupvView(marketSupvViewQueryReqVO);
        return success(marketSupvViewRespVO);
    }


}
