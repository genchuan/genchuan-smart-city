package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.inspecttask.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description = "管理后台 - 稽查任务统计 Response VO")
@Data
public class InspectTaskChartRespVO {

    @Schema(description = "任务类型分布，柱状图数据")
    private List<TaskTypeCount> taskTypeCount;

    @Schema(description = "任务处理时效，折线图数据")
    private List<TaskHandleTrend> taskHandleTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskTypeCount {
        @Schema(description = "类型名称")
        private String typeName;
        @Schema(description = "数量")
        private Long count;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaskHandleTrend {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "处理时长（天）")
        private Double duration;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "待处理任务数")
        private Long waitHandleTaskCount;
        @Schema(description = "已完成任务数")
        private Long finishedTaskCount;
    }

}