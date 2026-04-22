package cn.iocoder.yudao.module.inspectop.controller.admin.inspectuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 巡检人员图表统计 Response VO")
@Data
public class InspectUserChartRespVO {

    @Schema(description = "人员区域分布柱状图数据")
    private List<AreaData> areaData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Schema(description = "区域分布数据")
    @Data
    public static class AreaData {

        @Schema(description = "区域名称", example = "丰泽区")
        private String areaName;

        @Schema(description = "人员数量", example = "8")
        private Integer count;
    }

    @Schema(description = "卡片统计数据")
    @Data
    public static class CardData {

        @Schema(description = "总人数", example = "16")
        private Integer userCount;

        @Schema(description = "在线人数", example = "10")
        private Integer onlineUserCount;
    }
}