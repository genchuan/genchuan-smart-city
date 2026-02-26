package cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadCleaningDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 道路清扫计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoadCleaningMapper extends BaseMapperX<RoadCleaningDO> {

    default PageResult<RoadCleaningDO> selectPage(RoadCleaningPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadCleaningDO>()
                .eqIfPresent(RoadCleaningDO::getCleaningId, reqVO.getCleaningId())
                .eqIfPresent(RoadCleaningDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(RoadCleaningDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(RoadCleaningDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RoadCleaningDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(RoadCleaningDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(RoadCleaningDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(RoadCleaningDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(RoadCleaningDO::getQualityRate, reqVO.getQualityRate())
                .eqIfPresent(RoadCleaningDO::getProblemCount, reqVO.getProblemCount())
                .eqIfPresent(RoadCleaningDO::getAttendanceRate, reqVO.getAttendanceRate())
                .eqIfPresent(RoadCleaningDO::getToolIds, reqVO.getToolIds())
                .eqIfPresent(RoadCleaningDO::getStandard, reqVO.getStandard())
                .betweenIfPresent(RoadCleaningDO::getCheckinTime, reqVO.getCheckinTime())
                .eqIfPresent(RoadCleaningDO::getProgress, reqVO.getProgress())
                .eqIfPresent(RoadCleaningDO::getOperationStatus, reqVO.getOperationStatus())
                .eqIfPresent(RoadCleaningDO::getTrackCoverage, reqVO.getTrackCoverage())
                .betweenIfPresent(RoadCleaningDO::getLastReportTime, reqVO.getLastReportTime())
                .eqIfPresent(RoadCleaningDO::getIsAbnormal, reqVO.getIsAbnormal())
                .betweenIfPresent(RoadCleaningDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(RoadCleaningDO::getCheckPhotoUrl, reqVO.getCheckPhotoUrl())
                .eqIfPresent(RoadCleaningDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(RoadCleaningDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(RoadCleaningDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(RoadCleaningDO::getReformRequire, reqVO.getReformRequire())
                .eqIfPresent(RoadCleaningDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadCleaningDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadCleaningDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadCleaningDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RoadCleaningDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadCleaningDO::getId));
    }

    List<RoadCleaningDetailDO> selectDetailPage(@Param("reqVO") RoadCleaningPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") RoadCleaningPageReqVO pageReqVO);
}