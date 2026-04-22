package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 油车占位处置统计 Response VO")
@Data
public class OilCarHandleChartRespVO {

    @Schema(description = "处置进度趋势，折线图数据")
    private List<HandleProgressTrend> handleProgressTrend;

    @Schema(description = "各场站处置量，柱状图数据")
    private List<StationHandleCount> stationHandleCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "处置进度趋势")
    public static class HandleProgressTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "3")
        private Long count;
    }

    @Data
    @Schema(description = "各场站处置量")
    public static class StationHandleCount {
        @Schema(description = "场站名称", example = "泉州丰泽充电站")
        private String stationName;
        @Schema(description = "数量", example = "12")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "待处置数", example = "3")
        private Long waitHandleCount;
        @Schema(description = "处置完成率", example = "85.0")
        private Double handleCompleteRate;
    }

}