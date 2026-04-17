package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 库存补货 Request VO")
@Data
public class StockControlRestockReqVO {

    @Schema(description = "库存记录ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存记录ID不能为空")
    private Long id;

    @Schema(description = "补货数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "补货数量不能为空")
    private Integer quantity;

}
