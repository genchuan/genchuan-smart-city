package cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 巡查任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class InspectionTaskARespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "217")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "任务编号")
    @ExcelProperty("任务编号")
    private String taskNumber;

    @Schema(description = "任务名称")
    @ExcelProperty("任务名称")
    private String task;

    @Schema(description = "任务描述", example = "你说的对")
    @ExcelProperty("任务描述")
    private String taskDescription;

    @Schema(description = "巡查地点开始时间")
    @ExcelProperty("巡查地点开始时间")
    private String startingTimeA;

    @Schema(description = "结束时间优先级")
    @ExcelProperty("结束时间优先级")
    private String endTimeB;

    @Schema(description = "检查项目")
    @ExcelProperty("检查项目")
    private String inspectionItems;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}