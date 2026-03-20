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
                .eq(CommentRuleDO::getDeleted, false)
                .eqIfPresent(CommentRuleDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CommentRuleDO::getSystemId, reqVO.getSystemId())
                .eqIfPresent(CommentRuleDO::getRuleCategoryId, reqVO.getRuleCategoryId())
                .likeIfPresent(CommentRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(CommentRuleDO::getRuleType, reqVO.getRuleType())
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

}