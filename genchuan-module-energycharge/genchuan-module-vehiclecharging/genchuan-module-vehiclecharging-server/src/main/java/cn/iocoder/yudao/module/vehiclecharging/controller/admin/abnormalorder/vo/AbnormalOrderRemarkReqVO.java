package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Schema(description = "异常订单 - 修改备注 Request VO")
@Data
public class AbnormalOrderRemarkReqVO {

    @Schema(description = "异常订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "异常订单ID不能为空")
    private Long id;

    @Schema(description = "备注内容", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户反馈已收到退款，无其他问题")
    @NotEmpty(message = "备注内容不能为空")
    private String remark;
}