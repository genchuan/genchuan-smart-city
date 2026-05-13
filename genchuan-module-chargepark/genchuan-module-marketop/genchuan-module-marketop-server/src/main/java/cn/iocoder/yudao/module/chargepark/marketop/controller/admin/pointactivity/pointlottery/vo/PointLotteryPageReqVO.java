package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 积分抽奖分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PointLotteryPageReqVO extends PageParam {

    @Schema(description = "抽奖记录编号")
    private String no;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "奖品ID")
    private Long prizeId;

    @Schema(description = "记录状态")
    private String status;

    @Schema(description = "同步状态")
    private String syncStatus;

    @Schema(description = "发放人")
    private Long senderId;

    @Schema(description = "核查结果")
    private String checkResult;

    @Schema(description = "抽奖时间")
    private String lotteryTime;

    @Schema(description = "开始时间")
    private Long startTime;

    @Schema(description = "结束时间")
    private Long endTime;

    @Schema(description = "发放时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;
}
