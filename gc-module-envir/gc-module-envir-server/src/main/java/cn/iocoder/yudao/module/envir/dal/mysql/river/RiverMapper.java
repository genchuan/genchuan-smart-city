package cn.iocoder.yudao.module.envir.dal.mysql.river;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.river.RiverDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.user.UserDO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.river.vo.*;

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
                .eqIfPresent(RiverDO::getWaterCleaningFrequency, reqVO.getWaterCleaningFrequency())
                .eqIfPresent(RiverDO::getLandCleaningFrequency, reqVO.getLandCleaningFrequency())
                .eqIfPresent(RiverDO::getWaterQualityCycle, reqVO.getWaterQualityCycle())
                .eqIfPresent(RiverDO::getWaterQualityData, reqVO.getWaterQualityData())
                .eqIfPresent(RiverDO::getPollutionSourceId, reqVO.getPollutionSourceId())
                .eqIfPresent(RiverDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(RiverDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(RiverDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(RiverDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(RiverDO::getWasteFishingVolume, reqVO.getWasteFishingVolume())
                .eqIfPresent(RiverDO::getCleaningCoverage, reqVO.getCleaningCoverage())
                .eqIfPresent(RiverDO::getWaterQualityRate, reqVO.getWaterQualityRate())
                .eqIfPresent(RiverDO::getFishingPhotoUrl, reqVO.getFishingPhotoUrl())
                .eqIfPresent(RiverDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RiverDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RiverDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RiverDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RiverDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RiverDO::getId));
    }

    default List<RiverDetailDO> selectListDetail() {
        return selectJoinList(RiverDetailDO.class, new MPJLambdaWrapper<RiverDO>()
                .selectAll(RiverDO.class)
                .selectAs(AreaDO::getAreaName, RiverDetailDO::getAreaName)
                .selectAs(UserDO::getUserName, RiverDetailDO::getManagerName)
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, RiverDO::getAreaCode)
                .leftJoin(UserDO.class, UserDO::getUserId, RiverDO::getManagerId)
        );
    }
}