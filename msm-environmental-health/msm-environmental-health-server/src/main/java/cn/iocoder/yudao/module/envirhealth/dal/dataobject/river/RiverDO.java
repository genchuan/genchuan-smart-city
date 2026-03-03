package cn.iocoder.yudao.module.envirhealth.dal.dataobject.river;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 河道 DO
 *
 * @author 芋道源码
 */
@TableName("river")
@KeySequence("river_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiverDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String riverId;
    /**
     * 河道名称
     */
    private String name;
    /**
     * 责任河段
     */
    private String responsibilitySection;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 河道长度，单位：公里
     */
    private BigDecimal length;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 保洁覆盖率
     */
    private BigDecimal cleaningCoverage;
    /**
     * 水质达标率
     */
    private BigDecimal waterQualityRate;
    /**
     * 垃圾打捞总量（单位：吨）
     */
    private BigDecimal wasteFishingVolume;
    /**
     * 问题办结率
     */
    private BigDecimal problemCompleteRate;
    /**
     * 关联sys_cleaning_type.id
     */
    private String cleaningTypeId;
    /**
     * 保洁频次
     */
    private String waterCleaningFrequency;
    /**
     * 保洁时段
     */
    private String cleaningTime;
    /**
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 保洁工具IDs，JSON
     */
    private String toolIds;
    /**
     * 垃圾打捞预估量（单位：吨）
     */
    private BigDecimal wasteFishingEstimate;
    /**
     * 关联sys_monitor_type.id
     */
    private String monitorTypeId;
    /**
     * 监测周期
     */
    private String waterQualityCycle;
    /**
     * 监测指标，JSON
     */
    private String monitorIndicators;
    /**
     * 关联sys_user.id
     */
    private String monitorBy;
    /**
     * 计划监测时间
     */
    private LocalDateTime planMonitorTime;
    /**
     * 关联sys_monitor_status.id
     */
    private String monitorStatusId;
    /**
     * 上次监测时间
     */
    private LocalDateTime lastMonitorTime;
    /**
     * 下次监测提醒时间
     */
    private LocalDateTime nextMonitorRemindTime;
    /**
     * 监测数据达标率
     */
    private BigDecimal monitorDataQualifiedRate;
    /**
     * 预警次数
     */
    private Integer warningCount;
    /**
     * 关联sys_problem_type.id
     */
    private String problemTypeId;
    /**
     * 问题位置
     */
    private String problemLocation;
    /**
     * 问题描述
     */
    private String problemDesc;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 现场照片/视频URL
     */
    private String problemMediaUrl;
    /**
     * 关联sys_dept.id
     */
    private String deptId;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 关联sys_handle_status.id
     */
    private String handleStatusId;
    /**
     * 超时提醒：是/否
     */
    private String isTimeout;
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

    private String taskTypeId;

}