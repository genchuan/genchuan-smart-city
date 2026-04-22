package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 开票卡种订单 Request VO")
@Data
public class CardOrderInvoiceReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "id不能为空")
    private Long id;

}
