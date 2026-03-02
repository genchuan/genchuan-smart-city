package cn.iocoder.yudao.module.facility.dal.dataobject.road.roadconfig;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 道路监测配置 DO
 *
 * @author 亘川智城
 */
@TableName("road_config")
@KeySequence("road_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoadConfigDO extends BaseDO {

    /**
     * [主键ID] 主键，道路监测配置唯一标识
     */
    @TableId
    private Long id;
    /**
     * [配置编码] UUID格式
     */
    private String configCode;
    /**
     * [配置名称]
     */
    private String name;
    /**
     * [道路ID] 关联road_facility.id
     */
    private Long roadId;
    /**
     * [道路名称]
     */
    private String roadName;
    /**
     * [采集频率] 数据采集频率，单位：分钟
     */
    private Integer collectFrequency;
    /**
     * [坑洼数量阈值] 坑洼数量阈值
     */
    private BigDecimal potholeNumThreshold;
    /**
     * [裂缝长度阈值] 裂缝长度阈值，单位：米
     */
    private BigDecimal crackLengthThreshold;
    /**
     * [路面温度阈值] 路面温度阈值，单位：摄氏度
     */
    private BigDecimal roadTempThreshold;
    /**
     * [交通流量阈值] 交通流量阈值，单位：辆/小时
     */
    private BigDecimal trafficFlowThreshold;
    /**
     * [通用扩展字段1] 通用扩展字段1
     */
    private String extCommon1;
    /**
     * [通用扩展字段2] 通用扩展字段2
     */
    private String extCommon2;
    /**
     * [通用扩展字段3] 通用扩展字段3
     */
    private String extCommon3;
    /**
     * [通用扩展字段4] 通用扩展字段4
     */
    private String extCommon4;

}
