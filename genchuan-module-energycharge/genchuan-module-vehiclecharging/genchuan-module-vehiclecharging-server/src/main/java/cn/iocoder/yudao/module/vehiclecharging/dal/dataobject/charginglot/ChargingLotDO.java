package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 充电车位 DO
 *
 * @author zhucongquan
 */
@TableName("charging_lot")
@KeySequence("charging_lot_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargingLotDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 车位编号
     */
    private String lotCode;
    /**
     * 所属场站ID
     */
    private Long stationId;
    /**
     * 车位类型
     */
    private String lotType;
    /**
     * 关联充电桩ID
     */
    private Long pileId;
    /**
     * 占用时长（分钟）
     */
    private Integer occupyTime;
    /**
     * 车位状态
     */
    private String lotStatus;
    /**
     * 占用超时时间（分钟）
     */
    private Integer occupyTimeout;
    /**
     * 维护原因
     */
    private String maintainReason;
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