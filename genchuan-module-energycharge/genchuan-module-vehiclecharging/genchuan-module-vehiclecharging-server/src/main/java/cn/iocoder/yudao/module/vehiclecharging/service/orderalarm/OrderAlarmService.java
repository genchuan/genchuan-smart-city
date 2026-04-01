package cn.iocoder.yudao.module.vehiclecharging.service.orderalarm;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 订单告警 Service 接口
 *
 * @author zhucongquan
 */
public interface OrderAlarmService {

    /**
     * 创建订单告警
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderAlarm(@Valid OrderAlarmSaveReqVO createReqVO);

    /**
     * 更新订单告警
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderAlarm(@Valid OrderAlarmSaveReqVO updateReqVO);

    /**
     * 删除订单告警
     *
     * @param id 编号
     */
    void deleteOrderAlarm(Long id);

    /**
    * 批量删除订单告警
    *
    * @param ids 编号
    */
    void deleteOrderAlarmListByIds(List<Long> ids);

    /**
     * 获得订单告警
     *
     * @param id 编号
     * @return 订单告警
     */
    OrderAlarmDO getOrderAlarm(Long id);

    /**
     * 获得订单告警分页
     *
     * @param pageReqVO 分页查询
     * @return 订单告警分页
     */
    PageResult<OrderAlarmDO> getOrderAlarmPage(OrderAlarmPageReqVO pageReqVO);

}