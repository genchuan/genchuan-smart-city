package cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.caseview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 市监案件总览视图 Mapper
 *
 * @author lxs
 */
@Mapper
public interface MarketCaseViewMapper {

        /**
         * 查询市监案件总览视图
         *
         * @param marketCaseViewQueryReqVO 查询参数
         * @return MarketCaseViewRespVO 结果
         */
        MarketCaseViewRespVO getMarketCaseView(MarketCaseViewQueryReqVO marketCaseViewQueryReqVO);

}
