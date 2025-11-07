package cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 故障检测与定位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DetectionLocalizationRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8267")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "设备名称", example = "李四")
    @ExcelProperty("设备名称")
    private String equipmentName;

    @Schema(description = "设备编号")
    @ExcelProperty("设备编号")
    private String equipmentNumber;

    @Schema(description = "设备位置")
    @ExcelProperty("设备位置")
    private String deviceLocation;

    @Schema(description = "故障代码")
    @ExcelProperty("故障代码")
    private String faultCode;

    @Schema(description = "故障现象")
    @ExcelProperty("故障现象")
    private String faultPhenomenon;

    @Schema(description = "故障时间")
    @ExcelProperty("故障时间")
    private LocalDateTime mtbf;

    @Schema(description = "故障类型", example = "1")
    @ExcelProperty("故障类型")
    private String faultType;

    @Schema(description = "传感器数据")
    @ExcelProperty("传感器数据")
    private String sensorData;

    @Schema(description = "故障影响范围")
    @ExcelProperty("故障影响范围")
    private String scopeOfFaultImpact;

    @Schema(description = "故障严重程度")
    @ExcelProperty("故障严重程度")
    private String severityOfMalfunction;

    @Schema(description = "定位信息")
    @ExcelProperty("定位信息")
    private String locateInformation;

    @Schema(description = "人工确认情况")
    @ExcelProperty("人工确认情况")
    private String manuallyConfirmTheSituation;

    @Schema(description = "维修时间")
    @ExcelProperty("维修时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "维修结果")
    @ExcelProperty("维修结果")
    private String repairResults;

    @Schema(description = "恢复运行时间")
    @ExcelProperty("恢复运行时间")
    private LocalDateTime restoreRunningTime;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}