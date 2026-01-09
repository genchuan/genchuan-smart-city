package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查计划管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionPlanManagementRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "17996")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "计划名称", example = "王五")
    @ExcelProperty("计划名称")
    private String planName;

    @Schema(description = "巡查区域")
    @ExcelProperty("巡查区域")
    private String patrolArea;

    @Schema(description = "巡查周期")
    @ExcelProperty("巡查周期")
    private String inspectionCycle;

    @Schema(description = "计划开始时间")
    @ExcelProperty("计划开始时间")
    private String scheduledStartTime;

    @Schema(description = "计划结束时间")
    @ExcelProperty("计划结束时间")
    private String plannedEndTime;

    @Schema(description = "巡查人员安排")
    @ExcelProperty("巡查人员安排")
    private String arrangementOfPatrolPersonnel;

    @Schema(description = "巡查内容")
    @ExcelProperty("巡查内容")
    private String inspectionContent;

    @Schema(description = "巡查标准")
    @ExcelProperty("巡查标准")
    private String inspectionStandards;

    @Schema(description = "应急处置预案")
    @ExcelProperty("应急处置预案")
    private String emergencyResponsePlan;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}