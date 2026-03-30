package cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderrefund;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款订单 DO
 *
 * @author 亘川智城
 */
@TableName("park_order_refund")
@KeySequence("park_order_refund_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRefundDO extends BaseDO {

    /**
     * [主键ID] 退款订单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [原订单ID] 关联原订单ID，park_order_temp.id 或 park_order_period.id
     */
    private Long originalOrderId;
    /**
     * [原订单类型] 如:临停/期卡
     */
    private String orderType;
    /**
     * [退款金额] 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * [退款原因] 退款原因说明
     */
    private String refundReason;
    /**
     * [佐证材料] 退款佐证材料，JSON 格式，varchar 存储
     */
    private String proofFiles;
    /**
     * [申请人ID] 申请人ID，关联 park_user.id
     */
    private Long applyBy;
    /**
     * [联系电话] 申请人联系电话
     */
    private String contactPhone;
    /**
     * [审批状态] 如:待审批/已审批/已驳回
     */
    private String approveStatus;
    /**
     * [退款状态] 如:待退款/退款中/已到账/退款失败
     */
    private String refundStatus;
    /**
     * [退款时间] 实际退款时间，可为 NULL
     */
    private LocalDateTime refundTime;
    /**
     * [到账时间] 退款到账时间，可为 NULL
     */
    private LocalDateTime arrivalTime;
    /**
     * [备注] 退款订单相关备注说明
     */
    private String remark;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
