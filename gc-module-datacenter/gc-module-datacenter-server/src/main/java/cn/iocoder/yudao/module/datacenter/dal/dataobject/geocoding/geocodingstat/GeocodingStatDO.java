package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstat;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 基本地点数据统计报表 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_stat")
@KeySequence("gc_geocoding_stat_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingStatDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String statId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 行政区划代码
     */
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
    /**
     * 区域数据数量
     */
    private Integer regionCount;
    /**
     * 地片与区片数据数量
     */
    private Integer zoneCount;
    /**
     * 街巷数据数量
     */
    private Integer streetCount;
    /**
     * 门牌楼牌数据数量
     */
    private Integer houseCount;
    /**
     * 兴趣点数据数量
     */
    private Integer poiCount;
    /**
     * 新增数据数量
     */
    private Integer newDataCount;
    /**
     * 更新数据数量
     */
    private Integer updateDataCount;
    /**
     * 统计人
     */
    private String statUser;
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