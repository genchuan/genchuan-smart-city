package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-核销率 Response VO")
@Data
public class CycleReportDrillVerifyRateRespVO {

    @Schema(description = "优惠券ID")
    private Long couponId;
    @Schema(description = "券名称")
    private String couponName;
    @Schema(description = "券类型")
    private String couponType;
    @Schema(description = "面额")
    private BigDecimal amount;
    @Schema(description = "领取人ID")
    private Long receiverId;
    @Schema(description = "领取人姓名")
    private String receiverName;
    @Schema(description = "核销时间")
    private LocalDateTime verifyTime;
    @Schema(description = "核销人ID")
    private Long verifyPersonId;
    @Schema(description = "核销人姓名")
    private String verifyPersonName;
    @Schema(description = "租户ID")
    private Long tenantId;

}
