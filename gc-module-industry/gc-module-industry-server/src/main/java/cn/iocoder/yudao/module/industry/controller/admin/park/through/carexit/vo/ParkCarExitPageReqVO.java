package cn.iocoder.yudao.module.industry.controller.admin.park.through.carexit.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 离场记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkCarExitPageReqVO extends PageParam {

    @Schema(description = "离场记录ID（UUID）", example = "22619")
    private String exitId;

    @Schema(description = "入场记录ID", example = "11110")
    private String entryId;

    @Schema(description = "车牌")
    private String carNumber;

    @Schema(description = "离场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitTime;

    @Schema(description = "离场出入口ID", example = "20426")
    private String exitExitId;

    @Schema(description = "所属车场ID", example = "18494")
    private String lotId;

    @Schema(description = "停放时长（分钟）")
    private Integer parkingDuration;

    @Schema(description = "应缴费用")
    private BigDecimal feeAmount;

    @Schema(description = "实缴费用")
    private BigDecimal actualPayAmount;

    @Schema(description = "缴费状态：未缴费/已缴费/部分缴费", example = "2")
    private String payStatus;

    @Schema(description = "缴费记录ID", example = "18990")
    private String paymentId;

    @Schema(description = "离场类型：正常/异常/特殊放行", example = "2")
    private String exitType;

    @Schema(description = "异常原因", example = "不香")
    private String abnormalReason;

    @Schema(description = "识别设备")
    private String deviceCode;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] exitUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String exitRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}