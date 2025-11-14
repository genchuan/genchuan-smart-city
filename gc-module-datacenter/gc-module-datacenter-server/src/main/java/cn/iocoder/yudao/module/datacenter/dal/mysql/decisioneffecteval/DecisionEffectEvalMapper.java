package cn.iocoder.yudao.module.datacenter.dal.mysql.decisioneffecteval;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisioneffecteval.DecisionEffectEvalDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval.vo.*;

/**
 * 决策效果评估 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DecisionEffectEvalMapper extends BaseMapperX<DecisionEffectEvalDO> {

    default PageResult<DecisionEffectEvalDO> selectPage(DecisionEffectEvalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DecisionEffectEvalDO>()
                .eqIfPresent(DecisionEffectEvalDO::getEffectEvalId, reqVO.getEffectEvalId())
                .eqIfPresent(DecisionEffectEvalDO::getRelSchemeId, reqVO.getRelSchemeId())
                .likeIfPresent(DecisionEffectEvalDO::getRelSchemeName, reqVO.getRelSchemeName())
                .eqIfPresent(DecisionEffectEvalDO::getImplementationCycle, reqVO.getImplementationCycle())
                .eqIfPresent(DecisionEffectEvalDO::getExpectedEffect, reqVO.getExpectedEffect())
                .eqIfPresent(DecisionEffectEvalDO::getActualEffect, reqVO.getActualEffect())
                .eqIfPresent(DecisionEffectEvalDO::getEffectAchievementRate, reqVO.getEffectAchievementRate())
                .eqIfPresent(DecisionEffectEvalDO::getResourceTotalInput, reqVO.getResourceTotalInput())
                .eqIfPresent(DecisionEffectEvalDO::getInputReturnRate, reqVO.getInputReturnRate())
                .eqIfPresent(DecisionEffectEvalDO::getUserSatisfy, reqVO.getUserSatisfy())
                .eqIfPresent(DecisionEffectEvalDO::getEvalGrade, reqVO.getEvalGrade())
                .eqIfPresent(DecisionEffectEvalDO::getSuccessExp, reqVO.getSuccessExp())
                .eqIfPresent(DecisionEffectEvalDO::getImprovePts, reqVO.getImprovePts())
                .eqIfPresent(DecisionEffectEvalDO::getEvalUser, reqVO.getEvalUser())
                .betweenIfPresent(DecisionEffectEvalDO::getEvalTime, reqVO.getEvalTime())
                .eqIfPresent(DecisionEffectEvalDO::getEvalRptAttach, reqVO.getEvalRptAttach())
                .eqIfPresent(DecisionEffectEvalDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DecisionEffectEvalDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DecisionEffectEvalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DecisionEffectEvalDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DecisionEffectEvalDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DecisionEffectEvalDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DecisionEffectEvalDO::getId));
    }

}