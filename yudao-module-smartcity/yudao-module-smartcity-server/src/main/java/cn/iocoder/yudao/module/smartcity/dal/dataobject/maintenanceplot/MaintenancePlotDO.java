package cn.iocoder.yudao.module.smartcity.dal.dataobject.maintenanceplot;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 养护地块 DO
 *
 * @author zcq
 */
@TableName("smartcity_maintenance_plot")
@KeySequence("smartcity_maintenance_plot_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenancePlotDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 地块名称
     */
    private String plotName;
    /**
     * 地块描述
     */
    private String plotDescription;
    /**
     * 归属信息
     */
    private String ownership;
    /**
     * 地块面积
     */
    private String acreage;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 绿化类型
     */
    private String greeningType;
    /**
     * 上次养护的日期
     */
    private LocalDateTime lastMaintenanceDate;
    /**
     * 下次养护的计划日期
     */
    private LocalDateTime nextMaintenanceDate;

}