package cn.iocoder.yudao.module.envirhealth.dal.dataobject.user;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 考核 DO
 *
 * @author 芋道源码
 */
@TableName("sys_assessment")
@KeySequence("sys_assessment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String assessmentId;
    /**
     * 关联sys_user.id
     */
    private String userId;
    /**
     * 关联sys_job_type.id
     */
    private String jobTypeId;
    /**
     * 关联sys_team.id
     */
    private String teamId;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 考核周期
     */
    private String cycle;
    /**
     * 考勤得分
     */
    private BigDecimal attendanceScore;
    /**
     * 作业质量得分
     */
    private BigDecimal workQualityScore;
    /**
     * 问题处置得分
     */
    private BigDecimal problemSolvingScore;
    /**
     * 初始总分
     */
    private BigDecimal initialTotalScore;
    /**
     * 最终总分
     */
    private BigDecimal finalTotalScore;
    /**
     * 关联sys_assessment_grade.id
     */
    private String assessmentGradeId;
    /**
     * 考核意见
     */
    private String reviewOpinion;
    /**
     * 关联sys_user.id
     */
    private String assessBy;
    /**
     * 审核时间
     */
    private LocalDateTime reviewTime;
    /**
     * 佐证材料URL
     */
    private String proofUrl;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}