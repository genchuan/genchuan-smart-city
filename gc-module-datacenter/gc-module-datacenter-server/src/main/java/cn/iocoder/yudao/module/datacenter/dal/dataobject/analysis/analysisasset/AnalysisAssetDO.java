package cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisasset;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按资产分域分析研判统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_analysis_asset")
@KeySequence("stat_analysis_asset_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisAssetDO extends BaseDO {

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
     * 统计周期名称，如“2025年Q3”
     */
    private String statCycleName;
    /**
     * 资产大类ID，关联资产分类表
     */
    private String assetMajorId;
    /**
     * 资产大类名称，与大类ID同步，关联资产分类表
     */
    private String assetMajorName;
    /**
     * 资产小类ID，关联资产分类表
     */
    private String assetMinorId;
    /**
     * 资产小类名称，与小类ID同步，关联资产分类表
     */
    private String assetMinorName;
    /**
     * 资产总存量，周期内该分类资产总数量
     */
    private Integer totalAssetCount;
    /**
     * 资产完好率，完好资产数/总存量×100，0.00-100.00
     */
    private BigDecimal assetIntactRate;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;
    /**
     * 分类扩展字段1，预留
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留
     */
    private String extCommon2;

}