package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.marketopreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.MarketOpReportDO;
import jakarta.validation.Valid;

import java.util.List;

public interface MarketOpReportService {

    PageResult<MarketOpReportDO> getPage(MarketOpReportPageReqVO reqVO);

    MarketOpReportDO get(Long id);

    Long create(@Valid MarketOpReportCreateReqVO reqVO);

    MarketOpReportChartRespVO getChart(Long reportId);

    List<MarketOpReportDO> getList(MarketOpReportPageReqVO reqVO);

}
