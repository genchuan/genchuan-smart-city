package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("pay_notify_task")
@Data
public class PayCallbackDO {

    @TableId
    private Long id;

    private Long appId;

    private Integer type;

    private String merchantOrderId;

    private Integer status;

    private LocalDateTime nextNotifyTime;

    private Integer notifyTimes;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
