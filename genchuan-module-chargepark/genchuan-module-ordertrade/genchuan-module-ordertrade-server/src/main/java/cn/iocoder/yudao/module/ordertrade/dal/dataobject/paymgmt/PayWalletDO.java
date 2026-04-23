package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("pay_wallet")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayWalletDO extends BaseDO {

    @TableId
    private Long id;
    private Long userId;
    private Integer userType;
    private Integer balance;
    private Integer freezePrice;
    private Integer totalExpense;
    private Integer totalRecharge;
}
