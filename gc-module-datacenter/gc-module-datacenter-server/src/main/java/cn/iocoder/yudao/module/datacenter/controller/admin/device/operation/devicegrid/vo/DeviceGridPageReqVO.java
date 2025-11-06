package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备关联网格分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceGridPageReqVO extends PageParam {

    @Schema(description = "关联ID")
    private String deviceRelGridId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "网格ID")
    private String gridId;

    @Schema(description = "网格名称")
    private String gridName;

    @Schema(description = "网格编码")
    private String gridCode;

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