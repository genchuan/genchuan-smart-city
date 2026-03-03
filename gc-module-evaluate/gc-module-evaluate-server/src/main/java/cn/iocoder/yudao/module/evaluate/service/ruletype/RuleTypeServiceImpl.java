package cn.iocoder.yudao.module.evaluate.service.ruletype;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.ruletype.vo.RuleTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.sys.ruletype.RuleTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.ruletype.RuleTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RULE_TYPE_NOT_EXISTS;

/**
 * 规则类型字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RuleTypeServiceImpl implements RuleTypeService {

    @Resource
    private RuleTypeMapper ruleTypeMapper;

    @Override
    public Long createRuleType(RuleTypeSaveReqVO createReqVO) {
        // 插入
        RuleTypeDO ruleType = BeanUtils.toBean(createReqVO, RuleTypeDO.class);
        ruleTypeMapper.insert(ruleType);
        // 返回
        return ruleType.getId();
    }

    @Override
    public void updateRuleType(RuleTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateRuleTypeExists(updateReqVO.getId());
        // 更新
        RuleTypeDO updateObj = BeanUtils.toBean(updateReqVO, RuleTypeDO.class);
        ruleTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteRuleType(Long id) {
        // 校验存在
        validateRuleTypeExists(id);
        // 删除
        ruleTypeMapper.deleteById(id);
    }

    private void validateRuleTypeExists(Long id) {
        if (ruleTypeMapper.selectById(id) == null) {
            throw exception(RULE_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public RuleTypeDO getRuleType(Long id) {
        return ruleTypeMapper.selectById(id);
    }

    @Override
    public PageResult<RuleTypeDO> getRuleTypePage(RuleTypePageReqVO pageReqVO) {
        return ruleTypeMapper.selectPage(pageReqVO);
    }

}