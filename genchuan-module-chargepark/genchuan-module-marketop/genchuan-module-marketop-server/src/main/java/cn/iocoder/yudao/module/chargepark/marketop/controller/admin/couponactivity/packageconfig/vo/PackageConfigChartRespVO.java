package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.packageconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 券包配置图表统计 Response VO")
@Data
public class PackageConfigChartRespVO {

    @Schema(description = "生效券包数")
    private Integer enableCount;

    @Schema(description = "总销量")
    private Integer salesCount;

    @Schema(description = "券包类型分布")
    private List<TypeCountItem> typeList;

    @Data
    public static class TypeCountItem {
        @Schema(description = "券包类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

}
