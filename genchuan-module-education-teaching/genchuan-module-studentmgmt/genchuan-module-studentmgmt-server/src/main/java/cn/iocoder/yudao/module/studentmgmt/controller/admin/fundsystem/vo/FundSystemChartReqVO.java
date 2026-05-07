package cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 资助系统审核 Request VO")
@Data
public class FundSystemChartReqVO {

    @Schema(description = "统计时间范围，时间范围参数需要符合yyyy-MM-dd HH:mm:ss格式", example = "2023-01-01 00:00:00,2027-01-31 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] timeRange;

    @Schema(description = "年级，支持年级维度筛选。")
    private String grade;

}