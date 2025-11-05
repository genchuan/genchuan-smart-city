package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstreet;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 街巷数据管理 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_street")
@KeySequence("gc_geocoding_street_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingStreetDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 街巷ID
     */
    private String streetId;
    /**
     * 名称
     */
    private String streetName;
    /**
     * 标识码
     */
    private String streetCode;
    /**
     * 别名
     */
    private String alias;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 最小门牌号
     */
    private String minHouseNum;
    /**
     * 最大门牌号
     */
    private String maxHouseNum;
    /**
     * 起点名称
     */
    private String beginningName;
    /**
     * 止点名称
     */
    private String endingName;
    /**
     * 走向
     */
    private String direction;
    /**
     * 中心线坐标串
     */
    private String centerLineCoords;
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