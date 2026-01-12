package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carparking;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 在停车辆 DO
 *
 * @author zhucongquan
 */
@TableName("park_car_parking")
@KeySequence("park_car_parking_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkCarParkingDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 在停记录ID（UUID）
     */
    private String parkingId;
    /**
     * 入场记录ID
     */
    private String entryId;
    /**
     * 车牌
     */
    private String carNumber;
    /**
     * 所属车场ID
     */
    private String lotId;
    /**
     * 车位ID
     */
    private String spaceId;
    /**
     * 已停放时长（分钟）
     */
    private Integer parkingTime;
    /**
     * 状态：正常/疑似套牌/异常
     */
    private String parkingStatus;
    /**
     * 疑似套牌原因
     */
    private String suspiciousReason;
    /**
     * 异常原因
     */
    private String abnormalReason;
    /**
     * 业务更新时间
     */
    private LocalDateTime parkingUpdateTime;
    /**
     * 业务备注
     */
    private String parkingRemark;

}