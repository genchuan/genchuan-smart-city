package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 通用大屏核心指标看板 Response VO")
@Data
public class UniversalCoreMetricsRespVO {

    @Schema(description = "设施总量", example = "1200")
    private Integer totalCount;

    @Schema(description = "异常设施数", example = "86")
    private Integer abnormalCount;

    @Schema(description = "达标率（%）", example = "92.83")
    private BigDecimal qualifyRate;

    @Schema(description = "处置闭环率（%）", example = "88.45")
    private BigDecimal closeRate;

    @Schema(description = "同比增长率（%）", example = "5.32")
    private BigDecimal yoyGrowth;

    @Schema(description = "环比增长率（%）", example = "1.76")
    private BigDecimal momGrowth;


    @Schema(description = "统计周期", example = "202509")
    private String statCycle;

    @Schema(description = "行政区域编码", example = "110101")
    private String regionCode;

    @Schema(description = "达标率预警阈值", example = "0.9")
    private BigDecimal qualifyWarnThreshold = BigDecimal.valueOf(0.9);
}
