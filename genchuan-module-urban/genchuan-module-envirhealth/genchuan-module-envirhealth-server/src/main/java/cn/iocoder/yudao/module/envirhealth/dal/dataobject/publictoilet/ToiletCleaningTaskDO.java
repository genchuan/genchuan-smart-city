package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 公厕保洁任务 DO
 *
 * @author 亘川智城
 */
@TableName("public_toilet_cleaning_task")
@KeySequence("public_toilet_cleaning_task_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ToiletCleaningTaskDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 公厕ID，关联public_toilet.id
     */
    private String toiletId;
    /**
     * 任务编号
     */
    private String taskNo;
    /**
     * 保洁频次，如每天2次
     */
    private String cleaningFrequency;
    /**
     * 保洁时段
     */
    private String cleaningTime;
    /**
     * 保洁内容，如地面清洁/便池清洁/垃圾清理
     */
    private String cleaningContent;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 保洁人员IDs，JSON数组格式
     */
    private String cleanerIds;
    /**
     * 计划状态ID，关联sys_plan_status.id
     */
    private String planStatusId;
    /**
     * 完成率，%
     */
    private BigDecimal completionRate;
    /**
     * 是否异常：0-正常，1-异常
     */
    private Integer isAbnormal;
    /**
     * 异常描述
     */
    private String abnormalDesc;
    /**
     * 佐证材料URL，JSON数组格式
     */
    private String proofUrls;

    // ============ 新增字段 ============
    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 处置结果
     */
    private String handleResult;

    /**
     * 任务耗时（分钟）
     */
    private Integer handleDuration;

    /**
     * 满意度：1-不满意，2-一般，3-满意，4-非常满意
     */
    private String satisfaction;

    /**
     * 统计周期，如2026-03
     */
    private String statPeriod;

    /**
     * 复盘意见
     */
    private String reviewDesc;
}