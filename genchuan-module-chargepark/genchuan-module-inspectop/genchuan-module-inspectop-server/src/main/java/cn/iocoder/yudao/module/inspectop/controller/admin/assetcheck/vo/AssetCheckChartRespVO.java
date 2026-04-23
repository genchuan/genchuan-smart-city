package cn.iocoder.yudao.module.inspectop.controller.admin.assetcheck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Schema(description = "管理后台 - 资产盘点图表统计 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetCheckChartRespVO {

    @Schema(description = "盘点进度趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TrendData {
        @Schema(description = "时间点", example = "01")
        private String time;

        @Schema(description = "盘点进度", example = "30")
        private Integer progress;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "盘点总数量", example = "18")
        private Integer checkCount;

        @Schema(description = "盘点完成率", example = "0.83")
        private Double checkFinishRate;
    }
}