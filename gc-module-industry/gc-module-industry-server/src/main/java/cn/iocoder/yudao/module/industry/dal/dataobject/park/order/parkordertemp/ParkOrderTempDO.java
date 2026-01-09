package cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkordertemp;

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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 临停订单 DO
 *
 * @author lxs
 */
@TableName("park_order_temp")
@KeySequence("park_order_temp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkOrderTempDO extends BaseDO {

    /**
     * 主键ID，记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * 发生地区编码，12位国标地区编码
     */
    private String regionFullCode;
    /**
     * 订单编号，唯一订单编号
     */
    private String orderNo;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 用户ID，关联 sys_user.id，可为 NULL
     */
    private Long userId;
    /**
     * 停车场ID，关联 park_lot.lot_id
     */
    private Long lotId;
    /**
     * 入场记录ID，关联 park_car_entry.entry_id
     */
    private Long entryId;
    /**
     * 出场记录ID，关联 park_car_exit.exit_id，可为 NULL
     */
    private Long exitId;
    /**
     * 停放开始时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime parkingStartTime;
    /**
     * 停放结束时间，可为 NULL
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime parkingEndTime;
    /**
     * 停放时长，单位：分钟
     */
    private Integer parkingDuration;
    /**
     * 收费策略ID，关联 park_fee_strategy.fee_strategy_id
     */
    private Long feeStrategyId;
    /**
     * 原始费用
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
     * 订单状态：待支付/已支付/已取消/已退款/逃费
     */
    private String orderStatus;
    /**
     * 支付时间，可为 NULL
     */
    private LocalDateTime payTime;
    /**
     * 取消时间，可为 NULL
     */
    private LocalDateTime cancelTime;
    /**
     * 退款时间，可为 NULL
     */
    private LocalDateTime refundTime;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 通用扩展字段1，可为空
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，可为空
     */
    private String extCommon2;
    /**
     * 通用扩展字段3，可为空
     */
    private String extCommon3;
    /**
     * 通用扩展字段4，可为空
     */
    private String extCommon4;

}
