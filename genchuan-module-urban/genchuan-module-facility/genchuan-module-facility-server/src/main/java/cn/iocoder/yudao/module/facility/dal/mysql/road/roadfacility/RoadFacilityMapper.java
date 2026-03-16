package cn.iocoder.yudao.module.facility.dal.mysql.road.roadfacility;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadfacility.vo.RoadFacilityPageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadfacility.RoadFacilityDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 道路设施 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RoadFacilityMapper extends BaseMapperX<RoadFacilityDO> {

    default PageResult<RoadFacilityDO> selectPage(RoadFacilityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadFacilityDO>()
                .eqIfPresent(RoadFacilityDO::getRoadCode, reqVO.getRoadCode())
                .likeIfPresent(RoadFacilityDO::getRoadName, reqVO.getRoadName())
                .eqIfPresent(RoadFacilityDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(RoadFacilityDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(RoadFacilityDO::getLength, reqVO.getLength())
                .eqIfPresent(RoadFacilityDO::getWidth, reqVO.getWidth())
                .betweenIfPresent(RoadFacilityDO::getBuildTime, reqVO.getBuildTime())
                .eqIfPresent(RoadFacilityDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RoadFacilityDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(RoadFacilityDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadFacilityDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadFacilityDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadFacilityDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(RoadFacilityDO::getId));
    }

}
