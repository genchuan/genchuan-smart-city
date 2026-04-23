package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 异常离场统计 Response VO")
@Data
public class AbnormalLeaveChartRespVO {

    @Schema(description = "异常离场趋势，折线图数据")
    private List<AbnormalLeaveTrend> abnormalLeaveTrend;

    @Schema(description = "各场站异常数，柱状图数据")
    private List<StationAbnormalCount> stationAbnormalCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "异常离场趋势")
    public static class AbnormalLeaveTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "1")
        private Long count;
    }

    @Data
    @Schema(description = "各场站异常数")
    public static class StationAbnormalCount {
        @Schema(description = "场站名称", example = "泉州丰泽充电站")
        private String stationName;
        @Schema(description = "数量", example = "6")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "待处置异常数", example = "1")
        private Long waitHandleCount;
        @Schema(description = "处置完成率", example = "90.0")
        private Double handleCompleteRate;
    }

}