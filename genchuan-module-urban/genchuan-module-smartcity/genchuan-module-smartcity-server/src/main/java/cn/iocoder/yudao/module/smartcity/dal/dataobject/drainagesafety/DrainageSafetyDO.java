package cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagesafety;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 排水安全 DO
 *
 * @author 智慧城市运行管理服务平台
 */
@TableName("smartcity_drainage_safety")
@KeySequence("smartcity_drainage_safety_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrainageSafetyDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 预警编号
     */
    private String warningNumber;
    /**
     * 预警时间
     */
    private String warningTime;
    /**
     * 预警级别
     */
    private String warningLevel;
    /**
     * 预警区域
     */
    private String warningArea;
    /**
     * 监测点位
     */
    private String monitoringPoints;
    /**
     * 实时水位
     */
    private String realTimeWaterLevel;
    /**
     * 警戒水位
     */
    private String warningWaterLevel;
    /**
     * 水位变化速率
     */
    private String waterLevelRate;
    /**
     * 雨量
     */
    private String rainfall;

}