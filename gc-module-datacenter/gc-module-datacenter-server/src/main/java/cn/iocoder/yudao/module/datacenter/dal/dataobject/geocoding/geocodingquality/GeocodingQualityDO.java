package cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingquality;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 地理编码数据质量统计报表 DO
 *
 * @author zcq
 */
@TableName("gc_geocoding_quality")
@KeySequence("gc_geocoding_quality_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeocodingQualityDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 质量统计ID
     */
    private String qualityStatId;
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
     * 检查数据总量
     */
    private Integer totalCheckCount;
    /**
     * 属性完整性正确率(%)
     */
    private BigDecimal attributeIntegrityRate;
    /**
     * 位置精度合格率(%)
     */
    private BigDecimal positionAccuracyRate;
    /**
     * 数据更新及时率(%)
     */
    private BigDecimal dataUpdateTimelyRate;
    /**
     * 属性错误数据数量
     */
    private Integer attributeErrorCount;
    /**
     * 位置精度不合格数据数量
     */
    private Integer positionErrorCount;
    /**
     * 未及时更新数据数量
     */
    private Integer updateDelayedCount;
    /**
     * 质量整改建议
     */
    private String qualityImprovementSuggestion;
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