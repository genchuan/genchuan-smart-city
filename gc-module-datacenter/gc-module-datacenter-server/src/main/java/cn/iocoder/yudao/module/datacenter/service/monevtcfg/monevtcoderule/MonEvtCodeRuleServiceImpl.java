package cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtcoderule;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcoderule.MonEvtCodeRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtcoderule.MonEvtCodeRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件标识码规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtCodeRuleServiceImpl implements MonEvtCodeRuleService {

    @Resource
    private MonEvtCodeRuleMapper monEvtCodeRuleMapper;

    @Override
    public Long createMonEvtCodeRule(MonEvtCodeRuleSaveReqVO createReqVO) {
        // 插入
        MonEvtCodeRuleDO monEvtCodeRule = BeanUtils.toBean(createReqVO, MonEvtCodeRuleDO.class);
        monEvtCodeRuleMapper.insert(monEvtCodeRule);
        // 返回
        return monEvtCodeRule.getId();
    }

    @Override
    public void updateMonEvtCodeRule(MonEvtCodeRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtCodeRuleExists(updateReqVO.getId());
        // 更新
        MonEvtCodeRuleDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtCodeRuleDO.class);
        monEvtCodeRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtCodeRule(Long id) {
        // 校验存在
        validateMonEvtCodeRuleExists(id);
        // 删除
        monEvtCodeRuleMapper.deleteById(id);
    }

    private void validateMonEvtCodeRuleExists(Long id) {
        if (monEvtCodeRuleMapper.selectById(id) == null) {
            throw exception(MON_EVT_CODE_RULE_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtCodeRuleDO getMonEvtCodeRule(Long id) {
        return monEvtCodeRuleMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtCodeRuleDO> getMonEvtCodeRulePage(MonEvtCodeRulePageReqVO pageReqVO) {
        return monEvtCodeRuleMapper.selectPage(pageReqVO);
    }

}