package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备实时状态分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceRealTimeStatusPageReqVO extends PageParam {

    @Schema(description = "状态ID")
    private String realTimeStatusId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "运行状态")
    private String runStatus;

    @Schema(description = "最近上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastRptTime;

    @Schema(description = "实时参数1")
    private String realTimeParam1;

    @Schema(description = "实时参数2")
    private String realTimeParam2;

    @Schema(description = "状态更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statusUpdateTime;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}