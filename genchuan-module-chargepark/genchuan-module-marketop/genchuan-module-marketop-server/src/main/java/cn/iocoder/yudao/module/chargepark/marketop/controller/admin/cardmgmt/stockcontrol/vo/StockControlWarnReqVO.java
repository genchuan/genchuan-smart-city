package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 库存预警 Request VO")
@Data
public class StockControlWarnReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

}
