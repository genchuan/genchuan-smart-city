package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshareorder;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车位共享订单 DO
 *
 * @author lxs
 */
@TableName("park_space_share_order")
@KeySequence("park_space_share_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkSpaceShareOrderDO extends BaseDO {

    /**
     * [主键ID] 车位共享订单唯一标识
     */
    @TableId
    private Long id;
    /**
     * [订单编号] 车位共享订单业务编号
     */
    private String orderNo;
    /**
     * [共享配置ID] 绑定的车位共享配置ID
     */
    private Long shareId;
    /**
     * [使用用户ID] 使用用户ID
     */
    private Long userId;
    /**
     * [车牌号] 使用车辆车牌号
     */
    private String carNumber;
    /**
     * [使用日期] 车位实际使用日期
     */
    private LocalDate useDate;
    /**
     * [使用开始时间] 车位使用开始时间
     */
    private LocalDateTime startTime;
    /**
     * [使用结束时间] 车位使用结束时间
     */
    private LocalDateTime endTime;
    /**
     * [使用时长] 实际使用时长，单位：分钟
     */
    private Integer useDuration;
    /**
     * [费用金额] 车位共享产生的费用金额
     */
    private BigDecimal feeAmount;
    /**
     * [支付状态] 如：待支付/已支付/已取消
     */
    private String payStatus;
    /**
     * [支付记录ID] 支付记录ID
     */
    private Long paymentId;
    /**
     * [结算状态] 如：未结算/已结算
     */
    private String settlementStatus;
    /**
     * [结算时间] 订单结算完成时间
     */
    private LocalDateTime settlementTime;
    /**
     * [备注] 车位共享订单相关备注说明
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
