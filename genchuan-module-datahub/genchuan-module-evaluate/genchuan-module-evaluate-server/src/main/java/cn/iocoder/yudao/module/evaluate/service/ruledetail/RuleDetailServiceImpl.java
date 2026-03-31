package cn.iocoder.yudao.module.evaluate.service.ruledetail;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.RuleDetailSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.commentrule.CommentRuleDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail.RuleDetailMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.commentrule.CommentRuleMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.rulecategory.RuleCategoryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_DETAIL_NOT_EXISTS;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.appendLog;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.buildLog;

import cn.hutool.core.util.StrUtil;

/**
 * 评分规则明细 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RuleDetailServiceImpl implements RuleDetailService {

    @Resource
    private RuleDetailMapper ruleDetailMapper;

    @Resource
    private RuleCategoryMapper ruleCategoryMapper;

    @Resource
    private CommentRuleMapper commentRuleMapper;

    @Override
    public Long createRuleDetail(RuleDetailSaveReqVO createReqVO) {
        // 插入
        RuleDetailDO ruleDetail = BeanUtils.toBean(createReqVO, RuleDetailDO.class);
        ruleDetailMapper.insert(ruleDetail);

        // 追加变更日志
        CommentRuleDO rule = commentRuleMapper.selectById(ruleDetail.getRuleId());
        if (rule != null) {
            RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
            if (category != null) {
                String log = buildLog("【新增规则明细】",
                        StrUtil.format("新增规则明细，区间{}-{}，分值{}",
                                ruleDetail.getMinValue(), ruleDetail.getMaxValue(), ruleDetail.getScore()));
                category.setChangeLog(appendLog(category.getChangeLog(), "【新增规则明细】",
                        StrUtil.format("新增规则明细，区间{}-{}，分值{}",
                                ruleDetail.getMinValue(), ruleDetail.getMaxValue(), ruleDetail.getScore())));
                ruleCategoryMapper.updateById(category);
            }
        }

        // 返回
        return ruleDetail.getId();
    }

    @Override
    public void updateRuleDetail(RuleDetailSaveReqVO updateReqVO) {
        // 校验存在
        validateRuleDetailExists(updateReqVO.getId());
        RuleDetailDO oldDetail = ruleDetailMapper.selectById(updateReqVO.getId());
        CommentRuleDO rule = commentRuleMapper.selectById(oldDetail.getRuleId());
        // 更新
        RuleDetailDO updateObj = BeanUtils.toBean(updateReqVO, RuleDetailDO.class);
        ruleDetailMapper.updateById(updateObj);

        // 追加变更日志
        StringBuilder changeContent = new StringBuilder();
        if (!Objects.equals(oldDetail.getScore(), updateReqVO.getScore())) {
            changeContent.append(StrUtil.format("分数由「{}」改为「{}」",
                    oldDetail.getScore(), updateReqVO.getScore()));
        }
        if (!Objects.equals(oldDetail.getMinValue(), updateReqVO.getMinValue())) {
            if (changeContent.length() > 0) {
                changeContent.append("；");
            }
            changeContent.append(StrUtil.format("区间最小值由「{}」改为「{}」",
                    oldDetail.getMinValue(), updateReqVO.getMinValue()));
        }
        if (!Objects.equals(oldDetail.getMaxValue(), updateReqVO.getMaxValue())) {
            if (changeContent.length() > 0) {
                changeContent.append("；");
            }
            changeContent.append(StrUtil.format("区间最大值由「{}」改为「{}」",
                    oldDetail.getMaxValue(), updateReqVO.getMaxValue()));
        }
        if (changeContent.length() > 0) {
            if (rule != null) {
                RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
                if (category != null) {
                    String log = buildLog("【编辑规则明细】", changeContent.toString());
                    category.setChangeLog(appendLog(category.getChangeLog(), "【编辑规则明细】", changeContent.toString()));
                    ruleCategoryMapper.updateById(category);
                }
            }
        }
    }

    @Override
    public void deleteRuleDetail(Long id) {
        // 校验存在
        validateRuleDetailExists(id);
        RuleDetailDO ruleDetail = ruleDetailMapper.selectById(id);
        CommentRuleDO rule = commentRuleMapper.selectById(ruleDetail.getRuleId());
        // 删除
        ruleDetailMapper.deleteById(id);

        // 追加变更日志
        if (rule != null) {
            RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
            if (category != null) {
                String log = buildLog("【删除规则明细】",
                        StrUtil.format("删除规则明细，区间{}-{}", ruleDetail.getMinValue(), ruleDetail.getMaxValue()));
                category.setChangeLog(appendLog(category.getChangeLog(), "【删除规则明细】",
                        StrUtil.format("删除规则明细，区间{}-{}", ruleDetail.getMinValue(), ruleDetail.getMaxValue())));
                ruleCategoryMapper.updateById(category);
            }
        }
    }

    @Override
    public void deleteRuleDetailListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        // 批量查询被删除的明细信息（用于记录日志）
        List<RuleDetailDO> deletedDetails = ruleDetailMapper.selectByIds(ids);
        // 批量删除
        ruleDetailMapper.deleteByIds(ids);
        // 逐条追加变更日志
        for (RuleDetailDO detail : deletedDetails) {
            CommentRuleDO rule = commentRuleMapper.selectById(detail.getRuleId());
            if (rule != null) {
                RuleCategoryDO category = ruleCategoryMapper.selectById(rule.getRuleCategoryId());
                if (category != null) {
                    String log = buildLog("【删除规则明细】",
                            StrUtil.format("删除规则明细，区间{}-{}",
                                    detail.getMinValue(), detail.getMaxValue()));
                    category.setChangeLog(appendLog(category.getChangeLog(), "【删除规则明细】",
                            StrUtil.format("删除规则明细，区间{}-{}",
                                    detail.getMinValue(), detail.getMaxValue())));
                    ruleCategoryMapper.updateById(category);
                }
            }
        }
    }


    private void validateRuleDetailExists(Long id) {
        if (ruleDetailMapper.selectById(id) == null) {
            throw exception(RULE_DETAIL_NOT_EXISTS);
        }
    }

    @Override
    public RuleDetailDO getRuleDetail(Long id) {
        return ruleDetailMapper.selectById(id);
    }

    @Override
    public PageResult<RuleDetailDO> getRuleDetailPage(RuleDetailPageReqVO pageReqVO) {
        return ruleDetailMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RuleDetailDO> getRuleDetailListByRuleId(Long ruleId) {
        return ruleDetailMapper.selectListByRuleId(ruleId);
    }

    @Override
    public BigDecimal calculateScoreByCount(Long ruleId, Long count) {
        if (ruleId == null || count == null) {
            return null;
        }

        // 获取规则明细列表，按 sortOrder 排序
        List<RuleDetailDO> ruleDetails = ruleDetailMapper.selectListByRuleId(ruleId);
        if (ruleDetails == null || ruleDetails.isEmpty()) {
            return null;
        }

        // 按 sortOrder 排序
        ruleDetails.sort(Comparator.comparing(RuleDetailDO::getSortOrder, Comparator.nullsLast(Comparator.naturalOrder())));

        // 遍历匹配第一个满足条件的区间
        BigDecimal countDecimal = new BigDecimal(count);
        for (RuleDetailDO detail : ruleDetails) {
            boolean minMatch = true;
            boolean maxMatch = true;

            // 检查最小值
            if (detail.getMinValue() != null) {
                if (">".equals(detail.getOperatorMin())) {
                    minMatch = countDecimal.compareTo(detail.getMinValue()) > 0;
                } else {
                    minMatch = countDecimal.compareTo(detail.getMinValue()) >= 0;
                }
            }

            // 检查最大值
            if (detail.getMaxValue() != null) {
                if ("<".equals(detail.getOperatorMax())) {
                    maxMatch = countDecimal.compareTo(detail.getMaxValue()) < 0;
                } else {
                    maxMatch = countDecimal.compareTo(detail.getMaxValue()) <= 0;
                }
            }

            // 如果同时满足最小值和最大值条件，返回对应的分数
            if (minMatch && maxMatch) {
                return detail.getScore();
            }
        }

        return null;
    }

}