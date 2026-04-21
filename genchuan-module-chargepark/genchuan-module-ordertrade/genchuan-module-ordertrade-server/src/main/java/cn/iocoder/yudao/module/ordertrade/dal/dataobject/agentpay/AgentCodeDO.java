package cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@TableName("agent_code")
@KeySequence("agent_code_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentCodeDO extends BaseDO {

    @TableId
    private Long id;

    private String code;

    private Long merchantId;

    private Long ruleId;

    private LocalDateTime expireTime;

    private String status;

    private Long userId;

    private LocalDateTime useTime;

    private Long orderId;

    private String remark;

    private String reserve1;

    private String reserve2;
}
