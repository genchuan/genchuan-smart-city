package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 周期报表图表响应 VO")
@Data
public class CycleReportChartRespVO {

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Schema(description = "折线图数据")
    private Map<String, List<Map<String, Object>>> lineData;

    @Schema(description = "柱状图数据")
    private Map<String, List<Map<String, Object>>> barData;

    @Schema(description = "饼图数据")
    private Map<String, List<Map<String, Object>>> pieData;

    @Data
    public static class CardData {
        private Integer newUserCount;
        private Integer bindCarCount;
        private Integer plateAuthCount;
        private Integer newMerchantCount;
        private Integer linkMerchantCount;
        private BigDecimal rechargeAmount;
        private Integer sendCouponCount;
        private Integer newGroupCount;
        private Integer newMemberCount;
        private Integer avgCreditScore;
    }
}