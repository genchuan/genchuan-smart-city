package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 设备空间数据新增/修改 Request VO")
@Data
public class DeviceSpatialDataSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "空间数据ID")
    private String deviceSpatialId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备ID不能为空")
    private String deviceId;

    @Schema(description = "设备名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备名称不能为空")
    private String deviceName;

    @Schema(description = "设备编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "设备编码不能为空")
    private String deviceCode;

    @Schema(description = "坐标系类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "坐标系类型不能为空")
    private String coordSystem;

    @Schema(description = "坐标X(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标X(度)不能为空")
    private BigDecimal coordX;

    @Schema(description = "坐标Y(度)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "坐标Y(度)不能为空")
    private BigDecimal coordY;

    @Schema(description = "高程(米)")
    private BigDecimal elevation;

    @Schema(description = "定位精度(米)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "定位精度(米)不能为空")
    private BigDecimal positionAccuracy;

    @Schema(description = "空间数据格式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "空间数据格式不能为空")
    private String spatialDataFormat;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "录入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "录入时间不能为空")
    private LocalDateTime inputTime;

    @Schema(description = "录入人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "录入人不能为空")
    private String inputUser;

    @Schema(description = "更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "更新人")
    private String updateUser;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}