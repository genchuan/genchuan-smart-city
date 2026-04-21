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

@TableName("agent_record")
@KeySequence("agent_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentRecordDO extends BaseDO {

    @TableId
    private Long id;

    private String recordNo;

    private Long orderId;

    private Long merchantId;

    private BigDecimal amount;

    private LocalDateTime tradeTime;

    private String status;

    private Long checkerId;

    private LocalDateTime checkTime;

    private String checkResult;

    private String remark;

    private String reserve1;

    private String reserve2;
}
