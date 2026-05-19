package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "巡查巡检 - 交接日志统计图表 Response VO")
@Data
public class HandoverLogChartRespVO {

    @Schema(description = "趋势数据")
    private List<TrendData> trendData;

    @Schema(description = "卡片数据")
    private CardData cardData;

    @Data
    @Schema(description = "趋势数据")
    public static class TrendData {
        @Schema(description = "时间，格式：yyyy-MM-dd")
        private String time;

        @Schema(description = "交接日志数量")
        private Integer logCount;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总交接次数")
        private Integer totalCount;

        @Schema(description = "已确认次数")
        private Integer confirmedCount;

        /**
         * 计算确认率
         */
        public Double getConfirmRate() {
            if (totalCount == 0) {
                return 0.0;
            }
            return confirmedCount * 100.0 / totalCount;
        }
    }
}