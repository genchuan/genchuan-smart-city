package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.servicereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 图表 Request VO")
@Data
public class CycleReportChartReqVO {

    @Schema(description = "报表周期", requiredMode = Schema.RequiredMode.REQUIRED, example = "月报",
            allowableValues = {"日报", "周报", "月报", "季报", "半年报", "年报", "自定义报表"})
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2026-03-01 00:00:00")
    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "2026-03-31 23:59:59")
    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

}
