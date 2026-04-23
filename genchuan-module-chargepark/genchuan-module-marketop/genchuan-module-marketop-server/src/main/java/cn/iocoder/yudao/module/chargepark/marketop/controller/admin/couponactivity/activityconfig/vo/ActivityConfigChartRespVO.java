package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 活动配置图表统计 Response VO")
@Data
public class ActivityConfigChartRespVO {

    @Schema(description = "生效活动数")
    private Integer enableCount;

    @Schema(description = "活动参与率")
    private Integer joinRate;

    @Schema(description = "活动类型分布")
    private List<TypeCountItem> typeList;

    @Data
    public static class TypeCountItem {
        @Schema(description = "活动类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

}
