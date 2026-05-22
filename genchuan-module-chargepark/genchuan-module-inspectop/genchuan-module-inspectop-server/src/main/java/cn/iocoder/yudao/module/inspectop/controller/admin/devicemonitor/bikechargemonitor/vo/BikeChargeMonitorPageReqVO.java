package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo;

import cn.idev.excel.annotation.ExcelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "巡查巡检 - 两轮充电监测分页 Request VO")
@Data
public class BikeChargeMonitorPageReqVO extends PageParam {

    @Schema(description = "设备ID")
    private Long deviceId;

    @Schema(description = "场站ID")
    private Long stationId;

    @Schema(description = "场站名称")
    private String stationName;

    @Schema(description = "监测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] monitorTime;

    @Schema(description = "监测状态")
    private String monitorStatus;

    @Schema(description = "告警状态")
    private String alarmStatus;

    @Schema(description = "告警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alarmTime;

    @Schema(description = "告警备注")
    private String alarmRemark;

    @Schema(description = "处理状态")
    private String processStatus;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    // 【新增字段】创建日期（年月日）
    @Schema(description = "创建日期（年月日）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY) // 使用年月日格式接收参数
    private LocalDate createDate;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}