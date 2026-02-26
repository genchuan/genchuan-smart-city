package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公厕 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PublicToiletMapper extends BaseMapperX<PublicToiletDO> {

    default PageResult<PublicToiletDO> selectPage(PublicToiletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicToiletDO>()
                .eqIfPresent(PublicToiletDO::getToiletId, reqVO.getToiletId())
                .likeIfPresent(PublicToiletDO::getName, reqVO.getName())
                .eqIfPresent(PublicToiletDO::getLocation, reqVO.getLocation())
                .eqIfPresent(PublicToiletDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PublicToiletDO::getOpenHours, reqVO.getOpenHours())
                .eqIfPresent(PublicToiletDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(PublicToiletDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(PublicToiletDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(PublicToiletDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(PublicToiletDO::getComplaintRate, reqVO.getComplaintRate())
                .eqIfPresent(PublicToiletDO::getWarningCount, reqVO.getWarningCount())
                .eqIfPresent(PublicToiletDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(PublicToiletDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(PublicToiletDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(PublicToiletDO::getCleaningContent, reqVO.getCleaningContent())
                .eqIfPresent(PublicToiletDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(PublicToiletDO::getCleanerIds, reqVO.getCleanerIds())
                .eqIfPresent(PublicToiletDO::getConsumableStock, reqVO.getConsumableStock())
                .eqIfPresent(PublicToiletDO::getConsumableThreshold, reqVO.getConsumableThreshold())
                .eqIfPresent(PublicToiletDO::getConsumableGap, reqVO.getConsumableGap())
                .betweenIfPresent(PublicToiletDO::getLastSupplyTime, reqVO.getLastSupplyTime())
                .eqIfPresent(PublicToiletDO::getSupplyCycle, reqVO.getSupplyCycle())
                .eqIfPresent(PublicToiletDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicToiletDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicToiletDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PublicToiletDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PublicToiletDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublicToiletDO::getId));
    }

    List<PublicToiletDetailDO> selectDetailPage(@Param("reqVO") PublicToiletPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") PublicToiletPageReqVO pageReqVO);
}