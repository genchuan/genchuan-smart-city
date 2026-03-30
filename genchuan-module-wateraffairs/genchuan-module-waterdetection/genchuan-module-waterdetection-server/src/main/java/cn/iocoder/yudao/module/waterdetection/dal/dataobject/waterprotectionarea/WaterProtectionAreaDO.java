package cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterprotectionarea;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 水源保护区管理 DO
 *
 * @author zcq
 */
@TableName("gc_water_protection_area")
@KeySequence("gc_water_protection_area_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterProtectionAreaDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 保护区级别
     */
    private String protectionLevel;
    /**
     * 边界经纬度范围
     */
    private String boundaryRange;
    /**
     * 标识牌编号
     */
    private String signboardNo;
    /**
     * 标识牌位置
     */
    private String signboardLocation;
    /**
     * 安装时间
     */
    private LocalDateTime installTime;
    /**
     * 维护记录
     */
    private String maintenanceRecord;
    /**
     * 污染源治理状态
     */
    private String pollutionStatus;

}