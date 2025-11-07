package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备关联资产分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceAssetPageReqVO extends PageParam {

    @Schema(description = "关联ID")
    private String deviceRelAssetId;

    @Schema(description = "设备ID")
    private String deviceId;

    @Schema(description = "设备名称")
    private String deviceName;

    @Schema(description = "设备编码")
    private String deviceCode;

    @Schema(description = "资产ID")
    private String assetId;

    @Schema(description = "资产名称")
    private String assetName;

    @Schema(description = "资产编码")
    private String assetCode;

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