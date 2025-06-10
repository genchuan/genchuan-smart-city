package cn.iocoder.yudao.module.smartcity.dal.dataobject.roadcollapsesafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 路面塌陷安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_road_collapse_safety")
@KeySequence("smartcity_road_collapse_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadCollapseSafetyDO extends BaseDO {

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
    private String locationMonitoringPoints;
    /**
     * 路面状况
     */
    private String roadCondition;
    /**
     * 地下空洞信息
     */
    private String undergroundCavityInformation;
    /**
     * 土壤湿度
     */
    private String soilMoisture;
    /**
     * 土壤位移数据
     */
    private String soilDisplacementData;
    /**
     * 周边建筑物沉降情况
     */
    private String settlementSurroundingBuildings;
    /**
     * 地下水位变化
     */
    private String changesGroundwaterLevel;
    /**
     * 降雨情况
     */
    private String rainfallSituation;
    /**
     * 交通流量
     */
    private String trafficFlow;
    /**
     * 重型车辆通行情况
     */
    private String trafficHeavyVehicles;
    /**
     * 预警级别
     */
    private String warningLevel;
    /**
     * 预警时间
     */
    private LocalDateTime warningTime;
    /**
     * 专家评估意见
     */
    private String expertEvaluationOpinions;
    /**
     * 历史塌陷记录
     */
    private String historicalCollapseRecords;

}