package cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 库存调配 Request VO")
@Data
public class AssetStockAllocateReqVO {

    @Schema(description = "库存ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "库存ID不能为空")
    private Long id;

    @Schema(description = "目标场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "目标场站ID不能为空")
    private Long targetStationId;

    @Schema(description = "调配数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调配数量不能为空")
    @Min(value = 1, message = "调配数量必须大于0")
    private Integer allocateCount;
}