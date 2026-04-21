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

@TableName("reconcile_record")
@KeySequence("reconcile_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReconcileRecordDO extends BaseDO {

    @TableId
    private Long id;

    /** 所属对账单ID */
    private Long billId;

    /** 对账单号 */
    private String billNo;

    /** 商户ID */
    private Long merchantId;

    /** 订单编号 */
    private String orderNo;

    /** 系统金额 */
    private BigDecimal sysAmount;

    /** 商户上报金额 */
    private BigDecimal merchantAmount;

    /** 差异金额 */
    private BigDecimal diffAmount;

    /** 对账结果：matched/unmatched/only_sys/only_merchant */
    private String matchResult;

    /** 异常原因 */
    private String diffReason;

    /** 处理时间 */
    private LocalDateTime handleTime;

    /** 备注 */
    private String remark;

    private String reserve1;

    private String reserve2;
}
