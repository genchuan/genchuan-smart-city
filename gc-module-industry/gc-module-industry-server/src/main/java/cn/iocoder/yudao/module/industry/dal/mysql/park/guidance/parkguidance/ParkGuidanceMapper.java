package cn.iocoder.yudao.module.industry.dal.mysql.park.guidance.parkguidance;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo.ParkGuidancePageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.guidance.parkguidance.ParkGuidanceDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 停车诱导服务 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkGuidanceMapper extends BaseMapperX<ParkGuidanceDO> {

    default PageResult<ParkGuidanceDO> selectPage(ParkGuidancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkGuidanceDO>()
                .eqIfPresent(ParkGuidanceDO::getGuidanceId, reqVO.getGuidanceId())
                .eqIfPresent(ParkGuidanceDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkGuidanceDO::getUserPosX, reqVO.getUserPosX())
                .eqIfPresent(ParkGuidanceDO::getUserPosY, reqVO.getUserPosY())
                .eqIfPresent(ParkGuidanceDO::getRecommendParkId, reqVO.getRecommendParkId())
                .likeIfPresent(ParkGuidanceDO::getRecommendParkName, reqVO.getRecommendParkName())
                .eqIfPresent(ParkGuidanceDO::getRemainBerths, reqVO.getRemainBerths())
                .betweenIfPresent(ParkGuidanceDO::getEstDriveTime, reqVO.getEstDriveTime())
                .eqIfPresent(ParkGuidanceDO::getChargeStd, reqVO.getChargeStd())
                .eqIfPresent(ParkGuidanceDO::getGuidanceMethod, reqVO.getGuidanceMethod())
                .betweenIfPresent(ParkGuidanceDO::getGuidanceTime, reqVO.getGuidanceTime())
                .eqIfPresent(ParkGuidanceDO::getUserBehavior, reqVO.getUserBehavior())
                .betweenIfPresent(ParkGuidanceDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(ParkGuidanceDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(ParkGuidanceDO::getGridName, reqVO.getGridName())
                .likeIfPresent(ParkGuidanceDO::getMatterName, reqVO.getMatterName())
                .eqIfPresent(ParkGuidanceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkGuidanceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkGuidanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkGuidanceDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkGuidanceDO::getId));
    }

}
