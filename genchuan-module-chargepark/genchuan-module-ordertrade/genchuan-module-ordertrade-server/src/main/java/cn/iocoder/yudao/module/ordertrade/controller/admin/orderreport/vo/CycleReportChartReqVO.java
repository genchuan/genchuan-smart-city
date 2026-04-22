package cn.iocoder.yudao.module.ordertrade.controller.admin.orderreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 周期报表图表查询 Request VO")
@Data
public class CycleReportChartReqVO {

    @Schema(description = "报表周期：日报/周报/月报/季报/半年报/年报/自定义报表", required = true)
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计时段，如 2026-04-22 00:00:00-2026-04-22 23:59:59", required = true)
    @NotBlank(message = "统计时段不能为空")
    private String statTime;
}
