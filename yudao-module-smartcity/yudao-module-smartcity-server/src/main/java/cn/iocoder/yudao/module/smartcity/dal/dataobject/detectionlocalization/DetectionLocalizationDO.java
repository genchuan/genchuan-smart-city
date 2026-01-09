package cn.iocoder.yudao.module.smartcity.dal.dataobject.detectionlocalization;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 故障检测与定位 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_detection_localization")
@KeySequence("smartcity_detection_localization_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectionLocalizationDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 设备名称
     */
    private String equipmentName;
    /**
     * 设备编号
     */
    private String equipmentNumber;
    /**
     * 设备位置
     */
    private String deviceLocation;
    /**
     * 故障代码
     */
    private String faultCode;
    /**
     * 故障现象
     */
    private String faultPhenomenon;
    /**
     * 故障时间
     */
    private LocalDateTime mtbf;
    /**
     * 故障类型
     */
    private String faultType;
    /**
     * 传感器数据
     */
    private String sensorData;
    /**
     * 故障影响范围
     */
    private String scopeOfFaultImpact;
    /**
     * 故障严重程度
     */
    private String severityOfMalfunction;
    /**
     * 定位信息
     */
    private String locateInformation;
    /**
     * 人工确认情况
     */
    private String manuallyConfirmTheSituation;
    /**
     * 维修时间
     */
    private LocalDateTime maintenanceTime;
    /**
     * 维修结果
     */
    private String repairResults;
    /**
     * 恢复运行时间
     */
    private LocalDateTime restoreRunningTime;

}