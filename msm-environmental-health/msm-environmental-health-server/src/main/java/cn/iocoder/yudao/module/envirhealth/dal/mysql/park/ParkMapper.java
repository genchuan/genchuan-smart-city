package cn.iocoder.yudao.module.envirhealth.dal.mysql.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail.ParkDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公园 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ParkMapper extends BaseMapperX<ParkDO> {

    default PageResult<ParkDO> selectPage(ParkPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkDO>()
                .eqIfPresent(ParkDO::getParkId, reqVO.getParkId())
                .likeIfPresent(ParkDO::getName, reqVO.getName())
                .eqIfPresent(ParkDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ParkDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(ParkDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .eqIfPresent(ParkDO::getGreenMaintenanceCycle, reqVO.getGreenMaintenanceCycle())
                .eqIfPresent(ParkDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(ParkDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(ParkDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(ParkDO::getGreenSurvivalRate, reqVO.getGreenSurvivalRate())
                .eqIfPresent(ParkDO::getFacilityRate, reqVO.getFacilityRate())
                .eqIfPresent(ParkDO::getEnvironmentRate, reqVO.getEnvironmentRate())
                .eqIfPresent(ParkDO::getWasteTransferCompleteRate, reqVO.getWasteTransferCompleteRate())
                .eqIfPresent(ParkDO::getCleaningArea, reqVO.getCleaningArea())
                .eqIfPresent(ParkDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(ParkDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(ParkDO::getGreenTypeIds, reqVO.getGreenTypeIds())
                .eqIfPresent(ParkDO::getGreenArea, reqVO.getGreenArea())
                .eqIfPresent(ParkDO::getGreenMaintenanceContent, reqVO.getGreenMaintenanceContent())
                .eqIfPresent(ParkDO::getGreenStaffIds, reqVO.getGreenStaffIds())
                .eqIfPresent(ParkDO::getWasteCollectionPoints, reqVO.getWasteCollectionPoints())
                .eqIfPresent(ParkDO::getWasteTransferFrequency, reqVO.getWasteTransferFrequency())
                .betweenIfPresent(ParkDO::getWasteTransferTime, reqVO.getWasteTransferTime())
                .eqIfPresent(ParkDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(ParkDO::getWasteVolume, reqVO.getWasteVolume())
                .eqIfPresent(ParkDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(ParkDO::getFacilityLocation, reqVO.getFacilityLocation())
                .eqIfPresent(ParkDO::getFacilityDamageDesc, reqVO.getFacilityDamageDesc())
                .eqIfPresent(ParkDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(ParkDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(ParkDO::getFacilityPhotoUrl, reqVO.getFacilityPhotoUrl())
                .eqIfPresent(ParkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkDO::getId));
    }

    List<ParkDetailDO> selectDetailPage(@Param("reqVO") ParkPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ParkPageReqVO pageReqVO);
}