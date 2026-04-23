package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 套牌管控统计 Response VO")
@Data
public class FakePlateControlChartRespVO {

    @Schema(description = "套牌识别趋势，折线图数据")
    private List<FakeIdentifyTrend> fakeIdentifyTrend;

    @Schema(description = "各场站套牌数，柱状图数据")
    private List<StationFakeCount> stationFakeCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "套牌识别趋势")
    public static class FakeIdentifyTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "2")
        private Long count;
    }

    @Data
    @Schema(description = "各场站套牌数")
    public static class StationFakeCount {
        @Schema(description = "场站名称", example = "泉州丰泽充电站")
        private String stationName;
        @Schema(description = "数量", example = "8")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "待处置套牌数", example = "2")
        private Long waitHandleCount;
        @Schema(description = "处置完成率", example = "86.7")
        private Double handleCompleteRate;
    }

}