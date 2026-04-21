package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付记录新增/修改 Request VO")
@Data
public class AgentRecordSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "关联订单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联订单ID不能为空")
    private Long orderId;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "变动金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "变动金额不能为空")
    private BigDecimal amount;

    @Schema(description = "交易时间")
    private LocalDateTime tradeTime;

    @Schema(description = "状态：normal/abnormal")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
