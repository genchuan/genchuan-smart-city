package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingpoi;

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
 * 兴趣点数据管理 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_poi")
@KeySequence("gc_geocoding_poi_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingPoiDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 兴趣点ID
     */
    private String poiId;
    /**
     * 名称
     */
    private String poiName;
    /**
     * 标识码
     */
    private String poiCode;
    /**
     * 内部编码
     */
    private String intraCode;
    /**
     * 北斗网格位置码
     */
    private String bdGridCode;
    /**
     * 曾用名
     */
    private String oldName;
    /**
     * 所在层
     */
    private String floor;
    /**
     * 位置点坐标X
     */
    private BigDecimal coordX;
    /**
     * 位置点坐标Y
     */
    private BigDecimal coordY;
    /**
     * 所属类型
     */
    private String poiType;
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