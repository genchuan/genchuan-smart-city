package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 结算单审核 Request VO")
public class SettlementBillAuditReqVO {

    @Schema(description = "结算单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "结算单主键ID不能为空")
    private Long id;

    @Schema(description = "审核备注", example = "审核通过，数据无误")
    private String auditRemark;

}
