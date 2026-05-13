package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-核销率 Response VO")
@Data
public class CycleReportChartCardDrillVerifyRateRespVO {

    @Schema(description = "优惠券ID，关联优惠券表coupon_mgmt")
    private Long couponId;

    @Schema(description = "券名称，关联coupon_mgmt.name")
    private String couponName;

    @Schema(description = "券类型（满减/折扣/时长/立减，关联芋道字典表：coupon_mgmt_type）")
    private String couponType;

    @Schema(description = "面额，关联coupon_mgmt.amount")
    private BigDecimal amount;

    @Schema(description = "领取人ID，关联芋道用户表system_user")
    private Long receiverId;

    @Schema(description = "领取人姓名，关联system_user.real_name")
    private String receiverName;

    @Schema(description = "核销时间，关联coupon_mgmt.verify_time")
    private LocalDateTime verifyTime;

    @Schema(description = "核销人ID，关联system_user.id")
    private Long verifyPersonId;

    @Schema(description = "核销人姓名，关联system_user.real_name")
    private String verifyPersonName;

    @Schema(description = "租户ID")
    private Long tenantId;

}
