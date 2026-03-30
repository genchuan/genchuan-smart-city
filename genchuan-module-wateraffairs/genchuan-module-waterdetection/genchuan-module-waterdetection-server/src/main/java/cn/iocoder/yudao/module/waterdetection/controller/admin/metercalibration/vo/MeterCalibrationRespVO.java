package cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 监测仪表校准管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MeterCalibrationRespVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("序号")
    private Long id;

    @Schema(description = "仪表ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仪表ID")
    private String meterId;

    @Schema(description = "仪表类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("仪表类型")
    private String meterType;

    @Schema(description = "校准周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("校准周期(天)")
    private Double calibrationCycle;

    @Schema(description = "上次校准日期")
    @ExcelProperty("上次校准日期")
    private LocalDateTime lastCalibrationDate;

    @Schema(description = "本次校准日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("本次校准日期")
    private LocalDateTime currentCalibrationDate;

    @Schema(description = "标准溶液浓度")
    @ExcelProperty("标准溶液浓度")
    private Double standardSolutionConc;

    @Schema(description = "校准前示值")
    @ExcelProperty("校准前示值")
    private Double beforeCalibrationValue;

    @Schema(description = "校准后示值")
    @ExcelProperty("校准后示值")
    private Double afterCalibrationValue;

    @Schema(description = "操作人员ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人员ID")
    private String operatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}