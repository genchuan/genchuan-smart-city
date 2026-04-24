package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 商户发券 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MerchantSendCouponRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11496")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "商户ID，关联merchant_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14294")
    @ExcelProperty("商户ID，关联merchant_info.id")
    private Long merchantId;

    @Schema(description = "优惠券ID，关联营销模块优惠券表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1926")
    @ExcelProperty("优惠券ID，关联营销模块优惠券表")
    private Long couponId;

    @Schema(description = "优惠券名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("优惠券名称")
    private String couponName;

    @Schema(description = "发放数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5201")
    @ExcelProperty("发放数量")
    private Integer sendCount;

    @Schema(description = "执行时间")
    @ExcelProperty("执行时间")
    private LocalDateTime execTime;

    @Schema(description = "发券完成时间")
    @ExcelProperty("发券完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "已核销数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "31192")
    @ExcelProperty("已核销数量")
    private Integer useCount;

    @Schema(description = "发券状态：待执行/已执行/已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("发券状态：待执行/已执行/已取消")
    private String status;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}