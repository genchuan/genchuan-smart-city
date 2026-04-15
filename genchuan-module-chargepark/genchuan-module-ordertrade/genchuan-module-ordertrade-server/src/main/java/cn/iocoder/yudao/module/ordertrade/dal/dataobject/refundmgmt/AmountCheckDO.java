package cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 金额核算 DO
 * @author genchuan
 */
@TableName("amount_check")
@KeySequence("amount_check_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AmountCheckDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 核算编号，唯一 */
    private String checkNo;

    /** 关联订单ID */
    private Long orderId;

    /** 申请金额 */
    private BigDecimal applyAmount;

    /** 核算结果，字典：amount_check_check_result */
    private String checkResult;

    /** 核算明细 */
    private String checkDetail;

    /** 状态，字典：amount_check_status */
    private String status;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
