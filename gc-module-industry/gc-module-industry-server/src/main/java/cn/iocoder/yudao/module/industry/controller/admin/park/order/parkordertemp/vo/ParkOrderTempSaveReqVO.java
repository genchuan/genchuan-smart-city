package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 临停订单新增/修改 Request VO")
@Data
public class ParkOrderTempSaveReqVO {

    @Schema(description = "主键ID，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11")
    private Long id;

    @Schema(description = "发生地区编码，12位国标地区编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "110101001001")
    @NotEmpty(message = "发生地区编码，12位国标地区编码不能为空")
    private String regionFullCode;

    @Schema(description = "订单编号，唯一订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "ORD20251231001")
    @NotEmpty(message = "订单编号，唯一订单编号不能为空")
    private String orderNo;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED, example = "京A12345")
    @NotEmpty(message = "车牌号不能为空")
    private String carNumber;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "1")
    private Long userId;

    @Schema(description = "停车场ID，关联 park_lot.lot_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "停车场ID，关联 park_lot.lot_id不能为空")
    private Long lotId;

    @Schema(description = "入场记录ID，关联 park_car_entry.entry_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "5001")
    @NotNull(message = "入场记录ID，关联 park_car_entry.entry_id不能为空")
    private Long entryId;

    @Schema(description = "出场记录ID，关联 park_car_exit.exit_id，可为 NULL", example = "6001")
    private Long exitId;

    @Schema(description = "停放开始时间（毫秒时间戳，2025年12月示例）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1764556800000")
    @NotNull(message = "停放开始时间不能为空")
    private LocalDateTime parkingStartTime;

    @Schema(description = "停放结束时间（毫秒时间戳，2025年12月示例）", example = "1764564000000")
    private LocalDateTime parkingEndTime;

    @Schema(description = "停放时长，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED, example = "150")
    @NotNull(message = "停放时长，单位：分钟不能为空")
    private Integer parkingDuration;

    @Schema(description = "收费策略ID，关联 park_fee_strategy.fee_strategy_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "收费策略ID，关联 park_fee_strategy.fee_strategy_id不能为空")
    private Long feeStrategyId;

    @Schema(description = "原始费用", requiredMode = Schema.RequiredMode.REQUIRED, example = "50.00")
    @NotNull(message = "原始费用不能为空")
    private BigDecimal originalAmount;

    @Schema(description = "优惠金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "5.00")
    @NotNull(message = "优惠金额不能为空")
    private BigDecimal discountAmount;

    @Schema(description = "实付金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "45.00")
    @NotNull(message = "实付金额不能为空")
    private BigDecimal payAmount;

    @Schema(description = "订单状态：待支付/已支付/已取消/已退款/逃费", requiredMode = Schema.RequiredMode.REQUIRED, example = "已支付")
    @NotEmpty(message = "订单状态：待支付/已支付/已取消/已退款/逃费不能为空")
    private String orderStatus;

    @Schema(description = "支付时间（毫秒时间戳，2025年12月示例）", example = "1764564000000")
    private LocalDateTime payTime;

    @Schema(description = "取消时间（毫秒时间戳，2025年12月示例）", example = "1764559200000")
    private LocalDateTime cancelTime;

    @Schema(description = "退款时间（毫秒时间戳，2025年12月示例）", example = "1764567600000")
    private LocalDateTime refundTime;

    @Schema(description = "备注信息", example = "临时停车，VIP客户")
    private String remark;

    @Schema(description = "通用扩展字段1，可为空", example = "扩展字段1示例")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，可为空", example = "扩展字段2示例")
    private String extCommon2;

    @Schema(description = "通用扩展字段3，可为空", example = "扩展字段3示例")
    private String extCommon3;

    @Schema(description = "通用扩展字段4，可为空", example = "扩展字段4示例")
    private String extCommon4;

}


