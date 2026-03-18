package cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.*;

/**
 * 评分规则主 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CommentRuleMapper extends BaseMapperX<CommentRuleDO> {

    default PageResult<CommentRuleDO> selectPage(CommentRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentRuleDO>()
                .eqIfPresent(CommentRuleDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(CommentRuleDO::getRuleCategoryId, reqVO.getRuleCategoryId())
                .eqIfPresent(CommentRuleDO::getItemId, reqVO.getItemId())
                .likeIfPresent(CommentRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(CommentRuleDO::getRuleType, reqVO.getRuleType())
                .eqIfPresent(CommentRuleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CommentRuleDO::getApplyObjectType, reqVO.getApplyObjectType())
                .betweenIfPresent(CommentRuleDO::getEffectiveStartTime, reqVO.getEffectiveStartTime())
                .betweenIfPresent(CommentRuleDO::getEffectiveEndTime, reqVO.getEffectiveEndTime())
                .eqIfPresent(CommentRuleDO::getStatusChangeRemark, reqVO.getStatusChangeRemark())
                .eqIfPresent(CommentRuleDO::getCreator, reqVO.getCreator())
                .eqIfPresent(CommentRuleDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(CommentRuleDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(CommentRuleDO::getUpdateTime, reqVO.getUpdateTime())
                .eqIfPresent(CommentRuleDO::getOperationLog, reqVO.getOperationLog())
                .orderByDesc(CommentRuleDO::getId));
    }

    /**
     * 根据 systemId 和 itemId 查询评分规则ID
     *
     * @param systemId 体系ID
     * @param itemId   指标项ID
     * @return 评分规则ID，如果没有找到则返回null
     */
    default Long selectIdBySystemIdAndItemId(Long systemId, Long itemId) {
        CommentRuleDO commentRule = selectOne(new LambdaQueryWrapperX<CommentRuleDO>()
                .eq(CommentRuleDO::getSystemId, systemId)
                .eq(CommentRuleDO::getItemId, itemId)
                .eq(CommentRuleDO::getStatus, 1)
                .eq(CommentRuleDO::getDeleted, false)
                .orderByDesc(CommentRuleDO::getId)
                .last("LIMIT 1"));
        return commentRule != null ? commentRule.getId() : null;
    }

}