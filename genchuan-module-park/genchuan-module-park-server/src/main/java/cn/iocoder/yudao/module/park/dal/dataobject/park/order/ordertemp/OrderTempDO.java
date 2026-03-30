package cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 临停订单 DO
 *
 * @author 亘川智城
 */
@TableName("park_order_temp")
@KeySequence("park_order_temp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderTempDO extends BaseDO {

    /**
     * [主键ID] 临停订单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [订单唯一编号] 订单唯一编号
     */
    private String orderCode;
    /**
     * [车牌号码] 停车车辆的车牌号码
     */
    private String carNumber;
    /**
     * [所属车场ID] 所属车场ID，关联 park_lot.id
     */
    private Long lotId;
    /**
     * [泊位ID] 停车泊位ID，关联 park_space.id
     */
    private Long spaceId;
    /**
     * [关联录入车辆表ID] 关联 park_input_car.id
     */
    private Long parkInputCarId;

    /**
     * [优惠券ID] 关联 park_coupon.id
     */
    private Long couponId;
    /**
     * [入场时间] 从 park_input_car 获取
     */
    private LocalDateTime entryTime;
    /**
     * [出场时间] 从 park_input_car 获取
     */
    private LocalDateTime exitTime;
    /**
     * [停放时长] 停车时长，单位分钟
     */
    private Integer parkingDuration;
    /**
     * [应收金额] 应收金额
     */
    private BigDecimal originalAmount;
    /**
     * [优惠金额] 订单优惠金额
     */
    private BigDecimal discountAmount;
    /**
     * [实付金额] 用户实际支付金额
     */
    private BigDecimal payAmount;
    /**
     * [费率策略ID] 关联费率策略ID，park_fee_strategy.id
     */
    private Long feeStrategyId;
    /**
     * [订单状态] 如:待支付/已支付/已取消/已完成
     */
    private String orderStatus;
    /**
     * [支付状态] 如:未支付/已支付/部分支付
     */
    private String payStatus;
    /**
     * [支付方式] 如:微信/支付宝/现金/其他
     */
    private String payType;
    /**
     * [缴费记录ID] 关联缴费记录ID，park_payment.id，可为 NULL
     */
    private Long paymentId;
    /**
     * [备注] 临停订单相关备注说明
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
