package cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 退款订单新增/修改 Request VO")
@Data
public class OrderRefundSaveReqVO {

    @Schema(description = "[主键ID] 退款订单唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "17057")
    private Long id;

    @Schema(description = "[原订单ID] 关联原订单ID，park_order_temp.id 或 park_order_period.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "13463")
    @NotNull(message = "[原订单ID] 关联原订单ID，park_order_temp.id 或 park_order_period.id不能为空")
    private Long originalOrderId;

    @Schema(description = "[原订单类型] 如:临停/期卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[原订单类型] 如:临停/期卡不能为空")
    private String orderType;

    @Schema(description = "[退款金额] 退款金额")
    private BigDecimal refundAmount;

    @Schema(description = "[退款原因] 退款原因说明", example = "不香")
    private String refundReason;

    @Schema(description = "[佐证材料] 退款佐证材料，JSON 格式，varchar 存储")
    private String proofFiles;

    @Schema(description = "[申请人ID] 申请人ID，关联 park_user.id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[申请人ID] 申请人ID，关联 park_user.id不能为空")
    private Long applyBy;

    @Schema(description = "[联系电话] 申请人联系电话")
    private String contactPhone;

    @Schema(description = "[审批状态] 如:待审批/已审批/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[审批状态] 如:待审批/已审批/已驳回不能为空")
    private String approveStatus;

    @Schema(description = "[退款状态] 如:待退款/退款中/已到账/退款失败", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[退款状态] 如:待退款/退款中/已到账/退款失败不能为空")
    private String refundStatus;

    @Schema(description = "[退款时间] 实际退款时间，可为 NULL")
    private LocalDateTime refundTime;

    @Schema(description = "[到账时间] 退款到账时间，可为 NULL")
    private LocalDateTime arrivalTime;

    @Schema(description = "[备注] 退款订单相关备注说明", example = "你猜")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
