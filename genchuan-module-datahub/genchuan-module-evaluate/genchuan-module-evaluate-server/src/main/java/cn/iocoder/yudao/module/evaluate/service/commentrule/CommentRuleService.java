package cn.iocoder.yudao.module.evaluate.service.commentrule;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.*;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 评分规则主 Service 接口
 *
 * @author 亘川智城
 */
public interface CommentRuleService {

    /**
     * 创建评分规则主
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCommentRule(@Valid CommentRuleSaveReqVO createReqVO);

    /**
     * 更新评分规则主
     *
     * @param updateReqVO 更新信息
     */
    void updateCommentRule(@Valid CommentRuleSaveReqVO updateReqVO);

    /**
     * 删除评分规则主
     *
     * @param id 编号
     */
    void deleteCommentRule(Long id);

    /**
    * 批量删除评分规则主
    *
    * @param ids 编号
    */
    void deleteCommentRuleListByIds(List<Long> ids);

    /**
     * 获得评分规则主
     *
     * @param id 编号
     * @return 评分规则主
     */
    CommentRuleDO getCommentRule(Long id);

    /**
     * 获得评分规则主（含明细列表，树形结构）
     *
     * @param id 编号
     * @return 评分规则主（含明细）
     */
    CommentRuleRespVO getCommentRuleWithDetails(Long id);

    /**
     * 获得评分规则主分页
     *
     * @param pageReqVO 分页查询
     * @return 评分规则主分页
     */
    PageResult<CommentRuleRespVO> getCommentRulePage(CommentRulePageReqVO pageReqVO);

    /**
     * 根据 systemId 和 itemId 查询评分规则ID
     *
     * @param systemId 体系ID
     * @param itemId   指标项ID
     * @return 评分规则ID，如果没有找到则返回null
     */
    Long getCommentRuleIdBySystemIdAndItemId(Long systemId, Long itemId);

}