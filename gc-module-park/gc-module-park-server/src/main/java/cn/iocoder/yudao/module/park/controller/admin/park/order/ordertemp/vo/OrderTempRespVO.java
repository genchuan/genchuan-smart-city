package cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 临停订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderTempRespVO {

    @Schema(description = "[主键ID] 临停订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "9482")
    @ExcelProperty("[主键ID] 临停订单唯一标识")
    private Long id;

    @Schema(description = "[订单唯一编号] 订单唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[订单唯一编号] 订单唯一编号")
    private String orderCode;

    @Schema(description = "[车牌号码] 停车车辆的车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[车牌号码] 停车车辆的车牌号码")
    private String carNumber;

    @Schema(description = "[所属车场ID] 所属车场ID，关联 park_lot.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "4597")
    @ExcelProperty("[所属车场ID] 所属车场ID，关联 park_lot.id")
    private Long lotId;

    @Schema(description = "[泊位ID] 停车泊位ID，关联 park_space.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "3354")
    @ExcelProperty("[泊位ID] 停车泊位ID，关联 park_space.id")
    private Long spaceId;

    @Schema(description = "[关联录入车辆表ID] 关联 park_input_car.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11811")
    @ExcelProperty("[关联录入车辆表ID] 关联 park_input_car.id")
    private Long parkInputCarId;

    @Schema(description = "[使用的优惠券ID] 关联 park_coupon.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[使用的优惠券ID] ")
    private Long couponId;



    @Schema(description = "[入场时间] 从 park_input_car 获取")
    @ExcelProperty("[入场时间] 从 park_input_car 获取")
    private LocalDateTime entryTime;

    @Schema(description = "[出场时间] 从 park_input_car 获取")
    @ExcelProperty("[出场时间] 从 park_input_car 获取")
    private LocalDateTime exitTime;

    @Schema(description = "[停放时长] 停车时长，单位分钟")
    @ExcelProperty("[停放时长] 停车时长，单位分钟")
    private Integer parkingDuration;

    @Schema(description = "[应收金额] 应收金额")
    @ExcelProperty("[应收金额] 应收金额")
    private BigDecimal originalAmount;

    @Schema(description = "[优惠金额] 订单优惠金额")
    @ExcelProperty("[优惠金额] 订单优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "[实付金额] 用户实际支付金额")
    @ExcelProperty("[实付金额] 用户实际支付金额")
    private BigDecimal payAmount;

    @Schema(description = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "24602")
    @ExcelProperty("[费率策略ID] 关联费率策略ID，park_fee_strategy.id")
    private Long feeStrategyId;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[订单状态] 如:待支付/已支付/已取消/已完成")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付/部分支付", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[支付状态] 如:未支付/已支付/部分支付")
    private String payStatus;

    @Schema(description = "[支付方式] 如:微信/支付宝/现金/其他", example = "2")
    @ExcelProperty("[支付方式] 如:微信/支付宝/现金/其他")
    private String payType;

    @Schema(description = "[缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "6982")
    @ExcelProperty("[缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL")
    private Long paymentId;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 临停订单相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 临停订单相关备注说明")
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
