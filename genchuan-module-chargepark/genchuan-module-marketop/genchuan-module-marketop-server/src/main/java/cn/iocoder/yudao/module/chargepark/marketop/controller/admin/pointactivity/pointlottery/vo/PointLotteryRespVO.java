package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 积分抽奖 Response VO")
@Data
public class PointLotteryRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "抽奖记录编号")
    private String no;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "奖品ID")
    private Long prizeId;

    @Schema(description = "奖品名称")
    private String prizeName;

    @Schema(description = "抽奖时间")
    private LocalDateTime lotteryTime;

    @Schema(description = "消耗积分")
    private Integer costPoint;

    @Schema(description = "记录状态")
    private String status;

    @Schema(description = "发放时间")
    private LocalDateTime sendTime;

    @Schema(description = "发放人")
    private Long senderId;

    @Schema(description = "发放人名称")
    private String senderName;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "同步状态")
    private String syncStatus;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
