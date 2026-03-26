package cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 水源水文参数管理新增/修改 Request VO")
@Data
public class WaterHydrologyParamSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "监测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "监测时间不能为空")
    private LocalDateTime monitorTime;

    @Schema(description = "水位值(米)")
    private Double waterLevel;

    @Schema(description = "含水层厚度(米)")
    private Double aquiferThickness;

    @Schema(description = "渗透系数(m/d)")
    private Double permeabilityCoefficient;

    @Schema(description = "数据采集人")
    private String dataCollector;

}