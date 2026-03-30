package cn.iocoder.yudao.module.evaluate.dal.dataobject.option;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 选项 DO
 *
 * @author 芋道源码
 */
@TableName("survey_option")
@KeySequence("survey_option_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 选项ID（UUID）
     */
    private String optionId;
    /**
     * 题目ID（关联survey_question.question_id）
     */
    private String questionId;
    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 排序序号
     */
    private Integer sortNo;
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
