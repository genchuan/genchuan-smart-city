package cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("rule_config")
@KeySequence("rule_config_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleConfigDO extends BaseDO {

    @TableId
    private Long id;
    /**
     * 规则名称
     */
    private String name;
    /**
     * 规则类型(获取规则/消耗规则/赠送规则)
     */
    private String type;
    /**
     * 赠送比例
     */
    private BigDecimal giftRatio;
    /**
     * 状态(未生效/已生效)
     */
    private String status;
    /**
     * 审核人
     */
    private Long auditorId;
    /**
     * 审核时间
     */
    private LocalDateTime auditTime;
    /**
     * 匹配次数
     */
    private Integer matchCount;
    /**
     * 生效时间
     */
    private LocalDateTime effectTime;
    /**
     * 规则描述
     */
    private String description;
    /**
     * 适用场景(充电/停车/活动/其他)
     */
    private String scene;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;

}
