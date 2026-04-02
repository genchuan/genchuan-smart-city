package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pilealarm;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pilealarm.PilealarmDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pilealarm.vo.*;

/**
 * 充电桩告警 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PilealarmMapper extends BaseMapperX<PilealarmDO> {

    default PageResult<PilealarmDO> selectPage(PilealarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PilealarmDO>()
                .eqIfPresent(PilealarmDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(PilealarmDO::getPileId, reqVO.getPileId())
                .eqIfPresent(PilealarmDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PilealarmDO::getFaultType, reqVO.getFaultType())
                .eqIfPresent(PilealarmDO::getFaultDesc, reqVO.getFaultDesc())
                .eqIfPresent(PilealarmDO::getAlarmLevel, reqVO.getAlarmLevel())
                .betweenIfPresent(PilealarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(PilealarmDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(PilealarmDO::getHandleHour, reqVO.getHandleHour())
                .eqIfPresent(PilealarmDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(PilealarmDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PilealarmDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PilealarmDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PilealarmDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PilealarmDO::getReserve3, reqVO.getReserve3())
                .eqIfPresent(PilealarmDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PilealarmDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(PilealarmDO::getUpdateBy, reqVO.getUpdateBy())
                .orderByDesc(PilealarmDO::getId));
    }

}