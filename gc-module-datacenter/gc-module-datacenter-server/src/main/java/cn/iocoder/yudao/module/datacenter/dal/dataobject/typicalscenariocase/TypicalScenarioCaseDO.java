package cn.iocoder.yudao.module.datacenter.dal.dataobject.typicalscenariocase;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 典型场景案例库 DO
 *
 * @author 亘川智城
 */
@TableName("biz_typical_scenario_case")
@KeySequence("biz_typical_scenario_case_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypicalScenarioCaseDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 案例ID，唯一编码，UUID生成
     */
    private String typicalCaseId;
    /**
     * 案例名称，如“2025年XX区域燃气泄漏事件优化案例”
     */
    private String caseName;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联评估ID，关联决策效果评估表的评估ID，决策效果评估表（biz_decision_effect_eval）
     */
    private String relEvalId;
    /**
     * 案例标签，逗号分隔的标签（如“燃气/应急处置/传感器部署”）
     */
    private String caseTags;
    /**
     * 适用场景，案例适用的业务场景（如“城市燃气管道泄漏事件处置优化”）
     */
    private String applicableScenario;
    /**
     * 核心措施，案例的核心实施措施（分点描述），决策方案表（biz_decision_scheme）
     */
    private String coreMeasures;
    /**
     * 资源投入，如“传感器3台（8万元）、人员2名（5万元）”，决策方案表（biz_decision_scheme）
     */
    private String resourceInput;
    /**
     * 实施效果，如“事件下降55%，响应时长缩短至18分钟”，决策效果评估表（biz_decision_effect_eval）
     */
    private String implementationEffect;
    /**
     * 评估等级：优秀/良好/合格/不合格，取自决策效果评估表，决策效果评估表（biz_decision_effect_eval）
     */
    private String evalGrade;
    /**
     * 成功经验，案例的成功做法（取自评估表），决策效果评估表（biz_decision_effect_eval）
     */
    private String successExp;
    /**
     * 改进建议，案例的优化建议（取自优化建议表），决策优化建议表（biz_decision_opt_sugg）
     */
    private String improveSuggs;
    /**
     * 归档人，案例归档人ID，用户信息表（sys_user）
     */
    private String archUser;
    /**
     * 归档时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime archTime;
    /**
     * 案例状态：已归档/已下架，标识案例是否可用
     */
    private String caseStatus;
    /**
     * 分类扩展字段1，预留，如“案例来源”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“案例来源”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储案例附件链接
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储案例附件链接
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