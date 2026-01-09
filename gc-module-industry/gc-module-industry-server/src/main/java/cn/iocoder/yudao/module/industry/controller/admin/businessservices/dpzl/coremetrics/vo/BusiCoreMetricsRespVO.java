package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class BusiCoreMetricsRespVO {

    @Schema(description = "审批办结率")
    private BigDecimal approvalCompleteRate;

    @Schema(description = "政策兑现率")
    private BigDecimal polFulfillRate;

    @Schema(description = "企业满意度")
    private BigDecimal entSatisfy;

    @Schema(description = "诉求超期率")
    private BigDecimal appealOverdueRate;

    @Schema(description = "各区域审批办结率对比")
    private List<RegionApprovalRateVO> regionApprovalRates;

    @Schema(description = "近30天政策兑现趋势")
    private List<PolFulfillTrendVO> polFulfillTrends;

    @Data
    @Schema(description = "区域审批办结率VO")
    public static class RegionApprovalRateVO {

        @Schema(description = "区域名称")
        private String regionName;

        @Schema(description = "办结率")
        private BigDecimal completeRate;
    }

    @Data
    @Schema(description = "政策兑现趋势VO")
    public static class PolFulfillTrendVO {

        @Schema(description = "日期")
        private Date date;

        @Schema(description = "兑现数量")
        private Integer fulfillCount;
    }
}