package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 金额核算新增/修改 Request VO")
@Data
public class AmountCheckSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "核算编号", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String checkNo;
    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long orderId;
    @Schema(description = "申请金额", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal applyAmount;
    @Schema(description = "核算结果", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String checkResult;
    @Schema(description = "核算明细", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String checkDetail;
    @Schema(description = "状态", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String status;
    @Schema(description = "操作人ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long operatorId;
}
