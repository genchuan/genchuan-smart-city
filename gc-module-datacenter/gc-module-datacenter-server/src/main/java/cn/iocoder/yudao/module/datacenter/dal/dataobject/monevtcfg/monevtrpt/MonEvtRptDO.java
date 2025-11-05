package cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtrpt;

import lombok.*;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测事件统计报 DO
 *
 * @author 亘川智城
 */
@TableName("stat_mon_evt_rpt")
@KeySequence("stat_mon_evt_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonEvtRptDO extends BaseDO {

    /**
     * 自增主键ID
     */
    @TableId
    private Long id;
    /**
     * 唯一编码，采用UUID生成
     */
    private String statId;
    /**
     * 统计周期类型，格式:“YYYY”“YYYYQn”“YYYYMM”
     */
    private String statCycle;
    /**
     * 如“2025年09月”
     */
    private String statCycleName;
    /**
     * 符合GB/T 2260，统计区域的行政区划代码
     */
    private String regionCode;
    /**
     * 与行政区划代码关联，自动同步名称
     */
    private String regionName;
    /**
     * 关联监测事件分类配置表的大类ID
     */
    private String evtMajorId;
    /**
     * 与事件大类ID关联，自动同步名称
     */
    private String evtMajorName;
    /**
     * 关联监测事件分类配置表的小类ID（钻取时必填）
     */
    private String evtMinorId;
    /**
     * 与事件小类ID关联，自动同步名称
     */
    private String evtMinorName;
    /**
     * 事件处置部门统一社会信用代码
     */
    private String deptCode;
    /**
     * 与处置部门代码关联，自动同步名称
     */
    private String deptName;
    /**
     * 统计周期内该维度下事件上报总数
     */
    private Integer totalRptCount;
    /**
     * 状态为“待处置”的事件数量
     */
    private Integer pendCount;
    /**
     * 状态为“处置中”的事件数量
     */
    private Integer handlCount;
    /**
     * 状态为“已办结”的事件数量
     */
    private Integer completedCount;
    /**
     * 状态为“已驳回”的事件数量
     */
    private Integer rejectedCount;
    /**
     * 事件等级为“一级”的数量
     */
    private Integer level1Count;

}