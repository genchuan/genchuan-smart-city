package cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsanitation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 市容环卫新增/修改 Request VO")
@Data
public class EnvironmentalSanitationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "29302")
    private Long id;

    @Schema(description = "设施名称", example = "王五")
    private String facilityName;

    @Schema(description = "设施位置")
    private String facilityLocation;

    @Schema(description = "数量")
    private String quantity;

    @Schema(description = "建设时间")
    private LocalDateTime constructionTime;

    @Schema(description = "使用年限")
    private String serviceLife;

    @Schema(description = "处理能力")
    private String processingCapacity;

    @Schema(description = "维护需求")
    private String maintenanceRequirements;

    @Schema(description = "年度预算金额")
    private String annualBudgetAmount;

    @Schema(description = "成本效益分析")
    private String costBenefitAnalysis;

    @Schema(description = "对周边环境影响评估")
    private String assessmentSurEnvironment;

    @Schema(description = "节能减排指标")
    private String energyConservationTargets;

}