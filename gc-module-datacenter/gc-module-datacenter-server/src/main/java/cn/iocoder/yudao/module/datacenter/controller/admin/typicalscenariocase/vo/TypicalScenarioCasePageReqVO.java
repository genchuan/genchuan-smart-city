package cn.iocoder.yudao.module.datacenter.controller.admin.typicalscenariocase.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 典型场景案例库分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TypicalScenarioCasePageReqVO extends PageParam {

    @Schema(description = "案例ID，唯一编码，UUID生成", example = "14873")
    private String typicalCaseId;

    @Schema(description = "案例名称，如“2025年XX区域燃气泄漏事件优化案例”", example = "赵六")
    private String caseName;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", example = "14489")
    private String relSchemeId;

    @Schema(description = "关联评估ID，关联决策效果评估表的评估ID，决策效果评估表（biz_decision_effect_eval）", example = "1512")
    private String relEvalId;

    @Schema(description = "案例标签，逗号分隔的标签（如“燃气/应急处置/传感器部署”）")
    private String caseTags;

    @Schema(description = "适用场景，案例适用的业务场景（如“城市燃气管道泄漏事件处置优化”）")
    private String applicableScenario;

    @Schema(description = "核心措施，案例的核心实施措施（分点描述），决策方案表（biz_decision_scheme）")
    private String coreMeasures;

    @Schema(description = "资源投入，如“传感器3台（8万元）、人员2名（5万元）”，决策方案表（biz_decision_scheme）")
    private String resourceInput;

    @Schema(description = "实施效果，如“事件下降55%，响应时长缩短至18分钟”，决策效果评估表（biz_decision_effect_eval）")
    private String implementationEffect;

    @Schema(description = "评估等级：优秀/良好/合格/不合格，取自决策效果评估表，决策效果评估表（biz_decision_effect_eval）")
    private String evalGrade;

    @Schema(description = "成功经验，案例的成功做法（取自评估表），决策效果评估表（biz_decision_effect_eval）")
    private String successExp;

    @Schema(description = "改进建议，案例的优化建议（取自优化建议表），决策优化建议表（biz_decision_opt_sugg）")
    private String improveSuggs;

    @Schema(description = "归档人，案例归档人ID，用户信息表（sys_user）")
    private String archUser;

    @Schema(description = "归档时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] archTime;

    @Schema(description = "案例状态：已归档/已下架，标识案例是否可用", example = "2")
    private String caseStatus;

    @Schema(description = "分类扩展字段1，预留，如“案例来源”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“案例来源”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储案例附件链接")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储案例附件链接")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}