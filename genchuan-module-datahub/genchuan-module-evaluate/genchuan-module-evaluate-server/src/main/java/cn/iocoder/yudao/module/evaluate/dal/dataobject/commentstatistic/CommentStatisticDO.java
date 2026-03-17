package cn.iocoder.yudao.module.evaluate.dal.dataobject.commentstatistic;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查巡检统计 DO
 *
 * @author 亘川智城
 */
@TableName("eval_comment_statistic")
@KeySequence("eval_comment_statistic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentStatisticDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 指标项ID(关联指标项表的主键id  eval_index_item.id)
     */
    private Long itemId;
    /**
     * 街道：评价对象ID (关联eval_object.id)
     */
    private Long objectId;
    /**
     * 统计指标项数量
     */
    private Long count;
    /**
     * 关联到规则中回填的分数
     */
    private Long score;
    /**
     * 地址编码
     */
    private String addressCoding;
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
    /**
     * 变更日志
     */
    private String changeLog;
    /**
     * 状态: 1：待审核中，2：审核通过，3：不用审核
     */
    private String status;

}