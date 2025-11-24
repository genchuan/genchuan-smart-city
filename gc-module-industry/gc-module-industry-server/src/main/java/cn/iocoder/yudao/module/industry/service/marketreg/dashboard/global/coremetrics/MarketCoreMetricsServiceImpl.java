package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.coremetrics.MarketCoreMetricsMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 市监核心指标 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MarketCoreMetricsServiceImpl implements MarketCoreMetricsService {

    @Resource
    private MarketCoreMetricsMapper marketCoreMetricsMapper;

    @Override
    public MarketCoreMetricsRespVO getMarketCoreMetrics(MarketCoreMetricsQueryReqVO marketCoreMetricsQueryReqVO) {
        return marketCoreMetricsMapper.getMarketCoreMetrics(marketCoreMetricsQueryReqVO);
    }
}
