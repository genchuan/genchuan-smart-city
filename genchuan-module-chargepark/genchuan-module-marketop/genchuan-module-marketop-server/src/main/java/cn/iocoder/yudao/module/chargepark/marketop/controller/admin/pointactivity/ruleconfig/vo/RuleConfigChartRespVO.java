package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 规则配置图表统计 Response VO")
@Data
public class RuleConfigChartRespVO {

    @Schema(description = "生效配置数")
    private Integer enableCount;

    @Schema(description = "规则匹配率")
    private BigDecimal matchRate;

    @Schema(description = "规则类型占比")
    private List<TypeRateItem> typeList;

    @Data
    public static class TypeRateItem {
        @Schema(description = "规则类型")
        private String type;
        @Schema(description = "占比")
        private BigDecimal rate;
    }

}
