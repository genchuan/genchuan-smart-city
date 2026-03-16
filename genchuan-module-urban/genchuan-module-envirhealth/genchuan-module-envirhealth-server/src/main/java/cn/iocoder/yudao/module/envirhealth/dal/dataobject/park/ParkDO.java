package cn.iocoder.yudao.module.envirhealth.dal.dataobject.park;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 公园 DO
 *
 * @author 芋道源码
 */
@TableName("park")
@KeySequence("park_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 主键（UUID）
     */
    private String parkId;
    /**
     * 公园名称
     */
    private String name;
    /**
     * 公园地址
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
     * 绿化养护周期
     */
    private String greenMaintenanceCycle;
    /**
     * 关联sys_user.id
     */
    private String managerId;
    /**
     * 关联sys_operation_status.id
     */
    private String operationStatusId;
    /**
     * 保洁达标率
     */
    private BigDecimal cleaningRate;
    /**
     * 绿化存活率
     */
    private BigDecimal greenSurvivalRate;
    /**
     * 设施完好率
     */
    private BigDecimal facilityRate;
    /**
     * 环境达标率
     */
    private BigDecimal environmentRate;
    /**
     * 垃圾清运完成率
     */
    private BigDecimal wasteTransferCompleteRate;
    /**
     * 保洁区域
     */
    private String cleaningArea;
    /**
     * 保洁标准
     */
    private String cleaningStandard;
    /**
     * 负责人员IDs，JSON
     */
    private String staffIds;
    /**
     * 绿化品类IDs，JSON
     */
    private String greenTypeIds;
    /**
     * 养护区域
     */
    private String greenArea;
    /**
     * 养护内容
     */
    private String greenMaintenanceContent;
    /**
     * 养护人员IDs，JSON
     */
    private String greenStaffIds;
    /**
     * 垃圾收集点位
     */
    private Integer wasteCollectionPoints;
    /**
     * 垃圾清运频次
     */
    private String wasteTransferFrequency;
    /**
     * 清运时段
     */
    private String wasteTransferTime;
    /**
     * 关联sys_vehicle.id
     */
    private String vehicleId;
    /**
     * 垃圾清运量（单位：吨）
     */
    private BigDecimal wasteVolume;
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
    private String facilityDamageDesc;
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
    private String facilityPhotoUrl;
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

    private String planStatusId;

    private String taskTypeId;
}