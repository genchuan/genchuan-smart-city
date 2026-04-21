package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检任务图表数据 Response VO")
@Data
public class InspectTaskChartRespVO {

    @Schema(description = "任务类型分布柱状图数据")
    private List<TypeData> typeData;

    @Schema(description = "任务处理时效折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "任务类型分布数据")
    public static class TypeData {

        @Schema(description = "任务类型名称", example = "设备巡检")
        private String typeName;

        @Schema(description = "任务数量", example = "30")
        private Integer count;
    }

    @Data
    @Schema(description = "任务处理时效趋势数据")
    public static class TrendData {

        @Schema(description = "时间点", example = "01")
        private String time;

        @Schema(description = "平均处理时间(分钟)", example = "120")
        private Integer avgHandleTime;
    }

    @Data
    @Schema(description = "卡片统计数据")
    public static class CardData {

        @Schema(description = "待处理任务数", example = "12")
        private Integer waitTaskCount;

        @Schema(description = "已完成任务数", example = "56")
        private Integer finishTaskCount;
    }
}