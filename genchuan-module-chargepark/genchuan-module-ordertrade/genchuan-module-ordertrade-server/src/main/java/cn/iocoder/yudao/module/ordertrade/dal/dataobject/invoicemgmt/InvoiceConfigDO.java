package cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("invoice_config")
@KeySequence("invoice_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceConfigDO extends BaseDO {

    @TableId
    private Long id;

    private String category;

    private BigDecimal taxRate;

    private String taxBody;

    private String status;

    private Long auditorId;

    private LocalDateTime auditTime;

    private String remark;

    private String reserve1;

    private String reserve2;
}
