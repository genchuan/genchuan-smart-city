package cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.mnggridrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理网格统计 DO
 *
 * @author zhucongquan
 */
@TableName("stat_mng_grid_rpt")
@KeySequence("stat_mng_grid_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MngGridRptDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID
     */
    private String mgStatId;
    /**
     * 统计周期
     */
    private String statCycle;
    /**
     * 统计周期名称
     */
    private String statCycleName;
    /**
     * 行政区划代码
     */
    private String regionCode;
    /**
     * 行政区划名称
     */
    private String regionName;
    /**
     * 网格员ID
     */
    private String gridUserId;
    /**
     * 网格员姓名
     */
    private String gridUserName;
    /**
     * 所含单元数范围
     */
    private String unitCountRange;
    /**
     * 网格总数(个)
     */
    private Integer totalCount;
    /**
     * 总面积(m²)
     */
    private Integer totalArea;
    /**
     * 平均所含单元数(个)
     */
    private BigDecimal avgUnitCount;
    /**
     * 总所含单元网格数(个)
     */
    private Integer totalUnitCount;
    /**
     * 网格员覆盖数(个)
     */
    private Integer gridUserCoverCount;
    /**
     * 新增网格数(个)
     */
    private Integer newCount;
    /**
     * 统计人
     */
    private String statUserId;
    /**
     * 统计时间
     */
    private LocalDateTime statTime;
    /**
     * 报表备注
     */
    private String rptRemark;
    /**
     * 分类扩展字段1
     */
    private String extCat1;
    /**
     * 分类扩展字段2
     */
    private String extCat2;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;

}