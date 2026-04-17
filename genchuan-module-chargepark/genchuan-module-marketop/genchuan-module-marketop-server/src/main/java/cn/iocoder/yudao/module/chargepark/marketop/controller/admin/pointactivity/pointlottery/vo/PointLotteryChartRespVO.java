package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 积分抽奖图表统计 Response VO")
@Data
public class PointLotteryChartRespVO {

    @Schema(description = "总抽奖量")
    private Integer lotteryCount;

    @Schema(description = "中奖率")
    private BigDecimal winRate;

    @Schema(description = "抽奖量趋势(近30天)")
    private List<TrendItem> trendList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "抽奖量")
        private Integer count;
    }

}
