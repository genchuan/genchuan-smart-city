package cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 收运计划 DO
 *
 * @author 亘川智城
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
     * 业务主键（UUID）
     */
    private String garbageCollectionId;
    /**
     * 收运计划单编号
     */
    private String planNo;
    /**
     * 收运品类（关联sys_garbage_type.id）
     */
    private String garbageTypeId;
    /**
     * 收运区域（关联sys_area.area_code）
     */
    private String areaCode;
    /**
     * 收运点位ID，多个用逗号分隔
     */
    private String pointIds;
    /**
     * 收运频次
     */
    private String frequency;
    /**
     * 收运时段
     */
    private String timePeriod;
    /**
     * 负责车辆（关联sys_vehicle.sys_vehicle_id）
     */
    private String vehicleId;
    /**
     * 负责人员，多个用逗号分隔
     */
    private String staffIds;
    /**
     * 计划状态（关联sys_plan_status.sys_plan_status_id）
     */
    private String planStatusId;
    /**
     * 业务创建人（关联sys_user.id）
     */
    private String abnormalCreateBy;
    /**
     * 业务创建时间
     */
    private LocalDateTime abnormalCreateTime;
    /**
     * 业务更新时间
     */
    private LocalDateTime abnormalUpdateTime;
    /**
     * 完成率
     */
    private BigDecimal completionRate;
    /**
     * 异常记录数
     */
    private Integer abnormalCount;
    /**
     * 异常处置明细ID，多个用逗号分隔
     */
    private String abnormalDetailIds;
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

}