package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@TableName("pay_refund")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayRefundDO extends BaseDO {

    @TableId
    private Long id;
    private String no;
    private Long appId;
    private Long channelId;
    private String channelCode;
    private Long orderId;
    private String orderNo;
    private Long userId;
    private Integer userType;
    private String merchantOrderId;
    private String merchantRefundId;
    private String notifyUrl;
    private Integer status;
    private Integer payPrice;
    private Integer refundPrice;
    private String reason;
    private String userIp;
    private String channelOrderNo;
    private String channelRefundNo;
    private LocalDateTime successTime;
    private String channelErrorCode;
    private String channelErrorMsg;
    private String channelNotifyData;
}
