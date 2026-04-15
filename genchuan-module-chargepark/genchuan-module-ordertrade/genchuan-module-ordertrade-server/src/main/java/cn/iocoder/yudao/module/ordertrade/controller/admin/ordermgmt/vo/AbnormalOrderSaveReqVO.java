package cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 异常订单新增/修改 Request VO")
@Data
public class AbnormalOrderSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "订单不能为空")
    private Long orderId;

    @Schema(description = "异常原因", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "异常原因不能为空")
    private String reason;

    @Schema(description = "发生时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "发生时间不能为空")
    private LocalDateTime happenTime;

    @Schema(description = "状态：unhandled/handling/closed")
    private String status;

    @Schema(description = "处置人ID")
    private Long handlerId;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置结果")
    private String handleResult;

    @Schema(description = "备注")
    private String remark;
}
