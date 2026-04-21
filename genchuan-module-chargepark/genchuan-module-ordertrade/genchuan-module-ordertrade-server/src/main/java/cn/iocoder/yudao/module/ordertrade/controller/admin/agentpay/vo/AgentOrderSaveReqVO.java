package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 代付订单新增/修改 Request VO")
@Data
public class AgentOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "车牌")
    private String carNo;

    @Schema(description = "金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "金额不能为空")
    private BigDecimal amount;

    @Schema(description = "支付方式：wechat/alipay/bank")
    private String payType;

    @Schema(description = "状态：pending_pay/paid/completed/cancelled")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
