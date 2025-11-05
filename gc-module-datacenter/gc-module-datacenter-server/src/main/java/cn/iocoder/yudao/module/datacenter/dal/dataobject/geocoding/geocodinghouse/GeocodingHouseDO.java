package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinghouse;

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
 * 门(楼)牌数据管理 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_house")
@KeySequence("gc_geocoding_house_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingHouseDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 门牌楼牌ID
     */
    private String houseId;
    /**
     * 名称
     */
    private String houseName;
    /**
     * 标识码
     */
    private String houseCode;
    /**
     * 北斗网格位置码
     */
    private String bdGridCode;
    /**
     * 所在街巷
     */
    private String streetName;
    /**
     * 所在地片/区片
     */
    private String zoneName;
    /**
     * 门(楼)牌号
     */
    private String houseNum;
    /**
     * 所在街道名称
     */
    private String parentStreetName;
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
     * 位置点坐标X
     */
    private BigDecimal coordX;
    /**
     * 位置点坐标Y
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