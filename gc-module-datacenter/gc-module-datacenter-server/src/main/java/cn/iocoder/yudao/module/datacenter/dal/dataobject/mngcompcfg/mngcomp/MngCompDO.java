package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.mngcomp;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理部件统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_mng_comp")
@KeySequence("stat_mng_comp_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MngCompDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID，唯一编码，UUID生成
     */
    private String statMngCompId;
    /**
     * 统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期名称，如“2025年9月”
     */
    private String statCycleName;
    /**
     * 行政区划代码，符合GB/T 2260，关联行政区划表(sys_area)
     */
    private String areaCode;
    /**
     * 行政区划名称，与代码同步，关联行政区划表(sys_area)
     */
    private String areaName;
    /**
     * 部件大类ID，关联管理部件大类ID，关联管理部件大类表(biz_mng_comp_major)
     */
    private String compMajorId;
    /**
     * 部件大类名称，与大类ID同步，关联管理部件大类表(biz_mng_comp_major)
     */
    private String compMajorName;
    /**
     * 部件小类ID，关联管理部件小类ID（钻取时必填），关联管理部件小类表(biz_mng_comp_minor)
     */
    private String compMinorId;
    /**
     * 部件小类名称，与小类ID同步，关联管理部件小类表(biz_mng_comp_minor)
     */
    private String compMinorName;
    /**
     * 部件总数，该维度下部件总数，关联管理部件表(biz_mng_comp)
     */
    private Integer totalCompCount;
    /**
     * 完好部件数，状态为“完好”的数量，关联管理部件表(biz_mng_comp)
     */
    private Integer normalCompCount;
    /**
     * 破损部件数，状态为“破损”的数量，关联管理部件表(biz_mng_comp)
     */
    private Integer damagedCompCount;
    /**
     * 丢失部件数，状态为“丢失”的数量，关联管理部件表(biz_mng_comp)
     */
    private Integer lostCompCount;
    /**
     * 废弃部件数，状态为“废弃”的数量，关联管理部件表(biz_mng_comp)
     */
    private Integer discardedCompCount;
    /**
     * 新增部件数，统计周期内新增数量，关联管理部件表(biz_mng_comp)
     */
    private Integer newCompCount;
    /**
     * 更新部件数，统计周期内更新数量，关联管理部件表(biz_mng_comp)
     */
    private Integer updateCompCount;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}