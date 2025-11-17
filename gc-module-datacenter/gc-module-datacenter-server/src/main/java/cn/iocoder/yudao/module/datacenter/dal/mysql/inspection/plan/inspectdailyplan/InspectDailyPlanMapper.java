package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectdailyplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectdailyplan.InspectDailyPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日常巡查计划信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectDailyPlanMapper extends BaseMapperX<InspectDailyPlanDO> {

    default PageResult<InspectDailyPlanDO> selectPage(InspectDailyPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectDailyPlanDO>()
                .eqIfPresent(InspectDailyPlanDO::getDailyPlanId, reqVO.getDailyPlanId())
                .likeIfPresent(InspectDailyPlanDO::getDailyPlanName, reqVO.getDailyPlanName())
                .eqIfPresent(InspectDailyPlanDO::getDailyPlanCode, reqVO.getDailyPlanCode())
                .eqIfPresent(InspectDailyPlanDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectDailyPlanDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectDailyPlanDO::getRouteId, reqVO.getRouteId())
                .likeIfPresent(InspectDailyPlanDO::getRouteName, reqVO.getRouteName())
                .eqIfPresent(InspectDailyPlanDO::getStdId, reqVO.getStdId())
                .likeIfPresent(InspectDailyPlanDO::getStdName, reqVO.getStdName())
                .eqIfPresent(InspectDailyPlanDO::getPersonId, reqVO.getPersonId())
                .likeIfPresent(InspectDailyPlanDO::getPersonName, reqVO.getPersonName())
                .eqIfPresent(InspectDailyPlanDO::getPlanCycle, reqVO.getPlanCycle())
                .betweenIfPresent(InspectDailyPlanDO::getExecuteStartTime, reqVO.getExecuteStartTime())
                .betweenIfPresent(InspectDailyPlanDO::getExecuteEndTime, reqVO.getExecuteEndTime())
                .eqIfPresent(InspectDailyPlanDO::getPlanStatus, reqVO.getPlanStatus())
                .betweenIfPresent(InspectDailyPlanDO::getStartDate, reqVO.getStartDate())
                .betweenIfPresent(InspectDailyPlanDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(InspectDailyPlanDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectDailyPlanDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectDailyPlanDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectDailyPlanDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectDailyPlanDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectDailyPlanDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectDailyPlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectDailyPlanDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectDailyPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectDailyPlanDO::getId));
    }

}