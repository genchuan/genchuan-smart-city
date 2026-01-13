package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 优惠券分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkCouponPageReqVO extends PageParam {

    @Schema(description = "[优惠券码] 全局唯一优惠券码")
    private String couponCode;

    @Schema(description = "[优惠券名称] 优惠券名称", example = "张三")
    private String couponName;

    @Schema(description = "[优惠券类型] 满减券 / 折扣券 / 免费时长券 / 充值券", example = "1")
    private String couponType;

    @Schema(description = "[面值/折扣比例] 优惠券面值或折扣比例")
    private BigDecimal faceValue;

    @Schema(description = "[最低消费金额] 仅满减券 / 折扣券适用")
    private BigDecimal minConsume;

    @Schema(description = "[免费时长] 单位：分钟，仅免费时长券适用")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] freeTime;

    @Schema(description = "[适用范围] JSON 格式")
    private String applyScope;

    @Schema(description = "[生效时间] 优惠券生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[失效时间] 优惠券失效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[状态] 未发放 / 已发放 / 已使用 / 已过期 / 已作废", example = "1")
    private String status;

    @Schema(description = "[用户ID] 定向发放用户，关联 park_user.id", example = "15387")
    private Long userId;

    @Schema(description = "[活动ID] 关联 park_promotion.promotion_id", example = "9082")
    private Long promotionId;

    @Schema(description = "[领取时间] 优惠券领取时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] getTime;

    @Schema(description = "[使用时间] 优惠券使用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] useTime;

    @Schema(description = "[使用订单ID] 关联订单ID", example = "19275")
    private Long useOrderId;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 优惠券相关备注说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
