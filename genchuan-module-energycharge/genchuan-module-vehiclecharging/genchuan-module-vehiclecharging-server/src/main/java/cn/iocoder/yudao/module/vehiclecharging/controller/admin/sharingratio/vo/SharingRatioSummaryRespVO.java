package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 分账比例统计图表 Response VO")
@Data
public class SharingRatioSummaryRespVO {

    @Schema(description = "分账方案总数", example = "25")
    private Integer totalCount;

    @Schema(description = "已生效方案数", example = "18")
    private Integer validCount;

    @Schema(description = "已失效方案数", example = "5")
    private Integer invalidCount;

    @Schema(description = "未生效方案数", example = "2")
    private Integer pendingCount;

    @Schema(description = "分账类型占比饼图数据")
    private List<PieData> pieData;

    @Schema(description = "各合作方分账方案数量柱状图数据")
    private List<BarData> barData;

    @Data
    public static class PieData {
        @Schema(description = "分账类型名称", example = "电费分账")
        private String name;
        @Schema(description = "方案数量", example = "12")
        private Integer value;
    }

    @Data
    public static class BarData {
        @Schema(description = "合作方名称", example = "XX 能源公司")
        private String name;
        @Schema(description = "方案数量", example = "8")
        private Integer value;
    }
}