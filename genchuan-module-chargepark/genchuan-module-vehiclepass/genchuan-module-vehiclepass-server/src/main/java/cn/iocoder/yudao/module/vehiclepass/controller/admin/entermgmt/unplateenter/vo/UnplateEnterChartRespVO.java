package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;

@Schema(description = "管理后台 - 无牌入场统计 Response VO")
@Data
public class UnplateEnterChartRespVO {

    @Schema(description = "各场站无牌入场量")
    private List<StationUnplateCount> stationUnplateCount;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "场站无牌入场量")
    @Data
    public static class StationUnplateCount {
        @Schema(description = "场站名称")
        private String stationName;

        @Schema(description = "数量")
        private Long count;
    }

    @Schema(description = "卡片数据")
    @Data
    public static class CardData {
        @Schema(description = "无牌入场量")
        private Long unplateEnterCount;

        @Schema(description = "审核通过率")
        private Double auditPassRate;
    }

}