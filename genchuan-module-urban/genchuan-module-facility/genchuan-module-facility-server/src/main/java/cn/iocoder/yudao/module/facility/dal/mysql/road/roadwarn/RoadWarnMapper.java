package cn.iocoder.yudao.module.facility.dal.mysql.road.roadwarn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadwarn.vo.WarnPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadwarn.RoadWarnDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RoadWarnMapper extends BaseMapperX<RoadWarnDO> {

    default PageResult<RoadWarnDO> selectPage(WarnPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadWarnDO>()
                .eqIfPresent(RoadWarnDO::getWarnNo, reqVO.getWarnNo())
                .eqIfPresent(RoadWarnDO::getFacilityId, reqVO.getFacilityId())
                .likeIfPresent(RoadWarnDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(RoadWarnDO::getFacilityCode, reqVO.getFacilityCode())
                .eqIfPresent(RoadWarnDO::getWorkOrderId, reqVO.getWorkOrderId())
                .eqIfPresent(RoadWarnDO::getWorkOrderCode, reqVO.getWorkOrderCode())
                .eqIfPresent(RoadWarnDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(RoadWarnDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(RoadWarnDO::getMonitorId, reqVO.getMonitorId())
                .eqIfPresent(RoadWarnDO::getMonitorCode, reqVO.getMonitorCode())
                .eqIfPresent(RoadWarnDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RoadWarnDO::getAssignStatus, reqVO.getAssignStatus())
                .eqIfPresent(RoadWarnDO::getFacilityType, reqVO.getFacilityType())
                .eqIfPresent(RoadWarnDO::getType, reqVO.getType())
                .eqIfPresent(RoadWarnDO::getWayType, reqVO.getWayType())
                .eqIfPresent(RoadWarnDO::getLevel, reqVO.getLevel())
                .betweenIfPresent(RoadWarnDO::getTriggerTime, reqVO.getTriggerTime())
                .eqIfPresent(RoadWarnDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(RoadWarnDO::getOverIndex, reqVO.getOverIndex())
                .eqIfPresent(RoadWarnDO::getOverValue, reqVO.getOverValue())
                .eqIfPresent(RoadWarnDO::getThresholdValue, reqVO.getThresholdValue())
                .eqIfPresent(RoadWarnDO::getConfirmOpinion, reqVO.getConfirmOpinion())
                .eqIfPresent(RoadWarnDO::getInvalidReason, reqVO.getInvalidReason())
                .eqIfPresent(RoadWarnDO::getSuggest, reqVO.getSuggest())
                .betweenIfPresent(RoadWarnDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RoadWarnDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadWarnDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadWarnDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadWarnDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RoadWarnDO::getId));
    }

}
