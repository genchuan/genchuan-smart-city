package cn.iocoder.yudao.module.vehiclepass.controller.admin.passreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(description = "管理后台 - 周期报表图表 Request VO")
@Data
public class CycleReportChartReqVO {

    @NotBlank(message = "报表周期不能为空")
    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报）", requiredMode = Schema.RequiredMode.REQUIRED, example = "日报")
    private String reportCycle;

    @Schema(description = "场站ID", example = "1")
    private Long stationId;

    @NotNull(message = "统计时间不能为空")
    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04-22")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate statTime;

}