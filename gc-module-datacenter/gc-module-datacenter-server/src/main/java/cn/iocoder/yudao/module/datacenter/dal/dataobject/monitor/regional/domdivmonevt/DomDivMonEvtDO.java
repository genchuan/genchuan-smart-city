package cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmonevt;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件分域 DO
 *
 * @author zcq
 */
@TableName("stat_dom_div_mon_evt")
@KeySequence("stat_dom_div_mon_evt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DomDivMonEvtDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 分域ID
     */
    private String domId;
    /**
     * 分域名称
     */
    private String domName;
    /**
     * 分域编码
     */
    private String domCode;
    /**
     * 监测事件大类ID
     */
    private String monEvtMajorId;
    /**
     * 监测事件大类名称
     */
    private String monEvtMajorName;
    /**
     * 监测事件小类ID
     */
    private String monEvtMinorId;
    /**
     * 监测事件小类名称
     */
    private String monEvtMinorName;
    /**
     * 关联点位数量(个)
     */
    private Integer relPtCount;
    /**
     * 关联事件数量(件)
     */
    private Integer relEvtCount;
    /**
     * 事件办结率(%)
     */
    private BigDecimal evtCompleteRate;
    /**
     * 管控部门代码
     */
    private String deptCode;
    /**
     * 管控部门名称
     */
    private String deptName;
    /**
     * 分域状态
     */
    private Boolean domStatus;
    /**
     * 创建时间(业务)
     */
    private LocalDateTime createTimeBiz;
    /**
     * 更新时间(业务)
     */
    private LocalDateTime updateTimeBiz;
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