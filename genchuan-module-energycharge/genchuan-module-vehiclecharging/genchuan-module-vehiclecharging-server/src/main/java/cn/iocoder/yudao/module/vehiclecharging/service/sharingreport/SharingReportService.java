package cn.iocoder.yudao.module.vehiclecharging.service.sharingreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SharingReportService {

    PageResult<SharingReportPageRespVO> getSharingReportPage(SharingReportPageReqVO pageReqVO);

    void exportSingleReport(Long id, HttpServletResponse response) throws IOException;

    Long createSharingReport(@Valid SharingReportCustomCreateReqVO reqVO);

    void exportBatchReport(List<Long> ids, HttpServletResponse response) throws IOException;

    Map<String, Object> getPrintData(Long id);

    SharingReportSummaryRespVO getChartSummary(SharingReportChartReqVO reqVO);

    SharingReportTimeTrendRespVO getTimeTrend(@Valid SharingReportTimeTrendReqVO reqVO);

    SharingReportCooperatorTimeAmountRespVO getCooperatorTimeAmount(@Valid SharingReportCooperatorTimeAmountReqVO reqVO);

    SharingReportCooperatorRatioRespVO getCooperatorRatio(@Valid SharingReportCooperatorRatioReqVO reqVO);

    SharingReportTimeCountRespVO getTimeCount(@Valid SharingReportTimeCountReqVO reqVO);
}
