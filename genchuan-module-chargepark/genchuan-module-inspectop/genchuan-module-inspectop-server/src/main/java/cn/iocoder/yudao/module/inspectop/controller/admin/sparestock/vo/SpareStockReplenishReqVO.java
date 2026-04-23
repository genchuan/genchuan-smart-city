package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 备件仓储补货 Request VO")
@Data
public class SpareStockReplenishReqVO {

    @Schema(description = "备件库存ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备件库存ID不能为空")
    private Long id;

    @Schema(description = "补货数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "补货数量不能为空")
    @Min(value = 1, message = "补货数量必须大于0")
    private Integer replenishCount;
}