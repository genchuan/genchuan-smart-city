package cn.iocoder.yudao.module.datacenter.dal.mysql.routeversion;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.routeversion.RouteVersionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.routeversion.vo.*;

/**
 * 路线版本 Mapper
 *
 * @author zcq
 */
@Mapper
public interface RouteVersionMapper extends BaseMapperX<RouteVersionDO> {

    default PageResult<RouteVersionDO> selectPage(RouteVersionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RouteVersionDO>()
                .likeIfPresent(RouteVersionDO::getRouteId, reqVO.getRouteId())
                .likeIfPresent(RouteVersionDO::getRouteName, reqVO.getRouteName())
                .likeIfPresent(RouteVersionDO::getVersionNumber, reqVO.getVersionNumber())
                .likeIfPresent(RouteVersionDO::getVersionDescription, reqVO.getVersionDescription())
                .likeIfPresent(RouteVersionDO::getChangeReason, reqVO.getChangeReason())
                .likeIfPresent(RouteVersionDO::getChangeContent, reqVO.getChangeContent())
                .betweenIfPresent(RouteVersionDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(RouteVersionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RouteVersionDO::getId));
    }

}