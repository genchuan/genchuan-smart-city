package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngmatterrpt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项分域监测报表 DO
 *
 * @author zcq
 */
@TableName("stat_dom_mng_matter_rpt")
@KeySequence("stat_dom_mng_matter_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomMngMatterRptDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 报表ID
     */
    private String rptId;
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
    private String adminCode;
    /**
     * 行政区划名称
     */
    private String adminName;
    /**
     * 管理事项大类ID
     */
    private String mngMatterMajorId;
    /**
     * 管理事项大类名称
     */
    private String mngMatterMajorName;
    /**
     * 管理事项小类ID
     */
    private String mngMatterMinorId;
    /**
     * 管理事项小类名称
     */
    private String mngMatterMinorName;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 处置部门代码
     */
    private String deptCode;
    /**
     * 处置部门名称
     */
    private String deptName;
    /**
     * 事件总数
     */
    private Integer totalEvtCount;
    /**
     * 待处置事件数
     */
    private Integer pendEvtCount;
    /**
     * 处置中事件数
     */
    private Integer handlEvtCount;
    /**
     * 已办结事件数
     */
    private Integer completedEvtCount;
    /**
     * 事件办结率
     */
    private BigDecimal evtCompleteRate;
    /**
     * 平均处置时长
     */
    private BigDecimal avgHandleEndure;
    /**
     * 报表生成时间
     */
    private LocalDateTime rptCreateTime;
    /**
     * 报表生成人
     */
    private Long rptCreateUser;
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