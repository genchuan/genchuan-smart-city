package cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Schema(description = "巡查巡检 - 换班申请统计图表 Response VO")
@Data
public class ShiftApplyChartRespVO {

    @Schema(description = "申请量趋势折线图数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "趋势数据")
    public static class TrendData {
        @Schema(description = "时间，格式：yyyy-MM-dd 或 dd")
        private String time;

        @Schema(description = "申请数量")
        private Integer applyCount;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总申请数量")
        private Integer applyCount;

        @Schema(description = "审核通过率，保留两位小数")
        private BigDecimal auditPassRate;

        /**
         * 设置审核通过率
         * @param passedCount 已通过数量
         * @param totalCount 总数量
         */
        public void setAuditPassRate(Integer passedCount, Integer totalCount) {
            if (totalCount == null || totalCount == 0) {
                this.auditPassRate = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            } else {
                this.auditPassRate = BigDecimal.valueOf(passedCount)
                        .divide(BigDecimal.valueOf(totalCount), 4, RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP);
            }
        }
    }
}