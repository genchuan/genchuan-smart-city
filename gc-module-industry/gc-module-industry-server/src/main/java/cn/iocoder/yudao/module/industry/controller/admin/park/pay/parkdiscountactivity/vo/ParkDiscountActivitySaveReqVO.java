package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 优惠活动新增/修改 Request VO")
@Data
public class ParkDiscountActivitySaveReqVO {

    @Schema(description = "主键，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24770")
    private Long id;

    @Schema(description = "优惠活动唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "优惠活动唯一编号不能为空")
    private String discountActivityNo;

    @Schema(description = "优惠活动名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "优惠活动名称不能为空")
    private String activityName;

    @Schema(description = "优惠活动类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "优惠活动类型不能为空")
    private String discountType;

    @Schema(description = "优惠计算或使用规则")
    private String discountRule;

    @Schema(description = "单笔立减金额")
    private BigDecimal discountAmount;

    @Schema(description = "活动状态（0-停用，1-进行中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "活动状态（0-停用，1-进行中）不能为空")
    private Integer status;

    @Schema(description = "活动累计优惠金额")
    private BigDecimal totalDiscountAmount;

    @Schema(description = "参与活动的订单数量", example = "23673")
    private Integer participateOrderCount;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
