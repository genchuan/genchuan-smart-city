package cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngmatter;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按管理事项分析研判统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_analysis_mng_matter")
@KeySequence("stat_analysis_mng_matter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisMngMatterDO extends BaseDO {

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
     * 统计周期名称，如“2025年09月”
     */
    private String statCycleName;
    /**
     * 事项大类ID，关联管理事项分类表
     */
    private String matterMajorId;
    /**
     * 事项大类名称，与大类ID同步，关联管理事项分类表
     */
    private String matterMajorName;
    /**
     * 事项小类ID，关联管理事项分类表
     */
    private String matterMinorId;
    /**
     * 事项小类名称，与小类ID同步，关联管理事项分类表
     */
    private String matterMinorName;
    /**
     * 处置部门代码，关联部门信息表
     */
    private String deptCode;
    /**
     * 处置部门名称，与部门代码同步，关联部门信息表
     */
    private String deptName;
    /**
     * 事项上报总量，周期内该分类事项上报总数
     */
    private Integer totalRptCount;
    /**
     * 事项办结率，办结事项数/(上报总量-驳回数)×100，0.00-100.00
     */
    private BigDecimal matterCompleteRate;
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