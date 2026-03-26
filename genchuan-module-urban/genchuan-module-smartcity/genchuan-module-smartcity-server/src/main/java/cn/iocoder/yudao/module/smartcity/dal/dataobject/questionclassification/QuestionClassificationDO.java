package cn.iocoder.yudao.module.smartcity.dal.dataobject.questionclassification;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 问题录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_question_classification")
@KeySequence("smartcity_question_classification_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionClassificationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 所属领域
     */
    private String isArea;
    /**
     * 问题类型
     */
    private String questionType;
    /**
     * 紧急程度
     */
    private String urgency;
    /**
     * 涉及主体
     */
    private String involvingTheSubject;

}