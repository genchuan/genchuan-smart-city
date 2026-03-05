package cn.iocoder.yudao.module.evaluate.dal.dataobject.question;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 题目 DO
 *
 * @author 芋道源码
 */
@TableName("survey_question")
@KeySequence("survey_question_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 题目ID（UUID）
     */
    private String questionId;
    /**
     * 问卷ID（关联survey_questionnaire.questionnaire_id）
     */
    private String questionnaireId;
    /**
     * 题目名称
     */
    private String title;
    /**
     * 题目类型：单选/多选/打分题
     */
    private String questionType;
    /**
     * 分值设置，仅打分题
     */
    private String scoreRange;
    /**
     * 题目分值
     */
    private Integer score;
    /**
     * 创建人，关联sys_user.user_id
     */
    private Integer createBy;
    /**
     * 更新人，关联sys_user.user_id
     */
    private Integer updateBy;
    /**
     * 排序序号
     */
    private Integer sortNo;
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
