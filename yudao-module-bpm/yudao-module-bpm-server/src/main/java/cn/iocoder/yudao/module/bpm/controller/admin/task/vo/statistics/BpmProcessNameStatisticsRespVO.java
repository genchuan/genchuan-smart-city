package cn.iocoder.yudao.module.bpm.controller.admin.task.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Schema(description = "流程名称统计 Response VO")
public class BpmProcessNameStatisticsRespVO {

    @Schema(description = "饼图数据列表")
    private List<PieData> pieData;

    @Schema(description = "任务总数")
    private Long total;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "饼图数据")
    public static class PieData {
        @Schema(description = "数值", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
        private Long value;

        @Schema(description = "流程名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "环卫事件流程")
        private String name;
    }
}