package cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 市监全域数据地图 Mapper
 *
 * @author lxs
 */
@Mapper
public interface MarketOverviewMapper {

        /**
         * 查询市监全域数据地图
         *
         * @param marketOverviewQueryReqVO 查询参数
         * @return MarketOverviewRespVO 结果
         */
        MarketOverviewRespVO getMarketOverview(MarketOverviewQueryReqVO marketOverviewQueryReqVO);

}
