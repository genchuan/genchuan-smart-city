package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备关联设备分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceDevicePageReqVO extends PageParam {

    @Schema(description = "关联ID")
    private String deviceRelDeviceId;

    @Schema(description = "主设备ID")
    private String mainDeviceId;

    @Schema(description = "主设备名称")
    private String mainDeviceName;

    @Schema(description = "主设备编码")
    private String mainDeviceCode;

    @Schema(description = "从设备ID")
    private String slaveDeviceId;

    @Schema(description = "从设备名称")
    private String slaveDeviceName;

    @Schema(description = "从设备编码")
    private String slaveDeviceCode;

    @Schema(description = "关联类型")
    private String relType;

    @Schema(description = "关联时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] relTime;

    @Schema(description = "关联人")
    private String relUser;

    @Schema(description = "关联状态")
    private String relStatus;

    @Schema(description = "调整时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] adjustTime;

    @Schema(description = "调整人")
    private String adjustUser;

    @Schema(description = "调整原因")
    private String adjustReason;

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