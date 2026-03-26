package cn.iocoder.yudao.module.waterdetection.dal.dataobject.leakagecontrolplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 漏损控制方案建议 DO
 *
 * @author zcq
 */
@TableName("gc_leakage_control_plan")
@KeySequence("gc_leakage_control_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeakageControlPlanDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 分区ID
     */
    private String partitionId;
    /**
     * 超标漏损率(%)
     */
    private Double exceededLeakageRate;
    /**
     * 压力数据
     */
    private String pressureData;
    /**
     * 管道平均使用年限(年)
     */
    private Double pipeAvgAge;
    /**
     * 建议方案
     */
    private String suggestedPlan;
    /**
     * 方案实施时间
     */
    private LocalDateTime planImplementTime;
    /**
     * 实施后漏损率(%)
     */
    private Double postImplementRate;

}