package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 兑换类目图表统计 Response VO")
@Data
public class ExchangeCategoryChartRespVO {

    @Schema(description = "类目数")
    private Integer categoryCount;

    @Schema(description = "商品数")
    private Integer productCount;

    @Schema(description = "类目商品分布数据")
    private List<TypeItem> typeList;

    @Data
    public static class TypeItem {
        @Schema(description = "类目名称")
        private String categoryName;
        @Schema(description = "数量")
        private Integer count;
    }

}
