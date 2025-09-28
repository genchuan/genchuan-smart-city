package cn.iocoder.yudao.module.gc.dal.mysql.alarminformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.gc.dal.dataobject.alarminformation.AlarmInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo.*;

/**
 * 预警信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AlarmInformationMapper extends BaseMapperX<AlarmInformationDO> {

    default PageResult<AlarmInformationDO> selectPage(AlarmInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmInformationDO>()
                .eqIfPresent(AlarmInformationDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(AlarmInformationDO::getRiskTypeId, reqVO.getRiskTypeId())
                .likeIfPresent(AlarmInformationDO::getRiskTypeName, reqVO.getRiskTypeName())
                .eqIfPresent(AlarmInformationDO::getAlarmLevel, reqVO.getAlarmLevel())
                .eqIfPresent(AlarmInformationDO::getDomainId, reqVO.getDomainId())
                .likeIfPresent(AlarmInformationDO::getDomainName, reqVO.getDomainName())
                .eqIfPresent(AlarmInformationDO::getOccurRegion, reqVO.getOccurRegion())
                .eqIfPresent(AlarmInformationDO::getGpsCoordinate, reqVO.getGpsCoordinate())
                .betweenIfPresent(AlarmInformationDO::getTriggerTime, reqVO.getTriggerTime())
                .eqIfPresent(AlarmInformationDO::getAlarmStatus, reqVO.getAlarmStatus())
                .eqIfPresent(AlarmInformationDO::getTriggerReason, reqVO.getTriggerReason())
                .eqIfPresent(AlarmInformationDO::getIndicatorId, reqVO.getIndicatorId())
                .eqIfPresent(AlarmInformationDO::getHandlerId, reqVO.getHandlerId())
                .likeIfPresent(AlarmInformationDO::getHandlerName, reqVO.getHandlerName())
                .betweenIfPresent(AlarmInformationDO::getLastUpdateTime, reqVO.getLastUpdateTime())
                .orderByDesc(AlarmInformationDO::getAlarmId));
    }

}