package cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionexectrack;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 决策执行跟踪 DO
 *
 * @author 亘川智城
 */
@TableName("biz_decision_exec_track")
@KeySequence("biz_decision_exec_track_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecisionExecTrackDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 跟踪ID，唯一编码，UUID生成
     */
    private String execTrackId;
    /**
     * 关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）
     */
    private String relSchemeId;
    /**
     * 关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）
     */
    private String relSchemeName;
    /**
     * 计划实施周期，如“2025-10-01至2025-12-31”，决策方案表（biz_decision_scheme）
     */
    private String plannedExecCycle;
    /**
     * 已实施天数，天，当前日期-实施启动日期
     */
    private Integer executedDays;
    /**
     * 实施进度，%，0.00-100.00，已完成任务数/计划任务数×100，执行进度记录表（biz_exec_progress_rec）
     */
    private BigDecimal execProgress;
    /**
     * 预期效果指标，如“燃气泄漏事件下降50%，响应时长≤20分钟”，决策方案表（biz_decision_scheme）
     */
    private String expectedEffectIdx;
    /**
     * 实际效果指标，如“燃气泄漏事件下降40%，响应时长≤25分钟”，分域分析统计表（如stat_analysis_mon_evt）
     */
    private String actualEffectIdx;
    /**
     * 效果达成率，%，0.00-100.00，实际效果/预期效果×100（多指标取平均值）
     */
    private BigDecimal effectAchievementRate;
    /**
     * 未达标项，如“事件下降率未达预期（40%<50%）"
     */
    private String unmetItems;
    /**
     * 跟踪状态，实施中/已完成/逾期未完成，标识跟踪状态
     */
    private String trackStatus;
    /**
     * 跟踪人，负责跟踪的用户ID，用户信息表（sys_user）
     */
    private String trackUser;
    /**
     * 跟踪时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime trackTime;
    /**
     * 上次跟踪时间，格式：yyyy-MM-dd HH:mm:ss，记录上次跟踪时间
     */
    private LocalDateTime lastTrackTime;
    /**
     * 整改建议，针对未达标项的建议（如“加强专项小组巡检频次”）
     */
    private String rectificationSugg;
    /**
     * 分类扩展字段1，预留，如“跟踪频次”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“跟踪频次”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储实施负责人联系方式
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储实施负责人联系方式
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