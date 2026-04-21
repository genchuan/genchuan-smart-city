package cn.iocoder.yudao.module.stationresource.service.stationresource.decisionanalysis.stationopreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.StationOpReportRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.decisionanalysis.stationopreport.vo.ops.StationOpReportChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.decisionanalysis.stationopreport.StationOpReportDO;

public interface StationOpReportService {

    StationOpReportDO getReport(Long id);

    PageResult<StationOpReportDO> getReportPage(StationOpReportPageReqVO pageReqVO);

    StationOpReportChartRespVO getReportChart(Long reportId);
}
