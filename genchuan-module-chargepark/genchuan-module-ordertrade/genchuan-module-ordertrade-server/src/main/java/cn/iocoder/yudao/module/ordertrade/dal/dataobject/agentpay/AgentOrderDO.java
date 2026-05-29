package cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("agent_order")
@KeySequence("agent_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentOrderDO extends BaseDO {

    @TableId
    private Long id;

    private String orderNo;

    private Long merchantId;

    @TableField(exist = false)
    private String merchantName;

    private String carNo;

    private BigDecimal amount;

    private String payType;

    private String status;

    private LocalDateTime payTime;

    private Long invoiceId;

    private Long operatorId;

    private String remark;

    private String reserve1;

    private String reserve2;
}
