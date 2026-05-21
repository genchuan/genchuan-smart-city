package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠券分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CouponMgmtPageReqVO extends PageParam {

    @Schema(description = "券名称")
    private String name;

    @Schema(description = "券类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "面额")
    private BigDecimal amount;

//    @Schema(description = "有效期开始时间")
//    private Long validStartTime;
//
//    @Schema(description = "有效期结束时间")
//    private Long validEndTime;

    @Schema(description = "日期筛选，格式如：2026-04-24")
    private String date;

//    @Schema(description = "创建开始时间")
//    private Long startTime;
//
//    @Schema(description = "创建结束时间")
//    private Long endTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "有效时间时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] validTime;

    @Schema(description = "发放时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] sendTime;

    @Schema(description = "核销时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] verifyTime;

}
