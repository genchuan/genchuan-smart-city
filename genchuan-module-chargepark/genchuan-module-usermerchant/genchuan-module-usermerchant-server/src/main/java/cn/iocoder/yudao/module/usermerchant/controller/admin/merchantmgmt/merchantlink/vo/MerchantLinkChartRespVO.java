package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 商户对接表图表数据 Response VO")
@Data
public class MerchantLinkChartRespVO {

    @Schema(description = "对接类型分布数据")
    private List<MerchantLinkChartRespVO.LinkTypeDistributionVO> linkTypeDistribution;

    @Schema(description = "对接商户数")
    private Long linkMerchantCount;

    @Schema(description = "对接成功率")
    private BigDecimal linkSuccessRate;

    @Schema(description = "对接类型分布数据")
    @Data
    public static class LinkTypeDistributionVO {
        private String type;
        private Long count;
    }

}
