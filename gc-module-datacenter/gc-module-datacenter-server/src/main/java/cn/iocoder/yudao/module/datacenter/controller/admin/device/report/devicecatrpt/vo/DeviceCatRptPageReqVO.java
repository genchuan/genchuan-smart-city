package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 设备分类统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DeviceCatRptPageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String catStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "统计周期名称")
    private String statCycleName;

    @Schema(description = "行政区划代码")
    private String regionCode;

    @Schema(description = "行政区划名称")
    private String regionName;

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

    @Schema(description = "设备总数(台)")
    private Integer totalDeviceCount;

    @Schema(description = "在线设备数(台)")
    private Integer onlineDeviceCount;

    @Schema(description = "离线设备数(台)")
    private Integer offlineDeviceCount;

    @Schema(description = "故障设备数(台)")
    private Integer faultDeviceCount;

    @Schema(description = "新增设备数(台)")
    private Integer newDeviceCount;

    @Schema(description = "报废设备数(台)")
    private Integer scrappedDeviceCount;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "统计人")
    private String statUser;

    @Schema(description = "报表备注")
    private String rptRemark;

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