package cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 决策效果评估 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DecisionEffectEvalRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23667")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评估ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "1710")
    @ExcelProperty("评估ID，唯一编码，UUID生成")
    private String effectEvalId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27806")
    @ExcelProperty("关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）")
    private String relSchemeName;

    @Schema(description = "实施周期，实际实施周期（如“2025-10-01至2025-12-20”），决策执行跟踪表（biz_decision_exec_track）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实施周期，实际实施周期（如“2025-10-01至2025-12-20”），决策执行跟踪表（biz_decision_exec_track）")
    private String implementationCycle;

    @Schema(description = "预期效果，方案预期效果（如“事件下降50%”），决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预期效果，方案预期效果（如“事件下降50%”），决策方案表（biz_decision_scheme）")
    private String expectedEffect;

    @Schema(description = "实际效果，最终实际效果（如“事件下降55%”），分域分析统计表（如stat_analysis_mon_evt）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实际效果，最终实际效果（如“事件下降55%”），分域分析统计表（如stat_analysis_mon_evt）")
    private String actualEffect;

    @Schema(description = "效果达成率，实际效果/预期效果×100，取值范围：0.00-100.00，单位：%，无", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("效果达成率，实际效果/预期效果×100，取值范围：0.00-100.00，单位：%，无")
    private BigDecimal effectAchievementRate;

    @Schema(description = "资源总投入，实施过程中资源投入总额（设备+人员+其他），单位：万元，资源投入表（biz_res_input）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资源总投入，实施过程中资源投入总额（设备+人员+其他），单位：万元，资源投入表（biz_res_input）")
    private BigDecimal resourceTotalInput;

    @Schema(description = "投入回报率，(实际收益-资源投入)/资源投入×100，取值范围：-100.00-1000.00，单位：%，无")
    @ExcelProperty("投入回报率，(实际收益-资源投入)/资源投入×100，取值范围：-100.00-1000.00，单位：%，无")
    private BigDecimal inputReturnRate;

    @Schema(description = "用户满意度，用户满意度调查结果，取值范围：0.00-100.00，单位：%，用户满意度调查表（biz_user_satisfy_survey）")
    @ExcelProperty("用户满意度，用户满意度调查结果，取值范围：0.00-100.00，单位：%，用户满意度调查表（biz_user_satisfy_survey）")
    private BigDecimal userSatisfy;

    @Schema(description = "评估等级：优秀/良好/合格/不合格，判定规则：效果达成率≥120%=优秀，100%-119%=良好，80%-99%=合格，<80%=不合格，无", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估等级：优秀/良好/合格/不合格，判定规则：效果达成率≥120%=优秀，100%-119%=良好，80%-99%=合格，<80%=不合格，无")
    private String evalGrade;

    @Schema(description = "成功经验，方案实施中的成功做法（如“传感器部署覆盖重点区域”），无")
    @ExcelProperty("成功经验，方案实施中的成功做法（如“传感器部署覆盖重点区域”），无")
    private String successExp;

    @Schema(description = "改进点，需优化的问题（如“人员应急处置培训不足”），无")
    @ExcelProperty("改进点，需优化的问题（如“人员应急处置培训不足”），无")
    private String improvePts;

    @Schema(description = "评估人，负责评估的用户ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估人，负责评估的用户ID，用户信息表（sys_user）")
    private String evalUser;

    @Schema(description = "评估时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评估时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime evalTime;

    @Schema(description = "评估报告附件，评估报告文件路径（PDF格式），无")
    @ExcelProperty("评估报告附件，评估报告文件路径（PDF格式），无")
    private String evalRptAttach;

    @Schema(description = "分类扩展字段1，预留，如“评估类型”")
    @ExcelProperty("分类扩展字段1，预留，如“评估类型”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“评估类型”")
    @ExcelProperty("分类扩展字段2，预留，如“评估类型”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估会议纪要链接")
    @ExcelProperty("通用扩展字段1，预留，存储评估会议纪要链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估会议纪要链接")
    @ExcelProperty("通用扩展字段2，预留，存储评估会议纪要链接")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}