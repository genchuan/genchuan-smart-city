package cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 重点工作任务 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KeyWorkTasksRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "241")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "任务编号")
    @ExcelProperty("任务编号")
    private String taskNumber;

    @Schema(description = "任务名称")
    @ExcelProperty("任务名称")
    private String task;

    @Schema(description = "任务描述", example = "你猜")
    @ExcelProperty("任务描述")
    private String taskDescription;

    @Schema(description = "开始时间")
    @ExcelProperty("开始时间")
    private String startTime;

    @Schema(description = "结束时")
    @ExcelProperty("结束时")
    private String atTheEnd;

    @Schema(description = "考核指标")
    @ExcelProperty("考核指标")
    private String assessmentIndicators;

    @Schema(description = "风险评估")
    @ExcelProperty("风险评估")
    private String riskAssessment;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}