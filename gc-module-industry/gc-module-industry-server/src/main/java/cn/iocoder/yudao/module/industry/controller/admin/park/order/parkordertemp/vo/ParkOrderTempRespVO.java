package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 临停订单 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkOrderTempRespVO {

    @Schema(description = "主键ID，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "11815")
    @ExcelProperty("主键ID，记录唯一标识")
    private Long id;

    @Schema(description = "发生地区编码，12位国标地区编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发生地区编码，12位国标地区编码")
    private String regionFullCode;

    @Schema(description = "订单编号，唯一订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单编号，唯一订单编号")
    private String orderNo;

    @Schema(description = "车牌号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车牌号")
    private String carNumber;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "14816")
    @ExcelProperty("用户ID，关联 sys_user.id，可为 NULL")
    private Long userId;

    @Schema(description = "停车场ID，关联 park_lot.lot_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "32561")
    @ExcelProperty("停车场ID，关联 park_lot.lot_id")
    private Long lotId;

    @Schema(description = "入场记录ID，关联 park_car_entry.entry_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6441")
    @ExcelProperty("入场记录ID，关联 park_car_entry.entry_id")
    private Long entryId;

    @Schema(description = "出场记录ID，关联 park_car_exit.exit_id，可为 NULL", example = "31724")
    @ExcelProperty("出场记录ID，关联 park_car_exit.exit_id，可为 NULL")
    private Long exitId;

    @Schema(description = "停放开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停放开始时间")
    private LocalDateTime parkingStartTime;

    @Schema(description = "停放结束时间，可为 NULL")
    @ExcelProperty("停放结束时间，可为 NULL")
    private LocalDateTime parkingEndTime;

    @Schema(description = "停放时长，单位：分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("停放时长，单位：分钟")
    private Integer parkingDuration;

    @Schema(description = "收费策略ID，关联 park_fee_strategy.fee_strategy_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "11687")
    @ExcelProperty("收费策略ID，关联 park_fee_strategy.fee_strategy_id")
    private Long feeStrategyId;

    @Schema(description = "原始费用", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("原始费用")
    private BigDecimal originalAmount;

    @Schema(description = "优惠金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "实付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("实付金额")
    private BigDecimal payAmount;

    @Schema(description = "订单状态：待支付/已支付/已取消/已退款/逃费", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("订单状态：待支付/已支付/已取消/已退款/逃费")
    private String orderStatus;

    @Schema(description = "支付时间，可为 NULL")
    @ExcelProperty("支付时间，可为 NULL")
    private LocalDateTime payTime;

    @Schema(description = "取消时间，可为 NULL")
    @ExcelProperty("取消时间，可为 NULL")
    private LocalDateTime cancelTime;

    @Schema(description = "退款时间，可为 NULL")
    @ExcelProperty("退款时间，可为 NULL")
    private LocalDateTime refundTime;

    @Schema(description = "备注信息", example = "你猜")
    @ExcelProperty("备注信息")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "通用扩展字段1，可为空")
    @ExcelProperty("通用扩展字段1，可为空")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，可为空")
    @ExcelProperty("通用扩展字段2，可为空")
    private String extCommon2;

    @Schema(description = "通用扩展字段3，可为空")
    @ExcelProperty("通用扩展字段3，可为空")
    private String extCommon3;

    @Schema(description = "通用扩展字段4，可为空")
    @ExcelProperty("通用扩展字段4，可为空")
    private String extCommon4;

}
