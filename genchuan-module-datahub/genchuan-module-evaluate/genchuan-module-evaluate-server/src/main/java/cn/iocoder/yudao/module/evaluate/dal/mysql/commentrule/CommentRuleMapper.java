package cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 按规则分类ID统计规则数量
     */
    @Select("<script>" +
            "SELECT rule_category_id, COUNT(*) AS ruleCount " +
            "FROM eval_comment_rule " +
            "WHERE deleted = 0 AND rule_category_id IS NOT NULL " +
            "<if test='categoryIds != null and categoryIds.size() > 0'>" +
            "  AND rule_category_id IN " +
            "  <foreach collection='categoryIds' item='cid' open='(' separator=',' close=')'>#{cid}</foreach>" +
            "</if>" +
            "GROUP BY rule_category_id" +
            "</script>")
    java.util.List<java.util.Map<String, Object>> selectRuleCountGroupByCategoryId(@Param("categoryIds") java.util.List<Long> categoryIds);

}