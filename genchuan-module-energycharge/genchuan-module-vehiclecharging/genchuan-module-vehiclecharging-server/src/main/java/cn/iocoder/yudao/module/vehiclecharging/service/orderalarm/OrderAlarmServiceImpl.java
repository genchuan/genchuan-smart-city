package cn.iocoder.yudao.module.vehiclecharging.service.orderalarm;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderalarm.OrderAlarmMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 订单告警 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class OrderAlarmServiceImpl implements OrderAlarmService {

    @Resource
    private OrderAlarmMapper orderAlarmMapper;

    @Override
    public Long createOrderAlarm(OrderAlarmSaveReqVO createReqVO) {
        // 插入
        OrderAlarmDO orderAlarm = BeanUtils.toBean(createReqVO, OrderAlarmDO.class);
        orderAlarmMapper.insert(orderAlarm);

        // 返回
        return orderAlarm.getId();
    }

    @Override
    public void updateOrderAlarm(OrderAlarmSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderAlarmExists(updateReqVO.getId());
        // 更新
        OrderAlarmDO updateObj = BeanUtils.toBean(updateReqVO, OrderAlarmDO.class);
        orderAlarmMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderAlarm(Long id) {
        // 校验存在
        validateOrderAlarmExists(id);
        // 删除
        orderAlarmMapper.deleteById(id);
    }

    @Override
        public void deleteOrderAlarmListByIds(List<Long> ids) {
        // 删除
        orderAlarmMapper.deleteByIds(ids);
        }


    private void validateOrderAlarmExists(Long id) {
        if (orderAlarmMapper.selectById(id) == null) {
            throw exception(ORDER_ALARM_NOT_EXISTS);
        }
    }

    @Override
    public OrderAlarmDO getOrderAlarm(Long id) {
        return orderAlarmMapper.selectById(id);
    }

    @Override
    public PageResult<OrderAlarmDO> getOrderAlarmPage(OrderAlarmPageReqVO pageReqVO) {
        return orderAlarmMapper.selectPage(pageReqVO);
    }

}