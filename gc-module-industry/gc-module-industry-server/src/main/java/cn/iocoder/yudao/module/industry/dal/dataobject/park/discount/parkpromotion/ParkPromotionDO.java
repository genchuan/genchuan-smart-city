package cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkpromotion;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 优惠活动 DO
 *
 * @author lxs
 */
@TableName("park_promotion")
@KeySequence("park_promotion_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPromotionDO extends BaseDO {

    /**
     * [主键ID] 优惠活动唯一标识
     */
    @TableId
    private Long id;
    /**
     * [活动名称] 优惠活动名称
     */
    private String activityName;
    /**
     * [活动类型] 满减 / 折扣 / 赠送 / 充值送 / 其他
     */
    private String activityType;
    /**
     * [适用范围] 全局 / 区域 / 车场 / 用户类型（JSON字符串）
     */
    private String applyScope;
    /**
     * [活动开始时间] 活动生效开始时间
     */
    private LocalDateTime startTime;
    /**
     * [活动结束时间] 活动生效结束时间
     */
    private LocalDateTime endTime;
    /**
     * [活动总名额] NULL 表示不限
     */
    private Integer quota;
    /**
     * [已使用名额] 已消耗活动名额
     */
    private Integer usedQuota;
    /**
     * [状态] 未开始 / 进行中 / 已结束 / 已取消
     */
    private String status;
    /**
     * [活动规则配置] 活动规则定义（JSON字符串）
     */
    private String ruleConfig;
    /**
     * [活动数据统计] 统计信息（JSON字符串）
     */
    private String dataStatistics;
    /**
     * [备注] 优惠活动相关说明
     */
    private String remark;
    /**
     * [通用扩展字段1] 预留扩展
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 预留扩展
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 预留扩展
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 预留扩展
     */
    private String extCommon4;

}
