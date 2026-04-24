package cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
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

    /** 对账周期，如 2026-03、2026-W14 */
    private String cycle;

    /** 平台金额(元) */
    private BigDecimal platformAmount;

    /** 商户金额(元) */
    private BigDecimal merchantAmount;

    /** 对账状态：pending/reconciled/abnormal */
    private String status;

    /** 对账人ID */
    private Long reconcilerId;

    /** 对账时间 */
    private LocalDateTime reconcileTime;

    /** 确认时间 */
    private LocalDateTime confirmTime;

    /** 备注 */
    private String remark;

    private String reserve1;

    private String reserve2;
}
