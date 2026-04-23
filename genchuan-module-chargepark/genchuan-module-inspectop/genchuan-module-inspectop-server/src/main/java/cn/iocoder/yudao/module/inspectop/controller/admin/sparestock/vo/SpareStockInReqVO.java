package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "巡查巡检 - 备件仓储入库 Request VO")
@Data
public class SpareStockInReqVO {

    @Schema(description = "备件 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备件ID不能为空")
    private Long spareId;

    @Schema(description = "入库数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入库数量不能为空")
    private Integer inCount;

    @Schema(description = "供应商", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "供应商不能为空")
    private String supplier;
}