package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 结算单结算 Request VO")
public class SettlementBillSettleReqVO {

    @Schema(description = "结算单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "结算单主键ID不能为空")
    private Long id;

    @Schema(description = "结算渠道，对应字典", requiredMode = Schema.RequiredMode.REQUIRED, example = "对公转账")
    @NotBlank(message = "结算渠道不能为空")
    private String settlementChannel;

}
