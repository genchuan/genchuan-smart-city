package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 营销运营报表图表 Response VO")
@Data
public class MarketOpReportChartRespVO {

    @Schema(description = "营销核心指标")
    private CoreIndex coreIndex;

    @Schema(description = "营销运营趋势数据")
    private List<TrendItem> trendList;

    @Schema(description = "活动效果分布数据")
    private List<EffectItem> effectList;

    @Data
    public static class CoreIndex {
        @Schema(description = "活动参与率")
        private Double activityJoinRate;
        @Schema(description = "优惠券核销率")
        private Double couponVerifyRate;
        @Schema(description = "卡种销量")
        private Integer cardSaleCount;
    }

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "核心指标数值")
        private Integer indexValue;
    }

    @Data
    public static class EffectItem {
        @Schema(description = "活动名称")
        private String activityName;
        @Schema(description = "效果数值")
        private Double effectValue;
    }

}
