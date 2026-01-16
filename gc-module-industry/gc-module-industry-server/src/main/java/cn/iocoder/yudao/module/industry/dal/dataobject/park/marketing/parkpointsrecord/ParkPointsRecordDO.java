package cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrecord;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 积分变动记录 DO
 *
 * @author lxs
 */
@TableName("park_points_record")
@KeySequence("park_points_record_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPointsRecordDO extends BaseDO {

    /**
     * [主键ID] 积分变动记录唯一标识
     */
    @TableId
    private Long id;
    /**
     * [用户ID] 用户唯一标识
     */
    private Long userId;
    /**
     * [变动类型] 如:增加/减少
     */
    private String pointsType;
    /**
     * [变动积分] 本次积分变动数值
     */
    private BigDecimal pointsAmount;
    /**
     * [触发来源] 如:订单支付/积分兑换/活动奖励/过期扣除
     */
    private String triggerSource;
    /**
     * [关联ID] 关联业务ID，如订单ID/兑换ID/活动ID
     */
    private Long relatedId;
    /**
     * [变动时间] 积分发生变动的时间
     */
    private LocalDateTime changeTime;
    /**
     * [变动后余额] 本次积分变动后的积分余额
     */
    private BigDecimal balanceAfter;
    /**
     * [备注] 积分变动相关备注说明
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
