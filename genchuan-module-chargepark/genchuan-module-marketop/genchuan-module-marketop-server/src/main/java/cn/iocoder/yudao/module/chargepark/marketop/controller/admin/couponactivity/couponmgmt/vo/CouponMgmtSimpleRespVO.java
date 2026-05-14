package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 优惠券精简信息 Response VO")
@Data
public class CouponMgmtSimpleRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "优惠券名称")
    private String name;

}
