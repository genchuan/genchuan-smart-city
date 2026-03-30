package cn.iocoder.yudao.module.smartcity.dal.dataobject.patroltaskmanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡査任务管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_patrol_task_management")
@KeySequence("smartcity_patrol_task_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatrolTaskManagementDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 任务名称
     */
    private String task;
    /**
     * 所属计划
     */
    private String belongingPlan;
    /**
     * 巡查区域
     */
    private String patrolArea;
    /**
     * 巡查时间
     */
    private String patrolTime;
    /**
     * 执行人员
     */
    private String executive;
    /**
     * 预计时长
     */
    private String expectedDuration;
    /**
     * 任务描述
     */
    private String taskDescription;
    /**
     * 巡查重点
     */
    private String keyInspectionPoints;
    /**
     * 携带设备清单
     */
    private String listOfCarryingEquipment;
    /**
     * 完成情况说明
     */
    private String completionStatusDescription;
    /**
     * 异常情况记录
     */
    private String abnormalSituationRecord;
    /**
     * 处理措施
     */
    private String handlingMeasures;

}