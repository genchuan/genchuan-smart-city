package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectrouteinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectrouteinfo.vo.InspectRouteInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectrouteinfo.InspectRouteInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检路线信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRouteInfoMapper extends BaseMapperX<InspectRouteInfoDO> {

    default PageResult<InspectRouteInfoDO> selectPage(InspectRouteInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRouteInfoDO>()
                .eqIfPresent(InspectRouteInfoDO::getRouteId, reqVO.getRouteId())
                .likeIfPresent(InspectRouteInfoDO::getRouteName, reqVO.getRouteName())
                .eqIfPresent(InspectRouteInfoDO::getRouteCode, reqVO.getRouteCode())
                .eqIfPresent(InspectRouteInfoDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectRouteInfoDO::getAreaName, reqVO.getAreaName())
                .likeIfPresent(InspectRouteInfoDO::getStartPtName, reqVO.getStartPtName())
                .eqIfPresent(InspectRouteInfoDO::getStartPtX, reqVO.getStartPtX())
                .eqIfPresent(InspectRouteInfoDO::getStartPtY, reqVO.getStartPtY())
                .likeIfPresent(InspectRouteInfoDO::getEndPtName, reqVO.getEndPtName())
                .eqIfPresent(InspectRouteInfoDO::getEndPtX, reqVO.getEndPtX())
                .eqIfPresent(InspectRouteInfoDO::getEndPtY, reqVO.getEndPtY())
                .eqIfPresent(InspectRouteInfoDO::getPassGridIds, reqVO.getPassGridIds())
                .eqIfPresent(InspectRouteInfoDO::getPassGridNames, reqVO.getPassGridNames())
                .eqIfPresent(InspectRouteInfoDO::getEstimateEndure, reqVO.getEstimateEndure())
                .eqIfPresent(InspectRouteInfoDO::getInspectFrequency, reqVO.getInspectFrequency())
                .eqIfPresent(InspectRouteInfoDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectRouteInfoDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectRouteInfoDO::getRouteStatus, reqVO.getRouteStatus())
                .eqIfPresent(InspectRouteInfoDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectRouteInfoDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectRouteInfoDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectRouteInfoDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectRouteInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRouteInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRouteInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRouteInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRouteInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRouteInfoDO::getId));
    }

}