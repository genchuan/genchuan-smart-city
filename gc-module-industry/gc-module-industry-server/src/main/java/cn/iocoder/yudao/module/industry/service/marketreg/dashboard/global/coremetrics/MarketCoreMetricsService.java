package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsRespVO;

/**
 * 市监核心指标 Service 接口
 *
 * @author lxs
 */
public interface MarketCoreMetricsService {

        /**
         * 查询市监核心指标
         *
         * @param marketCoreMetricsQueryReqVO 查询条件
         * @return 查询结果
         */
        MarketCoreMetricsRespVO getMarketCoreMetrics(MarketCoreMetricsQueryReqVO marketCoreMetricsQueryReqVO);
}
