package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款记录新增/修改 Request VO")
@Data
public class RefundRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "申请ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款申请不能为空")
    private Long applyId;

    @Schema(description = "退款时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款时间不能为空")
    private LocalDateTime refundTime;

    @Schema(description = "状态：success/failed")
    private String status;

    @Schema(description = "备注")
    private String remark;
}
