package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("pay_wallet")
@Data
public class PayWalletDO {

    @TableId
    private Long id;

    private Long userId;

    private Integer userType;

    private Long balance;

    private Long freezeBalance;

    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
