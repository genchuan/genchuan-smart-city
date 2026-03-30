package cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterhydrologyparam;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 水源水文参数管理 DO
 *
 * @author zcq
 */
@TableName("gc_water_hydrology_param")
@KeySequence("gc_water_hydrology_param_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterHydrologyParamDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 监测时间
     */
    private LocalDateTime monitorTime;
    /**
     * 水位值(米)
     */
    private Double waterLevel;
    /**
     * 含水层厚度(米)
     */
    private Double aquiferThickness;
    /**
     * 渗透系数(m/d)
     */
    private Double permeabilityCoefficient;
    /**
     * 数据采集人
     */
    private String dataCollector;

}