package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-优惠券发放量 Response VO")
@Data
public class CycleReportDrillCouponSendCountRespVO {

    @Schema(description = "优惠券ID")
    private Long id;
    @Schema(description = "券名称")
    private String name;
    @Schema(description = "券类型")
    private String type;
    @Schema(description = "面额")
    private BigDecimal amount;
    @Schema(description = "发放时间")
    private LocalDateTime sendTime;
    @Schema(description = "发放人ID")
    private Long senderId;
    @Schema(description = "发放人姓名")
    private String senderName;
    @Schema(description = "领取人ID")
    private Long receiverId;
    @Schema(description = "领取人姓名")
    private String receiverName;
    @Schema(description = "券状态")
    private String status;
    @Schema(description = "租户ID")
    private Long tenantId;

}
