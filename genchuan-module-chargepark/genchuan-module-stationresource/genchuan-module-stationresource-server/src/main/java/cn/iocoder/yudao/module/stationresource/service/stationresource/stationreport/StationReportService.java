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
     * 获得场站资源报表分页
     *
     * @param pageReqVO 分页查询
     * @return 分页数据
     */
    PageResult<StationReportDO> getReportPage(StationReportPageReqVO pageReqVO);

    /**
     * 获得场站资源报表
     *
     * @param id 编号
     * @return 报表
     */
    StationReportDO getReport(Long id);

    /**
     * 生成场站资源报表
     *
     * @param reqVO 创建信息
     * @return 生成结果
     */
    Map<String, Object> createReport(StationReportCreateReqVO reqVO);

    /**
     * 导出场站资源报表
     *
     * @param pageReqVO 查询条件
     * @param response  响应
     */
    void exportReport(StationReportPageReqVO pageReqVO, HttpServletResponse response);

    /**
     * 获得场站资源报表图表数据
     *
     * @param reqVO 查询条件
     * @return 图表数据
     */
    StationReportChartRespVO getReportChart(StationReportChartReqVO reqVO);

    StationReportDO getReportPage2(StationOpReportCreateReqVO pageReqVO);

    PageResult<StationReportDO> getPage(StationOpReportPageReqVO pageReqVO);

    Long addReport(StationOpReportCreateReqVO reqVO);

    StationOpReportChartRespVO getReportChartData(StationOpReportChartReqVO reqVO);
}
