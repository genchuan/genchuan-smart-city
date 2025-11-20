package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 应急全域数据概览 查询 Request VO")
@Data
public class EmergOverviewQueryReqVO {
    @Schema(description = "查询地区-省市县三级shortCode码，6位",example = "110000")
    private String regionShortCode;

    @Schema(description = "统计开始时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-01-01 09:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Schema(description = "统计结束时间（含）,yyyy-MM-dd HH:mm:ss", example = "2025-11-01 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
