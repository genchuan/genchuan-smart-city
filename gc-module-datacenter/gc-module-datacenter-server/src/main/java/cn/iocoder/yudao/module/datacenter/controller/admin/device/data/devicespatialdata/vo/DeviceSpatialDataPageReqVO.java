package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备空间数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceSpatialDataPageReqVO extends PageParam {

    @Schema(description = "空间数据ID")
    private String deviceSpatialId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "坐标系类型")
    private String coordSystem;

    @Schema(description = "坐标X(度)")
    private BigDecimal coordX;

    @Schema(description = "坐标Y(度)")
    private BigDecimal coordY;

    @Schema(description = "高程(米)")
    private BigDecimal elevation;

    @Schema(description = "定位精度(米)")
    private BigDecimal positionAccuracy;

    @Schema(description = "空间数据格式")
    private String spatialDataFormat;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "录入时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inputTime;

    @Schema(description = "录入人")
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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}