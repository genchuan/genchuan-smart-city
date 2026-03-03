package cn.iocoder.yudao.module.facility.dal.mysql.road.roadconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadconfig.vo.RoadConfigRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadconfig.RoadConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 道路监测配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RoadConfigMapper extends BaseMapperX<RoadConfigDO> {

    default PageResult<RoadConfigDO> selectPage(RoadConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadConfigDO>()
                .eqIfPresent(RoadConfigDO::getConfigCode, reqVO.getConfigCode())
                .likeIfPresent(RoadConfigDO::getName, reqVO.getName())
                .eqIfPresent(RoadConfigDO::getRoadId, reqVO.getRoadId())
                .likeIfPresent(RoadConfigDO::getRoadName, reqVO.getRoadName())
                .eqIfPresent(RoadConfigDO::getCollectFrequency, reqVO.getCollectFrequency())
                .eqIfPresent(RoadConfigDO::getPotholeNumThreshold, reqVO.getPotholeNumThreshold())
                .eqIfPresent(RoadConfigDO::getCrackLengthThreshold, reqVO.getCrackLengthThreshold())
                .eqIfPresent(RoadConfigDO::getRoadTempThreshold, reqVO.getRoadTempThreshold())
                .eqIfPresent(RoadConfigDO::getTrafficFlowThreshold, reqVO.getTrafficFlowThreshold())
                .betweenIfPresent(RoadConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RoadConfigDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadConfigDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadConfigDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadConfigDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RoadConfigDO::getId));
    }

    PageResult<RoadConfigRespVO> selectPageWithRoad(
            @Param("reqVO") RoadConfigPageReqVO reqVO
    );
}
