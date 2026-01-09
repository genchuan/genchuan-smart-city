package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderperiod;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 期卡订单 DO
 *
 * @author lxs
 */
@TableName("park_order_period")
@KeySequence("park_order_period_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkOrderPeriodDO extends BaseDO {

    /**
     * 主键ID，自增ID，记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 唯一订单编号
     */
    private String orderNo;
    /**
     * 用户ID，关联 sys_user.id
     */
    private Long userId;
    /**
     * 期卡套餐ID，关联 park_period_package.package_id
     */
    private Long periodCardId;
    /**
     * 适用车场ID列表，JSON 格式
     */
    private String lotIds;
    /**
     * 生效时间
     */
    private LocalDateTime startTime;
    /**
     * 失效时间
     */
    private LocalDateTime endTime;
    /**
     * 卡状态（卡状态：未激活/已激活/已过期/已注销）
     */
    private String cardStatus;
    /**
     * 原始金额
     */
    private BigDecimal originalAmount;
    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * 实付金额
     */
    private BigDecimal payAmount;
    /**
     * 订单状态（状态：待支付/已支付/已取消/已退款）
     */
    private String orderStatus;
    /**
     * 支付时间
     */
    private LocalDateTime payTime;
    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;
    /**
     * 退款时间
     */
    private LocalDateTime refundTime;
    /**
     * 是否续费，0-否，1-是
     */
    private Boolean renewFlag;
    /**
     * 上一订单ID，续费关联
     */
    private Long prevOrderId;
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
