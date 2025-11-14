package cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 方案模拟对比统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SchemeSimComparePageReqVO extends PageParam {

    @Schema(description = "对比ID，唯一编码，UUID生成", example = "1825")
    private String simCompareId;

    @Schema(description = "对比名称，如“XX区域燃气方案A vs 方案B”", example = "王五")
    private String compareName;

    @Schema(description = "参与方案IDs，决策方案ID，英文逗号分隔（如“id1,id2”），决策方案表（biz_decision_scheme）")
    private String participateSchemeIds;

    @Schema(description = "参与方案名称，与方案IDs同步，逗号分隔，决策方案表（biz_decision_scheme）")
    private String participateSchemeNames;

    @Schema(description = "资源投入权重，%，10-50，资源投入维度权重，默认30%，权重配置表（sys_scheme_weight_cfg）")
    private Integer resInputWeight;

    @Schema(description = "预期效果权重，%，30-70，预期效果维度权重，默认50%，权重配置表（sys_scheme_weight_cfg）")
    private Integer expectedEffectWeight;

    @Schema(description = "实施周期权重，%，10-30，实施周期维度权重，默认20%，权重配置表（sys_scheme_weight_cfg）")
    private Integer implementationCycleWeight;

    @Schema(description = "方案1_ID，第一个参与方案ID，决策方案表（biz_decision_scheme）", example = "21145")
    private String scheme1Id;

    @Schema(description = "方案1_资源投入得分，分，0-100，方案1资源投入得分（预算越低、资源利用率越高得分越高）")
    private BigDecimal scheme1ResScore;

    @Schema(description = "方案1_预期效果得分，分，0-100，方案1预期效果得分（事件下降率越高、响应时长越短得分越高）")
    private BigDecimal scheme1EffectScore;

    @Schema(description = "方案1_周期得分，分，0-100，方案1周期得分（周期越短得分越高）")
    private BigDecimal scheme1CycleScore;

    @Schema(description = "方案1_综合得分，分，0-100，（资源得分×权重+效果得分×权重+周期得分×权重）/100")
    private BigDecimal scheme1CompositeScore;

    @Schema(description = "方案2_ID，第二个参与方案ID（至少2个方案对比），决策方案表（biz_decision_scheme）", example = "9766")
    private String scheme2Id;

    @Schema(description = "方案2_资源投入得分，分，0-100，同方案1逻辑")
    private BigDecimal scheme2ResScore;

    @Schema(description = "方案2_预期效果得分，分，0-100，同方案1逻辑")
    private BigDecimal scheme2EffectScore;

    @Schema(description = "方案2_周期得分，分，0-100，同方案1逻辑")
    private BigDecimal scheme2CycleScore;

    @Schema(description = "方案2_综合得分，分，0-100，同方案1逻辑")
    private BigDecimal scheme2CompositeScore;

    @Schema(description = "对比结论，如“方案1综合得分更高，推荐采纳”")
    private String compareConclusion;

    @Schema(description = "对比人，生成对比报告的用户ID，用户信息表（sys_user）")
    private String compareUser;

    @Schema(description = "对比时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] compareTime;

    @Schema(description = "分类扩展字段1，预留，如“对比场景”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“对比场景”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储权重设置说明")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储权重设置说明")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}