package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 支付应用新增/修改 Request VO")
@Data
public class PayAppSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "应用标识")
    private String appKey;

    @Schema(description = "应用名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "应用名不能为空")
    private String name;

    @Schema(description = "状态：0未生效/1已生效")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "支付结果回调地址")
    private String orderNotifyUrl;

    @Schema(description = "退款结果回调地址")
    private String refundNotifyUrl;

    @Schema(description = "转账结果回调地址")
    private String transferNotifyUrl;
}
