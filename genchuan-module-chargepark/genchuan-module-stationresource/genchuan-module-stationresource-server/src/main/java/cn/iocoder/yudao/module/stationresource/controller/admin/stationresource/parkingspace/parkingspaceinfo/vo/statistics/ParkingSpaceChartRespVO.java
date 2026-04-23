package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 车位数据可视化图表 Response VO")
@Data
public class ParkingSpaceChartRespVO {

    @Schema(description = "车位位置分布数据（地图使用）")
    private List<SpaceMapDTO> spaceMapList;

    @Schema(description = "卡片统计数据")
    private CardDataDTO cardData;

    // ==================== 车位地图子结构 ====================
    @Data
    public static class SpaceMapDTO {
        @Schema(description = "车位ID", example = "1")
        private Long id;

        @Schema(description = "车位名称", example = "B1-001")
        private String name;

        @Schema(description = "经度", example = "118.675324")
        private Double lon;

        @Schema(description = "纬度", example = "24.896541")
        private Double lat;

        @Schema(description = "实时状态", example = "空闲")
        private String realStatus;
    }

    // ==================== 统计卡片子结构 ====================
    @Data
    public static class CardDataDTO {
        @Schema(description = "总车位数", example = "2450")
        private Long totalSpaceCount;
//
        @Schema(description = "可用车位数（空闲）", example = "1280")
        private Long availableSpaceCount;
    }
}