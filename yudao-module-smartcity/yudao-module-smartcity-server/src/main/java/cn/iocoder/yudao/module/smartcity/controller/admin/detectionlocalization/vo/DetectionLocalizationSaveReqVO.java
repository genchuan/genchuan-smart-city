package cn.iocoder.yudao.module.smartcity.controller.admin.detectionlocalization.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 故障检测与定位新增/修改 Request VO")
@Data
public class DetectionLocalizationSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "8267")
    private Long id;

    @Schema(description = "设备名称", example = "李四")
    private String equipmentName;

    @Schema(description = "设备编号")
    private String equipmentNumber;

    @Schema(description = "设备位置")
    private String deviceLocation;

    @Schema(description = "故障代码")
    private String faultCode;

    @Schema(description = "故障现象")
    private String faultPhenomenon;

    @Schema(description = "故障时间")
    private LocalDateTime mtbf;

    @Schema(description = "故障类型", example = "1")
    private String faultType;

    @Schema(description = "传感器数据")
    private String sensorData;

    @Schema(description = "故障影响范围")
    private String scopeOfFaultImpact;

    @Schema(description = "故障严重程度")
    private String severityOfMalfunction;

    @Schema(description = "定位信息")
    private String locateInformation;

    @Schema(description = "人工确认情况")
    private String manuallyConfirmTheSituation;

    @Schema(description = "维修时间")
    private LocalDateTime maintenanceTime;

    @Schema(description = "维修结果")
    private String repairResults;

    @Schema(description = "恢复运行时间")
    private LocalDateTime restoreRunningTime;

}