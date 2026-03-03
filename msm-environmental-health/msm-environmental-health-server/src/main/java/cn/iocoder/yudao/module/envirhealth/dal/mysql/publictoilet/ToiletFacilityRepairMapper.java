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
                .eqIfPresent(ToiletFacilityRepairDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ToiletFacilityRepairDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ToiletFacilityRepairDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ToiletFacilityRepairDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ToiletFacilityRepairDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletFacilityRepairDO::getId));
    }

    List<ToiletFacilityRepairDetailDO> selectDetailPage(@Param("reqVO") ToiletFacilityRepairPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletFacilityRepairPageReqVO pageReqVO);
}