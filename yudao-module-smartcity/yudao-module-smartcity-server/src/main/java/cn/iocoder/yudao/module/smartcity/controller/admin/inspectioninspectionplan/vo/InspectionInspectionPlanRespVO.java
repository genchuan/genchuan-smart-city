package cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查计划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionInspectionPlanRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "15607")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "巡查项目")
    @ExcelProperty("巡查项目")
    private String inspectionProject;

    @Schema(description = "巡查地点")
    @ExcelProperty("巡查地点")
    private String patrolLocation;

    @Schema(description = "巡查周期")
    @ExcelProperty("巡查周期")
    private String inspectionCycle;

    @Schema(description = "巡查时间")
    @ExcelProperty("巡查时间")
    private LocalDateTime patrolTime;

    @Schema(description = "巡查方式")
    @ExcelProperty("巡查方式")
    private String inspectionMethod;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}