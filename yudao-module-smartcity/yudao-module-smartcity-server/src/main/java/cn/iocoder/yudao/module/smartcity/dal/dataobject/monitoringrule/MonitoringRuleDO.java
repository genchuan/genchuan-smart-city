package cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测项规则设置 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_monitoring_rule")
@KeySequence("smartcity_monitoring_rule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitoringRuleDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 水位超限预警
     */
    private String waterLevelOverlimitWarning;
    /**
     * 流量异常预警
     */
    private String flowAbnormalityWarning;
    /**
     * 水质超标预警
     */
    private String waterQualityExceedanceWarning;

}