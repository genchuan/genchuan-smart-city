package cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleabnormality;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 车辆异常 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_vehicle_abnormality")
@KeySequence("smartcity_vehicle_abnormality_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAbnormalityDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 车辆编号
     */
    private String vehicleNumber;
    /**
     * 异常发生时间
     */
    private LocalDateTime abnormalOccurrenceTime;
    /**
     * 异常地点
     */
    private String abnormalLocation;
    /**
     * 异常描述
     */
    private String abnormalDescription;
    /**
     * 车辆状态
     */
    private String vehicleStatus;
    /**
     * 影响程度
     */
    private String degreeOfImpact;
    /**
     * 处理措施
     */
    private String handlingMeasures;
    /**
     * 处理时间
     */
    private LocalDateTime processingTime;
    /**
     * 处理人员
     */
    private String processingPersonnel;
    /**
     * 维修费用
     */
    private String maintenanceCosts;

}