package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表请求 VO")
@Data
public class CycleReportChartReqVO {

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", required = true, example = "月报")
    private String reportCycle;

    @Schema(description = "统计开始时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "租户 ID", required = true, example = "1")
    @NotNull(message = "租户 ID 不能为空")
    private Long tenantId;
}