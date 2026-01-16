package cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpoints;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户积分 DO
 *
 * @author lxs
 */
@TableName("park_points")
@KeySequence("park_points_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPointsDO extends BaseDO {

    /**
     * [主键ID] 用户积分记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [当前总积分] 用户当前累计的总积分
     */
    private BigDecimal totalPoints;
    /**
     * [可用积分] 当前可使用的积分
     */
    private BigDecimal availablePoints;
    /**
     * [已使用积分] 已被消耗使用的积分
     */
    private BigDecimal usedPoints;
    /**
     * [已过期积分] 已过期失效的积分
     */
    private BigDecimal expiredPoints;
    /**
     * [上次更新时间] 积分数据上次变更时间
     */
    private LocalDateTime lastUpdateTime;
    /**
     * [备注] 用户积分相关备注说明
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
