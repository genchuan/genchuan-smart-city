package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalruleitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalruleitem.EvalRuleItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则项管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRuleItemMapper extends BaseMapperX<EvalRuleItemDO> {

    default PageResult<EvalRuleItemDO> selectPage(EvalRuleItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRuleItemDO>()
                .eqIfPresent(EvalRuleItemDO::getRuleItemId, reqVO.getRuleItemId())
                .likeIfPresent(EvalRuleItemDO::getRuleItemName, reqVO.getRuleItemName())
                .eqIfPresent(EvalRuleItemDO::getRuleItemCode, reqVO.getRuleItemCode())
                .eqIfPresent(EvalRuleItemDO::getRuleCatId, reqVO.getRuleCatId())
                .likeIfPresent(EvalRuleItemDO::getRuleCatName, reqVO.getRuleCatName())
                .eqIfPresent(EvalRuleItemDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalRuleItemDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalRuleItemDO::getScoreLogic, reqVO.getScoreLogic())
                .eqIfPresent(EvalRuleItemDO::getFullScore, reqVO.getFullScore())
                .eqIfPresent(EvalRuleItemDO::getRuleType, reqVO.getRuleType())
                .eqIfPresent(EvalRuleItemDO::getRuleDesc, reqVO.getRuleDesc())
                .eqIfPresent(EvalRuleItemDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalRuleItemDO::getCreateUser, reqVO.getCreateUser())
                .eqIfPresent(EvalRuleItemDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(EvalRuleItemDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(EvalRuleItemDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(EvalRuleItemDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRuleItemDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRuleItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRuleItemDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalRuleItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalRuleItemDO::getId));
    }

}