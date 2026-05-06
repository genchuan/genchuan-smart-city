package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-中奖率 Response VO")
@Data
public class CycleReportChartCardDrillWinningRateRespVO {

    @Schema(description = "抽奖记录ID，关联积分抽奖表point_lottery")
    private Long lotteryId;

    @Schema(description = "抽奖记录编号，关联point_lottery.no")
    private String lotteryNo;

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "抽奖时间，关联point_lottery.lottery_time")
    private LocalDateTime lotteryTime;

    @Schema(description = "奖品ID，关联奖品管理表prize_mgmt")
    private Long prizeId;

    @Schema(description = "奖品名称，关联prize_mgmt.name")
    private String prizeName;

    @Schema(description = "奖品类型（实物/虚拟/优惠券/卡种，关联芋道字典表：prize_mgmt_type）")
    private String prizeType;

    @Schema(description = "发放时间，关联point_lottery.send_time")
    private LocalDateTime sendTime;

    @Schema(description = "租户ID")
    private Long tenantId;

}
