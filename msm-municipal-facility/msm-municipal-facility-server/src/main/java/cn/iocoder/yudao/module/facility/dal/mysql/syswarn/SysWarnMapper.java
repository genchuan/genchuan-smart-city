package cn.iocoder.yudao.module.facility.dal.mysql.syswarn;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.syswarn.SysWarnDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 通用预警 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysWarnMapper extends BaseMapperX<SysWarnDO> {

    default PageResult<SysWarnDO> selectPage(SysWarnPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysWarnDO>()
                .eqIfPresent(SysWarnDO::getWarnNo, reqVO.getWarnNo())
                .eqIfPresent(SysWarnDO::getFacilityId, reqVO.getFacilityId())
                .likeIfPresent(SysWarnDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(SysWarnDO::getFacilityCode, reqVO.getFacilityCode())
                .eqIfPresent(SysWarnDO::getWorkOrderId, reqVO.getWorkOrderId())
                .eqIfPresent(SysWarnDO::getWorkOrderCode, reqVO.getWorkOrderCode())
                .eqIfPresent(SysWarnDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(SysWarnDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(SysWarnDO::getMonitorId, reqVO.getMonitorId())
                .eqIfPresent(SysWarnDO::getMonitorCode, reqVO.getMonitorCode())
                .eqIfPresent(SysWarnDO::getStatus, reqVO.getStatus())
                .eqIfPresent(SysWarnDO::getAssignStatus, reqVO.getAssignStatus())
                .eqIfPresent(SysWarnDO::getFacilityType, reqVO.getFacilityType())
                .eqIfPresent(SysWarnDO::getType, reqVO.getType())
                .eqIfPresent(SysWarnDO::getWayType, reqVO.getWayType())
                .eqIfPresent(SysWarnDO::getLevel, reqVO.getLevel())
                .betweenIfPresent(SysWarnDO::getTriggerTime, reqVO.getTriggerTime())
                .eqIfPresent(SysWarnDO::getDealLimit, reqVO.getDealLimit())
                .eqIfPresent(SysWarnDO::getOverIndex, reqVO.getOverIndex())
                .eqIfPresent(SysWarnDO::getOverValue, reqVO.getOverValue())
                .eqIfPresent(SysWarnDO::getThresholdValue, reqVO.getThresholdValue())
                .eqIfPresent(SysWarnDO::getConfirmOpinion, reqVO.getConfirmOpinion())
                .eqIfPresent(SysWarnDO::getInvalidReason, reqVO.getInvalidReason())
                .eqIfPresent(SysWarnDO::getSuggest, reqVO.getSuggest())
                .betweenIfPresent(SysWarnDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysWarnDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysWarnDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysWarnDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysWarnDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysWarnDO::getId));
    }

    /**
     * 通用预警分页查询
     */
//    PageResult<SysWarnDO> selectPage(@Param("reqVO") SysWarnPageReqVO reqVO);
    PageResult<SysWarnDO> getSysWarnPage(SysWarnPageReqVO reqVO);
}
