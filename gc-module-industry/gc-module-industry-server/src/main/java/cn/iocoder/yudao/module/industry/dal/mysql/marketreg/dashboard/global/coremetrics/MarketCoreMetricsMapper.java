package cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.coremetrics;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo.MarketCoreMetricsRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 市监核心指标 Mapper
 *
 * @author lxs
 */
@Mapper
public interface MarketCoreMetricsMapper {

        /**
         * 查询市监核心指标
         *
         * @param marketCoreMetricsQueryReqVO 查询参数
         * @return MarketCoreMetricsRespVO 结果
         */
        MarketCoreMetricsRespVO getMarketCoreMetrics(MarketCoreMetricsQueryReqVO marketCoreMetricsQueryReqVO);

}
