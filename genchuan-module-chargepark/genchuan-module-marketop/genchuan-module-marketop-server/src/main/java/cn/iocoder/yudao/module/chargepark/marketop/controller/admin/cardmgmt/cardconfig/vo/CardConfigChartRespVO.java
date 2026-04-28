package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 卡种配置图表统计 Response VO")
@Data
public class CardConfigChartRespVO {

    @Schema(description = "生效卡种数")
    private Integer enableCount;

    @Schema(description = "总销量")
    private Integer salesCount;

    @Schema(description = "卡种类型分布")
    private List<TypeRateItem> typeRatio;

    @Schema(description = "适用范围分布")
    private List<ScopeCountItem> ScopeCountList;


    @Data
    public static class TypeRateItem {
        @Schema(description = "卡种类型")
        private String type;
        @Schema(description = "占比")
        private BigDecimal rate;
    }

    @Data
    public static class ScopeCountItem {
        @Schema(description = "适用范围")
        private String scope;
        @Schema(description = "数量")
        private Integer count;
    }

}
