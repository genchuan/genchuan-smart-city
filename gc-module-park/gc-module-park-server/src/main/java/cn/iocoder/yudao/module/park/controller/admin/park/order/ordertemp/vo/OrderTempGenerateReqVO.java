package cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 临停订单生成 Request VO")
@Data
public class OrderTempGenerateReqVO {
    @Schema(description = "[车牌号码] 停车车辆的车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车牌号码] 停车车辆的车牌号码不能为空")
    private String carNumber;

    @Schema(description = "[所属车场ID] 所属车场ID，关联 park_lot.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4597")
    @NotNull(message = "[所属车场ID] 所属车场ID，关联 park_lot.id不能为空")
    private Long lotId;

    @Schema(description = "[泊位ID] 停车泊位ID，关联 park_space.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3354")
    @NotNull(message = "[泊位ID] 停车泊位ID，关联 park_space.id不能为空")
    private Long spaceId;

    @Schema(description = "[关联录入车辆表ID] 关联 park_input_car.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11811")
    @NotNull(message = "[关联录入车辆表ID] 关联 park_input_car.id不能为空")
    private Long parkInputCarId;

    @Schema(description = "[备注] 临停订单相关备注说明", example = "你说的对")
    private String remark;
    //车场表得到
//    @Schema(description = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24602")
//    @NotNull(message = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id不能为空")
//    private Long feeStrategyId;



    //通过录入车辆表获取
//    @Schema(description = "[入场时间] 从 park_input_car 获取")
//    private LocalDateTime entryTime;
//    //通过录入车辆表获取
//    @Schema(description = "[出场时间] 从 park_input_car 获取")
//    private LocalDateTime exitTime;

    //通过计算获得
//    @Schema(description = "[停放时长] 停车时长，单位分钟")
//    private Integer parkingDuration;

    //计算获取
//    @Schema(description = "[应收金额] 应收金额")
//    private BigDecimal originalAmount;
//
//    @Schema(description = "[优惠金额] 订单优惠金额")
//    private BigDecimal discountAmount;
//
//    @Schema(description = "[实付金额] 用户实际支付金额")
//    private BigDecimal payAmount;



//    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
//    @NotEmpty(message = "[订单状态] 如:待支付/已支付/已取消/已完成不能为空")
//    private String orderStatus;
//
//    @Schema(description = "[支付状态] 如:未支付/已支付/部分支付", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
//    @NotEmpty(message = "[支付状态] 如:未支付/已支付/部分支付不能为空")
//    private String payStatus;
//
//    @Schema(description = "[支付方式] 如:微信/支付宝/现金/其他", example = "2")
//    private String payType;



    //缴费记录删除
//    @Schema(description = "[缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "6982")
//    private Long paymentId;



}
