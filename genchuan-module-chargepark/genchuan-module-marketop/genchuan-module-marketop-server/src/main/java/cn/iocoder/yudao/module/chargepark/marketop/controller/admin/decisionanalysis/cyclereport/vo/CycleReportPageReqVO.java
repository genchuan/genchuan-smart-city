package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 周期报表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CycleReportPageReqVO extends PageParam {

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    private String reportCycle;

    @Schema(description = "统计开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statStartTime;

    @Schema(description = "统计结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statEndTime;

    @Schema(description = "生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "报表生成状态（已生成/生成中/生成失败）")
    private String generateStatus;

    @Schema(description = "租户ID")
    private Long tenantId;

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

    @Schema(description = "筛选规则")
    private String filterRule;

}
