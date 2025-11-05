package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingsegmentrule;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 地理编码分段组合规则配置 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_segmentrule")
@KeySequence("gc_geocoding_segmentrule_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingSegmentruleDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 规则ID
     */
    private String segmentRuleId;
    /**
     * 定位对象类型
     */
    private String locationType;
    /**
     * 分段规则
     */
    private String segmentRule;
    /**
     * 组合规则
     */
    private String combineRule;
    /**
     * 方位描述选项
     */
    private String directionOptions;
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