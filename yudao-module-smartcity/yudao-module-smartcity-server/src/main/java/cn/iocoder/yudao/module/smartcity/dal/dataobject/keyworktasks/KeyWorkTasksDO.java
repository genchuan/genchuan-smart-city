package cn.iocoder.yudao.module.smartcity.dal.dataobject.keyworktasks;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 重点工作任务 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_key_work_tasks")
@KeySequence("smartcity_key_work_tasks_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyWorkTasksDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务编号
     */
    private String taskNumber;
    /**
     * 任务名称
     */
    private String task;
    /**
     * 任务描述
     */
    private String taskDescription;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时
     */
    private String atTheEnd;
    /**
     * 考核指标
     */
    private String assessmentIndicators;
    /**
     * 风险评估
     */
    private String riskAssessment;
    /**
     * 备注
     */
    private String notes;

}