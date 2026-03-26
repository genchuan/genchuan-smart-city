package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionplanmanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查计划管理 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_plan_management")
@KeySequence("smartcity_inspection_plan_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionPlanManagementDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 巡查区域
     */
    private String patrolArea;
    /**
     * 巡查周期
     */
    private String inspectionCycle;
    /**
     * 计划开始时间
     */
    private String scheduledStartTime;
    /**
     * 计划结束时间
     */
    private String plannedEndTime;
    /**
     * 巡查人员安排
     */
    private String arrangementOfPatrolPersonnel;
    /**
     * 巡查内容
     */
    private String inspectionContent;
    /**
     * 巡查标准
     */
    private String inspectionStandards;
    /**
     * 应急处置预案
     */
    private String emergencyResponsePlan;
    /**
     * 备注
     */
    private String notes;

}