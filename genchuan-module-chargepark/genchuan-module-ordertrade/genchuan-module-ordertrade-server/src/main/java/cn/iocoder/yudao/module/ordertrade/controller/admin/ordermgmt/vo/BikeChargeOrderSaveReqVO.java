package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 两轮充电订单新增/修改 Request VO")
@Data
public class BikeChargeOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "主订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主订单不能为空")
    private Long orderId;

    @Schema(description = "设备ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "设备不能为空")
    private Long deviceId;

    @Schema(description = "开始充电时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "结束充电时间")
    private LocalDateTime endTime;

    @Schema(description = "充电度数（kWh）")
    private BigDecimal chargeDegree;

    @Schema(description = "费用")
    private BigDecimal fee;

    @Schema(description = "备注")
    private String remark;
}
