package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备遥测数据新增/修改 Request VO")
@Data
public class DeviceTelemetryDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "遥测数据ID")
    private String telemetryDataId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "遥测项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "遥测项名称不能为空")
    private String telemetryItemName;

    @Schema(description = "遥测项代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "遥测项代码不能为空")
    private String telemetryItemCode;

    @Schema(description = "遥测值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "遥测值不能为空")
    private String telemetryValue;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据类型不能为空")
    private String dataType;

    @Schema(description = "采集时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "采集时间不能为空")
    private LocalDateTime collectTime;

    @Schema(description = "数据来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据来源不能为空")
    private String dataSource;

    @Schema(description = "存储周期(天)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "存储周期(天)不能为空")
    private Integer storageCycle;

    @Schema(description = "过期状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "过期状态不能为空")
    private String expireStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间不能为空")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}