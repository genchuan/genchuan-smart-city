package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备实时状态新增/修改 Request VO")
@Data
public class DeviceRealTimeStatusSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "状态ID")
    private String realTimeStatusId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "运行状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "运行状态不能为空")
    private String runStatus;

    @Schema(description = "最近上报时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最近上报时间不能为空")
    private LocalDateTime lastRptTime;

    @Schema(description = "实时参数1")
    private String realTimeParam1;

    @Schema(description = "实时参数2")
    private String realTimeParam2;

    @Schema(description = "状态更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态更新时间不能为空")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "所属行政区划代码")
    private String regionCode;

    @Schema(description = "所属行政区划名称")
    private String regionName;

    @Schema(description = "所属网格ID")
    private String gridId;

    @Schema(description = "所属网格名称")
    private String gridName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}