package cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 在线数据与实验室比对新增/修改 Request VO")
@Data
public class OnlineLabComparisonSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "比对日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "比对日期不能为空")
    private LocalDateTime comparisonDate;

    @Schema(description = "监测点ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "监测点ID不能为空")
    private String monitorPointId;

    @Schema(description = "仪器类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "仪器类型不能为空")
    private String instrumentType;

    @Schema(description = "在线监测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "在线监测值不能为空")
    private Double onlineValue;

    @Schema(description = "实验室检测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实验室检测值不能为空")
    private Double labValue;

    @Schema(description = "偏差值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "偏差值不能为空")
    private Double deviationValue;

    @Schema(description = "是否超标(0否1是)")
    private Boolean isExceeded;

    @Schema(description = "预警状态")
    private String warningStatus;

}