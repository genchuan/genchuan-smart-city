package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "汽车充电 - 结算单驳回 Request VO")
public class SettlementBillRejectReqVO {

    @NotNull(message = "结算单主键ID不能为空")
    @Schema(description = "结算单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    private Long id;

    @NotBlank(message = "驳回原因不能为空")
    @Schema(description = "驳回原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "结算数据存在异常，金额核对不符")
    private String rejectReason;

}
