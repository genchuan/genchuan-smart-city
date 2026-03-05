package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletfacilityrepair.ToiletFacilityRepairPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletFacilityRepairDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletComplaintDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletFacilityRepairDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公厕设施维修 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ToiletFacilityRepairMapper extends BaseMapperX<ToiletFacilityRepairDO> {

    default PageResult<ToiletFacilityRepairDO> selectPage(ToiletFacilityRepairPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletFacilityRepairDO>()
                .eqIfPresent(ToiletFacilityRepairDO::getRepairId, reqVO.getRepairId())
                .eqIfPresent(ToiletFacilityRepairDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletFacilityRepairDO::getFacilityId, reqVO.getFacilityId())
                .eqIfPresent(ToiletFacilityRepairDO::getDamageDesc, reqVO.getDamageDesc())
                .eqIfPresent(ToiletFacilityRepairDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(ToiletFacilityRepairDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(ToiletFacilityRepairDO::getPhotoUrl, reqVO.getPhotoUrl())
                .eqIfPresent(ToiletFacilityRepairDO::getRepairBy, reqVO.getRepairBy())
                .eqIfPresent(ToiletFacilityRepairDO::getRepairStatus, reqVO.getRepairStatus())
                .betweenIfPresent(ToiletFacilityRepairDO::getExpectedCompleteTime, reqVO.getExpectedCompleteTime())
                .eqIfPresent(ToiletFacilityRepairDO::getAcceptResult, reqVO.getAcceptResult())
                .eqIfPresent(ToiletFacilityRepairDO::getAcceptOpinion, reqVO.getAcceptOpinion())
                .betweenIfPresent(ToiletFacilityRepairDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletFacilityRepairDO::getId));
    }

    /**
     * 查询全局最大序号（用于repair_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(repair_id, '-', -1)), 0) FROM public_toilet_facility_repair")
    Integer selectMaxSeq();

    List<ToiletFacilityRepairDetailDO> selectDetailPage(@Param("reqVO") ToiletFacilityRepairPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletFacilityRepairPageReqVO pageReqVO);

    /**
     * 统计待维修的设施数量
     * 待维修：repair_status IN ('待维修', '维修中', '不合格')
     */
    @Select("SELECT COUNT(*) FROM public_toilet_facility_repair " +
            "WHERE deleted = 0 " +
            "AND repair_status IN ('待维修', '维修中', '不合格')")
    Long countPendingRepair();
}