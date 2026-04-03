package cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "管理后台 - 充电桩图表统计 Response VO")
public class PileChartRespVO {

    @Schema(description = "运行时长趋势折线图数据")
    private List<RunTimeTrend> runTimeTrendList;

    @Schema(description = "各类型充电桩数量柱状图数据")
    private List<TypeBar> typeBarList;

    @Schema(description = "卡片统计数据")
    private CardInfo cardInfo;

    @Data
    @Schema(description = "运行时长趋势")
    public static class RunTimeTrend {

        @Schema(description = "时间（yyyy-MM-dd）", example = "2025-03-01")
        private String time;

        @Schema(description = "运行时长（单位：小时）", example = "12560")
        private Integer runTime;
    }

    @Data
    @Schema(description = "类型柱状图")
    public static class TypeBar {

        @Schema(description = "类型ID（充电模式ID）", example = "1")
        private String typeName;

        @Schema(description = "类型名称", example = "直流")
        private String typeNameName;

        @Schema(description = "桩数量", example = "280")
        private Integer count;
    }

    @Data
    @Schema(description = "卡片统计")
    public static class CardInfo {

        @Schema(description = "总桩数", example = "420")
        private Integer totalCount;

        @Schema(description = "已启用数", example = "380")
        private Integer enableCount;

        @Schema(description = "故障数", example = "15")
        private Integer faultCount;

        @Schema(description = "调试中数", example = "25")
        private Integer debugCount;
    }
}
