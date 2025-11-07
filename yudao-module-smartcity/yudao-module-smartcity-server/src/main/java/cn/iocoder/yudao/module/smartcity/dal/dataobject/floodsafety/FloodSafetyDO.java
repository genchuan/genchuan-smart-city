package cn.iocoder.yudao.module.smartcity.dal.dataobject.floodsafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 内涝安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_flood_safety")
@KeySequence("smartcity_flood_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FloodSafetyDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 监测点名称
     */
    private String monitoringPointName;
    /**
     * 监测点位置
     */
    private String locationOfMonitoringPoints;
    /**
     * 积水深度
     */
    private String depthOfAccumulatedWater;
    /**
     * 积水面积
     */
    private String catchmentArea;
    /**
     * 水流速度
     */
    private String waterFlowVelocity;
    /**
     * 水位变化趋势
     */
    private String trendOfWaterLevelChanges;
    /**
     * 降雨量
     */
    private String rainfall;
    /**
     * 降雨强度
     */
    private String rainfallIntensity;
    /**
     * 管道堵塞情况
     */
    private String pipelineBlockageSituation;
    /**
     * 泵站运行状态
     */
    private String pumpStationOperationStatus;
    /**
     * 设备故障信息
     */
    private String equipmentFaultInformation;
    /**
     * 预警级别
     */
    private String warningLevel;
    /**
     * 预警时间

     */
    private LocalDateTime warningTime;

}