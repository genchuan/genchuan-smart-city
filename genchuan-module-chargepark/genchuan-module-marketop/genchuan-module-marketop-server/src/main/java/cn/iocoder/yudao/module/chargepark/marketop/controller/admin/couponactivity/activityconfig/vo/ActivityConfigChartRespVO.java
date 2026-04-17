package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 活动配置图表统计 Response VO")
@Data
public class ActivityConfigChartRespVO {

    @Schema(description = "生效活动数")
    private Integer enableCount;

    @Schema(description = "活动参与率")
    private BigDecimal joinRate;

    @Schema(description = "活动类型参与率分布")
    private List<TypeRateItem> typeList;

    @Data
    public static class TypeRateItem {
        @Schema(description = "活动类型")
        private String type;
        @Schema(description = "参与率")
        private BigDecimal rate;
    }

}
