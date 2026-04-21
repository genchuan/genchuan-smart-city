package cn.iocoder.yudao.module.inspectop.controller.admin.inspectplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检计划图表统计 Response VO")
@Data
public class InspectPlanChartRespVO {

    @Schema(description = "计划执行趋势数据")
    private List<TrendData> trendData;

    @Schema(description = "计划类型分布数据")
    private List<TypeData> typeData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "计划执行趋势数据项")
    @Data
    public static class TrendData {

        @Schema(description = "时间（如：月-日，或月份）", example = "01")
        private String time;

        @Schema(description = "创建数量", example = "2")
        private Integer createCount;

        @Schema(description = "完成数量", example = "1")
        private Integer finishCount;
    }

    @Schema(description = "计划类型分布数据项")
    @Data
    public static class TypeData {

        @Schema(description = "类型名称", example = "日常")
        private String typeName;

        @Schema(description = "数量", example = "15")
        private Integer count;
    }

    @Schema(description = "卡片统计数据项")
    @Data
    public static class CardData {

        @Schema(description = "总计划数量", example = "24")
        private Integer planCount;

        @Schema(description = "完成率（0-1之间的小数）", example = "0.75")
        private Double finishRate;
    }
}