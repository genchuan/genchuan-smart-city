package cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 临停订单新增/修改 Request VO")
@Data
public class OrderTempSaveReqVO {

    @Schema(description = "[主键ID] 临停订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29620")
    private Long id;

    @Schema(description = "[车牌号码] 停车车辆的车牌号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[车牌号码] 停车车辆的车牌号码不能为空")
    private String carNumber;

    @Schema(description = "[入场记录ID] 关联入场记录，park_car_entry.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "9453")
    @NotNull(message = "[入场记录ID] 关联入场记录，park_car_entry.id不能为空")
    private Long entryId;

    @Schema(description = "[离场记录ID] 关联离场记录，park_car_exit.id，可为 NULL", example = "15708")
    private Long exitId;

    @Schema(description = "[所属车场ID] 所属车场ID，关联 park_lot.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "30894")
    @NotNull(message = "[所属车场ID] 所属车场ID，关联 park_lot.id不能为空")
    private Long lotId;

    @Schema(description = "[泊位ID] 停车泊位ID，关联 park_space.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "10920")
    @NotNull(message = "[泊位ID] 停车泊位ID，关联 park_space.id不能为空")
    private Long spaceId;

    @Schema(description = "[停放时长] 停车时长，单位分钟")
    private Integer parkingDuration;

    @Schema(description = "[应收金额] 应收金额")
    private BigDecimal originalAmount;

    @Schema(description = "[优惠金额] 订单优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "[实付金额] 用户实际支付金额")
    private BigDecimal payAmount;

    @Schema(description = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6462")
    @NotNull(message = "[费率策略ID] 关联费率策略ID，park_fee_strategy.id不能为空")
    private Long feeStrategyId;

    @Schema(description = "[订单状态] 如:待支付/已支付/已取消/已完成", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[订单状态] 如:待支付/已支付/已取消/已完成不能为空")
    private String orderStatus;

    @Schema(description = "[支付状态] 如:未支付/已支付/部分支付", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[支付状态] 如:未支付/已支付/部分支付不能为空")
    private String payStatus;

    @Schema(description = "[支付方式] 如:微信/支付宝/现金/其他", example = "1")
    private String payType;

    @Schema(description = "[缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL", example = "3864")
    private Long paymentId;

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
