package cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionoptsugg;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 决策优化建议 DO
 *
 * @author 亘川智城
 */
@TableName("biz_decision_opt_sugg")
@KeySequence("biz_decision_opt_sugg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionOptSuggDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 建议ID，唯一编码，UUID生成
     */
    private String optSuggId;
    /**
     * 关联评估ID，关联决策效果评估表的评估ID，决策效果评估表（biz_decision_effect_eval）
     */
    private String relEvalId;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）
     */
    private String relSchemeName;
    /**
     * 建议类型：系统自动/手动补充，标识建议来源
     */
    private String suggType;
    /**
     * 建议内容，优化建议详情（如“增加每月2次应急培训”）
     */
    private String suggContent;
    /**
     * 参考案例ID，关联典型场景案例库表的案例ID（系统自动建议时必填），典型场景案例库表（biz_typical_scenario_case）
     */
    private String refCaseId;
    /**
     * 参考案例名称，与案例ID同步，典型场景案例库表（biz_typical_scenario_case）
     */
    private String refCaseName;
    /**
     * 建议优先级：高/中/低，建议实施优先级
     */
    private String suggPriority;
    /**
     * 建议状态：待采纳/已采纳/已驳回，标识建议处理状态
     */
    private String suggStatus;
    /**
     * 提出人，建议提出人ID（系统自动建议为“sys”），用户信息表（sys_user）
     */
    private String proposer;
    /**
     * 提出人姓名，与提出人同步，用户信息表（sys_user）
     */
    private String proposerName;
    /**
     * 提出时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime proposeTime;
    /**
     * 处理人，处理建议的用户ID，用户信息表（sys_user）
     */
    private String handler;
    /**
     * 处理时间，格式：yyyy-MM-dd HH:mm:ss，建议状态非“待采纳”时必填
     */
    private LocalDateTime handleTime;
    /**
     * 处理意见，处理建议的理由（如“采纳，下月起执行培训”）
     */
    private String handleOpinion;
    /**
     * 分类扩展字段1，预留，如“建议适用场景”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“建议适用场景”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储建议实施计划
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储建议实施计划
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}