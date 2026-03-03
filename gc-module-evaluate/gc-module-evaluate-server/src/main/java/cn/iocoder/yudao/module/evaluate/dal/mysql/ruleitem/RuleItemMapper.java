package cn.iocoder.yudao.module.evaluate.dal.mysql.ruleitem;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则项 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface RuleItemMapper extends BaseMapperX<RuleItemDO> {

    default PageResult<RuleItemDO> selectPage(RuleItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RuleItemDO>()
                .eqIfPresent(RuleItemDO::getRuleItemId, reqVO.getRuleItemId())
                .eqIfPresent(RuleItemDO::getRuleCategoryId, reqVO.getRuleCategoryId())
                .eqIfPresent(RuleItemDO::getIndexId, reqVO.getIndexId())
                .likeIfPresent(RuleItemDO::getName, reqVO.getName())
                .eqIfPresent(RuleItemDO::getScoreLogic, reqVO.getScoreLogic())
                .eqIfPresent(RuleItemDO::getFullScore, reqVO.getFullScore())
                .eqIfPresent(RuleItemDO::getRuleTypeId, reqVO.getRuleTypeId())
                .betweenIfPresent(RuleItemDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(RuleItemDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(RuleItemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RuleItemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RuleItemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RuleItemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RuleItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RuleItemDO::getId));
    }

}