package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-报表周期 Response VO")
@Data
public class CycleReportDrillReportCycleRespVO {

    @Schema(description = "报表ID")
    private Long id;
    @Schema(description = "报表周期")
    private String reportCycle;
    @Schema(description = "统计周期")
    private String statCycle;
    @Schema(description = "时间尺度")
    private String timeScale;
    @Schema(description = "生成时间")
    private LocalDateTime createTime;
    @Schema(description = "报表生成状态")
    private String reportStatus;
    @Schema(description = "操作人")
    private String operator;
    @Schema(description = "活动数")
    private Integer activityCount;
    @Schema(description = "参与用户数")
    private Integer joinUserCount;
    @Schema(description = "抽奖量")
    private Integer lotteryCount;
    @Schema(description = "中奖率")
    private BigDecimal winningRate;
    @Schema(description = "优惠券发放量")
    private Integer couponSendCount;
    @Schema(description = "核销率")
    private BigDecimal verifyRate;
    @Schema(description = "卡种订单量")
    private Integer cardOrderCount;
    @Schema(description = "营收")
    private BigDecimal revenue;
    @Schema(description = "兑换量")
    private Integer exchangeCount;
    @Schema(description = "总库存")
    private Integer totalStock;
    @Schema(description = "预警库存数")
    private Integer warnStockCount;
    @Schema(description = "租户ID")
    private Long tenantId;

}
