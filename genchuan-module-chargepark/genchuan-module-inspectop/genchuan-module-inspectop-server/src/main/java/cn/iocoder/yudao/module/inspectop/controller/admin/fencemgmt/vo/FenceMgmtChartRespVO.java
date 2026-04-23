package cn.iocoder.yudao.module.inspectop.controller.admin.fencemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "巡查巡检 - 电子围栏图表统计 Response VO")
@Data
public class FenceMgmtChartRespVO {

    @Schema(description = "围栏区域分布地图数据，包含 id、name、area、status")
    private List<MapDataVO> mapData;

    @Schema(description = "卡片统计数据，包含 fenceCount、alarmCount")
    private CardDataVO cardData;

    @Schema(description = "地图数据VO")
    @Data
    public static class MapDataVO {
        @Schema(description = "围栏ID", example = "1")
        private Long id;

        @Schema(description = "围栏名称", example = "丰泽站围栏")
        private String name;

        @Schema(description = "围栏区域坐标数组字符串，格式为[[lon,lat],[lon,lat],...]",
                example = "[[118.67,24.89],[118.68,24.89],[118.68,24.90],[118.67,24.90]]")
        private String area;

        @Schema(description = "围栏状态", example = "已生效")
        private String status;
    }

    @Schema(description = "卡片数据VO")
    @Data
    public static class CardDataVO {
        @Schema(description = "围栏总数", example = "12")
        private Integer fenceCount;

        @Schema(description = "告警触发总数", example = "8")
        private Integer alarmCount;
    }
}