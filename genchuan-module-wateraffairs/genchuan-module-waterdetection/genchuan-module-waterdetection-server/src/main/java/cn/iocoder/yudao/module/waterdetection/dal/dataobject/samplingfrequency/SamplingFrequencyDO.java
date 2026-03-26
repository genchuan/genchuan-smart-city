package cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingfrequency;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采样频率设置 DO
 *
 * @author zcq
 */
@TableName("gc_sampling_frequency")
@KeySequence("gc_sampling_frequency_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SamplingFrequencyDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 采样点编号
     */
    private String pointCode;
    /**
     * 指标名称
     */
    private String indicatorName;
    /**
     * 采样频率(次/月/季)
     */
    private String frequency;
    /**
     * 执行周期
     */
    private String executionCycle;
    /**
     * 特殊时段(如汛期)调整规则
     */
    private String specialPeriodRule;

}