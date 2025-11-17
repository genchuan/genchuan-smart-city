package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.plan.inspectemerplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectemerplan.InspectEmerPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 应急巡查计划信息 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectEmerPlanMapper extends BaseMapperX<InspectEmerPlanDO> {

    default PageResult<InspectEmerPlanDO> selectPage(InspectEmerPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectEmerPlanDO>()
                .eqIfPresent(InspectEmerPlanDO::getEmerPlanId, reqVO.getEmerPlanId())
                .likeIfPresent(InspectEmerPlanDO::getEmerPlanName, reqVO.getEmerPlanName())
                .eqIfPresent(InspectEmerPlanDO::getEmerPlanCode, reqVO.getEmerPlanCode())
                .eqIfPresent(InspectEmerPlanDO::getEmerTopic, reqVO.getEmerTopic())
                .eqIfPresent(InspectEmerPlanDO::getEmerEvtId, reqVO.getEmerEvtId())
                .likeIfPresent(InspectEmerPlanDO::getEmerEvtName, reqVO.getEmerEvtName())
                .eqIfPresent(InspectEmerPlanDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectEmerPlanDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectEmerPlanDO::getStdId, reqVO.getStdId())
                .likeIfPresent(InspectEmerPlanDO::getStdName, reqVO.getStdName())
                .eqIfPresent(InspectEmerPlanDO::getPersonIds, reqVO.getPersonIds())
                .eqIfPresent(InspectEmerPlanDO::getPersonNames, reqVO.getPersonNames())
                .betweenIfPresent(InspectEmerPlanDO::getExecuteTime, reqVO.getExecuteTime())
                .eqIfPresent(InspectEmerPlanDO::getExecuteDeadline, reqVO.getExecuteDeadline())
                .eqIfPresent(InspectEmerPlanDO::getPlanStatus, reqVO.getPlanStatus())
                .eqIfPresent(InspectEmerPlanDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectEmerPlanDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectEmerPlanDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectEmerPlanDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectEmerPlanDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectEmerPlanDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectEmerPlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectEmerPlanDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectEmerPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectEmerPlanDO::getId));
    }

}