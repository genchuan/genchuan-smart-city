package cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.UrbanVillagePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.detail.UrbanVillageDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 城中村 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UrbanVillageMapper extends BaseMapperX<UrbanVillageDO> {

    default PageResult<UrbanVillageDO> selectPage(UrbanVillagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UrbanVillageDO>()
                .eqIfPresent(UrbanVillageDO::getVillageId, reqVO.getVillageId())
                .likeIfPresent(UrbanVillageDO::getName, reqVO.getName())
                .eqIfPresent(UrbanVillageDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UrbanVillageDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(UrbanVillageDO::getResponsibilityAreas, reqVO.getResponsibilityAreas())
                .eqIfPresent(UrbanVillageDO::getRoadCleaningFrequency, reqVO.getRoadCleaningFrequency())
                .eqIfPresent(UrbanVillageDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(UrbanVillageDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(UrbanVillageDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(UrbanVillageDO::getProblemRate, reqVO.getProblemRate())
                .eqIfPresent(UrbanVillageDO::getReviewPassRate, reqVO.getReviewPassRate())
                .eqIfPresent(UrbanVillageDO::getAssessmentScore, reqVO.getAssessmentScore())
                .likeIfPresent(UrbanVillageDO::getResponsibilityAreaName, reqVO.getResponsibilityAreaName())
                .eqIfPresent(UrbanVillageDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(UrbanVillageDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(UrbanVillageDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(UrbanVillageDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(UrbanVillageDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(UrbanVillageDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(UrbanVillageDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(UrbanVillageDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(UrbanVillageDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(UrbanVillageDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(UrbanVillageDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(UrbanVillageDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(UrbanVillageDO::getHandleDesc, reqVO.getHandleDesc())
                .eqIfPresent(UrbanVillageDO::getReformPhotoUrl, reqVO.getReformPhotoUrl())
                .eqIfPresent(UrbanVillageDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(UrbanVillageDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(UrbanVillageDO::getReviewResultId, reqVO.getReviewResultId())
                .eqIfPresent(UrbanVillageDO::getReviewOpinion, reqVO.getReviewOpinion())
                .betweenIfPresent(UrbanVillageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UrbanVillageDO::getId));
    }

    /**
     * 查询全局最大序号（用于village_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(village_id, '-', -1)), 0) FROM urban_village")
    Integer selectMaxSeq();

    List<UrbanVillageDetailDO> selectDetailPage(@Param("reqVO") UrbanVillagePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") UrbanVillagePageReqVO pageReqVO);
}