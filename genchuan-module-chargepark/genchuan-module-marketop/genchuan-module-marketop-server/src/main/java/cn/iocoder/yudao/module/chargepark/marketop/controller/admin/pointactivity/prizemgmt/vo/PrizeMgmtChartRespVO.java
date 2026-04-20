package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 奖品管理图表统计 Response VO")
@Data
public class PrizeMgmtChartRespVO {

    @Schema(description = "奖品总数")
    private Integer prizeCount;

    @Schema(description = "总发放量")
    private Integer sendCount;

    @Schema(description = "奖品类型分布")
    private List<TypeCountItem> typeList;

    @Data
    public static class TypeCountItem {
        @Schema(description = "奖品类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

}
