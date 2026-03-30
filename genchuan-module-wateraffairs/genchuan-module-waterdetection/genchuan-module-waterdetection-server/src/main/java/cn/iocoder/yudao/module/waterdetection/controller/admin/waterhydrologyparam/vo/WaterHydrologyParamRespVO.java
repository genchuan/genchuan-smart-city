package cn.iocoder.yudao.module.waterdetection.controller.admin.waterhydrologyparam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 水源水文参数管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WaterHydrologyParamRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "监测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("监测时间")
    private LocalDateTime monitorTime;

    @Schema(description = "水位值(米)")
    @ExcelProperty("水位值(米)")
    private Double waterLevel;

    @Schema(description = "含水层厚度(米)")
    @ExcelProperty("含水层厚度(米)")
    private Double aquiferThickness;

    @Schema(description = "渗透系数(m/d)")
    @ExcelProperty("渗透系数(m/d)")
    private Double permeabilityCoefficient;

    @Schema(description = "数据采集人")
    @ExcelProperty("数据采集人")
    private String dataCollector;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}