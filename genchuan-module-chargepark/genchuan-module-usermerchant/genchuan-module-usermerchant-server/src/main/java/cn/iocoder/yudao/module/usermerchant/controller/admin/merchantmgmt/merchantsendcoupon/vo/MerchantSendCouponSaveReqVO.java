package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantsendcoupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户发券新增/修改 Request VO")
@Data
public class MerchantSendCouponSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11496")
    private Long id;

    @Schema(description = "商户ID，关联merchant_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14294")
    @NotNull(message = "商户ID，关联merchant_info.id不能为空")
    private Long merchantId;

    @Schema(description = "优惠券ID，关联营销模块优惠券表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1926")
    @NotNull(message = "优惠券ID，关联营销模块优惠券表不能为空")
    private Long couponId;

    @Schema(description = "优惠券名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "优惠券名称不能为空")
    private String couponName;

    @Schema(description = "发放数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5201")
    @NotNull(message = "发放数量不能为空")
    private Integer sendCount;

    @Schema(description = "执行时间")
    private LocalDateTime execTime;

    @Schema(description = "发券完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "已核销数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "31192")
    @NotNull(message = "已核销数量不能为空")
    private Integer useCount;

    @Schema(description = "发券状态：待执行/已执行/已取消", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "发券状态：待执行/已执行/已取消不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}