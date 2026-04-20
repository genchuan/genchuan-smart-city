package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.marketopreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.MarketOpReportDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.decisionanalysis.MarketOpReportMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.MARKET_OP_REPORT_NOT_EXISTS;

@Service
@Validated
public class MarketOpReportServiceImpl implements MarketOpReportService {

    @Resource
    private MarketOpReportMapper marketOpReportMapper;

    @Override
    public PageResult<MarketOpReportDO> getPage(MarketOpReportPageReqVO reqVO) {
        return marketOpReportMapper.selectPage(reqVO);
    }

    @Override
    public MarketOpReportDO get(Long id) {
        return marketOpReportMapper.selectById(id);
    }

    @Override
    public Long create(MarketOpReportCreateReqVO reqVO) {
        // 1. 构建报表记录
        MarketOpReportDO report = BeanUtils.toBean(reqVO, MarketOpReportDO.class);
        report.setStatus("已生成");

        // 2. 聚合核心指标
        Integer activityJoinCount = marketOpReportMapper.selectActivityJoinCount(
                reqVO.getStartTime(), reqVO.getEndTime());
        Integer activityTotalCount = marketOpReportMapper.selectActivityTotalCount(
                reqVO.getStartTime(), reqVO.getEndTime());
        Integer couponVerifiedCount = marketOpReportMapper.selectCouponVerifiedCount(
                reqVO.getStartTime(), reqVO.getEndTime());
        Integer couponReceivedCount = marketOpReportMapper.selectCouponReceivedCount(
                reqVO.getStartTime(), reqVO.getEndTime());
        Integer cardSaleCount = marketOpReportMapper.selectCardSaleCount(
                reqVO.getStartTime(), reqVO.getEndTime());

        // 活动参与率 = 参与人次 / 活动总数
        double activityJoinRate = activityTotalCount != null && activityTotalCount > 0
                ? (double) activityJoinCount / activityTotalCount : 0.0;
        // 优惠券核销率 = 已核销 / 已领取
        double couponVerifyRate = couponReceivedCount != null && couponReceivedCount > 0
                ? (double) couponVerifiedCount / couponReceivedCount : 0.0;

        report.setActivityJoinRate(activityJoinRate);
        report.setCouponVerifyRate(couponVerifyRate);
        report.setCardSaleCount(cardSaleCount != null ? cardSaleCount : 0);

        // 3. 持久化
        marketOpReportMapper.insert(report);
        return report.getId();
    }

    @Override
    public MarketOpReportChartRespVO getChart(Long reportId) {
        // 1. 获取报表记录
        MarketOpReportDO report = validateExists(reportId);

        // 2. 构建图表响应
        MarketOpReportChartRespVO respVO = new MarketOpReportChartRespVO();

        // 核心指标（快照）
        MarketOpReportChartRespVO.CoreIndex coreIndex = new MarketOpReportChartRespVO.CoreIndex();
        coreIndex.setActivityJoinRate(report.getActivityJoinRate());
        coreIndex.setCouponVerifyRate(report.getCouponVerifyRate());
        coreIndex.setCardSaleCount(report.getCardSaleCount());
        respVO.setCoreIndex(coreIndex);

        // 趋势数据（实时聚合）
        String timeScale = report.getTimeScale() != null ? report.getTimeScale() : "day";
        List<MarketOpReportChartRespVO.TrendItem> trendList =
                marketOpReportMapper.selectTrendList(report.getStartTime(), report.getEndTime(), timeScale);
        respVO.setTrendList(trendList);

        // 活动效果数据（实时聚合）
        List<MarketOpReportChartRespVO.EffectItem> effectList =
                marketOpReportMapper.selectEffectList(report.getStartTime(), report.getEndTime());
        respVO.setEffectList(effectList);

        return respVO;
    }

    @Override
    public List<MarketOpReportDO> getList(MarketOpReportPageReqVO reqVO) {
        return marketOpReportMapper.selectList(
                new LambdaQueryWrapperX<MarketOpReportDO>()
                        .eqIfPresent(MarketOpReportDO::getType, reqVO.getType())
                        .betweenIfPresent(MarketOpReportDO::getStartTime, reqVO.getTimeRange())
                        .orderByDesc(MarketOpReportDO::getId));
    }

    private MarketOpReportDO validateExists(Long id) {
        MarketOpReportDO report = marketOpReportMapper.selectById(id);
        if (report == null) {
            throw exception(MARKET_OP_REPORT_NOT_EXISTS);
        }
        return report;
    }

}
