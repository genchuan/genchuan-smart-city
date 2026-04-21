package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检轨迹图表统计 Response VO")
@Data
public class InspectTrackChartRespVO {

    @Schema(description = "巡检轨迹展示地图数据，包含 userId、userName、points")
    private List<MapDataVO> mapData;

    @Schema(description = "巡检里程趋势折线图数据，包含 time、totalMileage")
    private List<TrendDataVO> trendData;

    @Schema(description = "卡片统计数据，包含 totalMileage、totalDuration")
    private CardDataVO cardData;

    @Schema(description = "地图数据VO")
    @Data
    public static class MapDataVO {
        @Schema(description = "巡检人员ID", example = "1")
        private Long userId;

        @Schema(description = "巡检人员姓名", example = "张三")
        private String userName;

        @Schema(description = "轨迹点数组字符串，格式为[[lon,lat],[lon,lat],...]",
                example = "[[118.675324,24.896541],[118.676324,24.897541]]")
        private String points;
    }

    @Schema(description = "趋势数据VO")
    @Data
    public static class TrendDataVO {
        @Schema(description = "时间（格式为月-日或小时，如'01-15'或'14'）", example = "01")
        private String time;

        @Schema(description = "总里程（公里）", example = "20.5")
        private BigDecimal totalMileage;
    }

    @Schema(description = "卡片数据VO")
    @Data
    public static class CardDataVO {
        @Schema(description = "总里程（公里）", example = "126.8")
        private BigDecimal totalMileage;

        @Schema(description = "总时长（分钟）", example = "1440")
        private Integer totalDuration;
    }
}