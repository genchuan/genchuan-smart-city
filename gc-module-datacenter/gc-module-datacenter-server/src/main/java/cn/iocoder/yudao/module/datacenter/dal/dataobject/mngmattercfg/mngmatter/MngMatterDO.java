package cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.mngmatter;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 管理事项统计 DO
 *
 * @author 亘川智城
 */
@TableName("stat_mng_matter")
@KeySequence("stat_mng_matter_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MngMatterDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 统计ID，唯一编码，UUID生成
     */
    private String statMngMatterId;
    /**
     * 统计周期，如年/季/月，格式:YYYY/YYYYQn/YYYYMM
     */
    private String statCycle;
    /**
     * 统计周期名称，如“2025年Q3”
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
     * 事项大类ID，关联管理事项大类ID，关联管理事项大类表(biz_mng_matter_major)
     */
    private String matterMajorId;
    /**
     * 事项大类名称，与大类ID同步，关联管理事项大类表(biz_mng_matter_major)
     */
    private String matterMajorName;
    /**
     * 事项小类ID，关联管理事项小类ID(钻取时必填)，关联管理事项小类表(biz_mng_matter_minor)
     */
    private String matterMinorId;
    /**
     * 事项小类名称，与小类ID同步，关联管理事项小类表(biz_mng_matter_minor)
     */
    private String matterMinorName;
    /**
     * 处置部门代码，关联主管部门信用代码，关联部门信息表(sys_org)
     */
    private String deptCode;
    /**
     * 处置部门名称，与部门代码同步，关联部门信息表(sys_org)
     */
    private String deptName;
    /**
     * 上报总数，统计周期内上报总数，关联管理事项表(biz_mng_matter)
     */
    private Integer totalRptCount;
    /**
     * 待处置数，状态为“待处置”的数量，关联管理事项表（biz_mng_matter）
     */
    private Integer pendCount;
    /**
     * 处置中数，状态为“处置中”的数量，关联管理事项表（biz_mng_matter）
     */
    private Integer handlCount;
    /**
     * 已办结数，状态为“已办结”的数量，关联管理事项表（biz_mng_matter）
     */
    private Integer completedCount;
    /**
     * 已驳回数，状态为“已驳回”的数量，关联管理事项表（biz_mng_matter）
     */
    private Integer rejectedCount;
    /**
     * 办结率，（已办结数/（上报总数 - 已驳回数））×100，关联管理事项表（biz_mng_matter）
     */
    private BigDecimal completeRate;
    /**
     * 平均处置时长，已办结事项处置时长平均值，关联管理事项表（biz_mng_matter）
     */
    private BigDecimal avgHandleEndure;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}