package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.carddrill;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo.*;

public interface CycleReportChartCardDrillService {

    PageResult<CycleReportChartCardDrillActivityCountRespVO> drillActivityCount(CycleReportChartCardDrillActivityCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillJoinUserCountRespVO> drillJoinUserCount(CycleReportChartCardDrillJoinUserCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillLotteryCountRespVO> drillLotteryCount(CycleReportChartCardDrillLotteryCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillWinningRateRespVO> drillWinningRate(CycleReportChartCardDrillWinningRateReqVO reqVO);

    PageResult<CycleReportChartCardDrillCouponSendCountRespVO> drillCouponSendCount(CycleReportChartCardDrillCouponSendCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillVerifyRateRespVO> drillVerifyRate(CycleReportChartCardDrillVerifyRateReqVO reqVO);

    PageResult<CycleReportChartCardDrillCardOrderCountRespVO> drillCardOrderCount(CycleReportChartCardDrillCardOrderCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillRevenueRespVO> drillRevenue(CycleReportChartCardDrillRevenueReqVO reqVO);

    PageResult<CycleReportChartCardDrillExchangeCountRespVO> drillExchangeCount(CycleReportChartCardDrillExchangeCountReqVO reqVO);

    PageResult<CycleReportChartCardDrillTotalStockRespVO> drillTotalStock(CycleReportChartCardDrillTotalStockReqVO reqVO);

    PageResult<CycleReportChartCardDrillWarnStockCountRespVO> drillWarnStockCount(CycleReportChartCardDrillWarnStockCountReqVO reqVO);

}
