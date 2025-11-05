package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应急全域数据概览 查询 Request VO")
@Data
public class EmergOverviewQueryReqVO {
//    @Schema(description = "查询开始时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime startTime;
//
//
//    @Schema(description = "查询结束时间")
//    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
//    private LocalDateTime endTime;


    @Schema(description = "查询地区-省市县的short_code")
    private String shortCode;

    @Schema(description = "查询周期-YYYYMM或者YYYY")
    private String statCycle;
}
