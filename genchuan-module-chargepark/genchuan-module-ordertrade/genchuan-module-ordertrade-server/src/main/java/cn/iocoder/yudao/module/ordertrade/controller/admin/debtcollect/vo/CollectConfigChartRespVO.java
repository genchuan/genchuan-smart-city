package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 追缴配置统计 Response VO
 * @author genchuan
 */
@Schema(description = "管理后台 - 追缴配置统计 Response VO")
@Data
public class CollectConfigChartRespVO {

    @Schema(description = "配置类型占比数据（饼图）")
    private List<Map<String,Object>> typeData;

    @Schema(description = "今日核心指标（卡片）")
    private CardData cardData;

    @Data
    @Schema(description = "卡片指标")
    public static class CardData {

        @Schema(description = "已生效配置数（卡片）")
        private Integer enableConfigCount;

        @Schema(description = "追缴触发率（%）（卡片）")
        private BigDecimal collectTriggerRate;
    }
}
