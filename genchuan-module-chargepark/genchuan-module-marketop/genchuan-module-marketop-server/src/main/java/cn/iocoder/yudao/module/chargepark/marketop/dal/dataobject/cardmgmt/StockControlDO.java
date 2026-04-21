package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("stock_control")
@KeySequence("stock_control_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockControlDO extends BaseDO {

    @TableId
    private Long id;
    /** 卡种ID */
    private Long cardId;
    /** 当前库存 */
    private Integer currentStock;
    /** 预警阈值 */
    private Integer warnThreshold;
    /** 状态(正常库存/低库存/预警库存) */
    private String status;
    /** 告警状态(未告警/已告警) */
    private String warnStatus;
    /** 同步时间 */
    private LocalDateTime syncTime;
    private String reserve1;
    private String reserve2;

}
