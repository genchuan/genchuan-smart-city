package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.caseview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewRespVO;

/**
 * 市监案件总览视图 Service 接口
 *
 * @author lxs
 */
public interface MarketCaseViewService {

        /**
         * 查询市监案件总览视图
         *
         * @param marketCaseViewQueryReqVO 查询条件
         * @return 查询结果
         */
        MarketCaseViewRespVO getMarketCaseView(MarketCaseViewQueryReqVO marketCaseViewQueryReqVO);
}
