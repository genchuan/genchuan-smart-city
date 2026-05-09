package cn.iocoder.yudao.module.kitchen.controller.admin.riskreport.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "企业风险概览（卡片 + 饼图1） Response VO")
public class RiskOverviewRespVO {

    // ==================== 卡片 ====================

    @Schema(description = "企业总数")
    private Integer totalEnterprises;

    @Schema(description = "高风险企业数")
    private Integer highRiskCount;

    @Schema(description = "中风险企业数")
    private Integer mediumRiskCount;

    @Schema(description = "低风险企业数")
    private Integer lowRiskCount;

    // ==================== 饼图1：风险等级分布 ====================

    @Schema(description = "风险等级分布")
    private List<RiskLevelDist> riskLevelDistribution;

    @Data
    @Schema(description = "风险等级分布项")
    public static class RiskLevelDist {
        @Schema(description = "风险等级（高风险/中风险/低风险）")
        private String riskLevel;

        @Schema(description = "数量")
        private Integer count;

        @Schema(description = "占比（百分比）")
        private Double percentage;
    }
}
