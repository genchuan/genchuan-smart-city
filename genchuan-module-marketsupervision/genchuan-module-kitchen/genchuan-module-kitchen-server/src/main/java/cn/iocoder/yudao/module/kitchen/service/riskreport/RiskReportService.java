package cn.iocoder.yudao.module.kitchen.service.riskreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.page.EntReportPageResp;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.statistics.EntViolationDistRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.statistics.EntViolationRankRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.statistics.RiskOverviewRespVO;
import org.springframework.http.ResponseEntity;

public interface RiskReportService {


    PageResult<EntReportPageResp> getEntReportPage(EntReportPageReq pageReqVO);

    ResponseEntity<byte[]> exportRiskReportPdf(EntReportPageReq pageReqVO);

    RiskOverviewRespVO getOverview(EntReportPageReq req);

    EntViolationDistRespVO getViolationDistribution(EntReportPageReq req);

    EntViolationRankRespVO getViolationRanking(EntReportPageReq req);
}
