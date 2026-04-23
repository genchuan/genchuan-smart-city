package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 放行记录统计 Response VO")
@Data
public class PassRecordChartRespVO {

    @Schema(description = "放行量趋势，折线图数据")
    private List<PassCountTrend> passCountTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "放行量趋势")
    public static class PassCountTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "5")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "今日放行量", example = "7")
        private Long todayPassCount;
        @Schema(description = "异常放行占比", example = "5.2")
        private Double abnormalPassRate;
    }

}