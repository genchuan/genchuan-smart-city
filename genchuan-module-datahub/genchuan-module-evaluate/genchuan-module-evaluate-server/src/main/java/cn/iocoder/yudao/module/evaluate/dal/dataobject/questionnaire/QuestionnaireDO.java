package cn.iocoder.yudao.module.evaluate.dal.dataobject.questionnaire;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 问卷 DO
 *
 * @author 亘川智城
 */
@TableName("survey_questionnaire")
@KeySequence("survey_questionnaire_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 问卷ID（UUID）
     */
    private String questionnaireId;
    /**
     * 问卷名称
     */
    private String name;
    /**
     * 问卷编码
     */
    private String code;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 调查对象范围
     */
    private String objectScope;
    /**
     * 调查对象范围ID（关联sys_scope.scope_id）
     */
    private String scopeId;
    /**
     * 发放方式ID（关联sys_issue_type.type_id）
     */
    private String issueTypeId;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 原开始时间
     */
    private LocalDateTime originalStartTime;
    /**
     * 原结束时间
     */
    private LocalDateTime originalEndTime;
    /**
     * 填写人数
     */
    private Integer fillCount;
    /**
     * 填写率
     */
    private BigDecimal fillRate;
    /**
     * 平均得分（如95.50）
     */
    private BigDecimal averageScore;
    /**
     * 最终填写率
     */
    private BigDecimal finalFillRate;
    /**
     * 最终平均分
     */
    private BigDecimal finalAverageScore;
    /**
     * 指标值映射结果
     */
    private String indexValue;
    /**
     * 数据关联状态：已关联评价/未关联评价
     */
    private String dataRelationStatus;
    /**
     * 问卷链接
     */
    private String link;
    /**
     * 问卷二维码
     */
    private String qrcode;
    /**
     * 问卷状态ID（关联sys_survey_status.status_id）
     */
    private Integer statusId;
    /**
     * 创建人ID（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 创建时间（业务字段）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 更新时间（业务字段）
     */
    private LocalDateTime bizUpdateTime;
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