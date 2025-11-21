package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalrulecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalrulecat.EvalRuleCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalrulecat.EvalRuleCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 规则分类管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRuleCatServiceImpl implements EvalRuleCatService {

    @Resource
    private EvalRuleCatMapper evalRuleCatMapper;

    @Override
    public Long createEvalRuleCat(EvalRuleCatSaveReqVO createReqVO) {
        // 插入
        EvalRuleCatDO evalRuleCat = BeanUtils.toBean(createReqVO, EvalRuleCatDO.class);
        evalRuleCatMapper.insert(evalRuleCat);
        // 返回
        return evalRuleCat.getId();
    }

    @Override
    public void updateEvalRuleCat(EvalRuleCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRuleCatExists(updateReqVO.getId());
        // 更新
        EvalRuleCatDO updateObj = BeanUtils.toBean(updateReqVO, EvalRuleCatDO.class);
        evalRuleCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRuleCat(Long id) {
        // 校验存在
        validateEvalRuleCatExists(id);
        // 删除
        evalRuleCatMapper.deleteById(id);
    }

    private void validateEvalRuleCatExists(Long id) {
        if (evalRuleCatMapper.selectById(id) == null) {
            throw exception(EVAL_RULE_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EvalRuleCatDO getEvalRuleCat(Long id) {
        return evalRuleCatMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRuleCatDO> getEvalRuleCatPage(EvalRuleCatPageReqVO pageReqVO) {
        return evalRuleCatMapper.selectPage(pageReqVO);
    }

}