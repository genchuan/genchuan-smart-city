package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 结算单重新审核 Request VO")
public class SettlementBillReauditReqVO {

    @NotNull(message = "结算单主键ID不能为空")
    @Schema(description = "结算单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Long id;

}
