package cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 仪器零点/量程漂移校验新增/修改 Request VO")
@Data
public class InstrumentCalibrationSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "仪器ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仪器ID不能为空")
    private String instrumentId;

    @Schema(description = "校验日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "校验日期不能为空")
    private LocalDateTime calibrationDate;

    @Schema(description = "零点校正液浓度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "零点校正液浓度不能为空")
    private Double zeroPointConc;

    @Schema(description = "零点漂移值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "零点漂移值不能为空")
    private Double zeroDrift;

    @Schema(description = "量程校正液浓度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "量程校正液浓度不能为空")
    private Double spanConc;

    @Schema(description = "量程漂移值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "量程漂移值不能为空")
    private Double spanDrift;

    @Schema(description = "校验结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "校验结果不能为空")
    private String calibrationResult;

    @Schema(description = "操作人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "操作人员ID不能为空")
    private String operatorId;

}