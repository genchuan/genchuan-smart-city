package cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.promotion;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 优惠活动 DO
 *
 * @author 亘川智城
 */
@TableName("park_promotion")
@KeySequence("park_promotion_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDO extends BaseDO {

    /**
     * [主键ID]
     */
    @TableId
    private Long id;
    /**
     * [活动名称]
     */
    private String activityName;
    /**
     * [活动类型] 如:满减/折扣/充值送/免费时长
     */
    private String activityType;
    /**
     * [活动开始时间]
     */
    private LocalDateTime startTime;
    /**
     * [活动结束时间]
     */
    private LocalDateTime endTime;
    /**
     * [总名额] 可为NULL
     */
    private Integer quota;
    /**
     * [已使用名额]
     */
    private Integer usedQuota;
    /**
     * [适用范围] 如:全局/区域/车场
     */
    private String applyScope;
    /**
     * [适用范围ID列表] JSON格式varchar
     */
    private String scopeIds;
    /**
     * [活动规则] JSON格式varchar，如满减金额/折扣比例
     */
    private String ruleConfig;
    /**
     * [状态] 如:未开始/进行中/已结束
     */
    private String status;
    /**
     * [创建人ID] 关联park_user.id
     */
    private Long createBy;
    /**
     * [备注]
     */
    private String remark;
    /**
     * [通用扩展字段1]
     */
    private String extCommon1;
    /**
     * [通用扩展字段2]
     */
    private String extCommon2;
    /**
     * [通用扩展字段3]
     */
    private String extCommon3;
    /**
     * [通用扩展字段4]
     */
    private String extCommon4;

}
