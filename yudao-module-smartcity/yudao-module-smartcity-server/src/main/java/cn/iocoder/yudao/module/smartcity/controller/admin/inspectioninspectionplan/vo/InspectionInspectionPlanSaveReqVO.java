package cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 巡查计划新增/修改 Request VO")
@Data
public class InspectionInspectionPlanSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15607")
    private Long id;

    @Schema(description = "巡查项目")
    private String inspectionProject;

    @Schema(description = "巡查地点")
    private String patrolLocation;

    @Schema(description = "巡查周期")
    private String inspectionCycle;

    @Schema(description = "巡查时间")
    private LocalDateTime patrolTime;

    @Schema(description = "巡查方式")
    private String inspectionMethod;

    @Schema(description = "备注")
    private String notes;

}