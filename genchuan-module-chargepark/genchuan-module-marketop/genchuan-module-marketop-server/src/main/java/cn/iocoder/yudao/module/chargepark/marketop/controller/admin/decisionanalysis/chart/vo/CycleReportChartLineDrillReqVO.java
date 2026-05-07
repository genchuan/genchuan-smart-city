package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表图表-折线图钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportChartLineDrillReqVO extends PageParam {

    @Schema(description = "折线图趋势类型", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "activityTrend",
            allowableValues = {"activityTrend", "lotteryTrend", "couponTrend", "orderTrend", "stockTrend"})
    @NotEmpty(message = "趋势类型不能为空")
    private String lineType;

    @Schema(description = "趋势名称。" +
            "activityTrend → 活动参与趋势；" +
            "lotteryTrend → 抽奖趋势；" +
            "couponTrend → 优惠券发放趋势；" +
            "orderTrend → 订单量趋势；" +
            "stockTrend → 库存趋势",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "活动参与趋势")
    @NotEmpty(message = "趋势名称不能为空")
    private String lineName;

    @Schema(description = "对应时段(支持yyyy-MM或yyyy-MM-dd格式)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04")
    @NotEmpty(message = "时段不能为空")
    private String date;

}
