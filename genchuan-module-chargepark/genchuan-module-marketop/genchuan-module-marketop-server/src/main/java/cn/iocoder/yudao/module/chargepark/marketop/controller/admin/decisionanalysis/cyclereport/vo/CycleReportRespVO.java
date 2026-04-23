package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表 Response VO")
@Data
public class CycleReportRespVO {

    @Schema(description = "报表主键ID")
    private Long id;

    @Schema(description = "报表周期")
    private String reportCycle;

    @Schema(description = "统计时段")
    private String statTime;

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
    private BigDecimal couponVerifyRate;

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

    @Schema(description = "报表生成状态")
    private String generateStatus;

    @Schema(description = "报表生成时间")
    private LocalDateTime generateTime;

    @Schema(description = "操作人")
    private String operator;

    @Schema(description = "生成耗时(ms)")
    private Integer generateCost;

    @Schema(description = "报表导出次数")
    private Integer exportCount;

    @Schema(description = "筛选规则")
    private String filterRule;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
