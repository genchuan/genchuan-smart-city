package cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkberthlock;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 泊位锁定记录 DO
 *
 * @author lxs
 */
@TableName("biz_park_berth_lock")
@KeySequence("biz_park_berth_lock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkBerthLockDO extends BaseDO {

    /**
     * 主键，唯一标识
     */
    @TableId
    private Long id;
    /**
     * 关联预约ID
     */
    private Long reservationId;
    /**
     * 锁定的泊位唯一标识
     */
    private Long berthId;
    /**
     * 锁定开始时间
     */
    private LocalDateTime lockStartTime;
    /**
     * 锁定结束时间
     */
    private LocalDateTime lockEndTime;
    /**
     * 泊位锁定状态
     */
    private String lockStatus;
    /**
     * 泊位解锁原因
     */
    private String unlockReason;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}
