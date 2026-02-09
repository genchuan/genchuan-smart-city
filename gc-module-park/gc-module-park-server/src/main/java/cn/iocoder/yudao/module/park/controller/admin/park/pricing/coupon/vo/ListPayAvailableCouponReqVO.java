package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 获取支付可用优惠券列表 VO")
@Data
public class ListPayAvailableCouponReqVO {
    @Schema(description = "[初始总金额]", example = "4447.0")
    private BigDecimal orginalAmount;

    @Schema(description = "[订单记录Id列表(字符串形式)]", example = "1,2,3")
    private String orderIdListStr;

//    @Schema(description = "[车牌号]", example = "京123456") //车牌号用于判断是否白名单
//    private String carNumber;

    @Schema(description = "[停车场ID]", example = "1") //车牌号用于判断是否白名单
    private Long parkLotId;
}
