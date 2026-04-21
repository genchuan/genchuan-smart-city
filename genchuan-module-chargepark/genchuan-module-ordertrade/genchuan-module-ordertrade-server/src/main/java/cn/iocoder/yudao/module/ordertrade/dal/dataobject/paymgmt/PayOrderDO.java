package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("pay_order")
@Data
public class PayOrderDO {

    @TableId
    private Long id;

    private String merchantOrderId;

    private String channelOrderNo;

    private Long appId;

    private String channelCode;

    private Integer amount;

    private Integer status;

    private LocalDateTime expireTime;

    private LocalDateTime notifyTime;

    private LocalDateTime successTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
