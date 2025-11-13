package cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.customrptcfg;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 自定义报表配置 DO
 *
 * @author 亘川智城
 */
@TableName("sys_custom_rpt_cfg")
@KeySequence("sys_custom_rpt_cfg_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomRptCfgDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 配置ID，唯一编码，UUID生成
     */
    private String customRptCfgId;
    /**
     * 报表模板名称，如“XX街道月度监测事件报表”
     */
    private String rptTemplateName;
    /**
     * 报表类型：分域分析/决策效果/资源调度，标识报表数据来源类型，数据字典表（sys_dict）
     */
    private String rptType;
    /**
     * 数据来源表，关联的数据库表名（如“stat_analysis_mon_evt”）
     */
    private String dataSourceTable;
    /**
     * 统计维度配置，JSON格式存储维度配置（如[{"dimension":"stat_cycle","name":"统计周期","type":"date","default":"month"}]）
     */
    private String statDimensionCfg;
    /**
     * 统计指标配置，JSON格式存储指标配置（如[{"idx":"total_rpt_count","name":"上报量","unit":"件","aggregation":"sum"}]）
     */
    private String statIdxCfg;
    /**
     * 展示样式配置，JSON格式存储样式配置（如{"type":"table,bar","tableFields":["stat_cycle_name","total_rpt_count"],"chartX":"stat_cycle_name","chartY":"total_rpt_count"}）
     */
    private String displayStyleCfg;
    /**
     * 筛选条件配置，JSON格式存储默认筛选条件（如[{"field":"region_code","value":"110101","operator":"eq"}]）
     */
    private String filterCfg;
    /**
     * 模板状态：启用/禁用，标识模板是否可用
     */
    private String templateStatus;
    /**
     * 创建人，模板创建人ID，用户信息表（sys_user）
     */
    private String createUser;
    /**
     * 更新人，模板更新人ID，用户信息表（sys_user）
     */
    private String updateUser;
    /**
     * 分类扩展字段1，预留，如“报表权限”
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“报表权限”
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储报表导出格式（Excel/PDF）
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储报表导出格式（Excel/PDF）
     */
    private String extCommon2;
    /**
     * 系统创建时间
     */
    private LocalDateTime createTimeSys;
    /**
     * 系统更新时间
     */
    private LocalDateTime updateTimeSys;

}