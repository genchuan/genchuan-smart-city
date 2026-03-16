package cn.iocoder.yudao.module.facility.service.road.roadmonitor;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor.RoadMonitorDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 道路监测 Service 接口
 *
 * @author 亘川智城
 */
public interface RoadMonitorService {

    /**
     * 创建道路监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitor(@Valid RoadMonitorSaveReqVO createReqVO);

    /**
     * 更新道路监测
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitor(@Valid RoadMonitorUpdateReqVO updateReqVO);

    /**
     * 删除道路监测
     *
     * @param id 编号
     */
    void deleteMonitor(Long id);

    /**
     * 获得道路监测
     *
     * @param id 编号
     * @return 道路监测
     */
    RoadMonitorDO getMonitor(Long id);

    /**
     * 获得道路监测分页
     *
     * @param pageReqVO 分页查询
     * @return 道路监测分页
     */
    PageResult<RoadMonitorDO> getMonitorPage(RoadMonitorPageReqVO pageReqVO);

    PageResult<RealtimePageRespVO> getRealtimePage(RealtimePageReqVO reqVO);

    int batchUpdateMonitorStatus(BatchUpdateRoadMonitorStatusReqVO reqVO);

    RoadMonitorCardVO getCardStatistics();

    RoadMonitorLineChartVO getLineChart(Long roadId);

    /**
     * 获取监测状态占比
     */
    List<ChartItemVO> getMonitorStatusRate();

    List<ChartItemVO> getDeviceOnlineRate();

    MonitorBarChartVO getBarChart();
}
