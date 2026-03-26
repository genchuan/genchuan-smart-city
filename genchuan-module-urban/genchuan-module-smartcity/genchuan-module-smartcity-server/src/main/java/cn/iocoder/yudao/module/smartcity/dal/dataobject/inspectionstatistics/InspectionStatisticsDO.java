package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionstatistics;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查分析统计 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_statistics")
@KeySequence("smartcity_inspection_statistics_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionStatisticsDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 巡查区域
     */
    private String patrolArea;
    /**
     * 巡查人员
     */
    private String patrolPersonnel;
    /**
     * 巡查任务完成率
     */
    private String completionInspectionTasks;
    /**
     * 平均巡查时长
     */
    private String averagePatrolDuration;
    /**
     * 问题发现数量
     */
    private String numberProblemDiscoveries;
    /**
     * 不同类型问题分布
     */
    private String distributionProblems;
    /**
     * 问题解决率
     */
    private String problemSolvingRate;
    /**
     * 重复问题发生率
     */
    private String repetitiveProblemRate;
    /**
     * 风险等级评估
     */
    private String riskLevelAssessment;
    /**
     * 建议与改进措施
     */
    private String suggestionsMeasures;

}