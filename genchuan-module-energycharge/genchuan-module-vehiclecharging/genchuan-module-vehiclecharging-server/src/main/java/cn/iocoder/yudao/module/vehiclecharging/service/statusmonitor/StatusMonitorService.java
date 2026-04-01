package cn.iocoder.yudao.module.vehiclecharging.service.statusmonitor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.StatusMonitorPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.AbnormalPoint;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.ParamTrend;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.StatusCountRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.StatusMonitorChartRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo.*;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.statusmonitor.StatusMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 实时监测 Service 接口
 *
 * @author 亘川智城
 */
public interface StatusMonitorService {
    PageResult<StatusMonitorRespVO> getStatusMonitorPage(StatusMonitorPageReqVO pageReqVO);

    List<StatusMonitorRefreshRespVO> refreshStatusMonitor(StatusMonitorRefreshReqVO refreshReqVO);


    void exportStatusMonitor(StatusMonitorExportReqVO exportReqVO, HttpServletResponse response) throws IOException;

    Long handleAbnormal(StatusMonitorHandleAbnormalReqVO handleReqVO);
    /**
     * 创建实时监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStatusMonitor(@Valid StatusMonitorSaveReqVO createReqVO);

    /**
     * 更新实时监测
     *
     * @param updateReqVO 更新信息
     */
    void updateStatusMonitor(@Valid StatusMonitorSaveReqVO updateReqVO);

    /**
     * 删除实时监测
     *
     * @param id 编号
     */
    void deleteStatusMonitor(Long id);
//
//    /**
//    * 批量删除实时监测
//    *
//    * @param ids 编号
//    */
//    void deleteStatusMonitorListByIds(List<Long> ids);
//
    /**
     * 获得实时监测
     *
     * @param id 编号
     * @return 实时监测
     */
    StatusMonitorDO getStatusMonitor(Long id);

    StatusMonitorChartRespVO getStatusMonitorChart();

    List<ParamTrend> getParamTrend(String deviceCode, LocalDateTime startTime, LocalDateTime endTime);

    List<AbnormalPoint> getAbnormalDeviceLocation(String area);

    List<StatusCountRespVO> getStatusCountByStation(String status);
//
//    /**
//     * 获得实时监测分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 实时监测分页
//     */
//    PageResult<StatusMonitorDO> getStatusMonitorPage(StatusMonitorPageReqVO pageReqVO);

}
