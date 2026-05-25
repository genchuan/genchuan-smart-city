package cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款记录 DO
 * @author genchuan
 */
@TableName("refund_record")
@KeySequence("refund_record_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RefundRecordDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 记录编号，唯一 */
    private String recordNo;

    /** 关联退款申请ID */
    private Long applyId;

    /** 退款申请编号（关联 refund_apply.apply_no，非数据库字段） */
    @TableField(exist = false)
    private String applyNo;

    /** 关联订单ID */
    private Long orderId;

    /** 订单编号（关联 all_order.order_no，非数据库字段） */
    @TableField(exist = false)
    private String orderNo;

    /** 退款金额 */
    private BigDecimal refundAmount;

    /** 退款时间 */
    private LocalDateTime refundTime;

    /** 状态，字典：refund_record_status */
    private String status;

    /** 核查理由 */
    private String checkReason;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
