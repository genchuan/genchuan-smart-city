package cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersourcemanagement;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 水源类型及属性管理 DO
 *
 * @author zcq
 */
@TableName("gc_water_source_management")
@KeySequence("gc_water_source_management_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WaterSourceManagementDO extends BaseDO {

    /**
     * 序号
     */
    @TableId
    private Long id;
    /**
     * 水源编码
     */
    private String sourceCode;
    /**
     * 水源名称
     */
    private String sourceName;
    /**
     * 水源类型
     */
    private String sourceType;
    /**
     * 经度
     */
    private Double longitude;
    /**
     * 纬度
     */
    private Double latitude;
    /**
     * 所属行政区
     */
    private String administrativeRegion;
    /**
     * 水源描述
     */
    private String sourceDescription;

}