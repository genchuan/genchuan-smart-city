package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationconfig.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 场站配置图表统计 Response VO")
@Data
public class StationConfigChartRespVO {

    @Schema(description = "配置类型饼图数据")
    private List<TypePieDTO> typePieList;

    @Schema(description = "卡片统计数据")
    private CardDataDTO cardData;

    // 配置类型饼图
    @Data
    public static class TypePieDTO {
        @Schema(description = "类型名称", example = "收费规则")
        private String name;

        @Schema(description = "数量", example = "15")
        private Integer value;
    }

    // 卡片数据
    @Data
    public static class CardDataDTO {
        @Schema(description = "已配置场站数量", example = "98")
        private Integer configedStationCount;

        @Schema(description = "已生效配置数量", example = "25")
        private Integer enableConfigCount;
    }
}
