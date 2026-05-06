package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.drill.CycleReportDrillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 周期报表钻取")
@RestController
@RequestMapping("/marketop/cycle-report/drill")
public class CycleReportDrillController {

    @Resource
    private CycleReportDrillService cycleReportDrillService;

    @GetMapping("/report-cycle")
    @Operation(summary = "报表周期钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillReportCycleRespVO>> drillReportCycle(@Valid CycleReportDrillReportCycleReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillReportCycle(reqVO));
    }

    @GetMapping("/activity-count")
    @Operation(summary = "活动数钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillActivityCountRespVO>> drillActivityCount(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillActivityCount(reqVO));
    }

    @GetMapping("/join-user-count")
    @Operation(summary = "参与用户数钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillJoinUserCountRespVO>> drillJoinUserCount(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillJoinUserCount(reqVO));
    }

    @GetMapping("/lottery-count")
    @Operation(summary = "抽奖量钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillLotteryCountRespVO>> drillLotteryCount(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillLotteryCount(reqVO));
    }

    @GetMapping("/winning-rate")
    @Operation(summary = "中奖率钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillWinningRateRespVO>> drillWinningRate(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillWinningRate(reqVO));
    }

    @GetMapping("/coupon-send-count")
    @Operation(summary = "优惠券发放量钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillCouponSendCountRespVO>> drillCouponSendCount(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillCouponSendCount(reqVO));
    }

    @GetMapping("/verify-rate")
    @Operation(summary = "核销率钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillVerifyRateRespVO>> drillVerifyRate(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillVerifyRate(reqVO));
    }

    @GetMapping("/card-order-count")
    @Operation(summary = "卡种订单量钻取")
    @PreAuthorize("@ss.hasPermission('marketop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportDrillCardOrderCountRespVO>> drillCardOrderCount(@Valid CycleReportDrillBaseReqVO reqVO) {
        return CommonResult.success(cycleReportDrillService.drillCardOrderCount(reqVO));
    }

}
