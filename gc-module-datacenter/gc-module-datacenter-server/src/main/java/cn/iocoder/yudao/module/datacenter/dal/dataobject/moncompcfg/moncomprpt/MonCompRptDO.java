package cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncomprpt;

import lombok.*;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 监测部件统计报 DO
 *
 * @author 亘川智城
 */
@TableName("stat_mon_comp_rpt")
@KeySequence("stat_mon_comp_rpt_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonCompRptDO extends BaseDO {

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
     * 统计周期类型，格式:YYYY/YYYYQn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期的中文描述，如“2025年Q3”“2025年09月”
     */
    private String statCycleName;
    /**
     * 符合GB/T 2260，统计区域的行政区划代码
     */
    private String regionCode;
    /**
     * 与行政区划代码关联，自动同步区域名称
     */
    private String regionName;
    /**
     * 关联监测部件分类配置表的大类ID
     */
    private String compMajorId;
    /**
     * 与部件大类ID关联，自动同步名称
     */
    private String compMajorName;
    /**
     * 关联监测部件分类配置表的小类ID(钻取统计时必填)
     */
    private String compMinorId;
    /**
     * 与部件小类ID关联，自动同步名称
     */
    private String compMinorName;
    /**
     * 该维度下监测部件总数量
     */
    private Integer totalCompCount;
    /**
     * 状态为“正常”的部件数量
     */
    private Integer normalCompCount;
    /**
     * 状态为“异常”的部件数量
     */
    private Integer abnCompCount;
    /**
     * 状态为“维护”的部件数量
     */
    private Integer mntCompCount;
    /**
     * 状态为“废弃”的部件数量
     */
    private Integer discardCompCount;
    /**
     * 统计周期内新增的部件数量（创建时间在周期内）
     */
    private Integer newCompCount;
    /**
     * 统计周期内更新的部件数量（更新时间在周期内）
     */
    private Integer updateCompCount;
    /**
     * 生成报表的用户ID
     */
    private String statUser;
    /**
     * 报表生成时间，格式：yyyy-MM-dd HH:mm:ss
     */
    private LocalDateTime statTime;
    /**
     * 报表说明，如“统计范围：XX市建成区监测部件”
     */
    private String rptRemark;
    /**
     * 预留字段，存储统计额外维度（如“统计范围”）
     */
    private String extCat1;
    /**
     * 预留字段，存储统计额外维度（如“统计范围”）
     */
    private String extCat2;
    /**
     * 预留通用字段，存储额外统计信息
     */
    private String extCommon1;
    /**
     * 预留通用字段，存储额外统计信息
     */
    private String extCommon2;

}