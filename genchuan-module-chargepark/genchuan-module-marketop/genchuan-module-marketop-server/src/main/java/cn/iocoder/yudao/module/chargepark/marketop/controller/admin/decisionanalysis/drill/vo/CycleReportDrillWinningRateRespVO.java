package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表钻取-中奖率 Response VO")
@Data
public class CycleReportDrillWinningRateRespVO {

    @Schema(description = "抽奖记录ID")
    private Long lotteryId;
    @Schema(description = "抽奖记录编号")
    private String lotteryNo;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "抽奖时间")
    private LocalDateTime lotteryTime;
    @Schema(description = "奖品ID")
    private Long prizeId;
    @Schema(description = "奖品名称")
    private String prizeName;
    @Schema(description = "奖品类型")
    private String prizeType;
    @Schema(description = "发放时间")
    private LocalDateTime sendTime;
    @Schema(description = "租户ID")
    private Long tenantId;

}
