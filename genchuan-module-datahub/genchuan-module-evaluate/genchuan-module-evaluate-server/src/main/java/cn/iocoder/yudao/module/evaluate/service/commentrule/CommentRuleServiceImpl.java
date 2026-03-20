package cn.iocoder.yudao.module.evaluate.service.commentrule;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailRespVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexitem.IndexItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexitem.IndexItemMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.indexsystem.IndexSystemMapper;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexsystem.IndexSystemDO;
import cn.iocoder.yudao.module.evaluate.service.ruledetail.RuleDetailService;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

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

    @Override
    public Long createCommentRule(CommentRuleSaveReqVO createReqVO) {
        // 插入
        CommentRuleDO commentRule = BeanUtils.toBean(createReqVO, CommentRuleDO.class);
        commentRuleMapper.insert(commentRule);

        // 返回
        return commentRule.getId();
    }

    @Override
    public void updateCommentRule(CommentRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateCommentRuleExists(updateReqVO.getId());
        // 更新
        CommentRuleDO updateObj = BeanUtils.toBean(updateReqVO, CommentRuleDO.class);
        commentRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommentRule(Long id) {
        // 校验存在
        validateCommentRuleExists(id);
        // 删除
        commentRuleMapper.deleteById(id);
    }

    @Override
        public void deleteCommentRuleListByIds(List<Long> ids) {
        // 删除
        commentRuleMapper.deleteByIds(ids);
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
            List<IndexSystemDO> systems = indexSystemMapper.selectBatchIds(systemIds);
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