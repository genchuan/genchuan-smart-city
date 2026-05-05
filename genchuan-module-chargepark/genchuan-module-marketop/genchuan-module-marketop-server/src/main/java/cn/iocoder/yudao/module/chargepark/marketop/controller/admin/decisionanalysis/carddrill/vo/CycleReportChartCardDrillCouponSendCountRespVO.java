package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-优惠券发放量 Response VO")
@Data
public class CycleReportChartCardDrillCouponSendCountRespVO {

    @Schema(description = "优惠券ID，关联优惠券表coupon_mgmt")
    private Long id;

    @Schema(description = "券名称，关联coupon_mgmt.name")
    private String name;

    @Schema(description = "券类型（满减/折扣/时长/立减，关联芋道字典表：coupon_mgmt_type）")
    private String type;

    @Schema(description = "面额，关联coupon_mgmt.amount")
    private BigDecimal amount;

    @Schema(description = "发放时间，关联coupon_mgmt.send_time")
    private LocalDateTime sendTime;

    @Schema(description = "发放人ID，关联芋道用户表system_user")
    private Long senderId;

    @Schema(description = "发放人姓名，关联system_user.real_name")
    private String senderName;

    @Schema(description = "领取人ID，关联system_user.id")
    private Long receiverId;

    @Schema(description = "领取人姓名，关联system_user.real_name")
    private String receiverName;

    @Schema(description = "券状态（未领取/已领取/已使用/已过期，关联芋道字典表：coupon_mgmt_status）")
    private String status;

    @Schema(description = "租户ID，关联coupon_mgmt.tenant_id")
    private Long tenantId;

}
