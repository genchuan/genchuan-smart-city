package cn.iocoder.yudao.module.evaluate.service.ruleitem;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.ruleitem.RuleItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 规则项 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RuleItemServiceImpl implements RuleItemService {

    @Resource
    private RuleItemMapper ruleItemMapper;

    @Override
    public Long createRuleItem(RuleItemSaveReqVO createReqVO) {
        // 插入
        RuleItemDO ruleItem = BeanUtils.toBean(createReqVO, RuleItemDO.class);
        ruleItemMapper.insert(ruleItem);
        // 返回
        return ruleItem.getId();
    }

    @Override
    public void updateRuleItem(RuleItemSaveReqVO updateReqVO) {
        // 校验存在
        validateRuleItemExists(updateReqVO.getId());
        // 更新
        RuleItemDO updateObj = BeanUtils.toBean(updateReqVO, RuleItemDO.class);
        ruleItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteRuleItem(Long id) {
        // 校验存在
        validateRuleItemExists(id);
        // 删除
        ruleItemMapper.deleteById(id);
    }

    private void validateRuleItemExists(Long id) {
        if (ruleItemMapper.selectById(id) == null) {
            throw exception(RULE_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public RuleItemDO getRuleItem(Long id) {
        return ruleItemMapper.selectById(id);
    }

    @Override
    public PageResult<RuleItemDO> getRuleItemPage(RuleItemPageReqVO pageReqVO) {
        return ruleItemMapper.selectPage(pageReqVO);
    }

}