package cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 临停订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderTempPageReqVO extends PageParam {

    @Schema(description = "[车牌号码] 停车车辆的车牌号码")
    private String carNumber;

    @Schema(description = "[订单唯一编号] 订单唯一编号")
    private String orderCode;

    @Schema(description = "[所属车场ID] 所属车场ID，关联 park_lot.id", example = "4597")
    private Long lotId;

    @Schema(description = "[泊位ID] 停车泊位ID，关联 park_space.id", example = "3354")
    private Long spaceId;

    @Schema(description = "[关联录入车辆表ID] 关联 park_input_car.id", example = "11811")
    private Long parkInputCarId;

    @Schema(description = "[使用的优惠券ID] 关联 park_coupon.id",  example = "1")
    private Long couponId;

    @Schema(description = "[入场时间] 从 park_input_car 获取")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] entryTime;

    @Schema(description = "[出场时间] 从 park_input_car 获取")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitTime;

    @Schema(description = "[停放时长] 停车时长，单位分钟")
    private Integer parkingDuration;

    @Schema(description = "[应收金额] 应收金额")
    private BigDecimal originalAmount;

    @Schema(description = "[优惠金额] 订单优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "[实付金额] 用户实际支付金额")
    private BigDecimal payAmount;

    @Schema(description = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id", example = "24602")
    private Long feeStrategyId;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已完成", example = "2")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付/部分支付", example = "2")
    private String payStatus;

    @Schema(description = "[支付方式] 如:微信/支付宝/现金/其他", example = "2")
    private String payType;

    @Schema(description = "[缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "6982")
    private Long paymentId;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 临停订单相关备注说明", example = "你说的对")
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
