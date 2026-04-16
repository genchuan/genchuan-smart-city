package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "汽车充电 - 订单退款备注 Request VO")
@Data
public class OrderRefundRemarkReqVO {

    @Schema(description = "退款申请 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款申请 ID 不能为空")
    private Long id;

    @Schema(description = "备注内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "备注内容不能为空")
    private String remark;
}