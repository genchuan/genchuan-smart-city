package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-抽奖量 Response VO")
@Data
public class CycleReportDrillLotteryCountRespVO {

    @Schema(description = "抽奖记录ID")
    private Long id;
    @Schema(description = "抽奖记录编号")
    private String no;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "抽奖时间")
    private LocalDateTime lotteryTime;
    @Schema(description = "消耗积分")
    private Integer costPoint;
    @Schema(description = "记录状态")
    private String status;
    @Schema(description = "租户ID")
    private Long tenantId;

}
