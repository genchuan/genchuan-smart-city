package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicecatmng.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分类管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceCatMngPageReqVO extends PageParam {

    @Schema(description = "分类管理ID")
    private String catMngId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备中类ID")
    private String deviceMidId;

    @Schema(description = "设备中类名称")
    private String deviceMidName;

    @Schema(description = "设备小类ID")
    private String deviceMinorId;

    @Schema(description = "设备小类名称")
    private String deviceMinorName;

    @Schema(description = "分类时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] catTime;

    @Schema(description = "分类人")
    private String catUser;

    @Schema(description = "分类状态")
    private String catStatus;

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