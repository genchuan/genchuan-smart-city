package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 临时停车订单新增/修改 Request VO")
@Data
public class TempParkOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "主订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主订单不能为空")
    private Long orderId;

    @Schema(description = "车位ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "车位不能为空")
    private Long spaceId;

    @Schema(description = "入场时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "入场时间不能为空")
    private LocalDateTime inTime;

    @Schema(description = "离场时间")
    private LocalDateTime outTime;

    @Schema(description = "停车时长（小时）")
    private BigDecimal parkHour;

    @Schema(description = "停车费用")
    private BigDecimal fee;

    @Schema(description = "备注")
    private String remark;
}
