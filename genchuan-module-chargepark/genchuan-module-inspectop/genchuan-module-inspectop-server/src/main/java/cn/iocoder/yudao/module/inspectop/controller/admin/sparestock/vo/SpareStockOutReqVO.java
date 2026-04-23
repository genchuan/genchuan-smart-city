package cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 备件仓储出库 Request VO")
@Data
public class SpareStockOutReqVO {

    @Schema(description = "备件 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备件ID不能为空")
    private Long spareId;

    @Schema(description = "出库数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "出库数量不能为空")
    private Integer outCount;

    @Schema(description = "领用人员", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "领用人员不能为空")
    private String receiver;
}