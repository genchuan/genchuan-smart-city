package cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalruleitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalruleitem.EvalRuleItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalruleitem.EvalRuleItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 规则项管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRuleItemServiceImpl implements EvalRuleItemService {

    @Resource
    private EvalRuleItemMapper evalRuleItemMapper;

    @Override
    public Long createEvalRuleItem(EvalRuleItemSaveReqVO createReqVO) {
        // 插入
        EvalRuleItemDO evalRuleItem = BeanUtils.toBean(createReqVO, EvalRuleItemDO.class);
        evalRuleItemMapper.insert(evalRuleItem);
        // 返回
        return evalRuleItem.getId();
    }

    @Override
    public void updateEvalRuleItem(EvalRuleItemSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRuleItemExists(updateReqVO.getId());
        // 更新
        EvalRuleItemDO updateObj = BeanUtils.toBean(updateReqVO, EvalRuleItemDO.class);
        evalRuleItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRuleItem(Long id) {
        // 校验存在
        validateEvalRuleItemExists(id);
        // 删除
        evalRuleItemMapper.deleteById(id);
    }

    private void validateEvalRuleItemExists(Long id) {
        if (evalRuleItemMapper.selectById(id) == null) {
            throw exception(EVAL_RULE_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public EvalRuleItemDO getEvalRuleItem(Long id) {
        return evalRuleItemMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRuleItemDO> getEvalRuleItemPage(EvalRuleItemPageReqVO pageReqVO) {
        return evalRuleItemMapper.selectPage(pageReqVO);
    }

}