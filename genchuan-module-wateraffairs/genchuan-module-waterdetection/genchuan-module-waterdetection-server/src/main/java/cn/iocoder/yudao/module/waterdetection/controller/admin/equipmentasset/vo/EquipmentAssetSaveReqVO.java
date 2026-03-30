package cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备资产台账管理新增/修改 Request VO")
@Data
public class EquipmentAssetSaveReqVO {

    @Schema(description = "序号", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "设备编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编号不能为空")
    private String equipmentCode;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String equipmentName;

    @Schema(description = "型号")
    private String model;

    @Schema(description = "规格")
    private String specification;

    @Schema(description = "安装位置")
    private String installLocation;

    @Schema(description = "安装日期")
    private LocalDateTime installDate;

    @Schema(description = "生产厂家")
    private String manufacturer;

    @Schema(description = "维护记录")
    private String maintenanceRecord;

}