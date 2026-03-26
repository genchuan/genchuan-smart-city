package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectiontaska;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查任务 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_task_a")
@KeySequence("smartcity_inspection_task_a_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionTaskADO extends BaseDO {

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
     * 巡查地点开始时间
     */
    private String startingTimeA;
    /**
     * 结束时间优先级
     */
    private String endTimeB;
    /**
     * 检查项目
     */
    private String inspectionItems;
    /**
     * 备注
     */
    private String notes;

}