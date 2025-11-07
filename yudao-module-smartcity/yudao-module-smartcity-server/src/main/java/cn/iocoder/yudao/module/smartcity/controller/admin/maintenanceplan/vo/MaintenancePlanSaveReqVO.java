package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 养护计划新增/修改 Request VO")
@Data
public class MaintenancePlanSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "30659")
    private Long id;

    @Schema(description = "计划编号")
    private String planNumber;

    @Schema(description = "计划名称", example = "王五")
    private String planName;

    @Schema(description = "计划制定日期")
    private LocalDateTime planFormulationDate;

    @Schema(description = "计划开始日期")
    private LocalDateTime startDate;

    @Schema(description = "计划结束日期")
    private LocalDateTime plannedEndDate;

    @Schema(description = "负责养护人员")
    private String maintenancePersonnel;

    @Schema(description = "养护地块")
    private String maintainParcel;

    @Schema(description = "养护内容")
    private String maintenanceContent;

    @Schema(description = "养护频率")
    private String maintenanceFrequency;

    @Schema(description = "养护资源需求")
    private String maintenanceDemand;

    @Schema(description = "计划状态", example = "2")
    private String planStatus;

    @Schema(description = "计划预算")
    private String plannedBudget;

    @Schema(description = "审批人")
    private String approver;

    @Schema(description = "审批意见")
    private String approvalOpinion;

    @Schema(description = "审批日期")
    private LocalDateTime approvalDate;

}