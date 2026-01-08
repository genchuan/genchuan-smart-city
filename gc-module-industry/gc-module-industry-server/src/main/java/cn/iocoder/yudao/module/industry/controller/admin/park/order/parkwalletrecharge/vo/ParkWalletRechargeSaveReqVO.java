package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 钱包充值新增/修改 Request VO")
@Data
public class ParkWalletRechargeSaveReqVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "15238")
    private Long id;

    @Schema(description = "充值单号，唯一充值单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "充值单号，唯一充值单号不能为空")
    private String rechargeNo;

    @Schema(description = "用户ID，关联 sys_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31192")
    @NotNull(message = "用户ID，关联 sys_user.id不能为空")
    private Long userId;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal rechargeAmount;

    @Schema(description = "实付金额，用户实际支付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实付金额，用户实际支付金额不能为空")
    private BigDecimal actualPayAmount;

    @Schema(description = "充值套餐ID，关联 park_recharge_package.package_id，可为 NULL", example = "18210")
    private Long rechargePackageId;

    @Schema(description = "优惠金额，充值优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "支付方式：微信/支付宝/银行卡/其他")
    private String payWay;

    @Schema(description = "充值状态：待支付 / 已支付 / 已取消 / 支付失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "充值状态：待支付 / 已支付 / 已取消 / 支付失败不能为空")
    private String rechargeStatus;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "到账时间，充值到账时间")
    private LocalDateTime rechargeTime;

    @Schema(description = "优惠券ID，关联 park_coupon.coupon_id，可为 NULL", example = "18141")
    private Long couponId;

    @Schema(description = "备注，业务备注说明", example = "随便")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
