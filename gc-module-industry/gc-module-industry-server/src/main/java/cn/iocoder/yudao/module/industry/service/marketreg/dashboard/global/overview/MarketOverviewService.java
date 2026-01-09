package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewRespVO;

/**
 * 市监全域数据地图 Service 接口
 *
 * @author lxs
 */
public interface MarketOverviewService {

        /**
         * 查询市监全域数据地图
         *
         * @param marketOverviewQueryReqVO 查询条件
         * @return 查询结果
         */
        MarketOverviewRespVO getMarketOverview(MarketOverviewQueryReqVO marketOverviewQueryReqVO);
}
