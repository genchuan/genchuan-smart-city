package cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测仪表校准管理新增/修改 Request VO")
@Data
public class MeterCalibrationSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "仪表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仪表ID不能为空")
    private String meterId;

    @Schema(description = "仪表类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仪表类型不能为空")
    private String meterType;

    @Schema(description = "校准周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "校准周期(天)不能为空")
    private Double calibrationCycle;

    @Schema(description = "上次校准日期")
    private LocalDateTime lastCalibrationDate;

    @Schema(description = "本次校准日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本次校准日期不能为空")
    private LocalDateTime currentCalibrationDate;

    @Schema(description = "标准溶液浓度")
    private Double standardSolutionConc;

    @Schema(description = "校准前示值")
    private Double beforeCalibrationValue;

    @Schema(description = "校准后示值")
    private Double afterCalibrationValue;

    @Schema(description = "操作人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人员ID不能为空")
    private String operatorId;

}