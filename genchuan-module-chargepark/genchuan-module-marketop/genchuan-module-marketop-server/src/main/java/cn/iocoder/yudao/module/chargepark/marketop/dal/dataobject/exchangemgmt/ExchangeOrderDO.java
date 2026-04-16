package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;

@TableName("exchange_order")
@KeySequence("exchange_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeOrderDO extends BaseDO {

    @TableId
    private Long id;
    /** 主订单ID */
    private Long orderId;
    /** 类目ID */
    private Long categoryId;
    /** 用户ID */
    private Long userId;
    /** 金额 */
    private BigDecimal amount;
    /** 订单状态（待支付/已支付/已完成/已取消） */
    private String status;
    /** 备注 */
    private String remark;
    /** 发货状态 */
    private String deliverStatus;
    /** 快递单号 */
    private String expressNo;

}
