package cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description = "管理后台 - 结果处置统计 Response VO")
@Data
public class ResultHandleChartRespVO {

    @Schema(description = "处置结果占比，饼图数据")
    private List<HandleResultRate> handleResultRate;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HandleResultRate {
        @Schema(description = "名称")
        private String name;
        @Schema(description = "值")
        private Long value;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "处置完成率")
        private Double handleCompleteRate;
        @Schema(description = "违规整改率")
        private Double violationRectifyRate;
    }

}