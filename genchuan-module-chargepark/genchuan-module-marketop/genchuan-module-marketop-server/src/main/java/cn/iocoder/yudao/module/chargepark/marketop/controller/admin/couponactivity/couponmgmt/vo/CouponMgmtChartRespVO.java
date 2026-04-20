package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 优惠券图表统计 Response VO")
@Data
public class CouponMgmtChartRespVO {

    @Schema(description = "领取量")
    private Integer sendCount;

    @Schema(description = "核销率")
    private BigDecimal verifyRate;

    @Schema(description = "领取趋势(近30天)")
    private List<TrendItem> trendList;

    @Schema(description = "券类型分布")
    private List<TypeCountItem> typeList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "领取数量")
        private Integer count;
    }

    @Data
    public static class TypeCountItem {
        @Schema(description = "券类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

}
