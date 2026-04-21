package cn.iocoder.yudao.module.inspectop.controller.admin.inspectreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;

@Schema(description = "巡查巡检 - 巡检上报图表 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectReportChartRespVO {

    @Schema(description = "上报量趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "上报类型分布柱状图数据")
    private List<TypeData> typeData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "上报量趋势数据")
    public static class TrendData {
        @Schema(description = "时间（格式：MM-dd 或 HH:mm）")
        private String time;

        @Schema(description = "上报数量")
        private Integer reportCount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "上报类型分布数据")
    public static class TypeData {
        @Schema(description = "上报类型名称")
        private String typeName;

        @Schema(description = "数量")
        private Integer count;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "卡片统计数据")
    public static class CardData {
        @Schema(description = "待审核数量")
        private Integer waitAuditCount;

        @Schema(description = "处置完成率")
        private BigDecimal processFinishRate;
    }
}