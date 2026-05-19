package cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingpayment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 停车缴费催缴 Request VO")
@Data
public class ParkingPaymentRemindReqVO {

    @Schema(description = "账单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "账单ID不能为空")
    private Long id;

    @Schema(description = "催缴内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "催缴内容不能为空")
    private String remindContent;

}
