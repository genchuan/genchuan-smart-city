package cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentmaintenance;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 设备保养计划管理 DO
 *
 * @author zcq
 */
@TableName("gc_equipment_maintenance")
@KeySequence("gc_equipment_maintenance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentMaintenanceDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 设备ID
     */
    private String equipmentId;
    /**
     * 设备类型
     */
    private String equipmentType;
    /**
     * 保养周期(天)
     */
    private Double maintenanceCycle;
    /**
     * 计划保养日期
     */
    private LocalDateTime planMaintenanceDate;
    /**
     * 实际保养日期
     */
    private LocalDateTime actualMaintenanceDate;
    /**
     * 保养内容
     */
    private String maintenanceContent;
    /**
     * 更换部件名称
     */
    private String replacedParts;
    /**
     * 保养后运行参数
     */
    private String postMaintenanceParams;
    /**
     * 维护人员ID
     */
    private String maintenanceStaffId;

}