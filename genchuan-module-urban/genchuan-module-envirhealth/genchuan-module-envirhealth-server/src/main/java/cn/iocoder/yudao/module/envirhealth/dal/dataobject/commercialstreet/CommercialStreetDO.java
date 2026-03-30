package cn.iocoder.yudao.module.envirhealth.dal.dataobject.commercialstreet;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商业街 DO
 *
 * @author 芋道源码
 */
@TableName("commercial_street")
@KeySequence("commercial_street_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommercialStreetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String streetId;
    /**
     * 商业街名称
     */
    private String name;
    /**
     * 商业街地址
     */
    private String address;
    /**
     * 关联sys_area.area_code
     */
    private String areaCode;
    /**
     * 保洁频次
     */
    private String cleaningFrequency;
    /**
     * 垃圾清运间隔
     */
    private String transferInterval;
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
     * 设施完好率
     */
    private BigDecimal facilityRate;
    /**
     * 问题平均处置时长（单位：小时）
     */
    private BigDecimal disposalDuration;
    /**
     * 收运完成率
     */
    private BigDecimal collectionCompleteRate;
    /**
     * 巡回保洁间隔
     */
    private String patrolInterval;
    /**
     * 保洁时段
     */
    private String cleaningTime;
    /**
     * 保洁人员IDs，JSON
     */
    private String cleanerIds;
    /**
     * 责任区域
     */
    private String responsibilityArea;
    /**
     * 垃圾收集点位数量
     */
    private Integer collectionPoints;
    /**
     * 异常记录数
     */
    private Integer abnormalCount;
    /**
     * 设施类型IDs，JSON
     */
    private String facilityIds;
    /**
     * 设施位置
     */
    private String facilityLocation;
    /**
     * 损坏描述
     */
    private String damageDesc;
    /**
     * 关联sys_user.id
     */
    private String reportBy;
    /**
     * 上报时间
     */
    private LocalDateTime reportTime;
    /**
     * 上报照片URL，JSON
     */
    private String problemPhotoUrl;
    /**
     * 关联sys_user.id
     */
    private String handleBy;
    /**
     * 派单时间
     */
    private LocalDateTime dispatchTime;
    /**
     * 关联sys_maintain_status.id
     */
    private String maintainStatusId;
    /**
     * 预计完成时间
     */
    private LocalDateTime expectedCompleteTime;
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
     * 关联sys_handle_status.id
     */
    private String handleStatusId;
    /**
     * 处置结果
     */
    private String handleResult;
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
     * 负责车辆ID，关联sys_vehicle.sys_vehicle_id（支持车辆钻取）
     */
    private String vehicleId;
    /**
     * 负责人员IDs，JSON格式，关联sys_user.user_id
     */
    private String staffIds;
    /**
     * 收运计划状态ID，关联sys_plan_status.sys_plan_status_id（支持状态筛选钻取）
     */
    private String planStatusId;

    private String taskTypeId;
}