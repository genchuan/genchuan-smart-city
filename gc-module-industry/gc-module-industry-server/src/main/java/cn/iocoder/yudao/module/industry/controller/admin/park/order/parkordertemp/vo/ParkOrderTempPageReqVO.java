package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 临停订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkOrderTempPageReqVO extends PageParam {

    @Schema(description = "发生地区编码，12位国标地区编码")
    private String regionFullCode;

    @Schema(description = "订单编号，唯一订单编号")
    private String orderNo;

    @Schema(description = "车牌号")
    private String carNumber;

    @Schema(description = "用户ID，关联 sys_user.id，可为 NULL", example = "14816")
    private Long userId;

    @Schema(description = "停车场ID，关联 park_lot.lot_id", example = "32561")
    private Long lotId;

    @Schema(description = "入场记录ID，关联 park_car_entry.entry_id", example = "6441")
    private Long entryId;

    @Schema(description = "出场记录ID，关联 park_car_exit.exit_id，可为 NULL", example = "31724")
    private Long exitId;

    @Schema(description = "停放开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] parkingStartTime;

    @Schema(description = "停放结束时间，可为 NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] parkingEndTime;

    @Schema(description = "停放时长，单位：分钟")
    private Integer parkingDuration;

    @Schema(description = "收费策略ID，关联 park_fee_strategy.fee_strategy_id", example = "11687")
    private Long feeStrategyId;

    @Schema(description = "原始费用")
    private BigDecimal originalAmount;

    @Schema(description = "优惠金额")
    private BigDecimal discountAmount;

    @Schema(description = "实付金额")
    private BigDecimal payAmount;

    @Schema(description = "订单状态：待支付/已支付/已取消/已退款/逃费", example = "2")
    private String orderStatus;

    @Schema(description = "支付时间，可为 NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] payTime;

    @Schema(description = "取消时间，可为 NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "退款时间，可为 NULL")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] refundTime;

    @Schema(description = "备注信息", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "通用扩展字段1，可为空")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，可为空")
    private String extCommon2;

    @Schema(description = "通用扩展字段3，可为空")
    private String extCommon3;

    @Schema(description = "通用扩展字段4，可为空")
    private String extCommon4;

}
