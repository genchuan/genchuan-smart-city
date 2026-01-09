package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 巡查计划管理新增/修改 Request VO")
@Data
public class InspectionPlanManagementSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "17996")
    private Long id;

    @Schema(description = "计划名称", example = "王五")
    private String planName;

    @Schema(description = "巡查区域")
    private String patrolArea;

    @Schema(description = "巡查周期")
    private String inspectionCycle;

    @Schema(description = "计划开始时间")
    private String scheduledStartTime;

    @Schema(description = "计划结束时间")
    private String plannedEndTime;

    @Schema(description = "巡查人员安排")
    private String arrangementOfPatrolPersonnel;

    @Schema(description = "巡查内容")
    private String inspectionContent;

    @Schema(description = "巡查标准")
    private String inspectionStandards;

    @Schema(description = "应急处置预案")
    private String emergencyResponsePlan;

    @Schema(description = "备注")
    private String notes;

}