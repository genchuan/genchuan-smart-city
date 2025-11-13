package cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.screentemplatemng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 大屏模板管理新增/修改 Request VO")
@Data
public class ScreenTemplateMngSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27628")
    private Long id;

    @Schema(description = "模板ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "11481")
    @NotEmpty(message = "模板ID，唯一编码，UUID生成不能为空")
    private String screenTemplateId;

    @Schema(description = "模板名称，如“城市监测事件总览大屏（2×2）”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "模板名称，如“城市监测事件总览大屏（2×2）”不能为空")
    private String templateName;

    @Schema(description = "大屏布局，网格布局类型，决定组件数量及位置，取值如2×2/3×2/3×3，组件样式字典表（sys_screen_component_style）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "大屏布局，网格布局类型，决定组件数量及位置，取值如2×2/3×2/3×3，组件样式字典表（sys_screen_component_style）不能为空")
    private String screenLayout;

    @Schema(description = "数据来源配置，JSON格式存储数据来源（如[{\"component_id\":\"comp1\",\"data_table\":\"stat_analysis_mon_evt\",\"idx\":\"total_rpt_count\"}]）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "数据来源配置不能为空")
    private String dataSourceCfg;

    @Schema(description = "组件配置，JSON格式存储组件配置（如[{\"component_id\":\"comp1\",\"type\":\"bar\",\"title\":\"事件上报量\",\"xField\":\"stat_cycle_name\",\"yField\":\"total_rpt_count\",\"color\":\"#1890FF\"}]），组件样式字典表（sys_screen_component_style）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "组件配置不能为空")
    private String componentCfg;

    @Schema(description = "刷新频率，大屏数据自动刷新间隔，单位：分钟，取值范围：1-60，默认5分钟", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "刷新频率，大屏数据自动刷新间隔，单位：分钟，取值范围：1-60，默认5分钟不能为空")
    private Integer refreshFreq;

    @Schema(description = "模板状态：启用/禁用，标识模板是否可用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "模板状态：启用/禁用，标识模板是否可用不能为空")
    private String templateStatus;

    @Schema(description = "创建人，模板创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，模板创建人ID，用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "更新人，模板更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "模板预览图，大屏模板预览图存储路径（PNG格式）")
    private String templatePreview;

    @Schema(description = "分类扩展字段1，预留，如“大屏用途”（指挥中心/展厅）")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“大屏用途”（指挥中心/展厅）")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储大屏分辨率（如“1920×1080”）")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储大屏分辨率（如“1920×1080”）")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}