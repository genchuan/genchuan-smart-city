package cn.iocoder.yudao.module.evaluate.service.commentrule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRulePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import cn.iocoder.yudao.module.evaluate.service.ruledetail.RuleDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.COMMENT_RULE_NOT_EXISTS;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.appendLog;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.buildLog;

/**
 * 评分规则主 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CommentRuleServiceImpl implements CommentRuleService {

    @Resource
    private CommentRuleMapper commentRuleMapper;

    @Resource
    private IndexItemMapper indexItemMapper;

    @Resource
    private IndexSystemMapper indexSystemMapper;

    @Resource
    private RuleDetailService ruleDetailService;

    @Resource
    private RuleCategoryMapper ruleCategoryMapper;

    @Override
    public Long createCommentRule(CommentRuleSaveReqVO createReqVO) {
        // 插入
        CommentRuleDO commentRule = BeanUtils.toBean(createReqVO, CommentRuleDO.class);
        commentRuleMapper.insert(commentRule);

        // 追加变更日志
        RuleCategoryDO category = ruleCategoryMapper.selectById(commentRule.getRuleCategoryId());
        if (category != null) {
            String log = buildLog("【新增规则】",
                    StrUtil.format("新增评分规则「{}」", commentRule.getRuleName()));
            category.setChangeLog(appendLog(category.getChangeLog(), "【新增规则】",
                    StrUtil.format("新增评分规则「{}」", commentRule.getRuleName())));
            ruleCategoryMapper.updateById(category);
        }

        // 返回
        return commentRule.getId();
    }

    @Override
    public void updateCommentRule(CommentRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateCommentRuleExists(updateReqVO.getId());
        CommentRuleDO oldRule = commentRuleMapper.selectById(updateReqVO.getId());
        // 更新
        CommentRuleDO updateObj = BeanUtils.toBean(updateReqVO, CommentRuleDO.class);
        commentRuleMapper.updateById(updateObj);

        // 追加变更日志
        StringBuilder changeContent = new StringBuilder();
        if (!Objects.equals(oldRule.getRuleName(), updateReqVO.getRuleName())) {
            changeContent.append(StrUtil.format("规则名称由「{}」改为「{}」",
                    oldRule.getRuleName(), updateReqVO.getRuleName()));
        }
        if (!Objects.equals(oldRule.getStatus(), updateReqVO.getStatus())) {
            if (changeContent.length() > 0) {
                changeContent.append("；");
            }
            changeContent.append(StrUtil.format("状态由「{}」改为「{}」",
                    oldRule.getStatus(), updateReqVO.getStatus()));
        }
        if (!Objects.equals(oldRule.getRuleType(), updateReqVO.getRuleType())) {
            if (changeContent.length() > 0) {
                changeContent.append("；");
            }
            changeContent.append(StrUtil.format("规则类型由「{}」改为「{}」",
                    oldRule.getRuleType(), updateReqVO.getRuleType()));
        }
        if (changeContent.length() > 0) {
            RuleCategoryDO category = ruleCategoryMapper.selectById(oldRule.getRuleCategoryId());
            if (category != null) {
                String log = buildLog("【编辑规则】", changeContent.toString());
                category.setChangeLog(appendLog(category.getChangeLog(), "【编辑规则】", changeContent.toString()));
                ruleCategoryMapper.updateById(category);
            }
        }
    }

    @Override
    public void deleteCommentRule(Long id) {
        // 校验存在
        validateCommentRuleExists(id);
        CommentRuleDO rule = commentRuleMapper.selectById(id);
        // 删除
        commentRuleMapper.deleteById(id);

        // 追加变更日志
        RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
        if (category != null) {
            String log = buildLog("【删除规则】",
                    StrUtil.format("删除评分规则「{}」", rule.getRuleName()));
            category.setChangeLog(appendLog(category.getChangeLog(), "【删除规则】",
                    StrUtil.format("删除评分规则「{}」", rule.getRuleName())));
            ruleCategoryMapper.updateById(category);
        }
    }

    @Override
    public void deleteCommentRuleListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 批量查询被删除的规则信息（用于记录日志）
        List<CommentRuleDO> deletedRules = commentRuleMapper.selectByIds(ids);
        // 批量删除
        commentRuleMapper.deleteByIds(ids);
        // 逐条追加变更日志
        for (CommentRuleDO rule : deletedRules) {
            RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
            if (category != null) {
                String log = buildLog("【删除规则】",
                        StrUtil.format("删除评分规则「{}」", rule.getRuleName()));
                category.setChangeLog(appendLog(category.getChangeLog(), "【删除规则】",
                        StrUtil.format("删除评分规则「{}」", rule.getRuleName())));
                ruleCategoryMapper.updateById(category);
            }
        }
    }


    private void validateCommentRuleExists(Long id) {
        if (commentRuleMapper.selectById(id) == null) {
            throw exception(COMMENT_RULE_NOT_EXISTS);
        }
    }

    @Override
    public CommentRuleDO getCommentRule(Long id) {
        return commentRuleMapper.selectById(id);
    }

    @Override
    public CommentRuleRespVO getCommentRuleWithDetails(Long id) {
        // 查询主表
        CommentRuleDO commentRule = commentRuleMapper.selectById(id);
        if (commentRule == null) {
            return null;
        }

        // 转换为RespVO
        CommentRuleRespVO respVO = BeanUtils.toBean(commentRule, CommentRuleRespVO.class);

        // 填充指标体系信息（systemId → IndexSystemDO）
        if (commentRule.getSystemId() != null) {
            IndexSystemDO system = indexSystemMapper.selectById(commentRule.getSystemId());
            if (system != null) {
                respVO.setSystemIdPk(system.getSystemId());
                respVO.setSystemName(system.getName());
            }
        }

        // 查询明细列表
        List<RuleDetailDO> details = ruleDetailService.getRuleDetailListByRuleId(id);
        respVO.setDetails(BeanUtils.toBean(details, RuleDetailRespVO.class));

        return respVO;
    }

    @Override
    public PageResult<CommentRuleRespVO> getCommentRulePage(CommentRulePageReqVO pageReqVO) {
        PageResult<CommentRuleDO> pageResult = commentRuleMapper.selectPage(pageReqVO);
        if (pageResult.getList() == null || pageResult.getList().isEmpty()) {
            return new PageResult<>(new ArrayList<>(), pageResult.getTotal());
        }

        // 收集所有 systemId 并批量查询体系信息
        List<Long> systemIds = pageResult.getList().stream()
                .map(CommentRuleDO::getSystemId)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<Long, IndexSystemDO> systemMap;
        if (!systemIds.isEmpty()) {
            List<IndexSystemDO> systems = indexSystemMapper.selectByIds(systemIds);
            systemMap = systems.stream()
                    .collect(Collectors.toMap(IndexSystemDO::getId, s -> s));
        } else {
            systemMap = new HashMap<>();
        }

        // 转换为 RespVO 并填充关联字段
        List<CommentRuleRespVO> voList = pageResult.getList().stream().map(rule -> {
            CommentRuleRespVO vo = BeanUtils.toBean(rule, CommentRuleRespVO.class);
            IndexSystemDO system = systemMap.get(rule.getSystemId());
            if (system != null) {
                vo.setSystemIdPk(system.getSystemId());
                vo.setSystemName(system.getName());
            }
            return vo;
        }).collect(Collectors.toList());

        return new PageResult<>(voList, pageResult.getTotal());
    }

    @Override
    public Long getCommentRuleIdBySystemIdAndItemId(Long systemId, Long itemId) {
        IndexItemDO item = indexItemMapper.selectById(itemId);
        return item != null ? item.getCommentRuleId() : null;
    }

}