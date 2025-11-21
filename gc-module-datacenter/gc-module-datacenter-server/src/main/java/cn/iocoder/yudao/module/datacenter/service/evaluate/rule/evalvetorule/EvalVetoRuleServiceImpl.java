package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalvetorule;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalvetorule.EvalVetoRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalvetorule.EvalVetoRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 否决项规则管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalVetoRuleServiceImpl implements EvalVetoRuleService {

    @Resource
    private EvalVetoRuleMapper evalVetoRuleMapper;

    @Override
    public Long createEvalVetoRule(EvalVetoRuleSaveReqVO createReqVO) {
        // 插入
        EvalVetoRuleDO evalVetoRule = BeanUtils.toBean(createReqVO, EvalVetoRuleDO.class);
        evalVetoRuleMapper.insert(evalVetoRule);
        // 返回
        return evalVetoRule.getId();
    }

    @Override
    public void updateEvalVetoRule(EvalVetoRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalVetoRuleExists(updateReqVO.getId());
        // 更新
        EvalVetoRuleDO updateObj = BeanUtils.toBean(updateReqVO, EvalVetoRuleDO.class);
        evalVetoRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalVetoRule(Long id) {
        // 校验存在
        validateEvalVetoRuleExists(id);
        // 删除
        evalVetoRuleMapper.deleteById(id);
    }

    private void validateEvalVetoRuleExists(Long id) {
        if (evalVetoRuleMapper.selectById(id) == null) {
            throw exception(EVAL_VETO_RULE_NOT_EXISTS);
        }
    }

    @Override
    public EvalVetoRuleDO getEvalVetoRule(Long id) {
        return evalVetoRuleMapper.selectById(id);
    }

    @Override
    public PageResult<EvalVetoRuleDO> getEvalVetoRulePage(EvalVetoRulePageReqVO pageReqVO) {
        return evalVetoRuleMapper.selectPage(pageReqVO);
    }

}