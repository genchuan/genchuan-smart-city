package cn.iocoder.yudao.module.waterdetection.dal.dataobject.testprogress;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测进度跟踪 DO
 *
 * @author zcq
 */
@TableName("gc_test_progress")
@KeySequence("gc_test_progress_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestProgressDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 任务编号
     */
    private String taskCode;
    /**
     * 当前进度(%)
     */
    private Double progressPercent;
    /**
     * 已完成指标
     */
    private String completedIndicators;
    /**
     * 未完成指标
     */
    private String pendingIndicators;
    /**
     * 预计完成时间
     */
    private LocalDateTime estimatedCompletion;
    /**
     * 延迟原因(如有)
     */
    private String delayReason;

}