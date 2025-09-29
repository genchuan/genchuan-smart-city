package cn.iocoder.yudao.module.datacenter.dal.mysql.patrolroute;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.patrolroute.PatrolRouteDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.patrolroute.vo.*;

/**
 * 巡查路线 Mapper
 *
 * @author zcq
 */
@Mapper
public interface PatrolRouteMapper extends BaseMapperX<PatrolRouteDO> {

    default PageResult<PatrolRouteDO> selectPage(PatrolRoutePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PatrolRouteDO>()
                .likeIfPresent(PatrolRouteDO::getRouteName, reqVO.getRouteName())
                .eqIfPresent(PatrolRouteDO::getRouteCode, reqVO.getRouteCode())
                .eqIfPresent(PatrolRouteDO::getAreaId, reqVO.getAreaId())
                .likeIfPresent(PatrolRouteDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(PatrolRouteDO::getRouteType, reqVO.getRouteType())
                .eqIfPresent(PatrolRouteDO::getRelatedPointIds, reqVO.getRelatedPointIds())
                .eqIfPresent(PatrolRouteDO::getPointNameList, reqVO.getPointNameList())
                .eqIfPresent(PatrolRouteDO::getRouteLength, reqVO.getRouteLength())
                .betweenIfPresent(PatrolRouteDO::getEstimatedTime, reqVO.getEstimatedTime())
                .eqIfPresent(PatrolRouteDO::getRouteDescription, reqVO.getRouteDescription())
                .eqIfPresent(PatrolRouteDO::getEnabledStatus, reqVO.getEnabledStatus())
                .betweenIfPresent(PatrolRouteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PatrolRouteDO::getId));
    }

}