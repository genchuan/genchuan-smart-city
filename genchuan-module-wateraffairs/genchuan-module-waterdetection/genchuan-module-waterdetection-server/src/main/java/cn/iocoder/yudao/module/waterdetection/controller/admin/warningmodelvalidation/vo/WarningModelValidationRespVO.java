package cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 预警模型校验 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WarningModelValidationRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "模型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("模型名称")
    private String modelName;

    @Schema(description = "校验时间段", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校验时间段")
    private String validationPeriod;

    @Schema(description = "预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("预警次数")
    private Double warningCount;

    @Schema(description = "准确预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("准确预警次数")
    private Double accurateWarningCount;

    @Schema(description = "误报次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("误报次数")
    private Double falseAlarmCount;

    @Schema(description = "准确率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("准确率(%)")
    private Double accuracyRate;

    @Schema(description = "调整建议")
    @ExcelProperty("调整建议")
    private String adjustmentSuggestion;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}