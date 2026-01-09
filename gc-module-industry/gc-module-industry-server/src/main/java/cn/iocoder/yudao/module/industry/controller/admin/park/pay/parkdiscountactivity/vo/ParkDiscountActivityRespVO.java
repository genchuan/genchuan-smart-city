package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 优惠活动 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkDiscountActivityRespVO {

    @Schema(description = "主键，记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24770")
    @ExcelProperty("主键，记录唯一标识")
    private Long id;

    @Schema(description = "优惠活动唯一编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("优惠活动唯一编号")
    private String discountActivityNo;

    @Schema(description = "优惠活动名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("优惠活动名称")
    private String activityName;

    @Schema(description = "优惠活动类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("优惠活动类型")
    private String discountType;

    @Schema(description = "优惠计算或使用规则")
    @ExcelProperty("优惠计算或使用规则")
    private String discountRule;

    @Schema(description = "单笔立减金额")
    @ExcelProperty("单笔立减金额")
    private BigDecimal discountAmount;

    @Schema(description = "活动状态（0-停用，1-进行中）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("活动状态（0-停用，1-进行中）")
    private Integer status;

    @Schema(description = "活动累计优惠金额")
    @ExcelProperty("活动累计优惠金额")
    private BigDecimal totalDiscountAmount;

    @Schema(description = "参与活动的订单数量", example = "23673")
    @ExcelProperty("参与活动的订单数量")
    private Integer participateOrderCount;

    @Schema(description = "记录创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

}
