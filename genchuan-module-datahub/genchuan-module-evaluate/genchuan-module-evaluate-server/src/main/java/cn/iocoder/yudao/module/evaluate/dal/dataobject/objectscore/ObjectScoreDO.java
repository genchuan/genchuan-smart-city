package cn.iocoder.yudao.module.evaluate.dal.dataobject.objectscore;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公司得分 DO
 *
 * @author 亘川智城
 */
@TableName("eval_object_score")
@KeySequence("eval_object_score_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObjectScoreDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 对象ID (关联eval_object.id)
     */
    private Long objectId;
    /**
     * 体系ID (关联eval_index_system.id)
     */
    private Long systemId;
    /**
     * 巡检人ID(关联sys_user.id)
     */
    private Long userId;
    /**
     * 总得分
     */
    private Long score;
    /**
     * 状态: 1：待审核中，2：审核通过，3：不用审核
     */
    private String status;
    /**
     * 评价说明
     */
    private String details;
    /**
     * 变更日志
     */
    private String changeLog;


}