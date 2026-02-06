package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 优惠券使用前折扣 Resp VO")
@Data
public class PreDiscountAutoCalculateRespVO {
    @Schema(description = "[优惠券使用前的结算金额]", example = "4447.0")
    BigDecimal preDiscountAmount;

    @Schema(description = "优惠原因说明", example = "白名单车辆免缴费用")
    String discountReasonDesc;
}
