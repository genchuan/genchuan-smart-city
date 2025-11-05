package cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcoderule.MonCompCodeRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompcoderule.MonCompCodeRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件标识码规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonCompCodeRuleServiceImpl implements MonCompCodeRuleService {

    @Resource
    private MonCompCodeRuleMapper monCompCodeRuleMapper;

    @Override
    public Long createMonCompCodeRule(MonCompCodeRuleSaveReqVO createReqVO) {
        // 插入
        MonCompCodeRuleDO monCompCodeRule = BeanUtils.toBean(createReqVO, MonCompCodeRuleDO.class);
        monCompCodeRuleMapper.insert(monCompCodeRule);
        // 返回
        return monCompCodeRule.getId();
    }

    @Override
    public void updateMonCompCodeRule(MonCompCodeRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateMonCompCodeRuleExists(updateReqVO.getId());
        // 更新
        MonCompCodeRuleDO updateObj = BeanUtils.toBean(updateReqVO, MonCompCodeRuleDO.class);
        monCompCodeRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonCompCodeRule(Long id) {
        // 校验存在
        validateMonCompCodeRuleExists(id);
        // 删除
        monCompCodeRuleMapper.deleteById(id);
    }

    private void validateMonCompCodeRuleExists(Long id) {
        if (monCompCodeRuleMapper.selectById(id) == null) {
            throw exception(MON_COMP_CODE_RULE_NOT_EXISTS);
        }
    }

    @Override
    public MonCompCodeRuleDO getMonCompCodeRule(Long id) {
        return monCompCodeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<MonCompCodeRuleDO> getMonCompCodeRulePage(MonCompCodeRulePageReqVO pageReqVO) {
        return monCompCodeRuleMapper.selectPage(pageReqVO);
    }

}