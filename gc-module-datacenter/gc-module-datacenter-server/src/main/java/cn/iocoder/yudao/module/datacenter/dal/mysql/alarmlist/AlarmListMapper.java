package cn.iocoder.yudao.module.datacenter.dal.mysql.alarmlist;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarmlist.AlarmListDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo.*;

/**
 * 预警告警列 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AlarmListMapper extends BaseMapperX<AlarmListDO> {

    default PageResult<AlarmListDO> selectPage(AlarmListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmListDO>()
                .eqIfPresent(AlarmListDO::getAlarmId, reqVO.getAlarmId())
                .eqIfPresent(AlarmListDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(AlarmListDO::getRiskTypeId, reqVO.getRiskTypeId())
                .likeIfPresent(AlarmListDO::getRiskTypeName, reqVO.getRiskTypeName())
                .eqIfPresent(AlarmListDO::getAlarmLevel, reqVO.getAlarmLevel())
                .eqIfPresent(AlarmListDO::getDomainId, reqVO.getDomainId())
                .likeIfPresent(AlarmListDO::getDomainName, reqVO.getDomainName())
                .eqIfPresent(AlarmListDO::getGpsCoordinate, reqVO.getGpsCoordinate())
                .eqIfPresent(AlarmListDO::getAlarmStatus, reqVO.getAlarmStatus())
                .eqIfPresent(AlarmListDO::getTriggerReason, reqVO.getTriggerReason())
                .eqIfPresent(AlarmListDO::getIndicatorId, reqVO.getIndicatorId())
                .eqIfPresent(AlarmListDO::getHandlerId, reqVO.getHandlerId())
                .likeIfPresent(AlarmListDO::getHandlerName, reqVO.getHandlerName())
                .eqIfPresent(AlarmListDO::getCreator, reqVO.getCreator())
                .eqIfPresent(AlarmListDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(AlarmListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlarmListDO::getId));
    }

}