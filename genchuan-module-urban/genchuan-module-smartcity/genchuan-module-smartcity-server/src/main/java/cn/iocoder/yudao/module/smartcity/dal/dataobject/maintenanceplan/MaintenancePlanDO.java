package cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 养护计划 DO
 *
 * @author zcq
 */
@TableName("smartcity_maintenance_plan")
@KeySequence("smartcity_maintenance_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenancePlanDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 计划编号
     */
    private String planNumber;
    /**
     * 计划名称
     */
    private String planName;
    /**
     * 计划制定日期
     */
    private LocalDateTime planFormulationDate;
    /**
     * 计划开始日期
     */
    private LocalDateTime startDate;
    /**
     * 计划结束日期
     */
    private LocalDateTime plannedEndDate;
    /**
     * 负责养护人员
     */
    private String maintenancePersonnel;
    /**
     * 养护地块
     */
    private String maintainParcel;
    /**
     * 养护内容
     */
    private String maintenanceContent;
    /**
     * 养护频率
     */
    private String maintenanceFrequency;
    /**
     * 养护资源需求
     */
    private String maintenanceDemand;
    /**
     * 计划状态
     */
    private String planStatus;
    /**
     * 计划预算
     */
    private String plannedBudget;
    /**
     * 审批人
     */
    private String approver;
    /**
     * 审批意见
     */
    private String approvalOpinion;
    /**
     * 审批日期
     */
    private LocalDateTime approvalDate;

}