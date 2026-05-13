package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表图表卡片钻取-抽奖量 Response VO")
@Data
public class CycleReportChartCardDrillLotteryCountRespVO {

    @Schema(description = "抽奖记录ID，关联积分抽奖表point_lottery")
    private Long id;

    @Schema(description = "抽奖记录编号，关联point_lottery.no")
    private String no;

    @Schema(description = "用户ID，关联芋道用户表system_user")
    private Long userId;

    @Schema(description = "用户名，关联system_user.username")
    private String userName;

    @Schema(description = "抽奖时间，关联point_lottery.lottery_time")
    private LocalDateTime lotteryTime;

    @Schema(description = "消耗积分，关联point_lottery.cost_point")
    private Integer costPoint;

    @Schema(description = "记录状态（正常记录/异常记录/已核查，关联芋道字典表：point_lottery_status）")
    private String status;

    @Schema(description = "租户ID，关联point_lottery.tenant_id")
    private Long tenantId;

}
