package cn.iocoder.yudao.module.datacenter.controller.admin.datavisualization.customrptcfg.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 自定义报表配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomRptCfgPageReqVO extends PageParam {

    @Schema(description = "配置ID，唯一编码，UUID生成", example = "22465")
    private String customRptCfgId;

    @Schema(description = "报表模板名称，如“XX街道月度监测事件报表”", example = "芋艿")
    private String rptTemplateName;

    @Schema(description = "报表类型：分域分析/决策效果/资源调度，标识报表数据来源类型，数据字典表（sys_dict）", example = "2")
    private String rptType;

    @Schema(description = "数据来源表，关联的数据库表名（如“stat_analysis_mon_evt”）")
    private String dataSourceTable;

    @Schema(description = "统计维度配置，JSON格式存储维度配置")
    private String statDimensionCfg;

    @Schema(description = "统计指标配置，JSON格式存储指标配置")
    private String statIdxCfg;

    @Schema(description = "展示样式配置，JSON格式存储样式配置")
    private String displayStyleCfg;

    @Schema(description = "筛选条件配置，JSON格式存储默认筛选条件")
    private String filterCfg;

    @Schema(description = "模板状态：启用/禁用，标识模板是否可用", example = "1")
    private String templateStatus;

    @Schema(description = "创建人，模板创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，模板更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“报表权限”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“报表权限”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储报表导出格式（Excel/PDF）")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储报表导出格式（Excel/PDF）")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}