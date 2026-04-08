package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 模块告警统计图表 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModuleAlarmChartRespVO {

    @Schema(description = "总告警数量", example = "42")
    private Integer totalCount;

    @Schema(description = "已修复告警数量", example = "39")
    private Integer repairedCount;

    @Schema(description = "修复率，保留2位小数", example = "0.93")
    private BigDecimal repairRate;

    @Schema(description = "柱状图数据：各模块告警数量")
    private List<BarData> barData;

    @Schema(description = "折线图数据：每日平均修复时长（小时）")
    private List<LineData> lineData;

    @Schema(description = "卡片数据：各状态告警数量")
    private CardData cardData;

    @Schema(description = "柱状图-模块告警数量项")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BarData {
        @Schema(description = "模块名称", example = "订单服务")
        private String name;
        @Schema(description = "告警数量", example = "15")
        private Long value;
    }

    @Schema(description = "折线图-每日修复时长项")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LineData {
        @Schema(description = "日期", example = "2025-03-01")
        private String date;
        @Schema(description = "平均修复时长（小时）", example = "15")
        private BigDecimal repairTime;
    }

    @Schema(description = "卡片数据-各状态告警数量")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "未排查数量", example = "2")
        private Long unCheckCount;
        @Schema(description = "已排查数量", example = "1")
        private Long checkedCount;
        @Schema(description = "修复中数量", example = "0")
        private Long repairingCount;
        @Schema(description = "已销账数量", example = "39")
        private Long closedCount;
    }

}
