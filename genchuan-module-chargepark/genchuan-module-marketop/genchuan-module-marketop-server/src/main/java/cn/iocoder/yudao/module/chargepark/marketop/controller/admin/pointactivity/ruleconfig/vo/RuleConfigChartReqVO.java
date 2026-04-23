package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 规则配置图表统计 Request VO")
@Data
public class RuleConfigChartReqVO {

    @Schema(description = "统计开始时间", example = "1704067200000")
    private Long startTime;

    @Schema(description = "统计结束时间", example = "1706745600000")
    private Long endTime;

}