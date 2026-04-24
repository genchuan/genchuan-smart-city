package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户充值新增/修改 Request VO")
@Data
public class MerchantRechargeCreateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2272")
    private Long id;

    @Schema(description = "商户ID，关联merchant_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "23661")
    @NotNull(message = "商户ID，关联merchant_info.id不能为空")
    private Long merchantId;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal amount;

    @Schema(description = "支付渠道：微信/支付宝/银行转账/平台余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "支付渠道：微信/支付宝/银行转账/平台余额不能为空")
    private String payChannel;

    @Schema(description = "充值状态：待支付/已支付/已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "充值状态：待支付/已支付/已取消不能为空")
    private String status;

    @Schema(description = "充值订单号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "充值订单号，唯一不能为空")
    private String orderNo;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}