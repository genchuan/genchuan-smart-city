package cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.supvview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo.MarketSupvViewRespVO;
import org.apache.ibatis.annotations.Mapper;
/**
 * 监管对象分布视图 Mapper
 *
 * @author lxs
 */
@Mapper
public interface MarketSupvViewMapper {

        /**
         * 查询监管对象分布视图
         *
         * @param marketSupvViewQueryReqVO 查询参数
         * @return MarketSupvViewRespVO 结果
         */
        MarketSupvViewRespVO getMarketSupvView(MarketSupvViewQueryReqVO marketSupvViewQueryReqVO);

}
