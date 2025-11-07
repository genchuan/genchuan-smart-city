package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionstatistics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡查分析统计新增/修改 Request VO")
@Data
public class InspectionStatisticsSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19334")
    private Long id;

    @Schema(description = "巡查区域")
    private String patrolArea;

    @Schema(description = "巡查人员")
    private String patrolPersonnel;

    @Schema(description = "巡查任务完成率")
    private String completionInspectionTasks;

    @Schema(description = "平均巡查时长")
    private String averagePatrolDuration;

    @Schema(description = "问题发现数量")
    private String numberProblemDiscoveries;

    @Schema(description = "不同类型问题分布")
    private String distributionProblems;

    @Schema(description = "问题解决率")
    private String problemSolvingRate;

    @Schema(description = "重复问题发生率")
    private String repetitiveProblemRate;

    @Schema(description = "风险等级评估")
    private String riskLevelAssessment;

    @Schema(description = "建议与改进措施")
    private String suggestionsMeasures;

}