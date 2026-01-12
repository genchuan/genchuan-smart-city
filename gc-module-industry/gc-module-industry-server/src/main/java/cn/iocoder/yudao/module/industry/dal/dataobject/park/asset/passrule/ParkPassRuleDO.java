package cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.passrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 通行规则 DO
 *
 * @author 亘川智城
 */
@TableName("park_pass_rule")
@KeySequence("park_pass_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkPassRuleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 通行规则ID（UUID）
     */
    private String passRuleId;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 关联出入口ID
     */
    private String entryExitId;
    /**
     * 允许车辆类型
     */
    private String allowCarTypes;
    /**
     * 禁止车辆类型
     */
    private String forbidCarTypes;
    /**
     * 高峰时段规则
     */
    private String peakTimeRule;
    /**
     * 平峰时段规则
     */
    private String offPeakTimeRule;
    /**
     * 状态：启用/禁用
     */
    private String status;
    /**
     * 业务创建时间
     */
    private LocalDateTime passRuleCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime passRuleUpdateTime;
    /**
     * 业务备注
     */
    private String passRuleRemark;

}