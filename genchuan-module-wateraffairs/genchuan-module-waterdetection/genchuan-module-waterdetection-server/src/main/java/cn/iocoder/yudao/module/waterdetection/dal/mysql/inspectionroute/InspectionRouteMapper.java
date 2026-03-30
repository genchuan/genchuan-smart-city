package cn.iocoder.yudao.module.waterdetection.dal.mysql.inspectionroute;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.inspectionroute.InspectionRouteDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.inspectionroute.vo.*;

/**
 * 巡检路线规划与优化 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectionRouteMapper extends BaseMapperX<InspectionRouteDO> {

    default PageResult<InspectionRouteDO> selectPage(InspectionRoutePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionRouteDO>()
                .eqIfPresent(InspectionRouteDO::getRouteId, reqVO.getRouteId())
                .eqIfPresent(InspectionRouteDO::getInspectionPointId, reqVO.getInspectionPointId())
                .eqIfPresent(InspectionRouteDO::getPointType, reqVO.getPointType())
                .eqIfPresent(InspectionRouteDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(InspectionRouteDO::getLatitude, reqVO.getLatitude())
                .betweenIfPresent(InspectionRouteDO::getEstimatedArrivalTime, reqVO.getEstimatedArrivalTime())
                .betweenIfPresent(InspectionRouteDO::getActualArrivalTime, reqVO.getActualArrivalTime())
                .eqIfPresent(InspectionRouteDO::getRouteAdjustReason, reqVO.getRouteAdjustReason())
                .betweenIfPresent(InspectionRouteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionRouteDO::getId));
    }

}