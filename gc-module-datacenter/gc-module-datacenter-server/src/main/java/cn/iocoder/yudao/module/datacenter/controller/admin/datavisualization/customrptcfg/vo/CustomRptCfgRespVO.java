package cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 自定义报表配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomRptCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30874")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "配置ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22465")
    @ExcelProperty("配置ID，唯一编码，UUID生成")
    private String customRptCfgId;

    @Schema(description = "报表模板名称，如“XX街道月度监测事件报表”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("报表模板名称，如“XX街道月度监测事件报表”")
    private String rptTemplateName;

    @Schema(description = "报表类型：分域分析/决策效果/资源调度，标识报表数据来源类型，数据字典表（sys_dict）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("报表类型：分域分析/决策效果/资源调度，标识报表数据来源类型，数据字典表（sys_dict）")
    private String rptType;

    @Schema(description = "数据来源表，关联的数据库表名（如“stat_analysis_mon_evt”）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据来源表，关联的数据库表名（如“stat_analysis_mon_evt”）")
    private String dataSourceTable;

    @Schema(
            description = "统计维度配置，JSON格式存储维度配置（如：[{\"dimension\":\"stat_cycle\",\"name\":\"统计周期\",\"type\":\"date\",\"default\":\"month\"}]）",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @ExcelProperty("统计维度配置")
    private String statDimensionCfg;

    @Schema(description = "统计指标配置，JSON格式存储指标配置（如[{\"idx\":\"total_rpt_count\",\"name\":\"上报量\",\"unit\":\"件\",\"aggregation\":\"sum\"}]）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计指标配置，JSON格式存储指标配置（如[{\"idx\":\"total_rpt_count\",\"name\":\"上报量\",\"unit\":\"件\",\"aggregation\":\"sum\"}]）")
    private String statIdxCfg;

    @Schema(description = "展示样式配置，JSON格式存储样式配置（如{\"type\":\"table,bar\",\"tableFields\":[\"stat_cycle_name\",\"total_rpt_count\"],\"chartX\":\"stat_cycle_name\",\"chartY\":\"total_rpt_count\"}）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("展示样式配置，JSON格式存储样式配置（如{\"type\":\"table,bar\",\"tableFields\":[\"stat_cycle_name\",\"total_rpt_count\"],\"chartX\":\"stat_cycle_name\",\"chartY\":\"total_rpt_count\"}）")
    private String displayStyleCfg;

    @Schema(description = "筛选条件配置，JSON格式存储默认筛选条件（如[{\"field\":\"region_code\",\"value\":\"110101\",\"operator\":\"eq\"}]）")
    @ExcelProperty("筛选条件配置，JSON格式存储默认筛选条件（如[{\"field\":\"region_code\",\"value\":\"110101\",\"operator\":\"eq\"}]）")
    private String filterCfg;

    @Schema(description = "模板状态：启用/禁用，标识模板是否可用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("模板状态：启用/禁用，标识模板是否可用")
    private String templateStatus;

    @Schema(description = "创建人，模板创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，模板创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，模板更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，模板更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“报表权限”")
    @ExcelProperty("分类扩展字段1，预留，如“报表权限”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“报表权限”")
    @ExcelProperty("分类扩展字段2，预留，如“报表权限”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储报表导出格式（Excel/PDF）")
    @ExcelProperty("通用扩展字段1，预留，存储报表导出格式（Excel/PDF）")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储报表导出格式（Excel/PDF）")
    @ExcelProperty("通用扩展字段2，预留，存储报表导出格式（Excel/PDF）")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}