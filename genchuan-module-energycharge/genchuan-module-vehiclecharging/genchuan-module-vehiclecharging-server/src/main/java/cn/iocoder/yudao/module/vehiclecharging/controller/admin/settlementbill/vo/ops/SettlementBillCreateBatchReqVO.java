package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 批量生成结算单 Request VO")
public class SettlementBillCreateBatchReqVO {

    @NotBlank(message = "合作方不能为空")
    @Schema(description = "合作方", requiredMode = Schema.RequiredMode.REQUIRED, example = "XX能源科技有限公司")
    private String cooperator;

    @NotBlank(message = "结算周期不能为空")
    @Schema(description = "结算周期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-04")
    private String settlementCycle;

}
