package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电桩 DO
 *
 * @author 亘川智城
 */
@TableName("charging_pile")
@KeySequence("charging_pile_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PileDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 设备编号
     */
    private String pileCode;
    /**
     * 型号
     */
    private String model;
    /**
     * 功率（单位：kW）
     */
    private BigDecimal power;
    /**
     * 生产厂家
     */
    private String manufacturer;
    /**
     * 所属场站ID，关联充电场站表charging_station
     */
    private Long stationId;
    /**
     * 绑定车位ID，关联充电车位表charging_lot
     */
    private Long lotId;
    /**
     * 充电模式：1=直流，2=交流，3=交直流混合，关联 charge_mode 表
     */
    private Long chargeMode;
    /**
     * 设备状态：1=已启用，2=已停用，3=未调试，4=已调试，关联 pile_status 表
     */
    private Long pileStatus;
    /**
     * 故障标记：0-无故障，1-有故障
     */
    private Boolean faultFlag;
    /**
     * 运行时长（单位：小时）
     */
    private Integer runTime;
    /**
     * 充电枪二维码
     */
    private String qrcode;
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


}