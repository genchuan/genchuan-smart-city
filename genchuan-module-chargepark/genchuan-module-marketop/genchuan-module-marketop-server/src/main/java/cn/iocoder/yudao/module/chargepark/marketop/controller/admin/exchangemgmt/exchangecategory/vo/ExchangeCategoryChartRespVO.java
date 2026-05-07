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

    @Schema(description = "类目状态分布数据")
    private List<StatusItem> statusCountList;

    @Schema(description = "类目范围分布数据")
    private List<ScopeItem> scopeCountList;

    @Data
    public static class StatusItem {
        @Schema(description = "状态")
        private String status;
        @Schema(description = "数量")
        private Integer count;
    }

    @Data
    public static class ScopeItem {
        @Schema(description = "范围")
        private String scope;
        @Schema(description = "数量")
        private Integer count;
    }

}
