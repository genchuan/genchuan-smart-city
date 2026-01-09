package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "管理后台 - 文旅客流总览视图 Response VO")
public class CulturePasFlowRespVO {

    @Schema(description = "实时客流总量", example = "12580")
    private Long totalRptCount;

    @Schema(description = "客流峰值时段（小时）", example = "16")
    private Integer maxHour;

    @Schema(description = "分时客流趋势数据")
    private List<HourlyTrend> hourlyTrends;

    @Schema(description = "区域客流分布数据")
    private List<RegionDistribution> regionDistributions;

    @Data
    public static class HourlyTrend {
        @Schema(description = "时间（格式：HH:mm）", example = "08:00")
        private String time;

        @Schema(description = "客流数量", example = "890")
        private Long count;

        @Schema(description = "是否为峰值点", example = "false")
        private Boolean isPeak;
    }

    @Data
    public static class RegionDistribution {
        @Schema(description = "区域名称", example = "东城区")
        private String regionName;

        @Schema(description = "客流数量", example = "3500")
        private Long count;

        @Schema(description = "占比（百分比）", example = "28.5")
        private Double proportion;

        @Schema(description = "是否超过阈值（30%）", example = "false")
        private Boolean isOverThreshold;
    }
}