package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠活动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkDiscountActivityPageReqVO extends PageParam {

    @Schema(description = "优惠活动唯一编号")
    private String discountActivityNo;

    @Schema(description = "优惠活动名称", example = "芋艿")
    private String activityName;

    @Schema(description = "优惠活动类型", example = "1")
    private String discountType;

    @Schema(description = "优惠计算或使用规则")
    private String discountRule;

    @Schema(description = "单笔立减金额")
    private BigDecimal discountAmount;

    @Schema(description = "活动状态（0-停用，1-进行中）", example = "2")
    private Integer status;

    @Schema(description = "活动累计优惠金额")
    private BigDecimal totalDiscountAmount;

    @Schema(description = "参与活动的订单数量", example = "23673")
    private Integer participateOrderCount;

    @Schema(description = "记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
