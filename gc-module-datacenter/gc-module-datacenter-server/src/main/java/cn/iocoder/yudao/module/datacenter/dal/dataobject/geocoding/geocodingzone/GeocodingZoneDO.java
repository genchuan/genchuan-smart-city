package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingzone;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 地片与区片数据管理 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_zone")
@KeySequence("gc_geocoding_zone_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingZoneDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 地片区片ID
     */
    private String zoneId;
    /**
     * 名称
     */
    private String zoneName;
    /**
     * 标识码
     */
    private String zoneCode;
    /**
     * 别名
     */
    private String alias;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 四至范围
     */
    private String boundary;
    /**
     * 所在街道名称
     */
    private String streetName;
    /**
     * 所在街道代码
     */
    private String streetCode;
    /**
     * 所在社区名称
     */
    private String communityName;
    /**
     * 所在社区代码
     */
    private String communityCode;
    /**
     * 空间表示类型
     */
    private String spaceType;
    /**
     * 标识点坐标X
     */
    private BigDecimal coordX;
    /**
     * 标识点坐标Y
     */
    private BigDecimal coordY;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 初始日期
     */
    private LocalDateTime initDate;
    /**
     * 变更日期
     */
    private LocalDateTime updateDate;
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