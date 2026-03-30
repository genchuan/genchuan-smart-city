package cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterbalance;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 水量平衡与漏损分析 DO
 *
 * @author zcq
 */
@TableName("gc_water_balance")
@KeySequence("gc_water_balance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterBalanceDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 分区ID
     */
    private String partitionId;
    /**
     * 统计周期(日/月/年)
     */
    private String statisticsPeriod;
    /**
     * 统计日期
     */
    private LocalDateTime statisticsDate;
    /**
     * 供水量(立方米)
     */
    private Double supplyVolume;
    /**
     * 售水量(立方米)
     */
    private Double salesVolume;
    /**
     * 合理损耗量(立方米)
     */
    private Double reasonableLoss;
    /**
     * 漏损量(立方米)
     */
    private Double leakageVolume;
    /**
     * 漏损率(%)
     */
    private Double leakageRate;
    /**
     * 是否超标(0否1是)
     */
    private Boolean isExceeded;

}