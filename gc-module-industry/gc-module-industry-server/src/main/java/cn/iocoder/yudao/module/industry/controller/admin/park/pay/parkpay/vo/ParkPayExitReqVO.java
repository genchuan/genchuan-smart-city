package cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "停车管理 - 停车缴费服务 - 出场 Request VO")
@Data
public class ParkPayExitReqVO {
    @Schema(description = "缴费记录 主键", example = "1")
    private Long parkPayId;



    @Schema(description = "优惠活动 主键", example = "1")
    private Long discountActivityId;
}
