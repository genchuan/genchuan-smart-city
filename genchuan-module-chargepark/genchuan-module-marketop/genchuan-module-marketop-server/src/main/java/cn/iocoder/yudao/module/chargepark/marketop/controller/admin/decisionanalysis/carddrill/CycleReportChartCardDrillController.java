package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.carddrill.CycleReportChartCardDrillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 周期报表图表卡片钻取")
@RestController
@RequestMapping("/marketop/cycle-report/chart/card-drill")
public class CycleReportChartCardDrillController {

    @Resource
    private CycleReportChartCardDrillService cycleReportChartCardDrillService;

    @GetMapping("/activity-count")
    @Operation(summary = "活动数卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillActivityCountRespVO>> drillActivityCount(@Valid CycleReportChartCardDrillActivityCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillActivityCount(reqVO));
    }

    @GetMapping("/join-user-count")
    @Operation(summary = "参与用户数卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillJoinUserCountRespVO>> drillJoinUserCount(@Valid CycleReportChartCardDrillJoinUserCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillJoinUserCount(reqVO));
    }

    @GetMapping("/lottery-count")
    @Operation(summary = "抽奖量卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillLotteryCountRespVO>> drillLotteryCount(@Valid CycleReportChartCardDrillLotteryCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillLotteryCount(reqVO));
    }

    @GetMapping("/winning-rate")
    @Operation(summary = "中奖率卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillWinningRateRespVO>> drillWinningRate(@Valid CycleReportChartCardDrillWinningRateReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillWinningRate(reqVO));
    }

    @GetMapping("/coupon-send-count")
    @Operation(summary = "优惠券发放量卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillCouponSendCountRespVO>> drillCouponSendCount(@Valid CycleReportChartCardDrillCouponSendCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillCouponSendCount(reqVO));
    }

    @GetMapping("/verify-rate")
    @Operation(summary = "核销率卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillVerifyRateRespVO>> drillVerifyRate(@Valid CycleReportChartCardDrillVerifyRateReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillVerifyRate(reqVO));
    }

    @GetMapping("/card-order-count")
    @Operation(summary = "卡种订单量卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillCardOrderCountRespVO>> drillCardOrderCount(@Valid CycleReportChartCardDrillCardOrderCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillCardOrderCount(reqVO));
    }

    @GetMapping("/revenue")
    @Operation(summary = "营收卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillRevenueRespVO>> drillRevenue(@Valid CycleReportChartCardDrillRevenueReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillRevenue(reqVO));
    }

    @GetMapping("/exchange-count")
    @Operation(summary = "兑换量卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillExchangeCountRespVO>> drillExchangeCount(@Valid CycleReportChartCardDrillExchangeCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillExchangeCount(reqVO));
    }

    @GetMapping("/total-stock")
    @Operation(summary = "总库存卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillTotalStockRespVO>> drillTotalStock(@Valid CycleReportChartCardDrillTotalStockReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillTotalStock(reqVO));
    }

    @GetMapping("/warn-stock-count")
    @Operation(summary = "预警库存数卡片钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportChartCardDrillWarnStockCountRespVO>> drillWarnStockCount(@Valid CycleReportChartCardDrillWarnStockCountReqVO reqVO) {
        return CommonResult.success(cycleReportChartCardDrillService.drillWarnStockCount(reqVO));
    }

}
