package cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet;

import lombok.*;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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

}