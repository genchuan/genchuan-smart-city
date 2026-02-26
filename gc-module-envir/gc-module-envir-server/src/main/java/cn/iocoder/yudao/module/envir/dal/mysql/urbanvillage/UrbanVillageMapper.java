package cn.iocoder.yudao.module.envir.dal.mysql.urbanvillage;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.park.ParkDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo.*;

/**
 * 城中村 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface UrbanVillageMapper extends BaseMapperX<UrbanVillageDO> {

    default PageResult<UrbanVillageDO> selectPage(UrbanVillagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UrbanVillageDO>()
                .eqIfPresent(UrbanVillageDO::getUrbanVillageId, reqVO.getUrbanVillageId())
                .likeIfPresent(UrbanVillageDO::getName, reqVO.getName())
                .eqIfPresent(UrbanVillageDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UrbanVillageDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(UrbanVillageDO::getResponsibilityAreas, reqVO.getResponsibilityAreas())
                .eqIfPresent(UrbanVillageDO::getAreaDivideRule, reqVO.getAreaDivideRule())
                .eqIfPresent(UrbanVillageDO::getRoadCleaningFrequency, reqVO.getRoadCleaningFrequency())
                .betweenIfPresent(UrbanVillageDO::getWasteCollectionTime, reqVO.getWasteCollectionTime())
                .eqIfPresent(UrbanVillageDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(UrbanVillageDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(UrbanVillageDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(UrbanVillageDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(UrbanVillageDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(UrbanVillageDO::getProblemRate, reqVO.getProblemRate())
                .eqIfPresent(UrbanVillageDO::getAssessmentScore, reqVO.getAssessmentScore())
                .eqIfPresent(UrbanVillageDO::getProblemPhotoUrl, reqVO.getProblemPhotoUrl())
                .eqIfPresent(UrbanVillageDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UrbanVillageDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(UrbanVillageDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(UrbanVillageDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(UrbanVillageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UrbanVillageDO::getId));
    }

    /**
     * 联表查询城中村详情列表
     * @return 详情列表
     */
    List<UrbanVillageDetailDO> selectListDetail();
}