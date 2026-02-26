package cn.iocoder.yudao.module.envir.dal.mysql.road;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.road.RoadDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.road.vo.*;

/**
 * 道路 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoadMapper extends BaseMapperX<RoadDO> {

    default PageResult<RoadDO> selectPage(RoadPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadDO>()
                .eqIfPresent(RoadDO::getSysRoadId, reqVO.getSysRoadId())
                .likeIfPresent(RoadDO::getName, reqVO.getName())
                .eqIfPresent(RoadDO::getCode, reqVO.getCode())
                .eqIfPresent(RoadDO::getLength, reqVO.getLength())
                .eqIfPresent(RoadDO::getWidth, reqVO.getWidth())
                .eqIfPresent(RoadDO::getRoadType, reqVO.getRoadType())
                .eqIfPresent(RoadDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RoadDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RoadDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RoadDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadDO::getId));
    }

}