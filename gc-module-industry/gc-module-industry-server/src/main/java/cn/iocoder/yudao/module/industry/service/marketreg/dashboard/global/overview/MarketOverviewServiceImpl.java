package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.overview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo.MarketOverviewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.overview.MarketOverviewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 市监全域数据地图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MarketOverviewServiceImpl implements MarketOverviewService {

    @Resource
    private MarketOverviewMapper marketOverviewMapper;

    @Override
    public MarketOverviewRespVO getMarketOverview(MarketOverviewQueryReqVO marketOverviewQueryReqVO) {
            return marketOverviewMapper.getMarketOverview(marketOverviewQueryReqVO);
    }
}
