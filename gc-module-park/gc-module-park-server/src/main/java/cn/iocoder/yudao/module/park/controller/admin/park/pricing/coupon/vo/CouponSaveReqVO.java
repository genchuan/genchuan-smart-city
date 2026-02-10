package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券新增/修改 Request VO")
@Data
public class CouponSaveReqVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "29954")
    private Long id;

    @Schema(description = "[优惠券码]")
    private String couponCode;

    @Schema(description = "[持有者ID] 关联 park_user.id", example = "1")
    private Long holderId;

    @Schema(description = "[优惠券名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "新用户满10减5券")
    @NotEmpty(message = "[优惠券名称]不能为空")
    private String couponName;

    @Schema(description = "[优惠券类型] 如:满减券/折扣券/免费时长券", example = "满减券")
    private String couponType;

    @Schema(description = "[面值/折扣比例]")
    private String faceValue;

    @Schema(description = "[最低消费金额]")
    private BigDecimal minConsume;

    @Schema(description = "[有效天数]")
    private Integer validDays;

    @Schema(description = "[生效时间]")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间]")
    private LocalDateTime endTime;

    @Schema(description = "[适用范围] 如:全局/区域/车场")
    private String applyScope;

    @Schema(description = "[适用范围ID列表] JSON格式varchar")
    private String scopeIds;

    @Schema(description = "[状态] 如:启用/禁用/已使用/已过期", example = "2")
    private String status;

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
