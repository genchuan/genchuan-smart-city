package cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.alarmrule;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 预警告警规则配置 DO
 *
 * @author zcq
 */
@TableName("gc_alarm_rule")
@KeySequence("gc_alarm_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlarmRuleDO extends BaseDO {

    /**
     * 规则ID
     */
    @TableId
    private Long id;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 关联对象类型
     */
    private String relatedObjectType;
    /**
     * 关联对象范围
     */
    private String relatedObjectScope;
    /**
     * 关联对象分类ID
     */
    private String relatedObjectCategoryId;
    /**
     * 关联对象ID列表
     */
    private String relatedObjectIdList;
    /**
     * 预警触发条件
     */
    private String triggerCondition;
    /**
     * 预警指标编码
     */
    private String warningIndicatorCode;
    /**
     * 预警阈值上限
     */
    private BigDecimal warningThresholdUpper;
    /**
     * 预警阈值下限
     */
    private BigDecimal warningThresholdLower;
    /**
     * 状态判定值
     */
    private String statusDetermineValue;
    /**
     * 触发时长(分钟)
     */
    private Integer triggerDuration;
    /**
     * 预警等级(1-5)
     */
    private Integer earlyWarningLevel;
    /**
     * 触发频率(次/小时)
     */
    private Integer triggerFrequency;
    /**
     * 重复触发间隔(分钟)
     */
    private Integer repeatTriggerInterval;
    /**
     * 启用状态(0-禁用,1-启用)
     */
    private Integer enableStatus;
    /**
     * 规则描述
     */
    private String ruleDescription;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 扩展分类字段1
     */
    private String extendCategory1;
    /**
     * 扩展分类字段2
     */
    private String extendCategory2;
    /**
     * 扩展分类字段3
     */
    private String extendCategory3;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}