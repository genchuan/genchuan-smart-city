package cn.iocoder.yudao.module.inspectop.service.spacemonitor;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.spacemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 车位状态监测 Service 接口
 *
 * @author zhucongquan
 */
public interface SpaceMonitorService {

    /**
     * 创建车位状态监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSpaceMonitor(@Valid SpaceMonitorSaveReqVO createReqVO);

    /**
     * 更新车位状态监测
     *
     * @param updateReqVO 更新信息
     */
    void updateSpaceMonitor(@Valid SpaceMonitorSaveReqVO updateReqVO);

    /**
     * 删除车位状态监测
     *
     * @param id 编号
     */
    void deleteSpaceMonitor(Long id);

    /**
    * 批量删除车位状态监测
    *
    * @param ids 编号
    */
    void deleteSpaceMonitorListByIds(List<Long> ids);

    /**
     * 获得车位状态监测
     *
     * @param id 编号
     * @return 车位状态监测
     */
    SpaceMonitorDO getSpaceMonitor(Long id);

//    /**
//     * 获得车位状态监测分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 车位状态监测分页
//     */
//    PageResult<SpaceMonitorDO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO);

    /**
     * 获得车位状态监测分页
     *
     * @param pageReqVO 分页查询
     * @return 车位状态监测分页
     */
    PageResult<SpaceMonitorRespVO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO);

//    /**
//     * 获得车位状态监测分页
//     *
//     * @param pageReqVO 分页查询
//     * @return 车位状态监测分页
//     */
//    List<SpaceMonitorRespVO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO);

    /**
     * 获取车位状态监测的定位信息
     *
     * @param id 监测记录ID
     * @return 定位信息
     */
    SpaceMonitorLocationRespVO getSpaceMonitorLocation(Long id);

    /**
     * 更新车位状态监测告警信息
     *
     * @param alarmReqVO 告警请求信息
     */
    void updateSpaceMonitorAlarm(@Valid SpaceMonitorAlarmReqVO alarmReqVO);

    /**
     * 获取车位状态监控数据
     *
     * @param reqVO 查询参数
     * @return 监控数据
     */
    SpaceMonitorChartRespVO getSpaceMonitorChart(SpaceMonitorChartReqVO reqVO);
}