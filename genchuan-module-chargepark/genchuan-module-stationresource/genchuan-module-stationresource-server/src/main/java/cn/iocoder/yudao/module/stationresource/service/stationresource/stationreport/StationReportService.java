package cn.iocoder.yudao.module.stationresource.service.stationresource.stationreport;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationreport.StationReportDO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * 场站资源报表 Service 接口
 *
 * @author 亘川智城
 */
public interface StationReportService {



    /**
     * 获得场站资源报表
     *
     * @param id 编号
     * @return 报表
     */
    StationReportDO getReport(Long id);


    PageResult<StationReportDO> getPage(StationOpReportPageReqVO pageReqVO);

    Long addReport(StationOpReportCreateReqVO reqVO);

    StationOpReportChartRespVO getReportChartData(StationOpReportChartReqVO reqVO);
}
