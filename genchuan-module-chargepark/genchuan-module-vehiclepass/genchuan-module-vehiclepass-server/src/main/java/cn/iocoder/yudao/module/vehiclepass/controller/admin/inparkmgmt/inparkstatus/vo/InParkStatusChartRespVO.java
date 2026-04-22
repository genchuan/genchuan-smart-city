package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 在停状态统计 Response VO")
@Data
public class InParkStatusChartRespVO {

    @Schema(description = "在停车辆分布")
    private List<CarLocation> carLocationList;

    @Schema(description = "在停量趋势")
    private List<InParkCountTrend> inParkCountTrend;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "在停车辆位置")
    @Data
    public static class CarLocation {
        @Schema(description = "车牌号")
        private String plateNo;
        @Schema(description = "经度")
        private BigDecimal lon;
        @Schema(description = "纬度")
        private BigDecimal lat;
        @Schema(description = "车位名称")
        private String spaceName;
    }

    @Schema(description = "在停量趋势")
    @Data
    public static class InParkCountTrend {
        @Schema(description = "时间")
        private String time;
        @Schema(description = "数量")
        private Long count;
    }

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "在停车辆数")
        private Long inParkCarCount;
        @Schema(description = "超时长车辆数")
        private Long overTimeCarCount;
    }

}