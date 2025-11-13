package cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 决策执行跟踪分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DecisionExecTrackPageReqVO extends PageParam {

    @Schema(description = "跟踪ID，唯一编码，UUID生成", example = "6340")
    private String execTrackId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", example = "5584")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", example = "赵六")
    private String relSchemeName;

    @Schema(description = "计划实施周期，如“2025-10-01至2025-12-31”，决策方案表（biz_decision_scheme）")
    private String plannedExecCycle;

    @Schema(description = "已实施天数，天，当前日期-实施启动日期")
    private Integer executedDays;

    @Schema(description = "实施进度，%，0.00-100.00，已完成任务数/计划任务数×100，执行进度记录表（biz_exec_progress_rec）")
    private BigDecimal execProgress;

    @Schema(description = "预期效果指标，如“燃气泄漏事件下降50%，响应时长≤20分钟”，决策方案表（biz_decision_scheme）")
    private String expectedEffectIdx;

    @Schema(description = "实际效果指标，如“燃气泄漏事件下降40%，响应时长≤25分钟”，分域分析统计表（如stat_analysis_mon_evt）")
    private String actualEffectIdx;

    @Schema(description = "效果达成率，%，0.00-100.00，实际效果/预期效果×100（多指标取平均值）")
    private BigDecimal effectAchievementRate;

    @Schema(description = "未达标项，如事件下降率未达预期（40%<50%）")
    private String unmetItems;

    @Schema(description = "跟踪状态，实施中/已完成/逾期未完成，标识跟踪状态", example = "1")
    private String trackStatus;

    @Schema(description = "跟踪人，负责跟踪的用户ID，用户信息表（sys_user）")
    private String trackUser;

    @Schema(description = "跟踪时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] trackTime;

    @Schema(description = "上次跟踪时间，格式：yyyy-MM-dd HH:mm:ss，记录上次跟踪时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastTrackTime;

    @Schema(description = "整改建议，针对未达标项的建议（如“加强专项小组巡检频次”）")
    private String rectificationSugg;

    @Schema(description = "分类扩展字段1，预留，如“跟踪频次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“跟踪频次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储实施负责人联系方式")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储实施负责人联系方式")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}