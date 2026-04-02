package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单退款 DO
 *
 * @author zhucongquan
 */
@TableName("order_refund")
@KeySequence("order_refund_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRefundDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 退款编号
     */
    private String refundCode;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 车牌号
     */
    private String plateNo;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 退款原因
     */
    private String refundReason;
    /**
     * 退款状态
     */
    private String refundStatus;
    /**
     * 审核人员
     */
    private String auditUser;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 审核备注
     */
    private String auditRemark;
    /**
     * 退款时间
     */
    private LocalDateTime refundTime;
    /**
     * 退款渠道
     */
    private String refundChannel;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;

    /**
     * 创建人
     */
    private String creator;
}