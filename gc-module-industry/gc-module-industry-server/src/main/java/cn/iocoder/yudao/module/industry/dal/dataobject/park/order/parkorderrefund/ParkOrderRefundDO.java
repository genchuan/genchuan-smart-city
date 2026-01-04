package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderrefund;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 退款订单 DO
 *
 * @author lxs
 */
@TableName("park_order_refund")
@KeySequence("park_order_refund_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkOrderRefundDO extends BaseDO {

    /**
     * 主键ID，自增ID，记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 唯一退款单号
     */
    private String refundNo;
    /**
     * 关联退款的原订单ID
     */
    private Long originalOrderId;
    /**
     * 原订单编号
     */
    private String originalOrderNo;
    /**
     * 退款申请人ID
     */
    private Long userId;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 退款原因
     */
    private String refundReason;
    /**
     * 退款状态：申请中/已通过/已拒绝/已到账
     */
    private String refundStatus;
    /**
     * 退款申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核人
     */
    private String auditBy;
    /**
     * 到账时间
     */
    private LocalDateTime transferTime;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}
