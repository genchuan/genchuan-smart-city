package cn.iocoder.yudao.module.envirhealth.dal.mysql.river;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.detail.RiverDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 河道 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RiverMapper extends BaseMapperX<RiverDO> {

    default PageResult<RiverDO> selectPage(RiverPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiverDO>()
                .eqIfPresent(RiverDO::getRiverId, reqVO.getRiverId())
                .likeIfPresent(RiverDO::getName, reqVO.getName())
                .eqIfPresent(RiverDO::getResponsibilitySection, reqVO.getResponsibilitySection())
                .eqIfPresent(RiverDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RiverDO::getLength, reqVO.getLength())
                .eqIfPresent(RiverDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(RiverDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(RiverDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(RiverDO::getWaterQualityRate, reqVO.getWaterQualityRate())
                .eqIfPresent(RiverDO::getWasteFishingVolume, reqVO.getWasteFishingVolume())
                .eqIfPresent(RiverDO::getProblemCompleteRate, reqVO.getProblemCompleteRate())
                .eqIfPresent(RiverDO::getCleaningTypeId, reqVO.getCleaningTypeId())
                .eqIfPresent(RiverDO::getWaterCleaningFrequency, reqVO.getWaterCleaningFrequency())
                .betweenIfPresent(RiverDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(RiverDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(RiverDO::getToolIds, reqVO.getToolIds())
                .eqIfPresent(RiverDO::getWasteFishingEstimate, reqVO.getWasteFishingEstimate())
                .eqIfPresent(RiverDO::getMonitorTypeId, reqVO.getMonitorTypeId())
                .eqIfPresent(RiverDO::getWaterQualityCycle, reqVO.getWaterQualityCycle())
                .eqIfPresent(RiverDO::getMonitorIndicators, reqVO.getMonitorIndicators())
                .eqIfPresent(RiverDO::getMonitorBy, reqVO.getMonitorBy())
                .betweenIfPresent(RiverDO::getPlanMonitorTime, reqVO.getPlanMonitorTime())
                .eqIfPresent(RiverDO::getMonitorStatusId, reqVO.getMonitorStatusId())
                .betweenIfPresent(RiverDO::getLastMonitorTime, reqVO.getLastMonitorTime())
                .betweenIfPresent(RiverDO::getNextMonitorRemindTime, reqVO.getNextMonitorRemindTime())
                .eqIfPresent(RiverDO::getMonitorDataQualifiedRate, reqVO.getMonitorDataQualifiedRate())
                .eqIfPresent(RiverDO::getWarningCount, reqVO.getWarningCount())
                .eqIfPresent(RiverDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(RiverDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(RiverDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(RiverDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(RiverDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(RiverDO::getProblemMediaUrl, reqVO.getProblemMediaUrl())
                .eqIfPresent(RiverDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(RiverDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(RiverDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(RiverDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(RiverDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(RiverDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RiverDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RiverDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RiverDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RiverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RiverDO::getId));
    }

    List<RiverDetailDO> selectDetailPage(@Param("reqVO") RiverPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") RiverPageReqVO pageReqVO);
}