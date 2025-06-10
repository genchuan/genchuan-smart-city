package cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 养护考核评价新增/修改 Request VO")
@Data
public class AssessmentAndEvaluationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "14192")
    private Long id;

    @Schema(description = "养护人员编号")
    private String maintenancePersonnelNumber;

    @Schema(description = "养护任务编号")
    private String maintenanceTaskNumber;

    @Schema(description = "考核周期")
    private String assessmentCycle;

    @Schema(description = "考核得分")
    private String assessmentScore;

    @Schema(description = "考核等级")
    private String assessmentLevel;

    @Schema(description = "评价意见")
    private String evaluationOpinion;

    @Schema(description = "改进建议")
    private String improvementSuggestions;

}