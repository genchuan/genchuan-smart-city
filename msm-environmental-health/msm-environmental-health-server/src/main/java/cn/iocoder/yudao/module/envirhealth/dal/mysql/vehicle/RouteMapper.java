package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.route.RoutePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.RouteDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 路线 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RouteMapper extends BaseMapperX<RouteDO> {

    default PageResult<RouteDO> selectPage(RoutePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RouteDO>()
                .eqIfPresent(RouteDO::getSysRouteId, reqVO.getSysRouteId())
                .likeIfPresent(RouteDO::getName, reqVO.getName())
                .eqIfPresent(RouteDO::getCode, reqVO.getCode())
                .eqIfPresent(RouteDO::getStartPoint, reqVO.getStartPoint())
                .eqIfPresent(RouteDO::getEndPoint, reqVO.getEndPoint())
                .eqIfPresent(RouteDO::getRoutePoints, reqVO.getRoutePoints())
                .eqIfPresent(RouteDO::getLength, reqVO.getLength())
                .eqIfPresent(RouteDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RouteDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RouteDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RouteDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RouteDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RouteDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RouteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RouteDO::getId));
    }

}