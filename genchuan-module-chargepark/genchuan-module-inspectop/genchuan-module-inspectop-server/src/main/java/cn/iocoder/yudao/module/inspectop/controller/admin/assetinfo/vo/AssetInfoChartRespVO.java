package cn.iocoder.yudao.module.inspectop.controller.admin.assetinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "管理后台 - 资产信息图表统计 Response VO")
@Data
public class AssetInfoChartRespVO {

    @Schema(description = "资产类型分布柱状图数据")
    private List<TypeData> typeData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "资产类型分布数据")
    public static class TypeData {

        @Schema(description = "资产类型名称")
        private String typeName;

        @Schema(description = "数量")
        private Integer count;
    }

    @Data
    @Schema(description = "卡片统计数据")
    public static class CardData {

        @Schema(description = "资产总数")
        private Integer totalAsset;

        @Schema(description = "正常资产数")
        private Integer normalAsset;
    }
}