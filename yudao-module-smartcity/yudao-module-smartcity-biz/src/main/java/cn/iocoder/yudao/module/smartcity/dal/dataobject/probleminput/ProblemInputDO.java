package cn.iocoder.yudao.module.smartcity.dal.dataobject.probleminput;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 问题录入 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_problem_input")
@KeySequence("smartcity_problem_input_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemInputDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 问题标题
     */
    private String questionTitle;
    /**
     * 问题描述
     */
    private String problemDescription;
    /**
     * 提问时间
     */
    private LocalDateTime questionTime;
    /**
     * 提问人
     */
    private String questioner;
    /**
     * 所属领域
     */
    private String isArea;
    /**
     * 紧急程度
     */
    private String urgency;
    /**
     * 问题类型
     */
    private String questionType;

}