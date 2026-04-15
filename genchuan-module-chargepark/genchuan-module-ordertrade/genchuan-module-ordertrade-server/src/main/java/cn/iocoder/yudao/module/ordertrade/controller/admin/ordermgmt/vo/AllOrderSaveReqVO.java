package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 全部订单新增/修改 Request VO")
@Data
public class AllOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "ORD-20250411001")
    @NotBlank(message = "订单编号不能为空")
    private String orderNo;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "场站不能为空")
    private Long stationId;

    @Schema(description = "订单类型（all_order_type）：temp_park/offtime_park/car_charge/bike_charge/share_charge", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "订单类型不能为空")
    private String type;

    @Schema(description = "总金额")
    private BigDecimal totalAmount;

    @Schema(description = "实付金额")
    private BigDecimal payAmount;

    @Schema(description = "订单状态（all_order_status）：pending_pay/paid/completed/cancelled")
    private String status;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "备注")
    private String remark;
}
