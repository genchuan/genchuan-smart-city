package cn.iocoder.yudao.module.datacenter.dal.dataobject.datavisualization.screentemplatemng;

import lombok.*;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 大屏模板管理 DO
 *
 * @author 亘川智城
 */
@TableName("sys_screen_template_mng")
@KeySequence("sys_screen_template_mng_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenTemplateMngDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 模板ID，唯一编码，UUID生成
     */
    private String screenTemplateId;
    /**
     * 模板名称，如“城市监测事件总览大屏（2×2）”
     */
    private String templateName;
    /**
     * 大屏布局，网格布局类型，决定组件数量及位置，取值如2×2/3×2/3×3，组件样式字典表（sys_screen_component_style）
     */
    private String screenLayout;
    /**
     * 数据来源配置，JSON格式存储数据来源（如[{"component_id":"comp1","data_table":"stat_analysis_mon_evt","idx":"total_rpt_count"}]）
     */
    private String dataSourceCfg;
    /**
     * 组件配置，JSON格式存储组件配置（如[{"component_id":"comp1","type":"bar","title":"事件上报量","xField":"stat_cycle_name","yField":"total_rpt_count","color":"#1890FF"}]），组件样式字典表（sys_screen_component_style）
     */
    private String componentCfg;
    /**
     * 刷新频率，大屏数据自动刷新间隔，单位：分钟，取值范围：1-60，默认5分钟
     */
    private Integer refreshFreq;
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
     * 模板预览图，大屏模板预览图存储路径（PNG格式）
     */
    private String templatePreview;
    /**
     * 分类扩展字段1，预留，如“大屏用途”（指挥中心/展厅）
     */
    private String extCat1;
    /**
     * 分类扩展字段2，预留，如“大屏用途”（指挥中心/展厅）
     */
    private String extCat2;
    /**
     * 通用扩展字段1，预留，存储大屏分辨率（如“1920×1080”）
     */
    private String extCommon1;
    /**
     * 通用扩展字段2，预留，存储大屏分辨率（如“1920×1080”）
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