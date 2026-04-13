package cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "汽车充电 - 充电车位图表统计 Response VO")
@Data
public class ChargingLotChartRespVO {

    @Schema(description = "车位总数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "580")
    private Integer totalCount;

    @Schema(description = "空闲车位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "320")
    private Integer idleCount;

    @Schema(description = "占用车位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "220")
    private Integer occupiedCount;

    @Schema(description = "维护中车位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "40")
    private Integer maintainCount;

    @Schema(description = "车位状态占比数据，用于饼图展示")
    private List<StatusRatio> statusRatio;

    @Schema(description = "各场站车位数量及占用情况，用于柱状图展示")
    private List<StationLot> stationLotList;

    @Data
    public static class StatusRatio {
        @Schema(description = "状态名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "空闲")
        private String status;
        @Schema(description = "该状态车位数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "320")
        private Integer count;
        @Schema(description = "该状态占比", requiredMode = Schema.RequiredMode.REQUIRED, example = "0.5517")
        private BigDecimal ratio;
    }

    @Data
    public static class StationLot {
        @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        private Long stationId;
        @Schema(description = "场站名称（这里用场站编码stationCode代替，若需名称则需关联场站表获取）", requiredMode = Schema.RequiredMode.REQUIRED, example = "泉州丰泽万达广场充电站")
        private String stationName;
        @Schema(description = "该场站总车位数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
        private Integer totalCount;
        @Schema(description = "该场站空闲车位数", requiredMode = Schema.RequiredMode.REQUIRED, example = "28")
        private Integer idleCount;
        @Schema(description = "该场站占用车位数", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
        private Integer occupiedCount;
        @Schema(description = "该场站维护中车位数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
        private Integer maintainCount;
    }
}