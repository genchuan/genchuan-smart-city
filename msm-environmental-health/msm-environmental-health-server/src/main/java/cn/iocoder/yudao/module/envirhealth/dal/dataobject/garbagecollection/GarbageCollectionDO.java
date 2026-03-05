package cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收运计划 DO
 *
 * @author 芋道源码
 */
@TableName("garbage_collection")
@KeySequence("garbage_collection_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarbageCollectionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 收运计划主键（UUID）
     */
    private String collectionId;
    /**
     * 收运计划单编号
     */
    private String planNo;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 关联sys_garbage_type.id
     */
    private String garbageTypeId;
    /**
     * 收运频次
     */
    private String frequency;
    /**
     * 收运时段
     */
    private String timePeriod;
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleId;
    /**
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 收运点位IDs，JSON
     */
    private String pointIds;
    /**
     * 关联sys_plan_status.id
     */
    private String planStatusId;
    /**
     * 完成率
     */
    private BigDecimal completionRate;
    /**
     * 异常记录数
     */
    private Integer abnormalCount;
    /**
     * 收运计划创建时间（原create_time）
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 收运计划更新时间（原update_time）
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 关联sys_user.id
     */
    private String createBy;

    /**
     * 当前进度（按点位完成率计算）
     */
    private BigDecimal progress;

    /**
     * 已收运量（实时上报累计）
     */
    private BigDecimal collectedVolume;

    /**
     * 打卡状态：到岗/离岗
     */
    private String checkinStatus;

    /**
     * 轨迹覆盖情况（系统自动校验）
     */
    private String trackCoverage;

    /**
     * 最新上报时间
     */
    private LocalDateTime lastReportTime;

    /**
     * 是否异常（系统自动标记）
     */
    private Boolean isAbnormal;

    /**
     * 通用扩展字段1
     */
    @JsonIgnore
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    @JsonIgnore
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    @JsonIgnore
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    @JsonIgnore
    private String extCommon4;

    /**
     * 完成时间
     */
    private LocalDateTime completeTime;

    /**
     * 总收运量
     */
    private BigDecimal totalVolume;

    /**
     * 异常处置结果：无/已办结/部分办结
     */
    private String abnormalResult;

    /**
     * 异常办结率（自动计算）
     */
    private BigDecimal abnormalCompleteRate;

}