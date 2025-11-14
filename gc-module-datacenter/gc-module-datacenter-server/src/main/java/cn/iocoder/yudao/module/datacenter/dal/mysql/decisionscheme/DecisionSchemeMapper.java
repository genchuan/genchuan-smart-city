package cn.iocoder.yudao.module.datacenter.dal.mysql.decisionscheme;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionscheme.DecisionSchemeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisionscheme.vo.*;

/**
 * 决策方案生成 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DecisionSchemeMapper extends BaseMapperX<DecisionSchemeDO> {

    default PageResult<DecisionSchemeDO> selectPage(DecisionSchemePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DecisionSchemeDO>()
                .eqIfPresent(DecisionSchemeDO::getDecisionSchemeId, reqVO.getDecisionSchemeId())
                .likeIfPresent(DecisionSchemeDO::getSchemeName, reqVO.getSchemeName())
                .eqIfPresent(DecisionSchemeDO::getRelAnalysisId, reqVO.getRelAnalysisId())
                .eqIfPresent(DecisionSchemeDO::getRelAnalysisType, reqVO.getRelAnalysisType())
                .eqIfPresent(DecisionSchemeDO::getSchemeGoal, reqVO.getSchemeGoal())
                .eqIfPresent(DecisionSchemeDO::getImplementationMeasures, reqVO.getImplementationMeasures())
                .eqIfPresent(DecisionSchemeDO::getResDemand, reqVO.getResDemand())
                .eqIfPresent(DecisionSchemeDO::getExpectedEffect, reqVO.getExpectedEffect())
                .eqIfPresent(DecisionSchemeDO::getSchemeStatus, reqVO.getSchemeStatus())
                .eqIfPresent(DecisionSchemeDO::getApplyUserId, reqVO.getApplyUserId())
                .likeIfPresent(DecisionSchemeDO::getApplyUserName, reqVO.getApplyUserName())
                .betweenIfPresent(DecisionSchemeDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(DecisionSchemeDO::getAuditUserId, reqVO.getAuditUserId())
                .likeIfPresent(DecisionSchemeDO::getAuditUserName, reqVO.getAuditUserName())
                .betweenIfPresent(DecisionSchemeDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(DecisionSchemeDO::getAuditOpinion, reqVO.getAuditOpinion())
                .eqIfPresent(DecisionSchemeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DecisionSchemeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DecisionSchemeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DecisionSchemeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DecisionSchemeDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DecisionSchemeDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(DecisionSchemeDO::getId));
    }

}