package cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@TableName("pay_app")
@Data
@EqualsAndHashCode(callSuper = true)
public class PayAppDO extends BaseDO {

    @TableId
    private Long id;
    private String appKey;
    private String name;
    private Integer status;
    private String remark;
    private String orderNotifyUrl;
    private String refundNotifyUrl;
    private String transferNotifyUrl;
}
