package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 积分活动图表统计 Response VO")
@Data
public class PointActivityChartRespVO {

    @Schema(description = "总活动数")
    private Integer activityCount;

    @Schema(description = "累计参与用户数")
    private Integer userCount;

    @Schema(description = "活动参与趋势(近30天)")
    private List<TrendItem> trendList;

    @Schema(description = "活动类型分布")
    private List<TypeCountItem> typeCountList;

    @Data
    public static class TrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "参与人数")
        private Integer count;
    }

    @Data
    public static class TypeCountItem {
        @Schema(description = "活动类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

}
