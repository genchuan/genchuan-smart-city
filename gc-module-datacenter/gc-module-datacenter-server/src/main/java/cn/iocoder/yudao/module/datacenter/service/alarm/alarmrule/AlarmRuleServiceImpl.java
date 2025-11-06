package cn.iocoder.yudao.module.datacenter.service.alarm.alarmrule;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRuleSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.alarmrule.AlarmRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.alarmrule.AlarmRuleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警规则配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class AlarmRuleServiceImpl implements AlarmRuleService {

    @Resource
    private AlarmRuleMapper alarmRuleMapper;

    @Override
    public Long createAlarmRule(AlarmRuleSaveReqVO createReqVO) {
        // 校验规则唯一性
        validateAlarmRuleUnique(createReqVO, null);

        // 设置默认启用状态为禁用
        createReqVO.setEnableStatus(1);

        // 插入
        AlarmRuleDO alarmRule = BeanUtils.toBean(createReqVO, AlarmRuleDO.class);
        alarmRuleMapper.insert(alarmRule);
        // 返回
        return alarmRule.getId();
    }

    @Override
    public void updateAlarmRule(AlarmRuleSaveReqVO updateReqVO) {
        // 校验存在
        AlarmRuleDO existingRule = validateAlarmRuleExists(updateReqVO.getId());

        // 校验关联对象范围不可修改
        if (!Objects.equals(existingRule.getRelatedObjectScope(), updateReqVO.getRelatedObjectScope())) {
            throw exception(ALARM_RULE_SCOPE_IMMUTABLE);
        }

        // 校验规则唯一性（排除自身）
        validateAlarmRuleUnique(updateReqVO, updateReqVO.getId());

        // 更新（只允许更新特定字段）
        AlarmRuleDO updateObj = BeanUtils.toBean(updateReqVO, AlarmRuleDO.class);
        // 保持关联对象范围不变
        updateObj.setRelatedObjectScope(existingRule.getRelatedObjectScope());
        alarmRuleMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlarmRule(Long id) {
        // 校验存在
        validateAlarmRuleExists(id);
        // 删除
        alarmRuleMapper.deleteById(id);
    }

    /**
     * 校验规则存在并返回规则对象
     */
    private AlarmRuleDO validateAlarmRuleExists(Long id) {
        AlarmRuleDO alarmRule = alarmRuleMapper.selectById(id);
        if (alarmRule == null) {
            throw exception(ALARM_RULE_NOT_EXISTS);
        }
        return alarmRule;
    }

    @Override
    public AlarmRuleDO getAlarmRule(Long id) {
        return alarmRuleMapper.selectById(id);
    }

    @Override
    public PageResult<AlarmRuleDO> getAlarmRulePage(AlarmRulePageReqVO pageReqVO) {
        return alarmRuleMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableAlarmRule(Long id) {
        // 校验存在
        AlarmRuleDO alarmRule = validateAlarmRuleExists(id);

        // 校验是否已启用
        if (Integer.valueOf(1).equals(alarmRule.getEnableStatus())) {
            throw exception(ALARM_RULE_ALREADY_ENABLED);
        }

        // 更新启用状态
        AlarmRuleDO updateObj = new AlarmRuleDO();
        updateObj.setId(id);
        updateObj.setEnableStatus(1);
        alarmRuleMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableAlarmRule(Long id) {
        // 校验存在
        AlarmRuleDO alarmRule = validateAlarmRuleExists(id);

        // 校验是否已禁用
        if (Integer.valueOf(0).equals(alarmRule.getEnableStatus())) {
            throw exception(ALARM_RULE_ALREADY_DISABLED);
        }

        // 更新启用状态
        AlarmRuleDO updateObj = new AlarmRuleDO();
        updateObj.setId(id);
        updateObj.setEnableStatus(0);
        alarmRuleMapper.updateById(updateObj);
    }

    @Override
    public void validateAlarmRuleUnique(AlarmRuleSaveReqVO reqVO, Long excludeId) {
        // 构建查询条件：同一对象范围 + 触发条件
        LambdaQueryWrapperX<AlarmRuleDO> queryWrapper = new LambdaQueryWrapperX<AlarmRuleDO>()
                .eq(AlarmRuleDO::getRelatedObjectScope, reqVO.getRelatedObjectScope())
                .eq(AlarmRuleDO::getTriggerCondition, reqVO.getTriggerCondition())
                .eq(AlarmRuleDO::getEnableStatus, 1); // 只校验启用的规则

        // 排除自身（用于更新操作）
        if (excludeId != null) {
            queryWrapper.ne(AlarmRuleDO::getId, excludeId);
        }

        // 查询是否存在重复规则
        Long count = alarmRuleMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw exception(ALARM_RULE_DUPLICATE);
        }

        // 校验阈值合理性
        validateThreshold(reqVO);
    }

    @Override
    public List<AlarmRuleDO> getAlarmRuleListByStatus(Integer enableStatus) {
        return alarmRuleMapper.selectList(new LambdaQueryWrapperX<AlarmRuleDO>()
                .eqIfPresent(AlarmRuleDO::getEnableStatus, enableStatus)
                .orderByDesc(AlarmRuleDO::getId));
    }

    @Override
    public List<AlarmRuleDO> getEnabledAlarmRuleList() {
        return getAlarmRuleListByStatus(1);
    }

    /**
     * 校验阈值合理性（上限 > 下限）
     */
    private void validateThreshold(AlarmRuleSaveReqVO reqVO) {
        if (reqVO.getWarningThresholdUpper() != null && reqVO.getWarningThresholdLower() != null) {
            if (reqVO.getWarningThresholdUpper().compareTo(reqVO.getWarningThresholdLower()) <= 0) {
                throw exception(ALARM_RULE_THRESHOLD_INVALID);
            }
        }
    }




}