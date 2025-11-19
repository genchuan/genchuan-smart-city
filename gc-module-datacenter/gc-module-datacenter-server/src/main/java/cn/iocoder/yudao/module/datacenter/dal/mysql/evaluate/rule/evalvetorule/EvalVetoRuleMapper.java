package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalvetorule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalvetorule.EvalVetoRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 否决项规则管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalVetoRuleMapper extends BaseMapperX<EvalVetoRuleDO> {

    default PageResult<EvalVetoRuleDO> selectPage(EvalVetoRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalVetoRuleDO>()
                .eqIfPresent(EvalVetoRuleDO::getVetoRuleId, reqVO.getVetoRuleId())
                .likeIfPresent(EvalVetoRuleDO::getVetoRuleName, reqVO.getVetoRuleName())
                .eqIfPresent(EvalVetoRuleDO::getVetoRuleCode, reqVO.getVetoRuleCode())
                .eqIfPresent(EvalVetoRuleDO::getApplyObjectType, reqVO.getApplyObjectType())
                .eqIfPresent(EvalVetoRuleDO::getVetoCondition, reqVO.getVetoCondition())
                .eqIfPresent(EvalVetoRuleDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalVetoRuleDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalVetoRuleDO::getIdxThreshold, reqVO.getIdxThreshold())
                .eqIfPresent(EvalVetoRuleDO::getValidCycle, reqVO.getValidCycle())
                .eqIfPresent(EvalVetoRuleDO::getVetoResult, reqVO.getVetoResult())
                .eqIfPresent(EvalVetoRuleDO::getVetoDesc, reqVO.getVetoDesc())
                .eqIfPresent(EvalVetoRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalVetoRuleDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalVetoRuleDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalVetoRuleDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalVetoRuleDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalVetoRuleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalVetoRuleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalVetoRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalVetoRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalVetoRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalVetoRuleDO::getId));
    }

}