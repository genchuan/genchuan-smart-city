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

@TableName("invoice_list")
@KeySequence("invoice_list_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceListDO extends BaseDO {

    @TableId
    private Long id;

    private String invoiceNo;

    private Long orderId;

    private String title;

    private String taxNo;

    private BigDecimal amount;

    private String status;

    private Long auditorId;

    private LocalDateTime auditTime;

    private LocalDateTime invoiceTime;

    private LocalDateTime pushTime;

    private String downloadUrl;

    private String remark;

    private String reserve1;

    private String reserve2;
}
