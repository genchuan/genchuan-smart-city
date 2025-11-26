package cn.iocoder.yudao.module.industry.service.marketreg.dashboard.global.caseview;


import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo.MarketCaseViewRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.marketreg.dashboard.global.caseview.MarketCaseViewMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 市监案件总览视图 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class MarketCaseViewServiceImpl implements MarketCaseViewService {

    @Resource
    private MarketCaseViewMapper marketCaseViewMapper;

    @Override
    public MarketCaseViewRespVO getMarketCaseView(MarketCaseViewQueryReqVO marketCaseViewQueryReqVO) {

        //填充规定完成时长
        marketCaseViewQueryReqVO.setPrescribedDuration(7);

        //检查规定完成时长是否为null
        if (marketCaseViewQueryReqVO.getPrescribedDuration()==null
                || marketCaseViewQueryReqVO.getPrescribedDuration()==0)
        {
            throw new IllegalArgumentException("规定完成时长不能为空或为0");
        }

        return marketCaseViewMapper.getMarketCaseView(marketCaseViewQueryReqVO);
    }
}
