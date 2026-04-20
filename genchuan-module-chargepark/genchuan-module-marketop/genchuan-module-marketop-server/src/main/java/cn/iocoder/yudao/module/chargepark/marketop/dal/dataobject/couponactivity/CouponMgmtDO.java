package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("coupon_mgmt")
@KeySequence("coupon_mgmt_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponMgmtDO extends BaseDO {

    @TableId
    private Long id;
    private String name;
    /** 券类型(满减/折扣/时长/立减) */
    private String type;
    /** 面额 */
    private BigDecimal amount;
    /** 使用条件 */
    private String useCondition;
    /** 状态(未领取/已领取/已使用/已过期) */
    private String status;
    /** 发放人 */
    private Long senderId;
    /** 发放时间 */
    private LocalDateTime sendTime;
    /** 领取人 */
    private Long receiverId;
    /** 核销时间 */
    private LocalDateTime verifyTime;
    /** 有效期 */
    private LocalDateTime validTime;
    /** 券描述 */
    private String description;
    /** 适用场站 */
    private String stationIds;
    private String reserve1;
    private String reserve2;

}
