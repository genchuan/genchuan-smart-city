package cn.iocoder.yudao.module.datacenter.service.decisioneffecteval;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisioneffecteval.DecisionEffectEvalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.decisioneffecteval.DecisionEffectEvalMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 决策效果评估 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DecisionEffectEvalServiceImpl implements DecisionEffectEvalService {

    @Resource
    private DecisionEffectEvalMapper decisionEffectEvalMapper;

    @Override
    public Long createDecisionEffectEval(DecisionEffectEvalSaveReqVO createReqVO) {
        // 插入
        DecisionEffectEvalDO decisionEffectEval = BeanUtils.toBean(createReqVO, DecisionEffectEvalDO.class);
        decisionEffectEvalMapper.insert(decisionEffectEval);
        // 返回
        return decisionEffectEval.getId();
    }

    @Override
    public void updateDecisionEffectEval(DecisionEffectEvalSaveReqVO updateReqVO) {
        // 校验存在
        validateDecisionEffectEvalExists(updateReqVO.getId());
        // 更新
        DecisionEffectEvalDO updateObj = BeanUtils.toBean(updateReqVO, DecisionEffectEvalDO.class);
        decisionEffectEvalMapper.updateById(updateObj);
    }

    @Override
    public void deleteDecisionEffectEval(Long id) {
        // 校验存在
        validateDecisionEffectEvalExists(id);
        // 删除
        decisionEffectEvalMapper.deleteById(id);
    }

    private void validateDecisionEffectEvalExists(Long id) {
        if (decisionEffectEvalMapper.selectById(id) == null) {
            throw exception(DECISION_EFFECT_EVAL_NOT_EXISTS);
        }
    }

    @Override
    public DecisionEffectEvalDO getDecisionEffectEval(Long id) {
        return decisionEffectEvalMapper.selectById(id);
    }

    @Override
    public PageResult<DecisionEffectEvalDO> getDecisionEffectEvalPage(DecisionEffectEvalPageReqVO pageReqVO) {
        return decisionEffectEvalMapper.selectPage(pageReqVO);
    }

}