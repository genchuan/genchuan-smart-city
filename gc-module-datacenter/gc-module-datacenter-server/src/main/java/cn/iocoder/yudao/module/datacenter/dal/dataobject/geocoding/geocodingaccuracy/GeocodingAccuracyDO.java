package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingaccuracy;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 位置精度标准配置 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_accuracy")
@KeySequence("gc_geocoding_accuracy_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingAccuracyDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 精度配置ID
     */
    private String accuracyConfigId;
    /**
     * 地形类型
     */
    private String terrainType;
    /**
     * 点位中误差标准(米)
     */
    private BigDecimal positionErrorStd;
    /**
     * 最大限差(米)
     */
    private BigDecimal maxTolerance;
    /**
     * 适用数据类型
     */
    private String applicableDataType;
    /**
     * 比例尺说明
     */
    private String scaleDesc;
    /**
     * 启用状态(0-禁用,1-启用)
     */
    private Integer enableStatus;
    /**
     * 配置人
     */
    private String configUser;
    /**
     * 配置时间
     */
    private LocalDateTime configTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分类扩展字段1
     */
    private String extCategory1;
    /**
     * 分类扩展字段2
     */
    private String extCategory2;
    /**
     * 分类扩展字段3
     */
    private String extCategory3;

}