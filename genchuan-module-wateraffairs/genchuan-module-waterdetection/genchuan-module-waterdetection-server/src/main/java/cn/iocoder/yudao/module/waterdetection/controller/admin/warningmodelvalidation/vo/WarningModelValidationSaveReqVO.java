package cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 预警模型校验新增/修改 Request VO")
@Data
public class WarningModelValidationSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "模型名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "模型名称不能为空")
    private String modelName;

    @Schema(description = "校验时间段", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "校验时间段不能为空")
    private String validationPeriod;

    @Schema(description = "预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "预警次数不能为空")
    private Double warningCount;

    @Schema(description = "准确预警次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "准确预警次数不能为空")
    private Double accurateWarningCount;

    @Schema(description = "误报次数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "误报次数不能为空")
    private Double falseAlarmCount;

    @Schema(description = "准确率(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "准确率(%)不能为空")
    private Double accuracyRate;

    @Schema(description = "调整建议")
    private String adjustmentSuggestion;

}