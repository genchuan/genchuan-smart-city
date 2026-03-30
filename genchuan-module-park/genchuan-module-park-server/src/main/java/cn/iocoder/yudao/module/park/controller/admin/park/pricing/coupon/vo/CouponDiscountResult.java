package cn.iocoder.yudao.module.park.controller.admin.park.pricing.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//优惠卷计算金额返回参数
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponDiscountResult {

    /** 是否可用 */
    private boolean usable;

    /** 不可用原因 */
    private String reason;

    /** 优惠金额 */
    private BigDecimal discountAmount;

    /** 优惠后金额 */
    private BigDecimal afterDiscountAmount;

//    public CouponDiscountResult(boolean usable, String reason, BigDecimal zero, Object o) {
//
//    }

    public static CouponDiscountResult fail(boolean usable ,String reason){
        return new CouponDiscountResult(usable,reason,BigDecimal.ZERO,null);
    }
}
