package cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 期卡订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderPeriodRespVO {

    @Schema(description = "[主键ID] 期卡订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "21996")
    @ExcelProperty("[主键ID] 期卡订单唯一标识")
    private Long id;

    @Schema(description = "[用户ID] 下单用户ID，关联 park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9532")
    @ExcelProperty("[用户ID] 下单用户ID，关联 park_user.id")
    private Long userId;

    @Schema(description = "[车辆ID] 下单车辆ID，关联 park_car.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32694")
    @ExcelProperty("[车辆ID] 下单车辆ID，关联 park_car.id")
    private Long carId;

    @Schema(description = "[套餐ID] 期卡套餐ID，关联 park_period_package.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11891")
    @ExcelProperty("[套餐ID] 期卡套餐ID，关联 park_period_package.id")
    private Long packageId;

    @Schema(description = "[适用车场ID列表] 适用车场ID列表，varchar 存储，关联 park_lot.id")
    @ExcelProperty("[适用车场ID列表] 适用车场ID列表，varchar 存储，关联 park_lot.id")
    private String lotIds;

    @Schema(description = "[原价] 套餐原价金额", example = "970")
    @ExcelProperty("[原价] 套餐原价金额")
    private BigDecimal originalPrice;

    @Schema(description = "[实付金额] 用户实际支付金额")
    @ExcelProperty("[实付金额] 用户实际支付金额")
    private BigDecimal payAmount;

    @Schema(description = "[优惠金额] 订单优惠金额")
    @ExcelProperty("[优惠金额] 订单优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "[生效时间] 期卡生效时间")
    @ExcelProperty("[生效时间] 期卡生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "[到期时间] 期卡到期时间")
    @ExcelProperty("[到期时间] 期卡到期时间")
    private LocalDateTime expireTime;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已过期", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[订单状态] 如:待支付/已支付/已取消/已过期")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[支付状态] 如:未支付/已支付")
    private String payStatus;

    @Schema(description = "[支付方式] 支付方式", example = "2")
    @ExcelProperty("[支付方式] 支付方式")
    private String payType;

    @Schema(description = "[支付记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "2606")
    @ExcelProperty("[支付记录ID] 关联缴费记录ID，park_payment.id，可为 NULL")
    private Long paymentId;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 期卡订单相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 期卡订单相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
