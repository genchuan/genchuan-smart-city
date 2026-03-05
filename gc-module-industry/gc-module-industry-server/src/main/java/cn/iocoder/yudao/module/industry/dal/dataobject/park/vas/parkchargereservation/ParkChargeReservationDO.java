package cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkchargereservation;

import lombok.*;

import java.time.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电预约 DO
 *
 * @author lxs
 */
@TableName("park_charge_reservation")
@KeySequence("park_charge_reservation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkChargeReservationDO extends BaseDO {

    /**
     * [主键ID] 充电预约记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [预约编号] 充电预约唯一编号
     */
    private String reservationNo;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [车牌号码] 用户车辆车牌号码
     */
    private String carNumber;
    /**
     * [充电桩ID] 充电桩唯一标识
     */
    private Long chargePileId;
    /**
     * [预约日期] 用户预约充电的日期
     */
    private LocalDate reserveDate;
    /**
     * [预约开始时间] 预约充电开始时间
     */
    private LocalDateTime startTime;
    /**
     * [预约结束时间] 预约充电结束时间
     */
    private LocalDateTime endTime;
    /**
     * [状态] 如:待使用/已使用/已取消/已过期
     */
    private String status;
    /**
     * [实际充电时长] 实际充电时长，单位：分钟
     */
    private Integer actualChargeTime;
    /**
     * [充电度数] 实际充电电量度数
     */
    private BigDecimal chargeAmount;
    /**
     * [充电费用] 实际产生的充电费用
     */
    private BigDecimal chargeFee;
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
    /**
     * [备注] 充电预约相关备注说明
     */
    private String remark;

}
