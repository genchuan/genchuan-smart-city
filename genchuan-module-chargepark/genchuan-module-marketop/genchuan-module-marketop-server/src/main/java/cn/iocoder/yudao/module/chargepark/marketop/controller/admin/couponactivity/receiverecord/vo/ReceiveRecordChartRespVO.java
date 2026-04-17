package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.receiverecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 领用记录图表统计 Response VO")
@Data
public class ReceiveRecordChartRespVO {

    @Schema(description = "领取总量")
    private Integer receiveCount;

    @Schema(description = "核销率")
    private BigDecimal verifyRate;

    @Schema(description = "领取趋势(近30天)")
    private List<TrendItem> trendList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "领取数量")
        private Integer count;
    }

}
