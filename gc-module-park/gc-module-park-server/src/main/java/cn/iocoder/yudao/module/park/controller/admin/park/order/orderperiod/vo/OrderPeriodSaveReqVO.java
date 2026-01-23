package cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 期卡订单新增/修改 Request VO")
@Data
public class OrderPeriodSaveReqVO {

    @Schema(description = "[主键ID] 期卡订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "21996")
    private Long id;

    @Schema(description = "[用户ID] 下单用户ID，关联 park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9532")
    @NotNull(message = "[用户ID] 下单用户ID，关联 park_user.id不能为空")
    private Long userId;

    @Schema(description = "[车辆ID] 下单车辆ID，关联 park_car.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32694")
    @NotNull(message = "[车辆ID] 下单车辆ID，关联 park_car.id不能为空")
    private Long carId;

    @Schema(description = "[套餐ID] 期卡套餐ID，关联 park_period_package.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11891")
    @NotNull(message = "[套餐ID] 期卡套餐ID，关联 park_period_package.id不能为空")
    private Long packageId;

    @Schema(description = "[适用车场ID列表] 适用车场ID列表，varchar 存储，关联 park_lot.id")
    private String lotIds;

    @Schema(description = "[原价] 套餐原价金额", example = "970")
    private BigDecimal originalPrice;

    @Schema(description = "[实付金额] 用户实际支付金额")
    private BigDecimal payAmount;

    @Schema(description = "[优惠金额] 订单优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "[生效时间] 期卡生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "[到期时间] 期卡到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[订单状态] 如:待支付/已支付/已取消/已过期不能为空")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[支付状态] 如:未支付/已支付不能为空")
    private String payStatus;

    @Schema(description = "[支付方式] 支付方式", example = "2")
    private String payType;

    @Schema(description = "[支付记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "2606")
    private Long paymentId;

    @Schema(description = "[备注] 期卡订单相关备注说明", example = "你说的对")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
