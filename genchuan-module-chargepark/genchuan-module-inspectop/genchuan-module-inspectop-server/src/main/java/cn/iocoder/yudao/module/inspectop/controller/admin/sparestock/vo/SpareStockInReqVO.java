package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;

@Schema(description = "巡查巡检 - 备件仓储入库 Request VO")
@Data
public class SpareStockInReqVO {

    @Schema(description = "备件ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备件ID不能为空")
    private Long spareId;

    @Schema(description = "备件名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "备件名称不能为空")
    private String spareName;

    @Schema(description = "入库数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入库数量不能为空")
    @Min(value = 1, message = "入库数量必须大于0")
    private Integer inCount;

    @Schema(description = "供应商", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "供应商不能为空")
    private String supplier;

    @Schema(description = "库存状态（新增时使用，可选）", example = "1")
    private String status = "1"; // 默认正常状态
}