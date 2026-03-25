package cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectioninspectionplan;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 巡查计划 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_inspection_inspection_plan")
@KeySequence("smartcity_inspection_inspection_plan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InspectionInspectionPlanDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 巡查项目
     */
    private String inspectionProject;
    /**
     * 巡查地点
     */
    private String patrolLocation;
    /**
     * 巡查周期
     */
    private String inspectionCycle;
    /**
     * 巡查时间
     */
    private LocalDateTime patrolTime;
    /**
     * 巡查方式
     */
    private String inspectionMethod;
    /**
     * 备注
     */
    private String notes;

}