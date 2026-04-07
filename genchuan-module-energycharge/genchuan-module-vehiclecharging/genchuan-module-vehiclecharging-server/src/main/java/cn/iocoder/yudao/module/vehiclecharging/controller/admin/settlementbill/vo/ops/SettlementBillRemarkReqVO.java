package cn.iocoder.yudao.module.vehiclecharging.controller.admin.settlementbill.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 结算单修改备注 Request VO")
public class SettlementBillRemarkReqVO {

    @NotNull(message = "结算单主键ID不能为空")
    @Schema(description = "结算单主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @NotBlank(message = "备注内容不能为空")
    @Schema(description = "备注内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "该结算单已完成发票开具")
    private String remark;

}
