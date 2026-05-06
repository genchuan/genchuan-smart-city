package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-总库存 Response VO")
@Data
public class CycleReportChartCardDrillTotalStockRespVO {

    @Schema(description = "卡种ID，关联卡种配置表card_config")
    private Long cardId;

    @Schema(description = "卡种名称，关联card_config.name")
    private String cardName;

    @Schema(description = "卡种类型（日卡/周卡/月卡/季卡/年卡，关联芋道字典表：card_config_type）")
    private String cardType;

    @Schema(description = "当前库存，关联stock_control.current_stock")
    private Integer currentStock;

    @Schema(description = "预警阈值，关联stock_control.warn_threshold")
    private Integer warnThreshold;

    @Schema(description = "库存状态（正常库存/低库存/预警库存，关联芋道字典表：stock_control_status）")
    private String status;

    @Schema(description = "租户ID，关联stock_control.tenant_id")
    private Long tenantId;

}
