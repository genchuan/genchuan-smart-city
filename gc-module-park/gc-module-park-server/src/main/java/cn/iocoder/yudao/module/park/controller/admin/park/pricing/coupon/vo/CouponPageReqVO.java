package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

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
public class CouponPageReqVO extends PageParam {

    @Schema(description = "[优惠券名称]", example = "赵六")
    private String couponName;

    @Schema(description = "[优惠券码] 唯一优惠券码")
    private String couponCode;

    @Schema(description = "[优惠券类型] 如:满减券/折扣券/免费时长券", example = "1")
    private String couponType;

    @Schema(description = "[面值/折扣比例]")
    private String faceValue;

    @Schema(description = "[最低消费金额] 满减券必填")
    private BigDecimal minConsume;

    @Schema(description = "[有效天数]")
    private Integer validDays;

    @Schema(description = "[生效时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "[失效时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "[适用范围] 如:全局/区域/车场")
    private String applyScope;

    @Schema(description = "[适用范围ID列表] JSON格式varchar")
    private String scopeIds;

    @Schema(description = "[领取次数]", example = "9619")
    private Integer getCount;

    @Schema(description = "[使用次数]", example = "32176")
    private Integer useCount;

    @Schema(description = "[状态] 如:启用/禁用/已过期", example = "2")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注]", example = "随便")
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
