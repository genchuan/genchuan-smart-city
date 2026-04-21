package cn.iocoder.yudao.module.inspectop.service.devicemonitor.bikechargemonitor;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.bikechargemonitor.BikeChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 两轮充电监测 Service 接口
 *
 * @author zhucongquan
 */
public interface BikeChargeMonitorService {

    /**
     * 创建两轮充电监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBikeChargeMonitor(@Valid BikeChargeMonitorSaveReqVO createReqVO);

    /**
     * 更新两轮充电监测
     *
     * @param updateReqVO 更新信息
     */
    void updateBikeChargeMonitor(@Valid BikeChargeMonitorSaveReqVO updateReqVO);

    /**
     * 删除两轮充电监测
     *
     * @param id 编号
     */
    void deleteBikeChargeMonitor(Long id);

    /**
    * 批量删除两轮充电监测
    *
    * @param ids 编号
    */
    void deleteBikeChargeMonitorListByIds(List<Long> ids);

    /**
     * 获得两轮充电监测
     *
     * @param id 编号
     * @return 两轮充电监测
     */
    BikeChargeMonitorDO getBikeChargeMonitor(Long id);

    /**
     * 获得两轮充电监测分页
     *
     * @param pageReqVO 分页查询
     * @return 两轮充电监测分页
     */
    // 修改返回类型：从 PageResult<BikeChargeMonitorDO> 改为 PageResult<BikeChargeMonitorRespVO>
    PageResult<BikeChargeMonitorRespVO> getBikeChargeMonitorPage(BikeChargeMonitorPageReqVO pageReqVO);

    /**
     * 获取两轮充电监测定位信息
     * 包括经度、纬度、场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息
     */
    BikeChargeMonitorLocationRespVO getBikeChargeMonitorLocation(Long id);

    /**
     * 告警更新两轮充电监测
     *
     * @param alarmReqVO 告警信息
     */
    void alarmBikeChargeMonitor(@Valid BikeChargeMonitorAlarmReqVO alarmReqVO);

    /**
     * 获取两轮充电监测图表数据
     * 包括地图数据、趋势数据和卡片数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    BikeChargeMonitorChartRespVO getBikeChargeMonitorChart(@Valid BikeChargeMonitorChartReqVO reqVO);

}