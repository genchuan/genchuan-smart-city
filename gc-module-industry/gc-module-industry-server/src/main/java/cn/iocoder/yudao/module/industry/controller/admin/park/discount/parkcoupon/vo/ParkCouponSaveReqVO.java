package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkcoupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券新增/修改 Request VO")
@Data
public class ParkCouponSaveReqVO {

    @Schema(description = "[主键ID] 优惠券唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "29701")
    private Long id;

    @Schema(description = "[优惠券码] 全局唯一优惠券码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[优惠券码] 全局唯一优惠券码不能为空")
    private String couponCode;

    @Schema(description = "[优惠券名称] 优惠券名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "[优惠券名称] 优惠券名称不能为空")
    private String couponName;

    @Schema(description = "[优惠券类型] 满减券 / 折扣券 / 免费时长券 / 充值券", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[优惠券类型] 满减券 / 折扣券 / 免费时长券 / 充值券不能为空")
    private String couponType;

    @Schema(description = "[适用范围类型] 全局/车场", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[适用范围类型] 全局/车场不能为空")
    private String applyScopeType;

    @Schema(description = "[适用范围值] 车场ID列表，英文逗号分隔，如1,2")
    private String applyScopeValue;

    @Schema(description = "[面值/折扣比例] 优惠券面值或折扣比例")
    private BigDecimal faceValue;

    @Schema(description = "[最低消费金额] 仅满减券 / 折扣券适用")
    private BigDecimal minConsume;

    @Schema(description = "[免费时长] 单位：分钟，仅免费时长券适用")
    private Integer freeTime;

    @Schema(description = "[生效时间] 优惠券生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[生效时间] 优惠券生效时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间] 优惠券失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "[失效时间] 优惠券失效时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "[状态] 未发放 / 已发放 / 已使用 / 已过期 / 已作废", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[状态] 未发放 / 已发放 / 已使用 / 已过期 / 已作废不能为空")
    private String status;

    @Schema(description = "[用户ID] 定向发放用户，关联 park_user.id", example = "14992")
    private Long userId;

    @Schema(description = "[活动ID] 关联 park_promotion.promotion_id", example = "25381")
    private Long promotionId;

    @Schema(description = "[领取时间] 优惠券领取时间")
    private LocalDateTime getTime;

    @Schema(description = "[使用时间] 优惠券使用时间")
    private LocalDateTime useTime;

    @Schema(description = "[使用订单ID] 关联订单ID", example = "29421")
    private Long useOrderId;

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
