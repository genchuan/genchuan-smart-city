package cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.reporttemplate.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 报告模板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReportTemplateRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31043")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "模板UUID（主键，UUID）", example = "16023")
    @ExcelProperty("模板UUID（主键，UUID）")
    private String templateId;

    @Schema(description = "模板编号")
    @ExcelProperty("模板编号")
    private String code;

    @Schema(description = "模板名称", example = "芋艿")
    @ExcelProperty("模板名称")
    private String name;

    @Schema(description = "适用任务类型ID（关联sys_task_type.type_id）", example = "29698")
    @ExcelProperty("适用任务类型ID（关联sys_task_type.type_id）")
    private String taskTypeId;

    @Schema(description = "版本号")
    @ExcelProperty("版本号")
    private String version;

    @Schema(description = "模板状态ID（关联sys_template_status.status_id）", example = "23450")
    @ExcelProperty("模板状态ID（关联sys_template_status.status_id）")
    private String statusId;

    @Schema(description = "创建人（关联sys_user.user_id）")
    @ExcelProperty("创建人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务创建时间（创建时间）")
    @ExcelProperty("业务创建时间（创建时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "使用次数", example = "22116")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "最近生成时间")
    @ExcelProperty("最近生成时间")
    private LocalDateTime latestUseTime;

    @Schema(description = "字段映射规则数")
    @ExcelProperty("字段映射规则数")
    private Integer mapRuleNum;

    @Schema(description = "模板文件大小（字节）")
    @ExcelProperty("模板文件大小（字节）")
    private Long fileSize;

    @Schema(description = "模板文件格式（Word/PDF）")
    @ExcelProperty("模板文件格式（Word/PDF）")
    private String fileFormat;

    @Schema(description = "版本迭代记录")
    @ExcelProperty("版本迭代记录")
    private String versionLog;

    @Schema(description = "模板使用率（%）")
    @ExcelProperty("模板使用率（%）")
    private BigDecimal useRate;

    @Schema(description = "字段映射完整度（%）")
    @ExcelProperty("字段映射完整度（%）")
    private BigDecimal mapCompleteRate;

    @Schema(description = "业务模板更新时间（模板更新时间）")
    @ExcelProperty("业务模板更新时间（模板更新时间）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "使用部门分布")
    @ExcelProperty("使用部门分布")
    private String deptDist;

    @Schema(description = "停用操作人（关联sys_user.user_id）")
    @ExcelProperty("停用操作人（关联sys_user.user_id）")
    private String stopBy;

    @Schema(description = "停用时间")
    @ExcelProperty("停用时间")
    private LocalDateTime stopTime;

    @Schema(description = "停用原因", example = "不喜欢")
    @ExcelProperty("停用原因")
    private String stopReason;

    @Schema(description = "停用时长（小时）")
    @ExcelProperty("停用时长（小时）")
    private BigDecimal stopHour;

    @Schema(description = "模板文件状态（正常/损坏/缺失）", example = "1")
    @ExcelProperty("模板文件状态（正常/损坏/缺失）")
    private String fileStatus;

    @Schema(description = "字段映射规则有效性（有效/无效）", example = "21686")
    @ExcelProperty("字段映射规则有效性（有效/无效）")
    private String mapValid;

    @Schema(description = "最新版本迭代记录")
    @ExcelProperty("最新版本迭代记录")
    private String latestVersionLog;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}