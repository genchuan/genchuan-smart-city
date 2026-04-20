package cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 两轮充电订单 DO
 * @author genchuan
 */
@TableName("bike_charge_order")
@KeySequence("bike_charge_order_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeChargeOrderDO extends BaseDO {

    /** 主键ID */
    @TableId
    private Long id;

    /** 订单编号，唯一 */
    private String orderNo;

    /** 用户ID */
    private Long userId;

    /** 用户昵称 */
    private String userNickname;

    /** 充电时长（分钟） */
    private Integer chargeDuration;

    /** 充电量（度） */
    private BigDecimal chargeQuantity;

    /** 订单金额 */
    private BigDecimal amount;

    /** 订单状态，字典：bike_charge_order_status */
    private String status;

    /** 订单生成时间 */
    private LocalDateTime createOrderTime;

    /** 所属场站ID */
    private Long stationId;

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 支付方式，字典：bike_charge_order_pay_method */
    private String payMethod;

    /** 优惠抵扣金额 */
    private BigDecimal discountAmount;

    /** 归档时间 */
    private LocalDateTime archiveTime;

    /** 操作人ID */
    private Long operatorId;

    /** 备用字段1 */
    private String reserve1;

    /** 备用字段2 */
    private String reserve2;
}
