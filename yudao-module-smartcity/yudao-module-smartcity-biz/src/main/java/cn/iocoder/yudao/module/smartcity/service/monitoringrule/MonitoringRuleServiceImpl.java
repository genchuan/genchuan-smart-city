package cn.iocoder.yudao.module.smartcity.service.monitoringrule;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringrule.MonitoringRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.monitoringrule.MonitoringRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 监测项规则设置 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class MonitoringRuleServiceImpl implements MonitoringRuleService {

    @Resource
    private MonitoringRuleMapper monitoringRuleMapper;

    @Override
    public Long createMonitoringRule(MonitoringRuleSaveReqVO createReqVO) {
        // 插入
        MonitoringRuleDO monitoringRule = BeanUtils.toBean(createReqVO, MonitoringRuleDO.class);
        monitoringRuleMapper.insert(monitoringRule);
        // 返回
        return monitoringRule.getId();
    }

    @Override
    public void updateMonitoringRule(MonitoringRuleSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitoringRuleExists(updateReqVO.getId());
        // 更新
        MonitoringRuleDO updateObj = BeanUtils.toBean(updateReqVO, MonitoringRuleDO.class);
        monitoringRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitoringRule(Long id) {
        // 校验存在
        validateMonitoringRuleExists(id);
        // 删除
        monitoringRuleMapper.deleteById(id);
    }

    private void validateMonitoringRuleExists(Long id) {
        if (monitoringRuleMapper.selectById(id) == null) {
            throw exception(MONITORING_RULE_NOT_EXISTS);
        }
    }

    @Override
    public MonitoringRuleDO getMonitoringRule(Long id) {
        return monitoringRuleMapper.selectById(id);
    }

    @Override
    public PageResult<MonitoringRuleDO> getMonitoringRulePage(MonitoringRulePageReqVO pageReqVO) {
        return monitoringRuleMapper.selectPage(pageReqVO);
    }

}