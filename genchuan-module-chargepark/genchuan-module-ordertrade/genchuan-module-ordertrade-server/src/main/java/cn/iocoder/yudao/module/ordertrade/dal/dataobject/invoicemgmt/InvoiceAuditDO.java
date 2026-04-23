package cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@TableName("invoice_audit")
@KeySequence("invoice_audit_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class InvoiceAuditDO extends BaseDO {

    @TableId
    private Long id;

    private Long applyId;

    private Long applicantId;

    private LocalDateTime applyTime;

    private String status;

    private Long auditorId;

    private LocalDateTime auditTime;

    private String auditResult;

    private String remark;

    private String reserve1;

    private String reserve2;
}
