package cn.iocoder.yudao.module.envirhealth.dal.mysql.park;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail.ParkDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
                .betweenIfPresent(ParkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkDO::getId));
    }

    /**
     * 查询全局最大序号（用于park_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(park_id, '-', -1)), 0) FROM park")
    Integer selectMaxSeq();

    List<ParkDetailDO> selectDetailPage(@Param("reqVO") ParkPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ParkPageReqVO pageReqVO);
}