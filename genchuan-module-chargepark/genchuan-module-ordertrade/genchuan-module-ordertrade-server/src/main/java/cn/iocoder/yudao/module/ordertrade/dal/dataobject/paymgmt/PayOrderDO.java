package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@TableName("pay_order")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayOrderDO extends BaseDO {

    @TableId
    private Long id;
    private Long appId;
    private Long channelId;
    private String channelCode;
    private Long userId;
    private Integer userType;
    private String merchantOrderId;
    private String subject;
    private String body;
    private String notifyUrl;
    private Integer price;
    private Double channelFeeRate;
    private Integer channelFeePrice;
    private Integer status;
    private String userIp;
    private LocalDateTime expireTime;
    private LocalDateTime successTime;
    private Long extensionId;
    private String no;
    private Integer refundPrice;
    private String channelUserId;
    private String channelOrderNo;
}
