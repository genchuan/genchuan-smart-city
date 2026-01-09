package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 期卡订单新增/修改 Request VO")
@Data
public class ParkOrderPeriodSaveReqVO {

    @Schema(description = "主键ID，自增ID，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "31187")
    private Long id;

    @Schema(description = "唯一订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "唯一订单编号不能为空")
    private String orderNo;

    @Schema(description = "用户ID，关联 sys_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "6297")
    @NotNull(message = "用户ID，关联 sys_user.id不能为空")
    private Long userId;

    @Schema(description = "期卡套餐ID，关联 park_period_package.package_id", requiredMode = Schema.RequiredMode.REQUIRED, example = "7841")
    @NotNull(message = "期卡套餐ID，关联 park_period_package.package_id不能为空")
    private Long periodCardId;

    @Schema(description = "适用车场ID列表，JSON 格式")
    private String lotIds;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生效时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "失效时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "卡状态（卡状态：未激活/已激活/已过期/已注销）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "卡状态（卡状态：未激活/已激活/已过期/已注销）不能为空")
    private String cardStatus;

    @Schema(description = "原始金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "原始金额不能为空")
    private BigDecimal originalAmount;

    @Schema(description = "优惠金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "优惠金额不能为空")
    private BigDecimal discountAmount;

    @Schema(description = "实付金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "实付金额不能为空")
    private BigDecimal payAmount;

    @Schema(description = "订单状态（状态：待支付/已支付/已取消/已退款）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "订单状态（状态：待支付/已支付/已取消/已退款）不能为空")
    private String orderStatus;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "取消时间")
    private LocalDateTime cancelTime;

    @Schema(description = "退款时间")
    private LocalDateTime refundTime;

    @Schema(description = "是否续费，0-否，1-是", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否续费，0-否，1-是不能为空")
    private Boolean renewFlag;

    @Schema(description = "上一订单ID，续费关联", example = "1784")
    private Long prevOrderId;

    @Schema(description = "备注信息", example = "你说的对")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}
