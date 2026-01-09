package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款订单新增/修改 Request VO")
@Data
public class ParkOrderRefundSaveReqVO {

    @Schema(description = "主键ID，自增ID，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "15297")
    private Long id;

    @Schema(description = "唯一退款单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "唯一退款单号不能为空")
    private String refundNo;

    @Schema(description = "关联退款的原订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1316")
    @NotNull(message = "关联退款的原订单ID不能为空")
    private Long originalOrderId;

    @Schema(description = "原订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "原订单编号不能为空")
    private String originalOrderNo;

    @Schema(description = "退款申请人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30440")
    @NotNull(message = "退款申请人ID不能为空")
    private Long userId;

    @Schema(description = "退款金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款金额不能为空")
    private BigDecimal refundAmount;

    @Schema(description = "退款原因", example = "不对")
    private String refundReason;

    @Schema(description = "退款状态：申请中/已通过/已拒绝/已到账", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "退款状态：申请中/已通过/已拒绝/已到账不能为空")
    private String refundStatus;

    @Schema(description = "退款申请时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "退款申请时间不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核人")
    private String auditBy;

    @Schema(description = "到账时间")
    private LocalDateTime transferTime;

    @Schema(description = "备注信息", example = "你猜")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
