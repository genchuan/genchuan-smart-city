package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CouponRespVO {

    @Schema(description = "[主键ID]", requiredMode = Schema.RequiredMode.REQUIRED, example = "19142")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[优惠券名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("[优惠券名称]")
    private String couponName;

    @Schema(description = "[优惠券码] 唯一优惠券码")
    @ExcelProperty("[优惠券码] 唯一优惠券码")
    private String couponCode;

    @Schema(description = "[优惠券类型] 如:满减券/折扣券/免费时长券", example = "1")
    @ExcelProperty("[优惠券类型] 如:满减券/折扣券/免费时长券")
    private String couponType;

    @Schema(description = "[面值/折扣比例]")
    @ExcelProperty("[面值/折扣比例]")
    private String faceValue;

    @Schema(description = "[最低消费金额] 满减券必填")
    @ExcelProperty("[最低消费金额] 满减券必填")
    private BigDecimal minConsume;

    @Schema(description = "[有效天数]")
    @ExcelProperty("[有效天数]")
    private Integer validDays;

    @Schema(description = "[生效时间]")
    @ExcelProperty("[生效时间]")
    private LocalDateTime startTime;

    @Schema(description = "[失效时间]")
    @ExcelProperty("[失效时间]")
    private LocalDateTime endTime;

    @Schema(description = "[适用范围] 如:全局/区域/车场")
    @ExcelProperty("[适用范围] 如:全局/区域/车场")
    private String applyScope;

    @Schema(description = "[适用范围ID列表] JSON格式varchar")
    @ExcelProperty("[适用范围ID列表] JSON格式varchar")
    private String scopeIds;

    @Schema(description = "[领取次数]", example = "9619")
    @ExcelProperty("[领取次数]")
    private Integer getCount;

    @Schema(description = "[使用次数]", example = "32176")
    @ExcelProperty("[使用次数]")
    private Integer useCount;

    @Schema(description = "[状态] 如:启用/禁用/已过期", example = "2")
    @ExcelProperty("[状态] 如:启用/禁用/已过期")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注]", example = "随便")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
