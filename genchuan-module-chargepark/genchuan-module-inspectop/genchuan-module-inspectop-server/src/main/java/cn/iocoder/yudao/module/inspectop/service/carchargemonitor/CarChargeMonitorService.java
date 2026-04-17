package cn.iocoder.yudao.module.inspectop.service.carchargemonitor;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.carchargemonitor.CarChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 汽车充电监测 Service 接口
 *
 * @author zhucongquan
 */
public interface CarChargeMonitorService {

    /**
     * 创建汽车充电监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCarChargeMonitor(@Valid CarChargeMonitorSaveReqVO createReqVO);

    /**
     * 更新汽车充电监测
     *
     * @param updateReqVO 更新信息
     */
    void updateCarChargeMonitor(@Valid CarChargeMonitorSaveReqVO updateReqVO);

    /**
     * 删除汽车充电监测
     *
     * @param id 编号
     */
    void deleteCarChargeMonitor(Long id);

    /**
    * 批量删除汽车充电监测
    *
    * @param ids 编号
    */
    void deleteCarChargeMonitorListByIds(List<Long> ids);

    /**
     * 获得汽车充电监测
     *
     * @param id 编号
     * @return 汽车充电监测
     */
    CarChargeMonitorDO getCarChargeMonitor(Long id);

    /**
     * 获得汽车充电监测分页
     *
     * @param pageReqVO 分页查询
     * @return 汽车充电监测分页
     */
    PageResult<CarChargeMonitorRespVO> getCarChargeMonitorPage(CarChargeMonitorPageReqVO pageReqVO);

    /**
     * 获取汽车充电监测定位信息
     * 包括经度、纬度、场站名称
     *
     * @param id 监测记录ID
     * @return 定位信息
     */
    CarChargeMonitorLocationRespVO getCarChargeMonitorLocation(Long id);

    /**
     * 告警更新汽车充电监测
     *
     * @param alarmReqVO 告警信息
     */
    void alarmCarChargeMonitor(@Valid CarChargeMonitorAlarmReqVO alarmReqVO);

    /**
     * 获取汽车充电监测图表数据
     * 包括地图数据、趋势数据和卡片数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    CarChargeMonitorChartRespVO getCarChargeMonitorChart(@Valid CarChargeMonitorChartReqVO reqVO);

}