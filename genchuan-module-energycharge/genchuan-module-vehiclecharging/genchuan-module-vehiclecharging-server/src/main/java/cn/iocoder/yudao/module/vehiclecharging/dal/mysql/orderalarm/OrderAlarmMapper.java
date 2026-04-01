package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderalarm;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;

/**
 * 订单告警 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface OrderAlarmMapper extends BaseMapperX<OrderAlarmDO> {

    default PageResult<OrderAlarmDO> selectPage(OrderAlarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderAlarmDO>()
                .eqIfPresent(OrderAlarmDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(OrderAlarmDO::getOrderCode, reqVO.getOrderCode())
                .eqIfPresent(OrderAlarmDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderAlarmDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OrderAlarmDO::getAbnormalType, reqVO.getAbnormalType())
                .betweenIfPresent(OrderAlarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(OrderAlarmDO::getPileCode, reqVO.getPileCode())
                .eqIfPresent(OrderAlarmDO::getAlarmStatus, reqVO.getAlarmStatus())
                .eqIfPresent(OrderAlarmDO::getVerifyResult, reqVO.getVerifyResult())
                .eqIfPresent(OrderAlarmDO::getHandleMeasure, reqVO.getHandleMeasure())
                .betweenIfPresent(OrderAlarmDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(OrderAlarmDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAlarmDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OrderAlarmDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(OrderAlarmDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderAlarmDO::getId));
    }

}