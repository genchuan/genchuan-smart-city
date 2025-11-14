package cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 决策优化建议 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DecisionOptSuggRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27524")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "建议ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "6963")
    @ExcelProperty("建议ID，唯一编码，UUID生成")
    private String optSuggId;

    @Schema(description = "关联评估ID，关联决策效果评估表的评估ID，决策效果评估表（biz_decision_effect_eval）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10884")
    @ExcelProperty("关联评估ID，关联决策效果评估表的评估ID，决策效果评估表（biz_decision_effect_eval）")
    private String relEvalId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "3551")
    @ExcelProperty("关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）")
    private String relSchemeName;

    @Schema(description = "建议类型：系统自动/手动补充，标识建议来源", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("建议类型：系统自动/手动补充，标识建议来源")
    private String suggType;

    @Schema(description = "建议内容，优化建议详情（如“增加每月2次应急培训”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("建议内容，优化建议详情（如“增加每月2次应急培训”）")
    private String suggContent;

    @Schema(description = "参考案例ID，关联典型场景案例库表的案例ID（系统自动建议时必填），典型场景案例库表（biz_typical_scenario_case）", example = "14485")
    @ExcelProperty("参考案例ID，关联典型场景案例库表的案例ID（系统自动建议时必填），典型场景案例库表（biz_typical_scenario_case）")
    private String refCaseId;

    @Schema(description = "参考案例名称，与案例ID同步，典型场景案例库表（biz_typical_scenario_case）", example = "王五")
    @ExcelProperty("参考案例名称，与案例ID同步，典型场景案例库表（biz_typical_scenario_case）")
    private String refCaseName;

    @Schema(description = "建议优先级：高/中/低，建议实施优先级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("建议优先级：高/中/低，建议实施优先级")
    private String suggPriority;

    @Schema(description = "建议状态：待采纳/已采纳/已驳回，标识建议处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("建议状态：待采纳/已采纳/已驳回，标识建议处理状态")
    private String suggStatus;

    @Schema(description = "提出人，建议提出人ID（系统自动建议为“sys”），用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("提出人，建议提出人ID（系统自动建议为“sys”），用户信息表（sys_user）")
    private String proposer;

    @Schema(description = "提出人姓名，与提出人同步，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("提出人姓名，与提出人同步，用户信息表（sys_user）")
    private String proposerName;

    @Schema(description = "提出时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("提出时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime proposeTime;

    @Schema(description = "处理人，处理建议的用户ID，用户信息表（sys_user）")
    @ExcelProperty("处理人，处理建议的用户ID，用户信息表（sys_user）")
    private String handler;

    @Schema(description = "处理时间，格式：yyyy-MM-dd HH:mm:ss，建议状态非“待采纳”时必填")
    @ExcelProperty("处理时间，格式：yyyy-MM-dd HH:mm:ss，建议状态非“待采纳”时必填")
    private LocalDateTime handleTime;

    @Schema(description = "处理意见，处理建议的理由（如“采纳，下月起执行培训”）")
    @ExcelProperty("处理意见，处理建议的理由（如“采纳，下月起执行培训”）")
    private String handleOpinion;

    @Schema(description = "分类扩展字段1，预留，如“建议适用场景”")
    @ExcelProperty("分类扩展字段1，预留，如“建议适用场景”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“建议适用场景”")
    @ExcelProperty("分类扩展字段2，预留，如“建议适用场景”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储建议实施计划")
    @ExcelProperty("通用扩展字段1，预留，存储建议实施计划")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储建议实施计划")
    @ExcelProperty("通用扩展字段2，预留，存储建议实施计划")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}