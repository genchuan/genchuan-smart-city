package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("card_order")
@KeySequence("card_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardOrderDO extends BaseDO {

    @TableId
    private Long id;
    /** 订单编号 */
    private String no;
    /** 用户ID */
    private Long userId;
    /** 卡种ID */
    private Long cardId;
    /** 订单金额 */
    private BigDecimal amount;
    /** 支付状态(待支付/已支付/已完成/已取消) */
    private String payStatus;
    /** 支付时间 */
    private LocalDateTime payTime;
    /** 激活时间 */
    private LocalDateTime activeTime;
    /** 开票状态(未开票/已开票) */
    private String invoiceStatus;
    /** 归档时间 */
    private LocalDateTime archiveTime;
    private String reserve1;
    private String reserve2;

}
