package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.eventtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypePageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.eventtype.EventTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件类别 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface EventTypeMapper extends BaseMapperX<EventTypeDO> {

    default PageResult<EventTypeDO> selectPage(EventTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventTypeDO>()
                .eqIfPresent(EventTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(EventTypeDO::getEventCode, reqVO.getEventCode())
                .likeIfPresent(EventTypeDO::getEventName, reqVO.getEventName())
                .eqIfPresent(EventTypeDO::getRelatedMonitorTypeId, reqVO.getRelatedMonitorTypeId())
                .eqIfPresent(EventTypeDO::getAlarmLevel, reqVO.getAlarmLevel())
                .eqIfPresent(EventTypeDO::getHandleRule, reqVO.getHandleRule())
                .eqIfPresent(EventTypeDO::getEventStatus, reqVO.getEventStatus())
                .eqIfPresent(EventTypeDO::getEventRemark, reqVO.getEventRemark())
                .betweenIfPresent(EventTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventTypeDO::getId));
    }

}