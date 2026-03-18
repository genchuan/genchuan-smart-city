package cn.iocoder.yudao.module.evaluate.service.ruledetail;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.math.BigDecimal;
import cn.iocoder.yudao.module.evaluate.controller.admin.ruledetail.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.ruledetail.RuleDetailDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.ruledetail.RuleDetailMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

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

    @Override
    public Long createRuleDetail(RuleDetailSaveReqVO createReqVO) {
        // 插入
        RuleDetailDO ruleDetail = BeanUtils.toBean(createReqVO, RuleDetailDO.class);
        ruleDetailMapper.insert(ruleDetail);

        // 返回
        return ruleDetail.getId();
    }

    @Override
    public void updateRuleDetail(RuleDetailSaveReqVO updateReqVO) {
        // 校验存在
        validateRuleDetailExists(updateReqVO.getId());
        // 更新
        RuleDetailDO updateObj = BeanUtils.toBean(updateReqVO, RuleDetailDO.class);
        ruleDetailMapper.updateById(updateObj);
    }

    @Override
    public void deleteRuleDetail(Long id) {
        // 校验存在
        validateRuleDetailExists(id);
        // 删除
        ruleDetailMapper.deleteById(id);
    }

    @Override
        public void deleteRuleDetailListByIds(List<Long> ids) {
        // 删除
        ruleDetailMapper.deleteByIds(ids);
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