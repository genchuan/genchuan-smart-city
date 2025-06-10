package cn.iocoder.yudao.module.smartcity.controller.admin.maintenanceplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 养护计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MaintenancePlanRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "30659")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "计划编号")
    @ExcelProperty("计划编号")
    private String planNumber;

    @Schema(description = "计划名称", example = "王五")
    @ExcelProperty("计划名称")
    private String planName;

    @Schema(description = "计划制定日期")
    @ExcelProperty("计划制定日期")
    private LocalDateTime planFormulationDate;

    @Schema(description = "计划开始日期")
    @ExcelProperty("计划开始日期")
    private LocalDateTime startDate;

    @Schema(description = "计划结束日期")
    @ExcelProperty("计划结束日期")
    private LocalDateTime plannedEndDate;

    @Schema(description = "负责养护人员")
    @ExcelProperty("负责养护人员")
    private String maintenancePersonnel;

    @Schema(description = "养护地块")
    @ExcelProperty("养护地块")
    private String maintainParcel;

    @Schema(description = "养护内容")
    @ExcelProperty("养护内容")
    private String maintenanceContent;

    @Schema(description = "养护频率")
    @ExcelProperty("养护频率")
    private String maintenanceFrequency;

    @Schema(description = "养护资源需求")
    @ExcelProperty("养护资源需求")
    private String maintenanceDemand;

    @Schema(description = "计划状态", example = "2")
    @ExcelProperty("计划状态")
    private String planStatus;

    @Schema(description = "计划预算")
    @ExcelProperty("计划预算")
    private String plannedBudget;

    @Schema(description = "审批人")
    @ExcelProperty("审批人")
    private String approver;

    @Schema(description = "审批意见")
    @ExcelProperty("审批意见")
    private String approvalOpinion;

    @Schema(description = "审批日期")
    @ExcelProperty("审批日期")
    private LocalDateTime approvalDate;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}