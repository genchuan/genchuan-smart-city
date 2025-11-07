package cn.iocoder.yudao.module.datacenter.service.alarm.alarmrule;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo.AlarmRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.alarmrule.AlarmRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 预警告警规则配置 Service 接口
 *
 * @author zcq
 */
public interface AlarmRuleService {

    /**
     * 创建预警告警规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAlarmRule(@Valid AlarmRuleSaveReqVO createReqVO);

    /**
     * 更新预警告警规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAlarmRule(@Valid AlarmRuleSaveReqVO updateReqVO);

    /**
     * 删除预警告警规则配置
     *
     * @param id 编号
     */
    void deleteAlarmRule(Long id);

    /**
     * 获得预警告警规则配置
     *
     * @param id 编号
     * @return 预警告警规则配置
     */
    AlarmRuleDO getAlarmRule(Long id);

    /**
     * 获得预警告警规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 预警告警规则配置分页
     */
    PageResult<AlarmRuleDO> getAlarmRulePage(AlarmRulePageReqVO pageReqVO);

    /**
     * 启用预警告警规则配置
     *
     * @param id 编号
     */
    void enableAlarmRule(Long id);

    /**
     * 禁用预警告警规则配置
     *
     * @param id 编号
     */
    void disableAlarmRule(Long id);

    /**
     * 校验规则唯一性
     *
     * @param reqVO 规则信息
     * @param excludeId 排除的规则ID（用于更新时排除自身）
     */
    void validateAlarmRuleUnique(AlarmRuleSaveReqVO reqVO, Long excludeId);
    /**
     * 根据启用状态获得预警告警规则配置列表
     *
     * @param enableStatus 启用状态
     * @return 预警告警规则配置列表
     */
    List<AlarmRuleDO> getAlarmRuleListByStatus(Integer enableStatus);

    /**
     * 获得所有启用的预警告警规则配置列表
     *
     * @return 启用的预警告警规则配置列表
     */
    List<AlarmRuleDO> getEnabledAlarmRuleList();
}