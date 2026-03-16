package cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderperiod;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class OrderPeriodDO extends BaseDO {

    /**
     * [主键ID] 期卡订单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 下单用户ID，关联 park_user.id
     */
    private Long userId;
    /**
     * [车辆ID] 下单车辆ID，关联 park_car.id
     */
    private Long carId;
    /**
     * [套餐ID] 期卡套餐ID，关联 park_period_package.id
     */
    private Long packageId;
    /**
     * [适用车场ID列表] 适用车场ID列表，varchar 存储，关联 park_lot.id
     */
    private String lotIds;
    /**
     * [原价] 套餐原价金额
     */
    private BigDecimal originalPrice;
    /**
     * [实付金额] 用户实际支付金额
     */
    private BigDecimal payAmount;
    /**
     * [优惠金额] 订单优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * [生效时间] 期卡生效时间
     */
    private LocalDateTime effectTime;
    /**
     * [到期时间] 期卡到期时间
     */
    private LocalDateTime expireTime;
    /**
     * [订单状态] 如:待支付/已支付/已取消/已过期
     */
    private String orderStatus;
    /**
     * [支付状态] 如:未支付/已支付
     */
    private String payStatus;
    /**
     * [支付方式] 支付方式
     */
    private String payType;
    /**
     * [支付记录ID] 关联缴费记录ID，park_payment.id，可为 NULL
     */
    private Long paymentId;
    /**
     * [备注] 期卡订单相关备注说明
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
