package cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.leaverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 离场记录统计 Response VO")
@Data
public class LeaveRecordChartRespVO {

    @Schema(description = "离场量趋势，折线图数据")
    private List<LeaveCountTrend> leaveCountTrend;

    @Schema(description = "各时段离场量，柱状图数据")
    private List<HourLeaveCount> hourLeaveCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "离场量趋势")
    public static class LeaveCountTrend {
        @Schema(description = "日期", example = "2025-04-07")
        private String date;
        @Schema(description = "数量", example = "115")
        private Long count;
    }

    @Data
    @Schema(description = "各时段离场量")
    public static class HourLeaveCount {
        @Schema(description = "时段", example = "17:00")
        private String hour;
        @Schema(description = "数量", example = "28")
        private Long count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "今日离场量", example = "140")
        private Long todayLeaveCount;
        @Schema(description = "离场峰值", example = "38")
        private Long leavePeak;
    }

}