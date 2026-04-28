package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.prizemgmt.vo.PrizeMgmtChartRespVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 规则配置图表统计 Response VO")
@Data
public class RuleConfigChartRespVO {

    @Schema(description = "生效配置数")
    private Integer enableCount;

    @Schema(description = "规则匹配率")
    private BigDecimal matchRate;

    @Schema(description = "规则类型占比")
    private List<TypeRateItem> typeList;

    @Schema(description = "规则类型分布")
    private List<TypeCountItem> typeCountList;

    @Schema(description = "场景分布")
    private List<SceneCountItem>  sceneCountList;

    @Data
    public static class TypeRateItem {
        @Schema(description = "规则类型")
        private String type;
        @Schema(description = "占比")
        private BigDecimal rate;
    }

    @Data
    public static class TypeCountItem {
        @Schema(description = "奖品类型")
        private String type;
        @Schema(description = "数量")
        private Integer count;
    }

    @Data
    public static class SceneCountItem {
        @Schema(description = "奖品类型")
        private String scene;
        @Schema(description = "数量")
        private Integer count;
    }
}
