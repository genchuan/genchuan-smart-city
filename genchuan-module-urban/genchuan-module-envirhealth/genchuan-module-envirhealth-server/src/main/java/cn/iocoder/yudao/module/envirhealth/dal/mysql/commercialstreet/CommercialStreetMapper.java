package cn.iocoder.yudao.module.envirhealth.dal.mysql.commercialstreet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.commercialstreet.vo.CommercialStreetPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet.detail.CommercialStreetDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商业街 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CommercialStreetMapper extends BaseMapperX<CommercialStreetDO> {

    default PageResult<CommercialStreetDO> selectPage(CommercialStreetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommercialStreetDO>()
                .eqIfPresent(CommercialStreetDO::getStreetId, reqVO.getStreetId())
                .likeIfPresent(CommercialStreetDO::getName, reqVO.getName())
                .eqIfPresent(CommercialStreetDO::getAddress, reqVO.getAddress())
                .eqIfPresent(CommercialStreetDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(CommercialStreetDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(CommercialStreetDO::getTransferInterval, reqVO.getTransferInterval())
                .eqIfPresent(CommercialStreetDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(CommercialStreetDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(CommercialStreetDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(CommercialStreetDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(CommercialStreetDO::getDisposalDuration, reqVO.getDisposalDuration())
                .eqIfPresent(CommercialStreetDO::getCollectionCompleteRate, reqVO.getCollectionCompleteRate())
                .eqIfPresent(CommercialStreetDO::getPatrolInterval, reqVO.getPatrolInterval())
                .betweenIfPresent(CommercialStreetDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(CommercialStreetDO::getCleanerIds, reqVO.getCleanerIds())
                .eqIfPresent(CommercialStreetDO::getResponsibilityArea, reqVO.getResponsibilityArea())
                .eqIfPresent(CommercialStreetDO::getCollectionPoints, reqVO.getCollectionPoints())
                .eqIfPresent(CommercialStreetDO::getAbnormalCount, reqVO.getAbnormalCount())
                .eqIfPresent(CommercialStreetDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(CommercialStreetDO::getFacilityLocation, reqVO.getFacilityLocation())
                .eqIfPresent(CommercialStreetDO::getDamageDesc, reqVO.getDamageDesc())
                .eqIfPresent(CommercialStreetDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(CommercialStreetDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(CommercialStreetDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(CommercialStreetDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(CommercialStreetDO::getDispatchTime, reqVO.getDispatchTime())
                .eqIfPresent(CommercialStreetDO::getMaintainStatusId, reqVO.getMaintainStatusId())
                .betweenIfPresent(CommercialStreetDO::getExpectedCompleteTime, reqVO.getExpectedCompleteTime())
                .eqIfPresent(CommercialStreetDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(CommercialStreetDO::getProblemLocation, reqVO.getProblemLocation())
                .eqIfPresent(CommercialStreetDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(CommercialStreetDO::getHandleStatusId, reqVO.getHandleStatusId())
                .eqIfPresent(CommercialStreetDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(CommercialStreetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CommercialStreetDO::getId));
    }

    /**
     * 查询全局最大序号（用于street_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(street_id, '-', -1)), 0) FROM commercial_street")
    Integer selectMaxSeq();

    List<CommercialStreetDetailDO> selectDetailPage(@Param("reqVO") CommercialStreetPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") CommercialStreetPageReqVO pageReqVO);
}