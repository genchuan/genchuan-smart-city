package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 开闸管理统计 Response VO")
@Data
public class GateOpenChartRespVO {

    @Schema(description = "开闸申请趋势，折线图数据")
    private List<OpenApplyTrend> openApplyTrend;

    @Schema(description = "各场站开闸量，柱状图数据")
    private List<StationOpenCount> stationOpenCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "开闸申请趋势")
    public static class OpenApplyTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "3")
        private Long count;
    }

    @Data
    @Schema(description = "各场站开闸量")
    public static class StationOpenCount {
        @Schema(description = "场站名称", example = "泉州丰泽充电站")
        private String stationName;
        @Schema(description = "数量", example = "15")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "申请量", example = "23")
        private Long applyCount;
        @Schema(description = "审批通过率", example = "91.3")
        private Double auditPassRate;
    }

}