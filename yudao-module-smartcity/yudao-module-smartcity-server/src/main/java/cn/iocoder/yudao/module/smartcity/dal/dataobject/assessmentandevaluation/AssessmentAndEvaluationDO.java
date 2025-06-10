package cn.iocoder.yudao.module.smartcity.dal.dataobject.assessmentandevaluation;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 养护考核评价 DO
 *
 * @author zcq
 */
@TableName("smartcity_assessment_and_evaluation")
@KeySequence("smartcity_assessment_and_evaluation_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentAndEvaluationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 养护人员编号
     */
    private String maintenancePersonnelNumber;
    /**
     * 养护任务编号
     */
    private String maintenanceTaskNumber;
    /**
     * 考核周期
     */
    private String assessmentCycle;
    /**
     * 考核得分
     */
    private String assessmentScore;
    /**
     * 考核等级
     */
    private String assessmentLevel;
    /**
     * 评价意见
     */
    private String evaluationOpinion;
    /**
     * 改进建议
     */
    private String improvementSuggestions;

}