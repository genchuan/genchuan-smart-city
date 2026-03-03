package cn.iocoder.yudao.module.facility.dal.mysql.road.warn;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.warn.vo.WarnPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.warn.WarnDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface WarnMapper extends BaseMapperX<WarnDO> {

    default PageResult<WarnDO> selectPage(WarnPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WarnDO>()
                .eqIfPresent(WarnDO::getWarnNo, reqVO.getWarnNo())
                .eqIfPresent(WarnDO::getFacilityId, reqVO.getFacilityId())
                .likeIfPresent(WarnDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(WarnDO::getFacilityCode, reqVO.getFacilityCode())
                .eqIfPresent(WarnDO::getWorkOrderId, reqVO.getWorkOrderId())
                .eqIfPresent(WarnDO::getWorkOrderCode, reqVO.getWorkOrderCode())
                .eqIfPresent(WarnDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(WarnDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(WarnDO::getMonitorId, reqVO.getMonitorId())
                .eqIfPresent(WarnDO::getMonitorCode, reqVO.getMonitorCode())
                .eqIfPresent(WarnDO::getStatus, reqVO.getStatus())
                .eqIfPresent(WarnDO::getAssignStatus, reqVO.getAssignStatus())
                .eqIfPresent(WarnDO::getFacilityType, reqVO.getFacilityType())
                .eqIfPresent(WarnDO::getType, reqVO.getType())
                .eqIfPresent(WarnDO::getWayType, reqVO.getWayType())
                .eqIfPresent(WarnDO::getLevel, reqVO.getLevel())
                .betweenIfPresent(WarnDO::getTriggerTime, reqVO.getTriggerTime())
                .eqIfPresent(WarnDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(WarnDO::getOverIndex, reqVO.getOverIndex())
                .eqIfPresent(WarnDO::getOverValue, reqVO.getOverValue())
                .eqIfPresent(WarnDO::getThresholdValue, reqVO.getThresholdValue())
                .eqIfPresent(WarnDO::getConfirmOpinion, reqVO.getConfirmOpinion())
                .eqIfPresent(WarnDO::getInvalidReason, reqVO.getInvalidReason())
                .eqIfPresent(WarnDO::getSuggest, reqVO.getSuggest())
                .betweenIfPresent(WarnDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(WarnDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(WarnDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(WarnDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(WarnDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(WarnDO::getId));
    }

}
