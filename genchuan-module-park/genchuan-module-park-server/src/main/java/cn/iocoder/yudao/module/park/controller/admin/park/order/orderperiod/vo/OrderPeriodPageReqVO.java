package cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 期卡订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderPeriodPageReqVO extends PageParam {

    @Schema(description = "[用户ID] 下单用户ID，关联 park_user.id", example = "9532")
    private Long userId;

    @Schema(description = "[车辆ID] 下单车辆ID，关联 park_car.id", example = "32694")
    private Long carId;

    @Schema(description = "[套餐ID] 期卡套餐ID，关联 park_period_package.id", example = "11891")
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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "[到期时间] 期卡到期时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已过期", example = "2")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付", example = "2")
    private String payStatus;

    @Schema(description = "[支付方式] 支付方式", example = "2")
    private String payType;

    @Schema(description = "[支付记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "2606")
    private Long paymentId;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

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
