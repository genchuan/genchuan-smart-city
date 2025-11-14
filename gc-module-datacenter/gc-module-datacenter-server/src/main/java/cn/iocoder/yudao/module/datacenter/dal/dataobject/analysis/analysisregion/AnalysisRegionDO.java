package cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisregion;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按行政区划分析研判统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_analysis_region")
@KeySequence("stat_analysis_region_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisRegionDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID，唯一编码，UUID生成
     */
    private String statAnalysisId;
    /**
     * 统计周期，格式：年/季/月，如YYYY/YYYYQn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期名称，中文描述，如“2025年Q3”
     */
    private String statCycleName;
    /**
     * 行政区划代码，符合GB/T 2260，关联行政区划表
     */
    private String regionCode;
    /**
     * 行政区划名称，与代码同步，关联行政区划表
     */
    private String regionName;
    /**
     * 行政区划级别，标识行政层级，如省级/市级/县级/街道级/社区级
     */
    private String regionLevel;
    /**
     * 正常监测部件数，周期内该区域正常监测部件数量
     */
    private Integer normalMonCompCount;
    /**
     * 异常监测部件数，周期内该区域异常监测部件数量
     */
    private Integer abnMonCompCount;
    /**
     * 监测事件办结率，周期内该区域监测事件办结率，0.00-100.00
     */
    private BigDecimal monEvtCompleteRate;
    /**
     * 统计人，生成报表的用户ID，关联用户信息表
     */
    private String statUser;
    /**
     * 统计时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime statTime;
    /**
     * 报表备注，如“统计范围:该区域建成区”
     */
    private String rptRemark;
    /**
     * 分类扩展字段1，预留，如“统计范围”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“统计范围”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储额外统计维度
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储额外统计维度
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}