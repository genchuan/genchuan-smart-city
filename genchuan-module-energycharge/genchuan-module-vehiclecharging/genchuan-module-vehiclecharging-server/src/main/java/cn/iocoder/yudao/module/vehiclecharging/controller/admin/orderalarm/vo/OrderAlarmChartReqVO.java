package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 订单告警图表统计 Request VO")
@Data
public class OrderAlarmChartReqVO {

    @Schema(description = "统计开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-03-01 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-03-31 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

}