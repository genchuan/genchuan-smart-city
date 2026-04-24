package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description = "管理后台 - 结束停车统计 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndParkChartRespVO {

    @Schema(description = "结束量趋势，折线图数据")
    private List<EndCountTrend> endCountTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EndCountTrend {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "数量")
        private Long count;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "结束量")
        private Long endCount;
        @Schema(description = "支付成功率")
        private Double paySuccessRate;
    }

}