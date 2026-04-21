package cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("agent_rule")
@KeySequence("agent_rule_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentRuleDO extends BaseDO {

    @TableId
    private Long id;

    private String name;

    private Long merchantId;

    private String agentType;

    private BigDecimal singleLimit;

    private BigDecimal dayLimit;

    private String scene;

    private String status;

    private Integer useCount;

    private Long auditorId;

    private LocalDateTime auditTime;

    private LocalDateTime lastUpdateTime;

    private String remark;

    private String reserve1;

    private String reserve2;
}
