package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@TableName("activity_config")
@KeySequence("activity_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityConfigDO extends BaseDO {

    @TableId
    private Long id;
    /** 活动名称 */
    private String name;
    /** 活动类型(新用户/节假日/店庆/日常) */
    private String type;
    /** 参与条件 */
    private String joinCondition;
    /** 规则内容 */
    private String ruleContent;
    /** 状态(未生效/已生效) */
    private String status;
    /** 审核人 */
    private Long auditorId;
    /** 审核时间 */
    private LocalDateTime auditTime;
    /** 参与人数 */
    private Integer joinCount;
    /** 生效时间 */
    private LocalDateTime effectTime;
    /** 活动描述 */
    private String description;
    /** 用户群体(新用户/老用户/全部) */
    private String userGroup;
    private String reserve1;
    private String reserve2;

}
