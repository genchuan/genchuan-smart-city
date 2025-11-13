package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 按设备分域预警告警统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnDevicePageReqVO extends PageParam {

    @Schema(description = "统计ID")
    private String warnDeviceStatId;

    @Schema(description = "统计周期")
    private String statCycle;

    @Schema(description = "设备大类ID")
    private String deviceMajorId;

    @Schema(description = "设备大类名称")
    private String deviceMajorName;

    @Schema(description = "设备型号ID")
    private String deviceModelId;

    @Schema(description = "设备型号名称")
    private String deviceModelName;

    @Schema(description = "预警总数")
    private Integer totalWarnCount;

    @Schema(description = "主要故障类型")
    private String mainFaultType;

    @Schema(description = "涉及设备数")
    private Integer deviceCount;

    @Schema(description = "在线修复数")
    private Integer onlineRepairCount;

    @Schema(description = "在线修复率")
    private BigDecimal onlineRepairRate;

    @Schema(description = "统计人")
    private String statUserId;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

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