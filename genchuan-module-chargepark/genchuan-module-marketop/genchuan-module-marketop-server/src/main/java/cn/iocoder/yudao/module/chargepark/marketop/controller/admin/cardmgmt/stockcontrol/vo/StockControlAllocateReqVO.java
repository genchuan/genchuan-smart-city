package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 库存调配 Request VO")
@Data
public class StockControlAllocateReqVO {

    @Schema(description = "卡种ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "卡种ID不能为空")
    private Long cardId;

    @Schema(description = "目标场站", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "目标场站不能为空")
    private String targetStationId;

    @Schema(description = "目标场站", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "源场站不能为空")
    private String sourceStationId;

    @Schema(description = "调配数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调配数量不能为空")
    private Integer number;

}
