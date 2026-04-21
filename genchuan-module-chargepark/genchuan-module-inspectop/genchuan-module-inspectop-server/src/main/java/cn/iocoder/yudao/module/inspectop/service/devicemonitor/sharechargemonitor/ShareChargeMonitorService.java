package cn.iocoder.yudao.module.inspectop.service.devicemonitor.sharechargemonitor;

import java.util.*;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.sharechargemonitor.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.sharechargemonitor.ShareChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 共享充电监测 Service 接口
 *
 * @author zhucongquan
 */
public interface ShareChargeMonitorService {

    /**
     * 创建共享充电监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShareChargeMonitor(@Valid ShareChargeMonitorSaveReqVO createReqVO);

    /**
     * 更新共享充电监测
     *
     * @param updateReqVO 更新信息
     */
    void updateShareChargeMonitor(@Valid ShareChargeMonitorSaveReqVO updateReqVO);

    /**
     * 删除共享充电监测
     *
     * @param id 编号
     */
    void deleteShareChargeMonitor(Long id);

    /**
    * 批量删除共享充电监测
    *
    * @param ids 编号
    */
    void deleteShareChargeMonitorListByIds(List<Long> ids);

    /**
     * 获得共享充电监测
     *
     * @param id 编号
     * @return 共享充电监测
     */
    ShareChargeMonitorDO getShareChargeMonitor(Long id);

    /**
     * 获得共享充电监测分页
     *
     * @param pageReqVO 分页查询
     * @return 共享充电监测分页
     */
    PageResult<ShareChargeMonitorRespVO> getShareChargeMonitorPage(ShareChargeMonitorPageReqVO pageReqVO);

    /**
     * 获取共享充电监测定位信息
     * 包括经度、纬度、场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息
     */
    ShareChargeMonitorLocationRespVO getShareChargeMonitorLocation(Long id);

    /**
     * 告警更新共享充电监测
     *
     * @param alarmReqVO 告警信息
     */
    void alarmShareChargeMonitor(@Valid ShareChargeMonitorAlarmReqVO alarmReqVO);

    /**
     * 获取共享充电监测图表数据
     * 包括地图数据、趋势数据和卡片数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    ShareChargeMonitorChartRespVO getShareChargeMonitorChart(@Valid ShareChargeMonitorChartReqVO reqVO);

}