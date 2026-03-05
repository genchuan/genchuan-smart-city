package cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carentry;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 入场记录 DO
 *
 * @author zhucongquan
 */
@TableName("park_car_entry")
@KeySequence("park_car_entry_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkCarEntryDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 入场记录ID（UUID）
     */
    private String entryId;
    /**
     * 车牌号码
     */
    private String carNumber;
    /**
     * 车辆类型
     */
    private String carType;
    /**
     * 入场时间
     */
    private LocalDateTime entryTime;
    /**
     * 入场出入口ID
     */
    private String entryExitId;
    /**
     * 所属车场ID
     */
    private String lotId;
    /**
     * 分配车位ID
     */
    private String spaceId;
    /**
     * 识别设备
     */
    private String deviceCode;
    /**
     * 入场类型：正常识别/无牌车/特殊放行
     */
    private String entryType;
    /**
     * 预约用户ID
     */
    private Long userId;
    /**
     * 预约ID
     */
    private String reservationId;
    /**
     * 业务创建时间
     */
    private LocalDateTime entryCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime entryUpdateTime;
    /**
     * 业务备注
     */
    private String entryRemark;

}