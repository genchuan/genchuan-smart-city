package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description = "管理后台 - 车辆录入统计 Response VO")
@Data
public class CarInputChartRespVO {

    @Schema(description = "录入量趋势，折线图数据")
    private List<InputCountTrend> inputCountTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InputCountTrend {
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
        @Schema(description = "录入量")
        private Long inputCount;
        @Schema(description = "审核通过率")
        private Double auditPassRate;
    }

}