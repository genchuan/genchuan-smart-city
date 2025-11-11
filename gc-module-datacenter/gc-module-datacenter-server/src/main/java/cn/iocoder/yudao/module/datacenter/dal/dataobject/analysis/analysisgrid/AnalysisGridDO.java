package cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisgrid;

import lombok.*;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 按网格分域分析研判统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_analysis_grid")
@KeySequence("stat_analysis_grid_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisGridDO extends BaseDO {

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
     * 统计周期，格式：年/季/月，如YYYY/YYYYOn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期名称，如“2025年09月”
     */
    private String statCycleName;
    /**
     * 网格ID，关联单元网格划分表
     */
    private String gridId;
    /**
     * 网格名称，与网格ID同步，关联单元网格划分表
     */
    private String gridName;
    /**
     * 网格类型，标识网格类型，如单元网格/管理网格
     */
    private String gridType;
    /**
     * 所在街道代码，关联行政区划表街道级代码
     */
    private String streetCode;
    /**
     * 所在街道名称，与街道代码同步，关联行政区划表
     */
    private String streetName;
    /**
     * 监测部件在线率，周期内网格内在线监测部件占比，0.00-100.00
     */
    private BigDecimal monCompOnlineRate;
    /**
     * 事件处置及时率，周期内网格内及时处置事件占比，0.00-100.00
     */
    private BigDecimal evtTimelyHandleRate;
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