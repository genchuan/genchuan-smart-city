package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 核心指标统计 Request VO")
@Data
public class WorkHomeCoreIndexReqVO {
//    cycle (string, optional): 统计周期（周 / 月），关联芋道字典表：assess_mgmt_cycle。
//    startTime (string, optional): 统计开始时间，格式 yyyy-MM-dd。
//    endTime (string, optional): 统计结束时间，格式 yyyy-MM-dd。
    @Schema(description = "统计周期")
    private String cycle;
    @Schema(description = "统计开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime startTime;
    @Schema(description = "统计结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime endTime;

}