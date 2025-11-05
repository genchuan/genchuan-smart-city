package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingregion;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 区域数据管理 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_region")
@KeySequence("gc_geocoding_region_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingRegionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 区域ID
     */
    private String regionId;
    /**
     * 名称
     */
    private String regionName;
    /**
     * 标识码
     */
    private String regionCode;
    /**
     * 别名
     */
    private String alias;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 边界描述
     */
    private String boundaryDesc;
    /**
     * 所在行政区域代码
     */
    private String parentRegionCode;
    /**
     * 所在街道代码
     */
    private String streetCode;
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