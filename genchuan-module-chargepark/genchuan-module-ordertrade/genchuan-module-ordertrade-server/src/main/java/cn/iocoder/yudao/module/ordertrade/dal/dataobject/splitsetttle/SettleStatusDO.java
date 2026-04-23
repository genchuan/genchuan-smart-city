package cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@TableName("settle_status")
@KeySequence("settle_status_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SettleStatusDO extends BaseDO {

    @TableId
    private Long id;

    private Long billId;

    private String status;

    private LocalDateTime updateTime;

    private String errorReason;

    private Long checkerId;

    private LocalDateTime checkTime;

    private String remark;

    private String reserve1;

    private String reserve2;
}
