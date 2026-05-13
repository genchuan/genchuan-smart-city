package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.drill;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo.*;

public interface CycleReportDrillService {

    PageResult<CycleReportDrillReportCycleRespVO> drillReportCycle(CycleReportDrillReportCycleReqVO reqVO);

    PageResult<CycleReportDrillActivityCountRespVO> drillActivityCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillJoinUserCountRespVO> drillJoinUserCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillLotteryCountRespVO> drillLotteryCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillWinningRateRespVO> drillWinningRate(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillCouponSendCountRespVO> drillCouponSendCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillVerifyRateRespVO> drillVerifyRate(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillCardOrderCountRespVO> drillCardOrderCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillRevenueRespVO> drillRevenue(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillExchangeCountRespVO> drillExchangeCount(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillTotalStockRespVO> drillTotalStock(CycleReportDrillBaseReqVO reqVO);

    PageResult<CycleReportDrillWarnStockCountRespVO> drillWarnStockCount(CycleReportDrillBaseReqVO reqVO);

}
