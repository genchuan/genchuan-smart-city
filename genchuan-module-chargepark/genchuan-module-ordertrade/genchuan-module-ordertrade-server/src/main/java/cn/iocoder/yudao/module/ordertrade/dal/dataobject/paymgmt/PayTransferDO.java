package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Map;

@TableName(value = "pay_transfer", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class PayTransferDO extends BaseDO {

    @TableId
    private Long id;
    private String no;
    private Long appId;
    private Long channelId;
    private String channelCode;
    private Long userId;
    private Integer userType;
    private String merchantTransferId;
    private String subject;
    private Integer price;
    private String userAccount;
    private String userName;
    private Integer status;
    private LocalDateTime successTime;
    private String notifyUrl;
    private String userIp;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, String> channelExtras;
    private String channelTransferNo;
    private String channelErrorCode;
    private String channelErrorMsg;
    private String channelNotifyData;
    private String channelPackageInfo;
}
