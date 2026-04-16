package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 错时停车订单新增/修改 Request VO")
@Data
public class OfftimeParkOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    private String orderNo;
    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    private String plateNo;
    @Schema(description = "预约开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime reserveStartTime;
    @Schema(description = "预约结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime reserveEndTime;
    @Schema(description = "实际开始时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime actualStartTime;
    @Schema(description = "实际结束时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime actualEndTime;
    @Schema(description = "订单金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal amount;
    @Schema(description = "支付状态", requiredMode = Schema.RequiredMode.REQUIRED)
    private String status;
    @Schema(description = "订单生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createOrderTime;
    @Schema(description = "所属场站ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long stationId;
    @Schema(description = "支付时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime payTime;
    @Schema(description = "支付方式", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String payMethod;
    @Schema(description = "归档时间", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime archiveTime;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
