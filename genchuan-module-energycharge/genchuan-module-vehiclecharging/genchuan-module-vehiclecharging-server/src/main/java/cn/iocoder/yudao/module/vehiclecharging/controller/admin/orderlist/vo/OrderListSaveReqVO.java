package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 订单列表新增/修改 Request VO")
@Data
public class OrderListSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单编号不能为空")
    private String orderCode;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "充电桩编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "充电桩编号不能为空")
    private String pileCode;

    @Schema(description = "充电时长")
    private Integer chargeTime;

    @Schema(description = "充电量")
    private BigDecimal chargeAmount;

    @Schema(description = "充电金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "充电金额不能为空")
    private BigDecimal chargeMoney;

    @Schema(description = "支付状态：未支付/已支付", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "支付状态：未支付/已支付不能为空")
    private String payStatus;

    @Schema(description = "订单状态：待支付/已支付/充电中/已完成/已取消", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单状态：待支付/已支付/充电中/已完成/已取消不能为空")
    private String orderStatus;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "取消订单原因")
    private String cancelReason;

    @Schema(description = "终止充电原因")
    private String stopReason;

    @Schema(description = "评价")
    private String evaluate;

    @Schema(description = "评价时间")
    private LocalDateTime evaluateTime;

    @Schema(description = "取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}