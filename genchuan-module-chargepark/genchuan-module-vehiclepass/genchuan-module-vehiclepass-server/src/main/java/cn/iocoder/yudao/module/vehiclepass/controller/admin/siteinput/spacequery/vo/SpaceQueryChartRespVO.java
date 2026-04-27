package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.*;

@Schema(description = "管理后台 - 泊位查询统计 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceQueryChartRespVO {

    @Schema(description = "泊位位置分布，地图数据")
    private List<SpaceLocation> spaceLocationList;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpaceLocation {
        @Schema(description = "泊位编号")
        private String spaceNo;
        @Schema(description = "经度")
        private java.math.BigDecimal lon;
        @Schema(description = "纬度")
        private java.math.BigDecimal lat;
        @Schema(description = "泊位状态")
        private String spaceStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        @Schema(description = "查询量")
        private Long queryCount;
        @Schema(description = "查询成功率")
        private Double querySuccessRate;
    }

}