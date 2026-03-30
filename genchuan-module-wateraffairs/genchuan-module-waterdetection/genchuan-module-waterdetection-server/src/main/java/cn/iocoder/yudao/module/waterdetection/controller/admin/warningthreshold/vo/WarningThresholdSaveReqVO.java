package cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警阈值管理新增/修改 Request VO")
@Data
public class WarningThresholdSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "指标名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指标名称不能为空")
    private String indicatorName;

    @Schema(description = "阈值类型(上限/下限)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "阈值类型(上限/下限)不能为空")
    private String thresholdType;

    @Schema(description = "阈值数值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "阈值数值不能为空")
    private Double thresholdValue;

    @Schema(description = "单位", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位不能为空")
    private String unit;

    @Schema(description = "适用场景(如管网末梢)")
    private String applicableScene;

    @Schema(description = "生效时间")
    private LocalDateTime effectiveTime;

}