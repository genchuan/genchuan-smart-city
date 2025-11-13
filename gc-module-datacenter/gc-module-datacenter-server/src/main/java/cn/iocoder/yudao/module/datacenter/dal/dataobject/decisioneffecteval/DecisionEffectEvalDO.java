package cn.iocoder.yudao.module.datacenter.dal.dataobject.decisioneffecteval;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 决策效果评估 DO
 *
 * @author 亘川智城
 */
@TableName("biz_decision_effect_eval")
@KeySequence("biz_decision_effect_eval_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionEffectEvalDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 评估ID，唯一编码，UUID生成
     */
    private String effectEvalId;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）
     */
    private String relSchemeName;
    /**
     * 实施周期，实际实施周期（如“2025-10-01至2025-12-20”），决策执行跟踪表（biz_decision_exec_track）
     */
    private String implementationCycle;
    /**
     * 预期效果，方案预期效果（如“事件下降50%”），决策方案表（biz_decision_scheme）
     */
    private String expectedEffect;
    /**
     * 实际效果，最终实际效果（如“事件下降55%”），分域分析统计表（如stat_analysis_mon_evt）
     */
    private String actualEffect;
    /**
     * 效果达成率，实际效果/预期效果×100，取值范围：0.00-100.00，单位：%，无
     */
    private BigDecimal effectAchievementRate;
    /**
     * 资源总投入，实施过程中资源投入总额（设备+人员+其他），单位：万元，资源投入表（biz_res_input）
     */
    private BigDecimal resourceTotalInput;
    /**
     * 投入回报率，(实际收益-资源投入)/资源投入×100，取值范围：-100.00-1000.00，单位：%，无
     */
    private BigDecimal inputReturnRate;
    /**
     * 用户满意度，用户满意度调查结果，取值范围：0.00-100.00，单位：%，用户满意度调查表（biz_user_satisfy_survey）
     */
    private BigDecimal userSatisfy;
    /**
     * 评估等级：优秀/良好/合格/不合格，判定规则：效果达成率≥120%=优秀，100%-119%=良好，80%-99%=合格，<80%=不合格，无
     */
    private String evalGrade;
    /**
     * 成功经验，方案实施中的成功做法（如“传感器部署覆盖重点区域”），无
     */
    private String successExp;
    /**
     * 改进点，需优化的问题（如“人员应急处置培训不足”），无
     */
    private String improvePts;
    /**
     * 评估人，负责评估的用户ID，用户信息表（sys_user）
     */
    private String evalUser;
    /**
     * 评估时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime evalTime;
    /**
     * 评估报告附件，评估报告文件路径（PDF格式），无
     */
    private String evalRptAttach;
    /**
     * 分类扩展字段1，预留，如“评估类型”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“评估类型”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储评估会议纪要链接
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储评估会议纪要链接
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