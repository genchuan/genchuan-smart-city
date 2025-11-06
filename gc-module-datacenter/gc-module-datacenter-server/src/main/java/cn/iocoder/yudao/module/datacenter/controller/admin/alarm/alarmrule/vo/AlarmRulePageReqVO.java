package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.alarmrule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AlarmRulePageReqVO extends PageParam {

    @Schema(description = "规则名称")
    private String ruleName;

    @Schema(description = "关联对象类型")
    private String relatedObjectType;

    @Schema(description = "关联对象范围")
    private String relatedObjectScope;

    @Schema(description = "关联对象分类ID")
    private String relatedObjectCategoryId;

    @Schema(description = "关联对象ID列表")
    private String relatedObjectIdList;

    @Schema(description = "预警触发条件")
    private String triggerCondition;

    @Schema(description = "预警指标编码")
    private String warningIndicatorCode;

    @Schema(description = "预警阈值上限")
    private BigDecimal warningThresholdUpper;

    @Schema(description = "预警阈值下限")
    private BigDecimal warningThresholdLower;

    @Schema(description = "状态判定值")
    private String statusDetermineValue;

    @Schema(description = "触发时长(分钟)")
    private Integer triggerDuration;

    @Schema(description = "预警等级(1-5)")
    private Integer earlyWarningLevel;

    @Schema(description = "触发频率(次/小时)")
    private Integer triggerFrequency;

    @Schema(description = "重复触发间隔(分钟)")
    private Integer repeatTriggerInterval;

    @Schema(description = "启用状态(0-禁用,1-启用)")
    private Integer enableStatus;

    @Schema(description = "规则描述")
    private String ruleDescription;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "扩展分类字段1")
    private String extendCategory1;

    @Schema(description = "扩展分类字段2")
    private String extendCategory2;

    @Schema(description = "扩展分类字段3")
    private String extendCategory3;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}