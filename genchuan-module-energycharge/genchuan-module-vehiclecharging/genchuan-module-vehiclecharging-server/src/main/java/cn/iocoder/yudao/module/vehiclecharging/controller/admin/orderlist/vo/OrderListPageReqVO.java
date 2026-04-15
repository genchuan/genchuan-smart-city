package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 订单列表分页 Request VO")
@Data
public class OrderListPageReqVO extends PageParam {

    @Schema(description = "订单编号")
    private String orderCode;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "充电桩编号")
    private String pileCode;

    @Schema(description = "充电时长")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] chargeTime;

    @Schema(description = "充电量")
    private BigDecimal chargeAmount;

    @Schema(description = "充电金额")
    private BigDecimal chargeMoney;

    @Schema(description = "支付状态：未支付/已支付")
    private String payStatus;

    @Schema(description = "订单状态：待支付/已支付/充电中/已完成/已取消")
    private String orderStatus;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "终止充电原因")
    private String stopReason;

    @Schema(description = "评价")
    private String evaluate;

    @Schema(description = "评价时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evaluateTime;

    @Schema(description = "取消时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] cancelTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建时间起始")
    private String startTime;

    @Schema(description = "创建时间结束")
    private String endTime;

}