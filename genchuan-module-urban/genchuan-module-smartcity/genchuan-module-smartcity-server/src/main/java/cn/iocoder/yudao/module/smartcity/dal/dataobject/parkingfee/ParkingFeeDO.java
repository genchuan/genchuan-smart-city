package cn.iocoder.yudao.module.smartcity.dal.dataobject.parkingfee;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 停车收费管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_parking_fee")
@KeySequence("smartcity_parking_fee_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingFeeDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 车牌号
     */
    private String licensePlateNumber;
    /**
     * 入场时间
     */
    private LocalDateTime admissionTime;
    /**
     * 出场时间
     */
    private LocalDateTime exitTime;
    /**
     * 停车时长
     */
    private String parkingDuration;
    /**
     * 收费金额
     */
    private String feeAmount;
    /**
     * 收费类型
     */
    private String feeType;
    /**
     * 缴费方式
     */
    private String paymentMethod;
    /**
     * 优惠金额
     */
    private String discountAmount;
    /**
     * 实收金额
     */
    private String actualReceivedAmount;
    /**
     * 停车场名称
     */
    private String parkingLotName;
    /**
     * 车位编号
     */
    private String parkingSpaceNumber;
    /**
     * 收费员编号
     */
    private String tollCollectorNumber;
    /**
     * 收费时间
     */
    private LocalDateTime chargingTime;
    /**
     * 是否欠费
     */
    private String isThere;
    /**
     * 欠费金额
     */
    private String amountOfOutstandingFees;
    /**
     * 补缴时间
     */
    private LocalDateTime paymentDeadline;
    /**
     * 减免原因
     */
    private String reasonForExemption;
    /**
     * 支付订单号
     */
    private String paymentOrderNumber;

}