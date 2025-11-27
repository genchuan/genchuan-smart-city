package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.supvview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.supvview.MarketSupvViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 监管对象分布视图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MarketSupvViewServiceImpl implements MarketSupvViewService {

    @Resource
    private MarketSupvViewMapper marketSupvViewMapper;

    @Override
    public MarketSupvViewRespVO getMarketSupvView(MarketSupvViewQueryReqVO marketSupvViewQueryReqVO) {
            return marketSupvViewMapper.getMarketSupvView(marketSupvViewQueryReqVO);
    }
}
