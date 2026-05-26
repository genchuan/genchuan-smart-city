package cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle;

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

@TableName("split_rate")
@KeySequence("split_rate_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SplitRateDO extends BaseDO {

    @TableId
    private Long id;

    private Long partnerId;

    @TableField(exist = false)
    private String partnerName;

    private String splitMode;

    private BigDecimal rateValue;

    private String status;

    private Long auditorId;

    private LocalDateTime auditTime;

    private String remark;

    private String reserve1;

    private String reserve2;
}
