package cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 生成周期报表 Request VO")
@Data
public class CycleReportCreateReqVO {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表", required = true)
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间", required = true)
    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", required = true)
    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime statEndTime;

    @Schema(description = "备注")
    private String remark;
}
