package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY;

@Schema(description = "管理后台 - 宿舍考勤预警看板 Request VO")
@Data
public class DormCheckChartReqVO {

    @Schema(description = "考勤时间", example = "2025-06-01")
//    @DateTimeFormat(FORMAT_YEAR_MONTH_DAY)
    @JsonFormat(pattern=FORMAT_YEAR_MONTH_DAY)
    private LocalDate  checkTime;
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY)
//    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY)
//    private LocalDateTime checkTime;


}