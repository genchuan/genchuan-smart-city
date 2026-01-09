package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.supvview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewRespVO;

/**
 * 监管对象分布视图 Service 接口
 *
 * @author lxs
 */
public interface MarketSupvViewService {

        /**
         * 查询监管对象分布视图
         *
         * @param marketSupvViewQueryReqVO 查询条件
         * @return 查询结果
         */
        MarketSupvViewRespVO getMarketSupvView(MarketSupvViewQueryReqVO marketSupvViewQueryReqVO);
}
