package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkwalletrecharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 钱包充值 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkWalletRechargeRespVO {

    @Schema(description = "主键ID，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "15238")
    @ExcelProperty("主键ID，自增")
    private Long id;

    @Schema(description = "充值单号，唯一充值单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充值单号，唯一充值单号")
    private String rechargeNo;

    @Schema(description = "用户ID，关联 sys_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "31192")
    @ExcelProperty("用户ID，关联 sys_user.id")
    private Long userId;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @Schema(description = "实付金额，用户实际支付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实付金额，用户实际支付金额")
    private BigDecimal actualPayAmount;

    @Schema(description = "充值套餐ID，关联 park_recharge_package.package_id，可为 NULL", example = "18210")
    @ExcelProperty("充值套餐ID，关联 park_recharge_package.package_id，可为 NULL")
    private Long rechargePackageId;

    @Schema(description = "优惠金额，充值优惠金额")
    @ExcelProperty("优惠金额，充值优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "支付方式：微信/支付宝/银行卡/其他")
    @ExcelProperty("支付方式：微信/支付宝/银行卡/其他")
    private String payWay;

    @Schema(description = "充值状态：待支付 / 已支付 / 已取消 / 支付失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("充值状态：待支付 / 已支付 / 已取消 / 支付失败")
    private String rechargeStatus;

    @Schema(description = "支付时间")
    @ExcelProperty("支付时间")
    private LocalDateTime payTime;

    @Schema(description = "到账时间，充值到账时间")
    @ExcelProperty("到账时间，充值到账时间")
    private LocalDateTime rechargeTime;

    @Schema(description = "优惠券ID，关联 park_coupon.coupon_id，可为 NULL", example = "18141")
    @ExcelProperty("优惠券ID，关联 park_coupon.coupon_id，可为 NULL")
    private Long couponId;

    @Schema(description = "备注，业务备注说明", example = "随便")
    @ExcelProperty("备注，业务备注说明")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间，记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，记录创建时间")
    private LocalDateTime createTime;

}
