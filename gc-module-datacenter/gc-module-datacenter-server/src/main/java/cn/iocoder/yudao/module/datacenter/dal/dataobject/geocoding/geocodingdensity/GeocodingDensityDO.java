package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingdensity;

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
 * 数据采集密度配置 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_density")
@KeySequence("gc_geocoding_density_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingDensityDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 密度配置ID
     */
    private String densityConfigId;
    /**
     * 区域类别
     */
    private String regionCategory;
    /**
     * 区域类别说明
     */
    private String regionCategoryDesc;
    /**
     * 门牌楼牌间隔(米)
     */
    private BigDecimal houseInterval;
    /**
     * 兴趣点间隔(米)
     */
    private BigDecimal poiInterval;
    /**
     * 特殊兴趣点采集要求
     */
    private String specialPoiRequirement;
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