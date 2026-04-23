package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Schema(description = "巡查巡检 - 交接日志统计图表 Response VO")
@Data
public class HandoverLogChartRespVO {

    @Schema(description = "日志量趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "趋势数据")
    public static class TrendData {
        @Schema(description = "时间，格式：yyyy-MM-dd 或 dd")
        private String time;

        @Schema(description = "日志数量")
        private Integer logCount;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总日志数量")
        private Integer logCount;

        @Schema(description = "确认率，保留两位小数")
        private BigDecimal confirmRate;

        /**
         * 设置确认率
         * @param confirmedCount 已确认数量
         * @param totalCount 总数量
         */
        public void setConfirmRate(Integer confirmedCount, Integer totalCount) {
            if (totalCount == null || totalCount == 0) {
                this.confirmRate = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            } else {
                this.confirmRate = BigDecimal.valueOf(confirmedCount)
                        .divide(BigDecimal.valueOf(totalCount), 4, RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }
    }
}