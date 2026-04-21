package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("point_activity")
@KeySequence("point_activity_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointActivityDO extends BaseDO {

    @TableId
    private Long id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动类型(注册赠分/消费赠分/邀请赠分/活动赠分)
     */
    private String type;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 积分规则
     */
    private String rule;
    /**
     * 活动描述
     */
    private String description;
    /**
     * 适用场站(场站ID列表,逗号分隔)
     */
    private String stationIds;
    /**
     * 参与人数
     */
    private Integer joinCount;
    /**
     * 审核人
     */
    private Long auditorId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 剩余积分额度
     */
    private Integer remainPoint;
    /**
     * 状态(待生效/进行中/已结束/已暂停)
     */
    private String status;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;

}
