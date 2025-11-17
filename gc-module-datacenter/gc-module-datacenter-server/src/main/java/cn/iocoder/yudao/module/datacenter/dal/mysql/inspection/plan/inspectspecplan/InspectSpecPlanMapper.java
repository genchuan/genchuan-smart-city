package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectspecplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectspecplan.vo.InspectSpecPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectspecplan.InspectSpecPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专项巡查计划信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSpecPlanMapper extends BaseMapperX<InspectSpecPlanDO> {

    default PageResult<InspectSpecPlanDO> selectPage(InspectSpecPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSpecPlanDO>()
                .eqIfPresent(InspectSpecPlanDO::getSpecPlanId, reqVO.getSpecPlanId())
                .likeIfPresent(InspectSpecPlanDO::getSpecPlanName, reqVO.getSpecPlanName())
                .eqIfPresent(InspectSpecPlanDO::getSpecPlanCode, reqVO.getSpecPlanCode())
                .eqIfPresent(InspectSpecPlanDO::getSpecTopic, reqVO.getSpecTopic())
                .eqIfPresent(InspectSpecPlanDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectSpecPlanDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectSpecPlanDO::getStdId, reqVO.getStdId())
                .likeIfPresent(InspectSpecPlanDO::getStdName, reqVO.getStdName())
                .eqIfPresent(InspectSpecPlanDO::getPersonIds, reqVO.getPersonIds())
                .eqIfPresent(InspectSpecPlanDO::getPersonNames, reqVO.getPersonNames())
                .betweenIfPresent(InspectSpecPlanDO::getExecuteStartDate, reqVO.getExecuteStartDate())
                .betweenIfPresent(InspectSpecPlanDO::getExecuteEndDate, reqVO.getExecuteEndDate())
                .betweenIfPresent(InspectSpecPlanDO::getDailyExecuteTime, reqVO.getDailyExecuteTime())
                .eqIfPresent(InspectSpecPlanDO::getPlanStatus, reqVO.getPlanStatus())
                .eqIfPresent(InspectSpecPlanDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectSpecPlanDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectSpecPlanDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectSpecPlanDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectSpecPlanDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectSpecPlanDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectSpecPlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectSpecPlanDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectSpecPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSpecPlanDO::getId));
    }

}