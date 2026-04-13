package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Schema(description = "汽车充电 - 订单退款批量退款 Request VO")
@Data
public class OrderRefundRefundReqVO {

    @Schema(description = "退款申请 ID 集合，支持批量退款", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "退款申请 ID 集合不能为空")
    private List<Long> ids;
}