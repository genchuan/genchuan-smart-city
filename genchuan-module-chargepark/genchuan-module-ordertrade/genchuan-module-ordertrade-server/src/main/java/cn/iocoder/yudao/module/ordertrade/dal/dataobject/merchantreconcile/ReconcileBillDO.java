package cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("reconcile_bill")
@KeySequence("reconcile_bill_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReconcileBillDO extends BaseDO {

    @TableId
    private Long id;

    /** 对账单号 */
    private String billNo;

    /** 商户ID */
    private Long merchantId;

    /** 商户名称 */
    private String merchantName;

    /** 对账日期 */
    private LocalDate billDate;

    /** 系统订单总金额 */
    private BigDecimal sysAmount;

    /** 商户上报总金额 */
    private BigDecimal merchantAmount;

    /** 差异金额 */
    private BigDecimal diffAmount;

    /** 对账状态：pending/confirmed/disputed/resolved */
    private String status;

    /** 确认时间 */
    private LocalDateTime confirmTime;

    /** 操作人ID */
    private Long operatorId;

    /** 备注 */
    private String remark;

    private String reserve1;

    private String reserve2;
}
