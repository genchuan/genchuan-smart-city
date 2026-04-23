package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@TableName("pay_notify_task")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayCallbackDO extends BaseDO {

    @TableId
    private Long id;
    private Long appId;
    private Integer type;
    private Long dataId;
    private String merchantOrderId;
    private String merchantRefundId;
    private String merchantTransferId;
    private Integer status;
    private LocalDateTime nextNotifyTime;
    private LocalDateTime lastExecuteTime;
    private Integer notifyTimes;
    private Integer maxNotifyTimes;
    private String notifyUrl;
}
